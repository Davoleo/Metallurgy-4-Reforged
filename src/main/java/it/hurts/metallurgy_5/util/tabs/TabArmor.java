package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.item.armor.ModArmors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class TabArmor extends CreativeTabs{
	
	public TabArmor() {
		super(Metallurgy_5.MODID +".armors");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModArmors.mithril_helmet);
	}
	
}
