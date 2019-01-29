package it.hurts.metallurgy_reforged.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

import it.hurts.metallurgy_reforged.Metallurgy;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModFluids {

    public static final List<Fluid> fluidList = new ArrayList<>();
    
    private final static ResourceLocation default_still = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_still");
    private final static ResourceLocation default_flowing = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_flow");
    
//    private final Metal THERMITE = new MetalStats()
    public final static FluidMolten THERMITE = new FluidMolten("thermite", default_still, default_flowing, 0x00FFFFFF, 3200);

    public static void registerFluids()
    {
        for (Fluid fluid : fluidList)
        {
            FluidRegistry.addBucketForFluid(fluid);
        }
        
        FluidRegistry.addBucketForFluid(THERMITE);
    }

}
