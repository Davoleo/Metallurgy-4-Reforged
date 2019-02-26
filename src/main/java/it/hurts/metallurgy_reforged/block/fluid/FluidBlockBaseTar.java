package it.hurts.metallurgy_reforged.block.fluid;

import java.util.Random;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;

/***************************
 * 
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 22 feb 2019
 * Time   : 20:19:18
 * 
 ***************************/
public class FluidBlockBaseTar extends FluidBlockBase{

	public FluidBlockBaseTar(Fluid fluid, Material material, String name) {
		super(fluid, material, name);
	}
	
    //Slows the player movements when swimming in Tar (80% less speed than in water)
	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollision(worldIn, pos, state, entityIn);
		entityIn.motionX *= 0.2D;
		entityIn.motionY *= 0.2D;
		entityIn.motionZ *= 0.2D;
	}

	//Handles the solidification of Liquid Tar into a solid form
	@Override
	public void randomTick(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random random) {
		super.randomTick(worldIn, pos, state, random);

		int count = 0;
		int index = 0;
		EnumFacing e;

		/*
		 * We save the block of position "pos" from the world
		 * LEVEL is a variable located in BlockFluidClassic, it handles the height of the fluid (from 0 to 15)
		 * 0 is the max height of the FluidBlock AKA the source block
		 */
		if(((FluidBlockBaseTar)state.getBlock()).isSourceBlock(worldIn, pos)) {

			do{
				e = EnumFacing.VALUES[index];
	
				IBlockState tarState = worldIn.getBlockState(pos.offset(e));
				
				if(tarState.getBlock().equals(this) && tarState.getValue(LEVEL) == 0) 
					count++;

				index++;
			}while(index < 6 && count == 0);
		
			if(count == 0)
				worldIn.setBlockState(pos, ModBlocks.oreTar.getDefaultState());
		}

	}
}
