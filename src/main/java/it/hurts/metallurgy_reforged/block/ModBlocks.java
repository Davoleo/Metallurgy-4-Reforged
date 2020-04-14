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

import it.hurts.metallurgy_reforged.block.fluid.FluidBlockTar;
import it.hurts.metallurgy_reforged.block.gadget.BlockPhosphorusLamp;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.model.Drop;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Class used as reference for all the manually registered blocks
public class ModBlocks {

	//All the metal blocks
	public static Map<BlockTypes, List<Block>> metalBlocks = Collections.emptyMap();
	//All the mod ores
	public static List<BlockOre> oreBlocks = new ArrayList<>();
	//All the miscellaneous blocks
	public static List<Block> miscBlocks = new ArrayList<>();
	//Molten FluidBlocks
	public static List<BlockFluidClassic> fluidBlocks = new ArrayList<>();

	//Mod Blocks with a custom drop
	public static BlockOre oreSulfur = new BlockOre("sulfur_ore", 3F, 2, Constants.MID_TIER_BLAST_RESISTANCE)
			.setDrops(new Drop(new ItemStack(ModItems.sulfur, 4), 1F));
	public static BlockOre orePhosphorite = new BlockOre("phosphorite_ore", 3F, 2, Constants.MID_TIER_BLAST_RESISTANCE)
			.setDrops(new Drop(ModItems.phosphorus, 3, 1F));
	public static BlockOre oreTar = new BlockOre("tar_ore", 3F, 2, Constants.MID_TIER_BLAST_RESISTANCE)
			.setDrops(new Drop(ModItems.tar, 2, 1F), new Drop(ModItems.bitumen, 2, 0.5F));
	public static BlockOre orePotash = new BlockOre("potash_ore", 3F, 2, Constants.MID_TIER_BLAST_RESISTANCE)
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

	//Tar FluidBlock
	public static FluidBlockTar fluidBlockTar = new FluidBlockTar(ModFluids.TAR, Material.WATER);

	//Other Blocks Initialization
	static
	{
		for (BlockTypes type : BlockTypes.values())
		{
			metalBlocks.put(type, new ArrayList<>());
		}

		//Initialize Bitumen, Charcoal and Sulfur Blocks
		BlockUtils.initBlock(blockBitumen, "bitumen_block", MetallurgyTabs.tabBlock, 3F, Constants.MID_TIER_BLAST_RESISTANCE, "p", 1);
		BlockUtils.initBlock(blockCharcoal, "charcoal_block", MetallurgyTabs.tabBlock, 3F, Constants.MID_TIER_BLAST_RESISTANCE, "p", 1);
		BlockUtils.initBlock(blockSulfur, "sulfur_block", MetallurgyTabs.tabBlock, 3F, Constants.MID_TIER_BLAST_RESISTANCE, "p", 1);
		miscBlocks.add(blockBitumen);
		miscBlocks.add(blockCharcoal);
		miscBlocks.add(blockSulfur);

		//Initialize Tar FluidBlock
		BlockUtils.initFluidBlock(fluidBlockTar, "molten_tar");
	}

	// TODO: 30/03/2020 for each block: register block, register ItemBlock, register Model
	public static List<Block> joinBlockLists()
	{
		List<Block> list = new ArrayList<>();
		metalBlocks.forEach((blockTypes, blocks) -> list.addAll(blocks));
		list.addAll(oreBlocks);
		list.addAll(miscBlocks);
		list.addAll(fluidBlocks);
		return list;
	}

	public static ItemBlock createItemBlock(Block block)
	{
		return ((ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

}