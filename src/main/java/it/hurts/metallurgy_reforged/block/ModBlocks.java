/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModBlocks
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.block.gadget.BlockPhosphorusLamp;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.model.Drop;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

//Class used as reference for all the manually registered blocks
public class ModBlocks {

	//All the miscellaneous blocks
	public static List<Block> miscBlocks = new ArrayList<>();

	//Mod Blocks with a custom drop
	public static BlockOre oreSulfur = new BlockOre("sulfur_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
			.setDrops(new Drop(new ItemStack(ModItems.sulfur, 4), 1F));
	public static BlockOre orePhosphorite = new BlockOre("phosphorite_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
			.setDrops(new Drop(ModItems.phosphorus, 3, 1F));
	public static BlockOre oreTar = new BlockOre("tar_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
			.setDrops(new Drop(ModItems.tar, 2, 1F), new Drop(ModItems.bitumen, 2, 0.5F));
	public static BlockOre orePotash = new BlockOre("potash_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
			.setDrops(new Drop(ModItems.potash, 3, 1F));

	//Bitumen, Charcoal and Sulfur Blocks
	public static Block blockBitumen = new Block(Material.ROCK);
	public static Block blockCharcoal = new Block(Material.ROCK);
	public static Block blockSulfur = new Block(Material.ROCK);

	public static BlockPhosphorusLamp blockPhosphorusLamp = new BlockPhosphorusLamp();

	//Road
	public static BlockOrientable blockRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "road_block", MetallurgyTabs.tabSpecial).setHardness(3F));
	public static BlockOrientable blockStripedRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "striped_road_block", MetallurgyTabs.tabSpecial).setHardness(3F));

	//Tile Entities
	public static BlockCrusher crusher = new BlockCrusher("crusher");
	public static BlockAlloyer alloyer = new BlockAlloyer("alloyer");
	//public static BlockLightningRod lightningRod = new BlockLightningRod("lightning_rod");
	public static BlockChamber chamber = new BlockChamber("sublimation_chamber");

	//Other Blocks Initialization
	static
	{
		//Initialize Bitumen, Charcoal and Sulfur Blocks
		BlockUtils.initBlock(blockBitumen, "bitumen_block", MetallurgyTabs.tabBlock, 3F, Constants.BlastResistance.MID_TIER, "p", 1);
		BlockUtils.initBlock(blockCharcoal, "charcoal_block", MetallurgyTabs.tabBlock, 3F, Constants.BlastResistance.MID_TIER, "p", 1);
		BlockUtils.initBlock(blockSulfur, "sulfur_block", MetallurgyTabs.tabBlock, 3F, Constants.BlastResistance.MID_TIER, "p", 1);
		miscBlocks.add(blockBitumen);
		miscBlocks.add(blockCharcoal);
		miscBlocks.add(blockSulfur);
		miscBlocks.add(oreTar);
		miscBlocks.add(oreSulfur);
		miscBlocks.add(orePotash);
		miscBlocks.add(orePhosphorite);

	}

	public static ItemBlock createItemBlock(Block block)
	{
		return ((ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

}