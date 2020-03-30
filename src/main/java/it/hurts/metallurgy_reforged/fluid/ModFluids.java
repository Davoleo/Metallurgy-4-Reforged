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

import java.util.ArrayList;
import java.util.List;

public class ModFluids {

	public static final List<FluidMolten> moltenMetalFluids = new ArrayList<>();
	public static final List<FluidMolten> extraFluids = new ArrayList<>();

	public static final FluidMolten TAR = new FluidMoltenTar("molten_tar", 0xFF111419, 1000, true);
	public static final FluidMolten THERMITE = new FluidMolten("molten_thermite", 0xFFC44205, 3200, true);

	// TODO: 30/03/2020 For each block: register the Fluid (FluidRegistry), the FluidBlock, register the FluidItemBlock, register the Model
}
