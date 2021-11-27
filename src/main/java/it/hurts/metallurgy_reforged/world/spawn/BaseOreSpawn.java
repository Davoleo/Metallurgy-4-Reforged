/*==============================================================================
 = Class: BaseOreSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class BaseOreSpawn implements IOreSpawn {

	private final Block blockToReplace;
	private final ResourceLocation[] biomes;

	public BaseOreSpawn(Block blockToReplace, String[] biomes)
	{
		this.blockToReplace = blockToReplace;
		this.biomes = parseBiomes(biomes);
	}

	private ResourceLocation[] parseBiomes(String[] biomesResource)
	{
		ResourceLocation[] resLocations = new ResourceLocation[biomesResource.length];
		for (int i = 0; i < biomesResource.length; i++)
			resLocations[i] = new ResourceLocation(biomesResource[i]);

		return resLocations;
	}

	@Override
	public boolean canOreSpawn(Chunk chunk, BlockPos pos, IBlockState state, Random random)
	{
		return isInBiome(chunk, pos, this.biomes);
	}

	@Override
	public Predicate<IBlockState> getBlockPredicate()
	{
		return BlockMatcher.forBlock(blockToReplace);
	}

	/**
	 * returns if the biome in current position is contained in biomes
	 */
	private static boolean isInBiome(Chunk chunk, BlockPos pos, ResourceLocation[] biomes)
	{
		if (biomes.length == 0)
			return true;

		for (ResourceLocation biome : biomes)
			if (chunk.getBiome(pos, chunk.getWorld().getBiomeProvider()).getRegistryName().equals(biome))
				return true;

		return false;
	}

}
