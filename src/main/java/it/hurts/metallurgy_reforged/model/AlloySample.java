/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AlloySample
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.model;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import jline.internal.Nullable;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
public class AlloySample {

	private Metal metal;
	private ItemStack fallbackStack = ItemStack.EMPTY;
	private int amount;
	private boolean isOriginal;
	private float xp = -1F;

	public AlloySample(Metal metal, int amount, float xp)
	{
		this.metal = metal;
		this.amount = amount;
		this.xp = xp;
		this.isOriginal = true;
	}

	public AlloySample(Metal metal, int amount)
	{
		this(metal, amount, -1F);
	}

	public AlloySample(ItemStack oreDictStack, int amount)
	{
		metal = ItemUtils.getMetalFromOreDictStack(oreDictStack);
		if (metal == null)
			this.fallbackStack = oreDictStack;
		this.amount = amount;
		this.isOriginal = false;
	}

	@Nullable
	public Metal getMetal()
	{
		return metal;
	}

	public int getAmount()
	{
		return amount;
	}

	public boolean isOriginal()
	{
		return isOriginal;
	}

	public float getXp()
	{
		return xp;
	}

	public boolean hasXp()
	{
		return xp != -1F;
	}

	public AlloySample setXp(float xp)
	{
		this.xp = xp;
		return this;
	}

	public boolean hasFallenBack()
	{
		return fallbackStack != ItemStack.EMPTY;
	}

	public ItemStack getStack()
	{
		if (hasFallenBack())
			return fallbackStack;

		return new ItemStack(metal.getIngot(), amount);
	}

	public ItemStack getFallbackStack()
	{
		return fallbackStack;
	}

}
