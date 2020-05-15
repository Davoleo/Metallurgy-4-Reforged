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
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.inventory.EntityEquipmentSlot;
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
		super(null, ItemStack.EMPTY, primer);
		this.resultType = resultType;
		ModRecipes.shapedMetalRecipes.add(this);
	}

	// TODO: 15/05/2020 comment this method
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
			//Tools
			case "axe":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.AXE));
			case "hoe":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.HOE));
			case "pickaxe":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.PICKAXE));
			case "shovel":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.SHOVEL));
			case "sword":
				if (metal.hasToolSet())
					return new ItemStack(metal.getTool(EnumTools.SWORD));
				//Armor
			case "helmet":
				if (metal.hasArmorSet())
					return new ItemStack(metal.getArmorPiece(EntityEquipmentSlot.HEAD));
			case "chestplate":
				if (metal.hasArmorSet())
					return new ItemStack(metal.getArmorPiece(EntityEquipmentSlot.CHEST));
			case "leggings":
				if (metal.hasArmorSet())
					return new ItemStack(metal.getArmorPiece(EntityEquipmentSlot.LEGS));
			case "boots":
				if (metal.hasArmorSet())
					return new ItemStack(metal.getArmorPiece(EntityEquipmentSlot.FEET));
				//Blocks
			case "block":
				return new ItemStack(metal.getBlock(BlockTypes.BLOCK));
			case "bricks":
				return new ItemStack(metal.getBlock(BlockTypes.BRICKS), 6);
			case "large_bricks":
				return new ItemStack(metal.getBlock(BlockTypes.LARGE_BRICKS), 4);
			case "crystals":
				return new ItemStack(metal.getBlock(BlockTypes.CRYSTAL), 4);
			case "engraved_block":
				return new ItemStack(metal.getBlock(BlockTypes.ENGRAVED_BLOCK), 8);
			case "hazard_block":
				return new ItemStack(metal.getBlock(BlockTypes.HAZARD_BLOCK), 4);
			case "reinforced_glass":
				return new ItemStack(metal.getBlock(BlockTypes.GLASS), 5);
			//Ingot
			case "ingot":
				return new ItemStack(metal.getIngot());
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
				String oreMetalName = ore.substring(ore.length() - (metalName.length()));

				if (oreMetalName.equals(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, metalName)))
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
