package hurts.metallurgy_5.proxy.util.tabs;

import hurts.metallurgy_5.Metallurgy_5;
import hurts.metallurgy_5.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBlock extends CreativeTabs{

	public TabBlock() {
		super(Metallurgy_5.MODID+".blocks");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.blockCopper);
	}
	
}
