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

public class ItemMetal extends ItemBase {

	private ItemTypes type;
	private MetalStats metal;

	public ItemMetal(MetalStats metal, ItemTypes type)
	{
		super(metal.toString() + "_" + type.getName(), type.getTab());
		this.type = type;
		this.metal = metal;
	}

	public ItemTypes getType()
	{
		return type;
	}

	public MetalStats getMetal()
	{
		return metal;
	}

}
