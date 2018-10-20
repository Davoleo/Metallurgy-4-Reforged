package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date: 20/10/2018
 * Hour: 22.52
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TabDust extends CreativeTabs {

    public TabDust()
    {
        super(Metallurgy_5.MODID + ".dusts");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.dustAstralSilver);
    }
}
