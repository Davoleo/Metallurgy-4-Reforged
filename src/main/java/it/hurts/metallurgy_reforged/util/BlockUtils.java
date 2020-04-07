/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockUtils {

	public static void initBlock(Block block, String name, CreativeTabs tab, float hardness, float blastResistance, String toolClass, int harvestLevel)
	{
		block.setRegistryName(Metallurgy.MODID, name);
		block.setTranslationKey(Metallurgy.MODID + "." + name);
		if (tab != null)
			block.setCreativeTab(tab);

		block.setHardness(hardness);
		block.setResistance(blastResistance);
		block.setHarvestLevel(toolClass, harvestLevel);
	}

	public static void initBlock(Block block, String name, CreativeTabs tab)
	{
		block.setRegistryName(Metallurgy.MODID, name);
		block.setTranslationKey(Metallurgy.MODID + "." + name);
		if (tab != null)
			block.setCreativeTab(tab);
	}


	public static EnumFacing getFreeFacing(IBlockAccess world, BlockPos pos)
	{
		for (EnumFacing facing : EnumFacing.values())
			if (world.isAirBlock(pos.offset(facing)))
				return facing;
		return null;
	}

}
