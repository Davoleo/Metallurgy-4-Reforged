/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationProjectE
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;

public class IntegrationProjectE {

	public static final Map<String, Long> emcMap = new HashMap<>();

	static
	{
		emcMap.put("adamantine", 8192L);
		emcMap.put("amordrine", 3072L);
		emcMap.put("angmallen", 1152L);
		emcMap.put("alduorite", 1024L);
		emcMap.put("astral_silver", 2048L);
		emcMap.put("atlarus", 8192L);
		emcMap.put("black_steel", 512L);
		emcMap.put("brass", 160L);
		emcMap.put("bronze", 160L);
		emcMap.put("carmot", 2048L);
		emcMap.put("celenegil", 4096L);
		emcMap.put("ceruclase", 1024L);
		emcMap.put("copper", 128L);
		emcMap.put("damascus_steel", 192L);
		emcMap.put("deep_iron", 512L);
		emcMap.put("desichalkos", 6144L);
		emcMap.put("electrum", 1280L);
		emcMap.put("etherium", 8192L);
		emcMap.put("eximite", 8192L);
		emcMap.put("haderoth", 3413L);
		emcMap.put("hepatizon", 508L);
		emcMap.put("ignatius", 256L);
		emcMap.put("infuscolium", 512L);
		emcMap.put("inolashite", 1024L);
		emcMap.put("kalendrite", 2048L);
		emcMap.put("krik", 512L);
		emcMap.put("lemurite", 1024L);
		emcMap.put("lutetium", 512L);
		emcMap.put("manganese", 2048L);
		emcMap.put("meutoite", 4096L);
		emcMap.put("midasium", 1024L);
		emcMap.put("mithril", 2048L);
		emcMap.put("orichalcum", 4096L);
		emcMap.put("osmium", 512L);
		emcMap.put("oureclase", 1024L);
		emcMap.put("platinum", 4096L);
		emcMap.put("prometheum", 256L);
		emcMap.put("quicksilver", 4096L);
		emcMap.put("rubracium", 1024L);
		emcMap.put("sanguinite", 8192L);
		emcMap.put("shadow_iron", 256L);
		emcMap.put("shadow_steel", 448L);
		emcMap.put("silver", 512L);
		emcMap.put("steel", 1024L);
		emcMap.put("tartarite", 16384L);
		emcMap.put("tin", 256L);
		emcMap.put("vulcanite", 4096L);
		emcMap.put("vyroxeres", 2048L);
		emcMap.put("zinc", 512L);
	}

	public static void init()
	{
		//Other Items
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.dustIron, 256L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.dustGold, 2048L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.potash, 128L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.dustPotash, 256L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.phosphorus, 128L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.bitumen, 256L);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ModItems.tar, 128L);

		ModMetals.metalMap.forEach((s, metal) -> {
			long baseValue = emcMap.get(metal.toString());

			//Nuggets and Dusts
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getDust(), baseValue);
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getNugget(), baseValue / 9);

			//Blocks
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.BLOCK), baseValue * 9);
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.ENGRAVED_BLOCK), baseValue);
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.LARGE_BRICKS), baseValue);
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.BRICKS), baseValue);
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.CRYSTAL), baseValue + (baseValue / 9 / 4 * 3));
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.HAZARD_BLOCK), baseValue + (baseValue / 9 / 4 * 5));
			ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getBlock(BlockTypes.GLASS), baseValue + (getExistingEMCValue(Blocks.GLASS) / 4));

			//Tools
			if (metal.hasToolSet())
			{
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getTool(EnumTools.AXE), (baseValue * 3) + (getExistingEMCValue(Items.STICK) * 2));
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getTool(EnumTools.HOE), (baseValue * 2) + (getExistingEMCValue(Items.STICK) * 2));
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getTool(EnumTools.PICKAXE), (baseValue * 3) + (getExistingEMCValue(Items.STICK) * 2));
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getTool(EnumTools.SHOVEL), baseValue + (getExistingEMCValue(Items.STICK) * 2));
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getTool(EnumTools.SWORD), (baseValue * 2) + (getExistingEMCValue(Items.STICK) * 2));
			}

			//Armor
			if (metal.hasArmorSet())
			{
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getArmorPiece(EntityEquipmentSlot.HEAD), baseValue * 5);
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getArmorPiece(EntityEquipmentSlot.CHEST), baseValue * 8);
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getArmorPiece(EntityEquipmentSlot.LEGS), baseValue * 7);
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getArmorPiece(EntityEquipmentSlot.FEET), baseValue * 4);
			}

			if (!metal.isAlloy())
			{
				//Debug Print
				//System.out.println(metal.toString() + " --- " + emcMap.keySet().contains(metal.toString()));
				ProjectEAPI.getEMCProxy().registerCustomEMC(metal.getIngot(), emcMap.get(metal.toString()));
			}
			else
			{
				//Alloy MC calculations
				Table<ItemStack, ItemStack, ItemStack> recipes = AlloyerRecipes.getInstance().getRecipeTable();

				for (Table.Cell<ItemStack, ItemStack, ItemStack> entry : recipes.cellSet())
					if (entry.getValue().getItem().equals(metal.getIngot()))
						ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(metal.getIngot()), getAlloyIngredientsEMC(entry.getRowKey()) + getAlloyIngredientsEMC(entry.getColumnKey()));
			}
		});
	}

	private static long getAlloyIngredientsEMC(ItemStack ingot)
	{
		if (ingot.getItem().equals(Items.IRON_INGOT))
			return 256;
		else if (ingot.getItem().equals(Items.GOLD_INGOT))
			return 2048;

		Metal metal = ItemUtils.getMetalFromItem(((ItemMetal) ingot.getItem()));

		if (metal != null && metal.hasToolSet())
			return getEMCbyHarvestLevel(metal.getToolMaterial().getHarvestLevel());
		return 0;
	}

	private static long getEMCbyHarvestLevel(int harvestLevel)
	{
		if (harvestLevel == 0)
			harvestLevel = 1;
		return harvestLevel * harvestLevel * 150;
	}

	private static long getExistingEMCValue(IForgeRegistryEntry<?> entry)
	{
		if (entry instanceof Item)
		{
			return ProjectEAPI.getEMCProxy().getValue(((Item) entry));
		}

		if (entry instanceof Block)
		{
			return ProjectEAPI.getEMCProxy().getValue(((Block) entry));
		}

		return 0L;
	}

}
