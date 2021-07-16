/*==============================================================================
 = Class: WorldUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class WorldUtils {

	public static Set<BlockPos> getAllColliding(Vec3d start, Vec3d target)
	{
		Set<BlockPos> list = Sets.newLinkedHashSet();
		Vec3d directionVec = target.subtract(start);
		double length = directionVec.length();
		final double xBit = directionVec.x / (10 * length);
		final double yBit = directionVec.y / (10 * length);
		final double zBit = directionVec.z / (10 * length);

		for (int i = 1; i <= length * 10; i++)
		{
			double x = start.x + xBit * i;
			double y = start.y + yBit * i;
			double z = start.z + zBit * i;
			list.add(new BlockPos(x, y, z));
		}

		return list;
	}

	public static List<BlockPos> getBlocksWithinRadius(World world, BlockPos pos, float xradius, float yradius, float zradius, Block... blocks)
	{
		return getBlocksWithinRadius(world, pos, xradius, yradius, zradius, blockPos -> ArrayUtils.contains(blocks, world.getBlockState(blockPos)));
	}

	public static List<BlockPos> getBlocksWithinRadius(World world, BlockPos pos, float xradius, float yradius, float zradius, Block block)
	{
		return getBlocksWithinRadius(world, pos, xradius, yradius, zradius, (test) -> world.getBlockState(test).getBlock() == block);
	}

	public static List<BlockPos> getBlocksWithinRadius(World world, BlockPos pos, float xradius, float yradius, float zradius, Predicate<BlockPos> comparison)
	{
		List<BlockPos> blockList = new ArrayList<>();
		for (int x = (int) -xradius; x <= xradius; x++)
		{
			for (int z = (int) -zradius; z <= zradius; z++)
			{
				for (int y = (int) -yradius; y <= yradius; y++)
				{
					if (comparison.test(pos.add(x, y, z)))
						blockList.add(pos.add(x, y, z));
				}
			}
		}

		return blockList;
	}

	public static Optional<BlockPos> getRandomBlockWithinRadius(final int attempts, BlockPos center, int xradius, int yradius, int zradius, Predicate<BlockPos> validator)
	{
		for (int i = 0; i < attempts; i++)
		{
			int x = center.getX() - xradius + Utils.random.nextInt(xradius * 2 + 1);
			int y = center.getY() - yradius + Utils.random.nextInt(yradius * 2 + 1);
			int z = center.getZ() - zradius + Utils.random.nextInt(zradius * 2 + 1);

			BlockPos pos = new BlockPos(x, y, z);
			if (validator.test(pos))
				return Optional.of(pos);
		}

		return Optional.empty();
	}

}
