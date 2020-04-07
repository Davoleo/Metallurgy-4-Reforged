/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemOreDetector
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemOreDetector extends Item implements IHasModel {

	public ItemOreDetector()
	{
		ItemUtils.initItem(this, "ore_detector", MetallurgyTabs.tabSpecial);
		this.setMaxStackSize(1);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
	{
		super.onUsingTick(stack, player, count);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 120;
	}

	@Nonnull
	@Override
	public String getCategory()
	{
		return "gadget";
	}

}
