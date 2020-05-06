/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

public class ArmorStats {

	private final int[] damageReduction;
	private final int enchantability;
	private final int durability;
	private final float toughness;

	public ArmorStats(int[] damageReduction, int enchantability, int durability, float toughness)
	{
		this.damageReduction = damageReduction;
		this.enchantability = enchantability;
		this.durability = durability;
		this.toughness = toughness;
	}

	public int[] getDamageReduction()
	{
		return damageReduction;
	}

	//Armor Enchantability
	public int getEnchantability()
	{
		return enchantability;
	}

	public int getDurability()
	{
		return durability;
	}

	public float getToughness()
	{
		return toughness;
	}

}
