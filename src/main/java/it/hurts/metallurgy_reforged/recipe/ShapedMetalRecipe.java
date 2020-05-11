/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ShapedMetalRecipe
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import com.google.common.base.CaseFormat;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Map;

public class ShapedMetalRecipe extends ShapedOreRecipe implements IRecipeMetal {

	private String resultType;

	public ShapedMetalRecipe(CraftingHelper.ShapedPrimer primer, String resultType)
	{
		super(null, new ItemStack(Blocks.COMMAND_BLOCK), primer);
		this.resultType = resultType;
		ModRecipes.shapedMetalRecipes.add(this);
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting crafting)
	{
		// TODO: 10/05/2020 Make this code drier
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

	public ItemStack getOutputFromMetal(Metal metal)
	{
		//axe, hoe, pickaxe, shovel, sword, helmet, chestplate, leggings, boots, block, ingot
		switch (resultType)
		{
			case "pickaxe":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.PICKAXE));
			default:
				return ItemStack.EMPTY;
		}
	}

	public static Metal getMetalFromOreDictStack(ItemStack stack)
	{

		if (stack.isEmpty())
			return null;

		int[] ids = OreDictionary.getOreIDs(stack);

		for (int id : ids)
		{
			String ore = OreDictionary.getOreName(id);

			for (Map.Entry<String, Metal> entry : ModMetals.metalMap.entrySet())
			{
				String metalName = entry.getKey();
				if (ore.endsWith(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metalName)))
				{
					return entry.getValue();
				}
			}
		}

		return null;
	}

	@SuppressWarnings("unused")
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			CraftingHelper.ShapedPrimer primer = Utils.parseShapedRecipe(context, json);
			return new ShapedMetalRecipe(primer, JsonUtils.getString(json, "result"));
		}

	}

}
