package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
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
		
		this.name = name;

		setCreativeTab(MetallurgyTabs.tabBlock);
		setTranslationKey(name);
		setRegistryName(name);
		ModBlocks.blockList.add(this);
	}
	
	public void registerItemModel(Item itemBlock) {
		Metallurgy.proxy.registerItemRenderer(itemBlock, 0, name);
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
