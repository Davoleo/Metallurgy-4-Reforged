/*==============================================================================
 = Class: ArmorStats
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.material;

public class ArmorStats {

	private final int[] damageReduction;
	private final int enchantability;
	private final int durability;
	private final float toughness;

	private double maxHealth;
	private double knockbackResistance;
	private double movementSpeed;

	public ArmorStats(int[] damageReduction, int enchantability, int durability, float toughness)
	{
		this.damageReduction = damageReduction;
		this.enchantability = enchantability;
		this.durability = durability;
		this.toughness = toughness;

		this.maxHealth = 0;
		this.knockbackResistance = 0;
		this.movementSpeed = 0;
	}

	public void setAttributes(double maxHealth, double knockbackResistance, double movementSpeed)
	{
		this.maxHealth = maxHealth;
		this.knockbackResistance = knockbackResistance;
		this.movementSpeed = movementSpeed;
	}

	public double getMaxHealth()
	{
		return maxHealth;
	}

	public double getKnockbackResistance()
	{
		return knockbackResistance;
	}

	public double getMovementSpeed()
	{
		return movementSpeed;
	}

	/**
	 * @return Array of armor protection values [reverse order: Boots -> Helmet]
	 */
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
