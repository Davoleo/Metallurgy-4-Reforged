/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemOreDict
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.IOreDict;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemOreDict extends Item implements IOreDict, IHasModel {

	//Internal Variables ----------------------------------------------------
	private String oreName;
	private String tooltip;

	//Constructors
	public ItemOreDict(String name, String oreName, CreativeTabs tab, List list)
	{
		ItemUtils.initItem(this, name, tab, list);
		this.oreName = oreName;
	}

	@Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (this.tooltip != null)
			tooltip.add(this.tooltip);
	}

	//Setters & Getters -----------------------------------------------------
	public ItemOreDict setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}

	@Nonnull
	@Override
	public String getCategory()
	{
		return "";
	}

	@Nonnull
	@Override
	public ItemOreDict setCreativeTab(@Nonnull CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}

}
