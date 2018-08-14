package hurts.metallurgy_5.proxy.util.tabs;

import hurts.metallurgy_5.Metallurgy_5;
import hurts.metallurgy_5.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabIngot extends CreativeTabs{

	public TabIngot() {
		super(Metallurgy_5.MODID + ".ingots");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.ingotManganese);
	}
	
}
