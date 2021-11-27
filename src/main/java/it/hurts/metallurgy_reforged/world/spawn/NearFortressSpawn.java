/*==============================================================================
 = Class: NearFortressSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import it.hurts.metallurgy_reforged.world.ModWorldGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class NearFortressSpawn extends NetherOreSpawn {

	public NearFortressSpawn()
	{
		super(null);
	}

	@Override
	public boolean canOreSpawn(Chunk chunk, BlockPos pos, IBlockState state, Random random)
	{
		return true;
	}

	@Override
	public int getRarity(Chunk chunk, int originalRarity)
	{
		BlockPos pos = new BlockPos(chunk.x * 16, chunk.getWorld().getSeaLevel(), chunk.z * 16);

		BlockPos blockpos = chunk.getWorld().findNearestStructure("Fortress", pos, false);

		if (blockpos != null && blockpos.getDistance(pos.getX(), pos.getY(), pos.getZ()) < 90)
		{
			return ModWorldGen.COMMON;
		}

		return originalRarity;
	}

}
