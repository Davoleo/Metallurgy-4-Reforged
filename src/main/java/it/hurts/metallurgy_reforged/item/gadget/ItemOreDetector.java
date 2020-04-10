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

import it.hurts.metallurgy_reforged.item.ItemBase;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemOreDetector extends ItemBase {

	public ItemOreDetector()
	{
		super("ore_detector", MetallurgyTabs.tabSpecial, "gadget");
		this.setMaxStackSize(1);
	}

	@Override
	public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
	{
		super.onUsingTick(stack, player, count);
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 120;
	}
}
