/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationProjectE
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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


		for (Metal m : ModMetals.metalList)
		{
			ProjectEAPI.getEMCProxy().registerCustomEMC(m.getDust(), emcMap.get(m.toString()));

			if (!m.isAlloy())
			{
				//Debug Print
				//System.out.println(m.toString() + " --- " + emcMap.keySet().contains(m.toString()));

				ProjectEAPI.getEMCProxy().registerCustomEMC(m.getIngot(), emcMap.get(m.toString()));
				ProjectEAPI.getEMCProxy().registerCustomEMC(m.getBlocks(), emcMap.get(m.toString()) * 9);
				ProjectEAPI.getEMCProxy().registerCustomEMC(m.getNugget(), emcMap.get(m.toString()) / 9);
			}
			else
			{
				//Alloy MC calculations
				Table<ItemStack, ItemStack, ItemStack> recipes = AlloyerRecipes.getInstance().getRecipeTable();

				for (Table.Cell<ItemStack, ItemStack, ItemStack> entry : recipes.cellSet())
					if (entry.getValue().getItem().equals(m.getIngot()))
						ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), getAlloyIngredientsEMC(entry.getRowKey()) + getAlloyIngredientsEMC(entry.getColumnKey()));
			}
		}
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

}
