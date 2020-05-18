/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OreDetectorRecipe
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

public class OreDetectorRecipe extends ShapelessOreRecipe {

	public OreDetectorRecipe(ResourceLocation group, NonNullList<Ingredient> input)
	{
		super(group, input, ItemStack.EMPTY);
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv)
	{
		ItemStack output = ItemStack.EMPTY;
		NonNullList<ItemStack> inputs = NonNullList.create();
		Metal metalModel = null;

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
			else if (stack.getItem() == ModItems.oreDetector)
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
			final NonNullList<Ingredient> ingredients = Utils.parseShapelessRecipe(context, json);
			return new OreDetectorRecipe(null, ingredients);
		}

	}

}
