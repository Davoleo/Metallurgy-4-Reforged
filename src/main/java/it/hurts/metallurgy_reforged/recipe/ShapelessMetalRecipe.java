/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ShapelessMetalRecipe
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

import static it.hurts.metallurgy_reforged.util.ItemUtils.getMetalFromOreDictStack;

public class ShapelessMetalRecipe extends ShapelessOreRecipe implements IRecipeMetal {

	private String resultType;

	public ShapelessMetalRecipe(NonNullList<Ingredient> input, String resultType)
	{
		super(null, input, ItemStack.EMPTY);
		this.resultType = resultType;
		ModRecipes.shapelessMetalRecipes.add(this);
	}

	//TODO: comment this method
	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting crafting)
	{
		Metal metalModel = null;

		for (int i = 0; i < crafting.getSizeInventory(); i++)
		{
			ItemStack stack = crafting.getStackInSlot(i);

			Metal otherMetal = getMetalFromOreDictStack(stack);

			if (otherMetal != null)
			{
				if (metalModel == null)
					metalModel = otherMetal;
				else if (metalModel != otherMetal)
					return ItemStack.EMPTY;
			}
		}

		if (metalModel == null)
			return ItemStack.EMPTY;

		return getOutputFromMetal(metalModel);
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	public ItemStack getOutputFromMetal(Metal metal)
	{
		switch (resultType)
		{
			case "nugget":
				return new ItemStack(metal.getNugget(), 9);
			case "ingot":
				return new ItemStack(metal.getIngot(), 9);
			default:
				return ItemStack.EMPTY;
		}
	}

	@SuppressWarnings("unused")
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			NonNullList<Ingredient> input = Utils.parseShapelessRecipe(context, json);
			return new ShapelessMetalRecipe(input, JsonUtils.getString(json, "result"));
		}

	}

}
