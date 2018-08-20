package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.armor.ModArmors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabArmor extends CreativeTabs{
	
	public TabArmor() {
		super(Metallurgy_5.MODID+".armors");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModArmors.mithril_helmet);
	}
	
}
