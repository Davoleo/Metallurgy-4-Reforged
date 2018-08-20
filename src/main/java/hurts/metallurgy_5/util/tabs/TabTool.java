package hurts.metallurgy_5.util.tabs;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 11.29
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TabTool extends CreativeTabs {

    public TabTool()
    {
        super(Metallurgy_5.MODID + ".tools");
    }

    public ItemStack getTabIconItem()
    {
       return new ItemStack(Items.DIAMOND_SWORD);
    }

}
