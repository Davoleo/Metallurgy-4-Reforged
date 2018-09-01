package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.fluid.ModFluids;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 30/08/2018
 * Hour: 10.43
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TabFluid extends CreativeTabs {

    public TabFluid()
    {
     super(Metallurgy_5.MODID + ".fluids");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return ModFluids.MOLTEN_ADAMANTINE.getBucket();
    }

}
