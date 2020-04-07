/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemMetal
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMetal extends Item {

	private ItemTypes type;
	private String tooltip;

	public ItemMetal(MetalStats metal, ItemTypes type)
	{
		this.type = type;
		ItemUtils.initItem(this, metal.toString() + "_" + type.getName(), type.getTab());
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (this.tooltip != null)
			tooltip.add(this.tooltip);
	}

	//Setters & Getters -----------------------------------------------------
	public ItemMetal setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}

	public ItemTypes getType()
	{
		return type;
	}

}
