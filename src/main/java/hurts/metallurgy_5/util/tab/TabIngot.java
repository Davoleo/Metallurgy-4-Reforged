package hurts.metallurgy_5.util.tab;

import hurts.metallurgy_5.init.ModItems;
import hurts.metallurgy_5.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabIngot extends CreativeTabs {
	
	public TabIngot()
	{
		super(Reference.MOD_ID);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.MAGNESE_INGOT);
	}

}
