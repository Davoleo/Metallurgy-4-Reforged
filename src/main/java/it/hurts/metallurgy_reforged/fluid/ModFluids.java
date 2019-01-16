package it.hurts.metallurgy_reforged.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModFluids {

    public static final List<Fluid> fluidList = new ArrayList<>();

    public static void registerFluids()
    {
        for (Fluid fluid : fluidList)
        {
            FluidRegistry.addBucketForFluid(fluid);
        }
    }

}
