/*==============================================================================
 = Class: VeinNextToBlockSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;
import java.util.Set;

/**
 * Decrease generation chance to 50% of the original chance
 * if none of the blocks in {@link this#adjacentBlockSet } is around the ore
 */
public class VeinNextToBlockSpawn extends BaseOreSpawn {

	private final Set<Block> adjacentBlockSet;

	public VeinNextToBlockSpawn(Block blockToReplace, String[] biomes, Set<Block> adjacentBlockSet)
	{
		super(blockToReplace, biomes);
		this.adjacentBlockSet = adjacentBlockSet;
	}

	@Override
	public int getRarity(Chunk chunk, int originalRarity)
	{
		return Math.round(originalRarity * 1.05F);
	}

	@Override
	public boolean canOreSpawn(Chunk chunk, BlockPos pos, IBlockState state, Random random)
	{
		if (!super.canOreSpawn(chunk, pos, state, random))
			return false;

		BlockPos.MutableBlockPos offMutPos = new BlockPos.MutableBlockPos(pos);

		for (EnumFacing facing : EnumFacing.values())
		{
			offMutPos.move(facing);
			if (offMutPos.getX() >> 4 != chunk.x || offMutPos.getZ() >> 4 != chunk.z)
				continue;

			if (adjacentBlockSet.contains(chunk.getBlockState(offMutPos).getBlock()))
				return true;
		}

		return random.nextBoolean();
	}

}
