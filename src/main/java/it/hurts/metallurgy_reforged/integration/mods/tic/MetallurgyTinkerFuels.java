/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTinkerFuels
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.fluid.ModFluids;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;

public class MetallurgyTinkerFuels {

	public static void init() {
		TinkerRegistry.registerSmelteryFuel(new FluidStack(ModFluids.THERMITE, 50), 100);
	}

}
