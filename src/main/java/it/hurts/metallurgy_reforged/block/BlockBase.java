/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nonnull;
import java.util.Objects;

public class BlockBase extends Block {

	//Internal state / variables -------------------------------------------------
	protected String name;

	//Constructor ----------------------------------------------------------------
	//Creates an instance of a basic block setting the TranslationKey, the RegistryName, the CreativeTab, the Material and adding it to the list of Mod blocks
	public BlockBase(Material material, String name) {
		super(material);
		
		this.name = name;

		setCreativeTab(MetallurgyTabs.tabBlock);
		setTranslationKey(name);
		setRegistryName(name);
		ModBlocks.blockList.add(this);
	}

	//Custom Methods -------------------------------------------------------------

	//Registers the model of the item
	public void registerItemModel(Item itemBlock) {
		Metallurgy.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	//Creates the inventory version of the block
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(Objects.requireNonNull(getRegistryName()));
	}

	//Overridden Methods -------------------------------------------------------------

	//Overrides the creative tab
	@Nonnull
	@Override
	public BlockBase setCreativeTab(@Nonnull CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
}
