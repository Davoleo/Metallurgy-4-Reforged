/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModFluids
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.fluid;

import it.hurts.metallurgy_reforged.block.fluid.FluidBlockBaseTar;
import net.minecraft.block.material.Material;

public class ModFluids {

	public static final FluidMolten TAR = ((FluidMolten) new FluidMolten("molten_tar", 0xFF111419, 1000) {
		@Override
		public void initFluidBlock()
		{
			block = new FluidBlockBaseTar(this, Material.WATER, fluid.getName());
		}
	}.setLuminosity(0));

	public static final FluidMolten THERMITE = new FluidMolten("molten_thermite", 0xFFC44205, 3200);

	// TODO: 30/03/2020 For each block: register the Fluid (FluidRegistry), the FluidBlock, register the FluidItemBlock, register the Model
}
