package it.hurts.metallurgy_reforged.fluid;

import it.hurts.metallurgy_reforged.block.fluid.FluidBlockBaseTar;
import net.minecraft.block.material.Material;

/***************************
 * 
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 22 feb 2019
 * Time   : 20:18:22
 * 
 ***************************/
public class FluidMoltenTar extends FluidMolten{

	public FluidMoltenTar(String name, int mapColor, int temperature, boolean isNew) {
		super(name, mapColor, temperature, isNew);
		this.setLuminosity(0);
	}

	@Override
	public void initFluidBlock() {
		super.block = new FluidBlockBaseTar(this, Material.WATER, fluid.getName());
	}

}
