/*==============================================================================
 = Class: EnumEffectCategory
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.item.tool.EnumTools;

public enum EnumEffectCategory {
	TOOL(EnumTools.AXE, EnumTools.PICKAXE, EnumTools.SHOVEL),
	ARMOR(),
	WEAPON(EnumTools.AXE, EnumTools.SWORD),
	ALL(EnumTools.AXE, EnumTools.PICKAXE, EnumTools.HOE, EnumTools.SHOVEL, EnumTools.SWORD),
	HOE(EnumTools.HOE),
	PICKAXE(EnumTools.PICKAXE);

	private final EnumTools[] tools;

	EnumEffectCategory(EnumTools... tools)
	{
		this.tools = tools;
	}

	public EnumTools[] getTools()
	{
		return tools;
	}

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
