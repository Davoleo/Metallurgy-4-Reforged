/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EnumTools
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.model;

import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.tool.*;

public enum EnumTools {
	AXE("axe", ItemAxeBase.class, RegistrationConfig.categoryItems.enableMetalAxes),
	HOE("hoe", ItemHoeBase.class, RegistrationConfig.categoryItems.enableMetalHoes),
	PICKAXE("pickaxe", ItemPickaxeBase.class, RegistrationConfig.categoryItems.enableMetalPickaxes),
	SHOVEL("shovel", ItemShovelBase.class, RegistrationConfig.categoryItems.enableMetalShovels),
	SWORD("sword", ItemSwordBase.class, RegistrationConfig.categoryItems.enableMetalSwords);

	private String name;
	private Class<?> toolClass;
	private boolean isEnabled;

	EnumTools(String name, Class<?> toolClass, boolean enabled)
	{
		this.name = name;
		this.toolClass = toolClass;
		this.isEnabled = enabled;
	}

	public String getName()
	{
		return name;
	}

	public Class<?> getToolClass()
	{
		return toolClass;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}
}
