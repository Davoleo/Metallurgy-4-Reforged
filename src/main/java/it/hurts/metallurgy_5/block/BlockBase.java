package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nonnull;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class BlockBase extends Block {

	protected String name;

	public BlockBase(Material material, String name) {
		super(material);
		setCreativeTab(MetallurgyTabs.tabBlock);
		
		this.name = name;
		
		setUnlocalizedName(name);
		setRegistryName(name);
		ModBlocks.blockList.add(this);
	}
	
	public void registerItemModel(Item itemBlock) {
		Metallurgy_5.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Nonnull
	@Override
	public BlockBase setCreativeTab(@Nonnull CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
}
