package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/***************************
* Authors : ItHurtsLikeHell & Davoleo
* Project: Metallurgy-5
***************************/

public class TabIngot extends CreativeTabs{

    private int icon;
    private NonNullList<ItemStack> iconList;

	public TabIngot() {
		super(Metallurgy_5.MODID + ".ingots");
	}

    private NonNullList<ItemStack> icons(){
        NonNullList<ItemStack> items = NonNullList.create();

        for (Item item : Item.REGISTRY) {
            if (item != null) {
                if (item.getUnlocalizedName().contains("_ingot")) {

                    items.add(new ItemStack(item));
                }
            }
        }
        return items;
    }

    @Override
    public ItemStack getTabIconItem()
    {
        iconList = this.icons();
        icon = Metallurgy_5.ticker/20;

        if(!iconList.isEmpty())
            return new ItemStack(iconList.get(icon % iconList.size()).getItem());
        return ItemStack.EMPTY;
    }
}
