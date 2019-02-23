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
	
//	80% less then l'acqua
	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollision(worldIn, pos, state, entityIn);
		entityIn.motionX *= 0.2D;
		entityIn.motionY *= 0.2D;
		entityIn.motionZ *= 0.2D;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		super.randomTick(worldIn, pos, state, random);
		int count = 0;
		int index = 0;
		EnumFacing e;
		
		if(((FluidBlockBaseTar)state.getBlock()).isSourceBlock(worldIn, pos)) {
		
			do{
//			Prendiamo dal mondo il blocco di posizione pos
//			Level è una variabile presente nel BlockFluidClassic che va da 0 a 15 e controlla ( incrementandolo ) 
//			quanto è basso il liquido ( 0 = sourceBlock )
				e = EnumFacing.VALUES[index];
	
				IBlockState tarState = worldIn.getBlockState(pos.offset(e));
				
				if(tarState.getBlock().equals(this) && tarState.getValue(LEVEL) == 0) 
					count++;

				index++;
			}while(index < 6 && count == 0);
		
			if(count == 0)
				worldIn.setBlockState(pos, ModBlocks.oreBitumen.getDefaultState());
		}

	}
}
