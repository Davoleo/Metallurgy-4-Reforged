/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationCT
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.potions.IPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class IntegrationCT {

	public static void preInit()
	{

		CraftTweakerAPI.registerClass(CompatCrusher.class);
		CraftTweakerAPI.registerClass(CompatAlloyer.class);
		CraftTweakerAPI.registerClass(CompatSublimationChamber.class);

	}

	public static ItemStack toStack(IItemStack iStack)
	{
		if (iStack == null)
			return ItemStack.EMPTY;
		return (ItemStack) iStack.getInternal();
	}

	public static ItemStack[] toStacks(IItemStack[] iStacks)
	{
		if (iStacks == null)
		{
			return new ItemStack[0];
		}
		ItemStack[] ret = new ItemStack[iStacks.length];
		for (int i = 0; i < iStacks.length; i++)
		{
			ret[i] = toStack(iStacks[i]);
		}
		return ret;
	}

	public static Potion getInternalPotion(IPotion effect)
	{
		if (effect == null)
			return null;
		return (Potion) effect.getInternal();
	}

}
