package hurts.metallurgy_5.items;

import hurts.metallurgy_5.Main;
import hurts.metallurgy_5.init.IHasModel;
import hurts.metallurgy_5.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.tabIngot);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRender(this,0,"inventory");
		
	}

	
	
}
