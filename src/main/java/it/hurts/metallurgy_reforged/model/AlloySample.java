/*==============================================================================
 = Class: AlloySample
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.model;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class AlloySample {

	private final Metal metal;
	private ItemStack fallbackStack = ItemStack.EMPTY;
	private final int amount;
	private final boolean isOriginal;
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

	/**
	 * @return
	 * either an ingot stack of the provided metal<br>
	 * or the fallbackStack provided in the constructor<br>
	 * if a metal was disabled it'll return an empty itemstack
	 */
	public ItemStack getStack()
	{
		//The second condition is important when metals are disabled in materials.json (it'll return an empty itemStack)
		if (hasFallenBack() || metal == null)
			return fallbackStack;

		return new ItemStack(metal.getIngot(), amount);
	}

	public ItemStack getFallbackStack()
	{
		return fallbackStack;
	}

}
