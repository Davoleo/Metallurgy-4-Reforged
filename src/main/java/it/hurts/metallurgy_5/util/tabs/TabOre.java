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
	
public class TabOre extends CreativeTabs{

	public TabOre() {
		super(Metallurgy_5.MODID+".ores");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.oreOrichalcum);
	}
		
}
