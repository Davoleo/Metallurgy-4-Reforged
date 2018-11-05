package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date: 20/10/2018
 * Hour: 22.58
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TabMachine extends CreativeTabs {

    public TabMachine()
    {
        super(Metallurgy_5.MODID + ".machines");
    }


    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModBlocks.alloyer);
    }
}
