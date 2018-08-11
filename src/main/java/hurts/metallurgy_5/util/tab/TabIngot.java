package hurts.metallurgy_5.util.tab;

import hurts.metallurgy_5.init.ModItems;
import hurts.metallurgy_5.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabIngot extends CreativeTabs {
	
	public TabIngot()
	{
		super(Reference.MOD_ID + ".ingots");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.ANGMALLEN_INGOT);
	}
	
	@SideOnly(Side.CLIENT)
	public void displayItems(NonNullList<ItemStack> itemToShowOnTab)
	{
		for(Item item : Item.REGISTRY){
			if (item != null){
					item.getSubItems(this, itemToShowOnTab);
			}
		}
	}

}
