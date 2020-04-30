/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init()
	{

		//Furnace Recipes
		ModMetals.metalMap.forEach((name, metal) -> {
			if (metal.getOre() != null)
			{
				GameRegistry.addSmelting(metal.getOre(), new ItemStack(metal.getIngot()), 1F);
			}
			GameRegistry.addSmelting(metal.getDust(), new ItemStack(metal.getIngot()), 0.6F);
		});

		//Dust2Ingot
		GameRegistry.addSmelting(ModItems.dustIron, new ItemStack(Items.IRON_INGOT), 0.6F);
		GameRegistry.addSmelting(ModItems.dustGold, new ItemStack(Items.GOLD_INGOT), 0.6F);

		//Ore2Material
		GameRegistry.addSmelting(ModBlocks.oreTar, new ItemStack(ModItems.tar), 0.5F);
		GameRegistry.addSmelting(ModBlocks.orePhosphorite, new ItemStack(ModItems.phosphorus), 0.5F);
		GameRegistry.addSmelting(ModBlocks.orePotash, new ItemStack(ModItems.potash), 0.5F);
		GameRegistry.addSmelting(ModBlocks.oreSulfur, new ItemStack(ModItems.sulfur), 0.5F);
	}

}
