/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemTypes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.creativetab.CreativeTabs;

public enum ItemTypes {

	INGOT("ingot", MetallurgyTabs.tabIngot),
	DUST("dust", MetallurgyTabs.tabDust),
	NUGGET("nugget", MetallurgyTabs.tabNugget);

	private String name;
	private CreativeTabs tab;

	ItemTypes(String name, CreativeTabs tab)
	{
		this.name = name;
		this.tab = tab;
	}

	public String getName()
	{
		return name;
	}

	public CreativeTabs getTab()
	{
		return tab;
	}
}
