/*==============================================================================
 = Class: ItemZincBuckler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemZincBuckler extends ItemBuckler {

	public ItemZincBuckler()
	{
		super("zinc_buckler", 150, 30);
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 60;
	}

	@Override
	public int getItemEnchantability()
	{
		return 10;
	}

}
