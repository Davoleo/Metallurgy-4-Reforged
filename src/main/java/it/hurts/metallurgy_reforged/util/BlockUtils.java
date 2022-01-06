/*==============================================================================
 = Class: BlockUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockMetal;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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

	public static void initFluidBlock(BlockFluidClassic fluidBlock, String name)
	{
		fluidBlock.getFluid().setBlock(fluidBlock);
		fluidBlock.setRegistryName(Metallurgy.MODID, name);
		fluidBlock.setTranslationKey(Metallurgy.MODID + "." + name);
		fluidBlock.setCreativeTab(MetallurgyTabs.tabFluid);

		ModFluids.fluidBlocks.add(fluidBlock);
	}

	/**
	 * Gets the instance of a Metal from an Block
	 *
	 * @param block An Block instance
	 *
	 * @return The metal the parameter item is made of (null if it isn't made of any metal)
	 */
	public static Metal getMetalFromBlock(Block block)
	{
		if (block instanceof BlockMetal)
		{
			BlockMetal blockMetal = ((BlockMetal) block);

			for (Map.Entry<String, Metal> entry : ModMetals.metalMap.entrySet())
			{
				if (blockMetal.getMetalStats().getName().equals(entry.getKey()))
				{
					return entry.getValue();
				}
			}
		}

		return null;
	}

	public static List<BlockPos> getAdjacentPosList(IBlockAccess world, BlockPos pos, Predicate<BlockPos> condition)
	{
		List<BlockPos> positions = new ArrayList<>(6);

		for (EnumFacing facing : EnumFacing.values())
		{
			BlockPos offsetPos = pos.offset(facing);
			if (condition.test(offsetPos))
				positions.add(offsetPos);
		}

		return positions;
	}

}
