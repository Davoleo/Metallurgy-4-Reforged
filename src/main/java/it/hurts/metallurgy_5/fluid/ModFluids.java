package it.hurts.metallurgy_5.fluid;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModFluids {

    //TODO : Implement all the molten metals

    public static final FluidMolten MOLTEN_ADAMANTINE = (FluidMolten) new FluidMolten(
            "molten_adamantine",
            new ResourceLocation(Metallurgy_5.MODID, "blocks/molten_metal_still"),
            new ResourceLocation(Metallurgy_5.MODID, "blocks/molten_metal_flow"),
            0xFFa30000
    )
            .setMaterial(Material.IRON)
            .setDensity(800)
            .setGaseous(false)
            .setLuminosity(9)
            .setViscosity(3000)
            .setTemperature(4000);


    public static void registerFluids()
    {
        FluidRegistry.registerFluid(MOLTEN_ADAMANTINE);
        FluidRegistry.addBucketForFluid(MOLTEN_ADAMANTINE);
    }

}
