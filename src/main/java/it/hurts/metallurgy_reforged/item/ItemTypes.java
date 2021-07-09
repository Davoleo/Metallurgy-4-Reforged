/*==============================================================================
 = Class: ItemTypes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.creativetab.CreativeTabs;

public enum ItemTypes {

	INGOT("ingot", MetallurgyTabs.tabIngot, true),
	DUST("dust", MetallurgyTabs.tabDust, RegistrationConfig.categoryItems.enableMetalDusts),
	NUGGET("nugget", MetallurgyTabs.tabNugget, RegistrationConfig.categoryItems.enableMetalNuggets);

	private String name;
	private CreativeTabs tab;
	private boolean isEnabled;

	ItemTypes(String name, CreativeTabs tab, boolean isEnabled)
	{
		this.name = name;
		this.tab = tab;
		this.isEnabled = isEnabled;
	}

    public String getName()
    {
        return name;
    }

	public CreativeTabs getTab()
	{
		return tab;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}
}
