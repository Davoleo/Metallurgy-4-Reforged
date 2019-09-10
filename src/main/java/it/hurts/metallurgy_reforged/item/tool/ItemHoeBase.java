/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemHoeBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemHoeBase extends ItemHoe implements IHasModel {

	public ItemHoeBase(ToolMaterial material, String name)
	{
		super(material);
		ItemUtils.initItem(this, name, MetallurgyTabs.tabTool, ModTools.toolList);
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
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
	}

	@Nonnull
	@Override
	public String getCategory()
	{
		return "tool/hoe";
	}

}
