package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class TabIngot extends CreativeTabs{

    //TODO : Fix Item icon display cycle

	public TabIngot() {
		super(Metallurgy_5.MODID + ".ingots");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list)
	{
        for (Item item : Item.REGISTRY) {
            if (item != null) {
                if (item.getUnlocalizedName().contains("_ingot")) {
                    item.getSubItems(CreativeTabs.SEARCH, list);    // CreativeTabs.SEARCH will find all items even if they belong to another tab
                    //   except if the item has no tab (item.getCreativeTab() == NULL)
                }
            }
        }
	}

	@SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.ingotAdamantine);
    }
}
