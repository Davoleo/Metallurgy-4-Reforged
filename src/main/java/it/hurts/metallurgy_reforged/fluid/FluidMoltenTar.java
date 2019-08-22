/*
 * -------------------------------------------------------------------------------------------------------
 * Class: FluidMoltenTar
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.fluid;

public class FluidMoltenTar extends FluidMolten{

	public FluidMoltenTar(String name, int mapColor, int temperature, boolean isNew) {
		super(name, mapColor, temperature, isNew);
		this.setLuminosity(0);
	}

//	@Override
//	public void initFluidBlock() {
//		super.block = new FluidBlockBaseTar(this, Material.WATER, fluid.getName());
//	}

}
