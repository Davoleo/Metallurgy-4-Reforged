package hurts.metallurgy_5.util.tab;

import hurts.metallurgy_5.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabOre extends CreativeTabs {
	
	public TabOre()
	{
		super(Reference.MOD_ID + ".ores");
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(Blocks.IRON_ORE);
	}
	
	@SideOnly(Side.CLIENT)
	public void displayItems(NonNullList<ItemStack> itemToShowOnTab) // Cicla oggetti della tab
	{
		for(Item item : Item.REGISTRY){
			if (item != null){
					item.getSubItems(this, itemToShowOnTab);
			}
		}
	}

}
