/*==============================================================================
 = Class: EnumTools
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public enum EnumTools {
	AXE("axe", ItemAxeBase.class, RegistrationConfig.categoryItems.enableMetalAxes),
	HOE("hoe", ItemHoeBase.class, RegistrationConfig.categoryItems.enableMetalHoes),
	PICKAXE("pickaxe", ItemPickaxeBase.class, RegistrationConfig.categoryItems.enableMetalPickaxes),
	SHOVEL("shovel", ItemShovelBase.class, RegistrationConfig.categoryItems.enableMetalShovels),
	SWORD("sword", ItemSwordBase.class, RegistrationConfig.categoryItems.enableMetalSwords);

	private final String name;
	private final Class<?> toolClass;
	private final boolean isEnabled;

	<T extends Item> EnumTools(String name, Class<T> toolClass, boolean enabled)
	{
		this.name = name;
		this.toolClass = toolClass;
		this.isEnabled = enabled;
	}

	public String getName()
	{
		return name;
	}

	@SuppressWarnings("unchecked")
	public <T extends Item> Class<T> getToolClass()
	{
		return (Class<T>) toolClass;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}

	@Nullable
	public static EnumTools byInstance(Item tool)
	{
		for (EnumTools type : EnumTools.values())
		{
			if (type.getToolClass().isInstance(tool))
				return type;
		}

		return null;
	}
}
