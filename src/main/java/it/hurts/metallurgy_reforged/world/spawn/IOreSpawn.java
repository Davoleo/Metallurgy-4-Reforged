/*==============================================================================
 = Class: IOreSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public interface IOreSpawn {

	/**
	 * This method is used to determine if the ore should spawn under special conditions
	 *
	 * @param world the world where the ore will spawn
	 * @param pos   the position where the ore will spawn
	 * @param state the blockstate of the previews block in pos
	 *
	 * @return if ore can spawn
	 */
	boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random);

	/**
	 * @return the predicate that establish if the ore can spawn by replacing the previews block
	 */
	@SuppressWarnings("Guava")
	Predicate<IBlockState> getBlockPredicate();

	/**
	 * @return the ore spawn rarity
	 */
	default int getRarity(World world, int chunkX, int chunkZ, int originalRarity)
	{
		return originalRarity;
	}

}
