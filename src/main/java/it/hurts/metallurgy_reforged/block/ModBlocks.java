/*==============================================================================
 = Class: ModBlocks
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.block.gadget.BlockIceShield;
import it.hurts.metallurgy_reforged.block.gadget.BlockPhosphorusLamp;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ItemBlockOre;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.model.Drop;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Class used as reference for all the manually registered blocks
public class ModBlocks {

	//All the miscellaneous blocks
	public static List<Block> miscBlocks = new ArrayList<>();

	//Mod Blocks with a custom drop
    public static BlockOre oreSulfur = new BlockOre("sulfur_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
            .setDrops(new Drop(new ItemStack(ModItems.SULFUR, 4), 1F));
    public static BlockOre orePhosphorite = new BlockOre("phosphorite_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
            .setDrops(new Drop(ModItems.PHOSPHORUS, 3, 1F));
    public static BlockOre oreTar = new BlockOre("tar_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
            .setDrops(new Drop(ModItems.TAR, 2, 1F), new Drop(ModItems.BITUMEN, 2, 0.5F));
    public static BlockOre orePotash = new BlockOre("potash_ore", 3F, 2, Constants.BlastResistance.MID_TIER)
            .setDrops(new Drop(ModItems.POTASH, 3, 1F));

	//Bitumen, Charcoal and Sulfur Blocks
	public static Block blockBitumen = new Block(Material.ROCK);
	public static Block blockCharcoal = new Block(Material.ROCK);
	public static Block blockSulfur = new Block(Material.ROCK);

	public static Block iceShield = new BlockIceShield();

	public static BlockPhosphorusLamp blockPhosphorusLamp = new BlockPhosphorusLamp();

	//Road
	public static BlockOrientable blockRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "road_block", MetallurgyTabs.tabSpecial).setHardness(3F));
	public static BlockOrientable blockStripedRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "striped_road_block", MetallurgyTabs.tabSpecial).setHardness(3F));

	//Tile Entities
	public static BlockCrusher crusher = new BlockCrusher("crusher");
	public static BlockAlloyer alloyer = new BlockAlloyer("alloyer");
	//public static BlockLightningRod lightningRod = new BlockLightningRod("lightning_rod");
	public static BlockChamber chamber = new BlockChamber("sublimation_chamber");

	//Metal Scaffolding - Machine Frame
	public static BlockMetal structureBlock = new BlockMetal("bimetal_structure", BlockTypes.BLOCK) {
		@Override
		public boolean isFullCube(@Nonnull IBlockState state)
		{
			return false;
		}

		@Override
		public boolean isOpaqueCube(@Nonnull IBlockState state)
		{
			return false;
		}

		@Nonnull
		@Override
		public BlockRenderLayer getRenderLayer()
		{
			return BlockRenderLayer.CUTOUT;
		}
	};

	//Vanilla Decorative Blocks Init
	//Iron
	public static BlockMetal engravedIronBlock = new BlockMetal(Constants.METAL_IRON, BlockTypes.ENGRAVED_BLOCK);
	public static BlockMetal largeIronBricks = new BlockMetal(Constants.METAL_IRON, BlockTypes.LARGE_BRICKS);
	public static BlockMetal ironBricks = new BlockMetal(Constants.METAL_IRON, BlockTypes.BRICKS);
	public static BlockMetal ironCrystal = new BlockMetal(Constants.METAL_IRON, BlockTypes.CRYSTAL);
	public static BlockMetal ironHazardBlock = new BlockMetal(Constants.METAL_IRON, BlockTypes.HAZARD_BLOCK);
	public static BlockMetal ironReinforcedGlass = new BlockMetal(Constants.METAL_IRON, BlockTypes.GLASS);
	//Gold
	public static BlockMetal engravedGoldBlock = new BlockMetal(Constants.METAL_GOLD, BlockTypes.ENGRAVED_BLOCK);
	public static BlockMetal largeGoldBricks = new BlockMetal(Constants.METAL_GOLD, BlockTypes.LARGE_BRICKS);
	public static BlockMetal goldBricks = new BlockMetal(Constants.METAL_GOLD, BlockTypes.BRICKS);
	public static BlockMetal goldCrystal = new BlockMetal(Constants.METAL_GOLD, BlockTypes.CRYSTAL);
	public static BlockMetal goldHazardBlock = new BlockMetal(Constants.METAL_GOLD, BlockTypes.HAZARD_BLOCK);
	public static BlockMetal goldReinforcedGlass = new BlockMetal(Constants.METAL_GOLD, BlockTypes.GLASS);

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

		if (RegistrationConfig.categoryBlocks.enableEngravedMetalBlocks)
			Collections.addAll(miscBlocks, engravedGoldBlock, engravedIronBlock);
		if (RegistrationConfig.categoryBlocks.enableLargeBricksMetalBlocks)
			Collections.addAll(miscBlocks, largeGoldBricks, largeIronBricks);
		if (RegistrationConfig.categoryBlocks.enableBricksMetalBlocks)
			Collections.addAll(miscBlocks, goldBricks, ironBricks);
		if (RegistrationConfig.categoryBlocks.enableCrystalMetalBlocks)
			Collections.addAll(miscBlocks, goldCrystal, ironCrystal);
		if (RegistrationConfig.categoryBlocks.enableHazardMetalBlocks)
			Collections.addAll(miscBlocks, goldHazardBlock, ironHazardBlock);
		if (RegistrationConfig.categoryBlocks.enableReinforcedGlassBlocks)
			Collections.addAll(miscBlocks, goldReinforcedGlass, ironReinforcedGlass);

		miscBlocks.add(structureBlock);

		miscBlocks.add(iceShield);
	}

	public static ItemBlock createItemBlock(Block block)
	{
		if (block instanceof BlockOre)
			return (ItemBlock) new ItemBlockOre((BlockOre) block).setRegistryName(block.getRegistryName());

		return (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

}