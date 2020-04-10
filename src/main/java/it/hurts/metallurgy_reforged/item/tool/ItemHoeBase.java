/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemHoeBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

//TODO: if instanceof ItemAxeBase -> modelSubDir = tool/hoe
public class ItemHoeBase extends ItemHoe {

	public ItemHoeBase(ToolMaterial material, String name)
	{
		super(material);
		ItemUtils.initItem(this, name, MetallurgyTabs.tabTool);
	}

	private ItemStack getRepairStack()
	{
		String material = this.getMaterialName().toLowerCase();
		Metal metal = Utils.getMetalFromString(material);
		if (metal != null)
			return new ItemStack(metal.getIngot());
		else
			return ItemStack.EMPTY;
	}

	@Override
	public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair)
	{
		return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
	}
}
