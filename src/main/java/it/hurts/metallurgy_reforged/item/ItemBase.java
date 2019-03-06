/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class ItemBase extends Item{

	protected String name;

	public ItemBase(String name)
    {
		this.name = name;
		setTranslationKey(Metallurgy.MODID + "." + name);
		setRegistryName(Metallurgy.MODID, name);
		ModItems.itemList.add(this);
	}
	
	public void registerItemModel()
    {
		Metallurgy.proxy.registerItemRenderer(this, 0, name);
	}

	public void registerItemModel(String subdirectory)
	{
		Metallurgy.proxy.registerItemRenderer(this, 0, name, subdirectory);
	}

	@Nonnull
	@Override
	public ItemBase setCreativeTab(@Nonnull CreativeTabs tab)
    {
		super.setCreativeTab(tab);
		return this;
	}
	
}
