package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

/***************************
 * Authors : ItHurtsLikeHell & Davoleo
 * Project: Metallurgy-5
 ***************************/

public class TabDust extends CreativeTabs {

    private int icon;
    private ArrayList<ItemStack> iconList = new ArrayList<>();

    public TabDust()
    {
        super(Metallurgy_5.MODID + ".dusts");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        int counter = 0;

        for (Item item : Item.REGISTRY) {
            if (item != null) {
                if (item.getUnlocalizedName().contains("_dust")) {
                    item.getSubItems(CreativeTabs.SEARCH, list);
                    iconList.add(list.get(counter));
                    counter++;
                }
            }
        }
    }

    @Override
    public ItemStack getTabIconItem()
    {
        icon = Metallurgy_5.ticker/20;

        if(!iconList.isEmpty())
            return new ItemStack(iconList.get(icon % iconList.size()).getItem());
        return ItemStack.EMPTY;
    }
}
