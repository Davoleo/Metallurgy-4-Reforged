/*==============================================================================
 = Class: BaseOreSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BaseOreSpawn implements IOreSpawn {

	private final Block blockToReplace;
	private final Biome[] biomes;

	public BaseOreSpawn(Block blockToReplace, Biome[] biomes)
	{
		this.blockToReplace = blockToReplace;
		this.biomes = biomes;
	}


	@Override
	public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
	{
		return isInBiome(world, pos, this.biomes);
	}

	@Override
	public Predicate<IBlockState> getBlockPredicate()
	{
		return BlockMatcher.forBlock(blockToReplace);
	}

	/**
	 * returns if the biome in current position is contained in biomes
	 */
	private static boolean isInBiome(World world, BlockPos pos, Biome[] biomes)
	{
		if (biomes.length != 0)
		{
			for (Biome biome : biomes)
				if (world.getBiome(pos) == biome)
					return true;
		}
		return true;
	}

}
