/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemBase extends Item {

	private String name;
	private String tooltip;
	private String modelSubDir;

	public ItemBase(String name, CreativeTabs tab, String modelSubDir)
	{
		this.name = name;
		this.modelSubDir = modelSubDir;
		ItemUtils.initItem(this, name, tab);
	}

	public ItemBase(String name, CreativeTabs tab)
	{
		this(name, tab, "");
	}

	@Override
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
	{
		if (this.tooltip != null)
			tooltip.add(this.tooltip);
	}

	//Setters & Getters -----------------------------------------------------
	public ItemBase setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}

	public String getModelSubDir()
	{
		return modelSubDir;
	}

}
