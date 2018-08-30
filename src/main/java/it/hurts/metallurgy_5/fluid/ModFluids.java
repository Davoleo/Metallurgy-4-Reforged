package it.hurts.metallurgy_5.fluid;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy_5
 * Copyright - � - Davoleo - 2018
 **************************************************/

public class ModFluids {

    public static final FluidMolten MOLTEN_ADAMANTINE = (FluidMolten) new FluidMolten(
            "molten_adamantine",
            new ResourceLocation(Metallurgy_5.MODID, "blocks/molten_metal_still"),
            new ResourceLocation(Metallurgy_5.MODID, "blocks/molten_metal_flow"),
            0xa3000000
    )
            .setMaterial(Material.IRON)
            .setDensity(800)
            .setGaseous(false)
            .setLuminosity(9)
            .setViscosity(1000)
            .setTemperature(4000);


    public static void registerFluids()
    {
        FluidRegistry.registerFluid(MOLTEN_ADAMANTINE);
    }

    public static void initBuckets()
    {
        FluidUtil.getFilledBucket(new FluidStack(ModFluids.MOLTEN_ADAMANTINE, 1));
    }


}
