package it.hurts.metallurgy_5.util.tabs;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.block.ModBlocks;
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

public class TabBlock extends CreativeTabs{

	public TabBlock() {
		super(Metallurgy_5.MODID+".blocks");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.blockDeepIron);
	}
	
}
