package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.item.tool.ModTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 11.29
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TabTool extends CreativeTabs {

    //TODO : Fix Item icon display cycle

    public TabTool()
    {
        super(Metallurgy_5.MODID + ".tools");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem()
    {
       return new ItemStack(ModTools.tartarite_sword);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        for (Item item : Item.REGISTRY) {
            if (item != null) {
                if (item.getUnlocalizedName().contains("_axe") ||
                        item.getUnlocalizedName().contains("_hoe") ||
                        item.getUnlocalizedName().contains("_pickaxe") ||
                        item.getUnlocalizedName().contains("_shovel") ||
                        item.getUnlocalizedName().contains("_sword") )
                {
                    item.getSubItems(CreativeTabs.SEARCH, list);  // CreativeTabs.SEARCH will find all items even if they belong to another tab
                    //   except if the item has no tab (item.getCreativeTab() == NULL)
                }
            }
        }
    }

}
