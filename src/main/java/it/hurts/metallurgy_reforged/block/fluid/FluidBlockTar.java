/*==============================================================================
 = Class: FluidBlockTar
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block.fluid;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.Random;

public class FluidBlockTar extends BlockFluidClassic {

	public FluidBlockTar(Fluid fluid, Material material)
	{
		super(fluid, material);
	}

	/**
	 * Slows the player movements when swimming in Tar (80% less speed than in water)
	 */
	@Override
	public void onEntityCollision(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Entity entityIn)
	{
		super.onEntityCollision(worldIn, pos, state, entityIn);
		entityIn.motionX *= 0.2D;
		entityIn.motionY *= 0.2D;
		entityIn.motionZ *= 0.2D;
	}

	/**
	 * Handles the solidification of Liquid Tar into a solid form
	 */
	@Override
	public void randomTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random random)
	{
		super.randomTick(worldIn, pos, state, random);

		int count = 0;
		int index = 0;
		EnumFacing e;

		/*
		 * We save the block of position "pos" from the world
		 * LEVEL is a variable located in BlockFluidClassic, it handles the height of the fluid (from 0 to 15)
		 * 0 is the max height of the FluidBlock AKA the source block
		 */
		if (((FluidBlockTar) state.getBlock()).isSourceBlock(worldIn, pos))
		{

			do
			{
				e = EnumFacing.VALUES[index];

				IBlockState tarState = worldIn.getBlockState(pos.offset(e));

				if (tarState.getBlock().equals(this) && tarState.getValue(LEVEL) == 0)
					count++;

				index++;
			} while (index < 6 && count == 0);

			if (count == 0)
				worldIn.setBlockState(pos, ModBlocks.oreTar.getDefaultState());
		}

	}

}
