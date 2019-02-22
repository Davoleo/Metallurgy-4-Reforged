package it.hurts.metallurgy_reforged.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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



}
