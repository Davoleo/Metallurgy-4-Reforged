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
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.ItemTypes;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

//Used in recipes/_factories.json
@SuppressWarnings("unused")
public class OreDetectorRecipe extends ShapelessOreRecipe {

	public OreDetectorRecipe(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result)
	{
		super(group, input, result);
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv)
	{
		ItemStack stack = super.getCraftingResult(inv);
		NonNullList<ItemStack> inputs = NonNullList.create();

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack s = inv.getStackInSlot(i);
			if (s.getItem() instanceof ItemMetal && ((ItemMetal) s.getItem()).getType() == ItemTypes.INGOT)
			{
				inputs.add(s);
			}
		}

		writeMetalsToNBT(stack, inputs);
		return stack;
	}

	private void writeMetalsToNBT(ItemStack detector, NonNullList<ItemStack> inputs)
	{
		NBTTagCompound compound = detector.getTagCompound();

		if (compound != null)
			compound = new NBTTagCompound();

		NBTTagList ingots = new NBTTagList();

		for (ItemStack input : inputs)
		{
			NBTTagCompound ingot = new NBTTagCompound();
			ingot.setString("ingot", input.getItem().getRegistryName().getPath());
			ingots.appendTag(ingot);
		}

		compound.setTag("ingots", ingots);
		detector.setTagCompound(compound);
	}

	@Nonnull
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		ItemStack stack = ItemStack.EMPTY;

		int i = 0;
		while (i < inv.getSizeInventory())
		{
			++i;
			stack = inv.getStackInSlot(i);

			if (stack.getItem() instanceof ItemOreDetector)
				break;
		}

		return NonNullList.from(stack);
	}

	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			final NonNullList<Ingredient> ingredients = Utils.parseShapelessRecipe(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new OreDetectorRecipe(null, ingredients, result);
		}

	}

}
