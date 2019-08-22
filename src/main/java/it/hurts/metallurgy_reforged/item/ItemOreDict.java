/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemOreDict
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ItemOreDict extends Item implements IHasModel {

	//Internal Variables ----------------------------------------------------
	private String oreName;
	private String tooltip;

	//Constructors
	public ItemOreDict(String name, String oreName, ItemGroup tab, List list)
	{
		super(new Item.Properties().group(tab));
		ItemUtils.initItem(this, name, tab, list);
		this.oreName = oreName;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		if (this.tooltip != null)
			tooltip.add(new StringTextComponent(this.tooltip));
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
}
