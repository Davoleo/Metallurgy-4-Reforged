/*==============================================================================
 = Class: HighChanceYLevel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Manipulates the chance of something
 *
 * @implNote This is not an ideal implementation as it makes the ore overall rarer as it does not redistribute the
 * removed ores but just removes them from the world completely
 */
public class HighChanceYLevelSpawn extends BaseOreSpawn {

	private final int yBound;
	private final boolean above;
	private final float chance;

	public HighChanceYLevelSpawn(Block blockToReplace, ResourceLocation[] biomes, int yBound, boolean above, float chance)
	{
		super(blockToReplace, biomes);
		this.yBound = yBound;
		this.above = above;
		this.chance = chance;
	}

	@Override
	public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
	{
		//Checks Biome
		if (super.canOreSpawn(world, pos, state, random))
			//the ore was generated above or below Y level bound accordingly to the field,
			//or it's got a 50% chance to spawn
			return (pos.getY() >= yBound == above) || random.nextFloat() < chance;
		else
			return false;
	}

}
