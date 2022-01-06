/*==============================================================================
 = Class: OreDetectorRecipe
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class OreDetectorRecipe extends ShapelessOreRecipe {

	private OreDetectorRecipe(NonNullList<Ingredient> input)
	{
		super(null, input, ItemStack.EMPTY);
	}

	private boolean isCleaningRecipe(InventoryCrafting inv)
	{
		//Clean Detector recipe
		Item anyItemReally = null;
		int stackCount = 0;
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stackInSlot = inv.getStackInSlot(i);
			if (!stackInSlot.isEmpty())
			{
				anyItemReally = inv.getStackInSlot(i).getItem();
				stackCount++;
			}
		}

		return stackCount == 1 && anyItemReally == ModItems.ORE_DETECTOR;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv)
	{
		ItemStack output = ItemStack.EMPTY;
		NonNullList<ItemStack> inputs = NonNullList.create();
		Metal metalModel = null;

		if (isCleaningRecipe(inv))
			return new ItemStack(ModItems.ORE_DETECTOR);

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			Metal otherMetal = ItemUtils.getMetalFromOreDictStack(stack);
			if (otherMetal != null)
			{
				if (otherMetal == metalModel || otherMetal.isAlloy())
					return ItemStack.EMPTY;

				inputs.add(stack);
				metalModel = otherMetal;
			}
			else if (stack.getItem() == ModItems.ORE_DETECTOR)
			{
				//set the output of the recipe depending if the detector already has some metals
				if (!ItemOreDetector.getDetectorMetals(stack).isEmpty())
					return ItemStack.EMPTY;
				else
					output = stack.copy();
			}
		}

		if (metalModel == null)
		{
			return output;
		}

		ItemOreDetector.addIngotsToDetector(output, inputs);
		return output;
	}

	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
	{
		if (isCleaningRecipe(inv))
			return true;

		//All other detector recipes
		return super.matches(inv, world);
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	//Used in recipes/_factories.json
	@SuppressWarnings("unused")
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			final NonNullList<Ingredient> ingredients = NonNullList.create();

			JsonArray jsonIngredients = JsonUtils.getJsonArray(json, "ingredients");

			for (final JsonElement element : JsonUtils.getJsonArray(json, "ingredients"))
				ingredients.add(CraftingHelper.getIngredient(element, context));

			if (ingredients.isEmpty())
				throw new JsonParseException("No ingredients for shapeless recipe");

			return new OreDetectorRecipe(ingredients);
		}

	}

}
