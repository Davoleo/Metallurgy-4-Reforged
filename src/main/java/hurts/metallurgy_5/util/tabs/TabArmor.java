package hurts.metallurgy_5.util.tabs;

import hurts.metallurgy_5.Metallurgy_5;
import hurts.metallurgy_5.armor.ModArmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabArmor extends CreativeTabs{
	
	public TabArmor() {
		super(Metallurgy_5.MODID+".armor");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModArmor.mithril_helmet);
	}
	
}
