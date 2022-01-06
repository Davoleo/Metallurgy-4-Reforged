/*==============================================================================
 = Class: SmallIslandsSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import it.hurts.metallurgy_reforged.world.ModWorldGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class SmallIslandsSpawn extends BaseOreSpawn {

	public SmallIslandsSpawn(String[] biomes)
	{
		super(ModWorldGen.DEFAULT_END_BLOCK, biomes);
	}

	@Override
	public boolean canOreSpawn(Chunk chunk, BlockPos pos, IBlockState state, Random random)
	{
		if (Math.abs(pos.getX()) >= 700 || Math.abs(pos.getZ()) >= 700)
			return super.canOreSpawn(chunk, pos, state, random);
		return false;
	}

}
