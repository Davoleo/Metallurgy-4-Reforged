/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ToolStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

public class ToolStats {

	private final int toolMagic, harvestLevel, maxUses;
	private final float efficiency, damage;

	public ToolStats(int toolMagic, int harvestLevel, int maxUses, float efficiency, float damage)
	{
		this.toolMagic = toolMagic;
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.damage = damage;
	}

	//Tool Enchantability
	public int getToolMagic()
	{
		return toolMagic;
	}

	public int getHarvestLevel()
	{
		return harvestLevel;
	}

	public int getMaxUses()
	{
		return maxUses;
	}

	public float getEfficiency()
	{
		return efficiency;
	}

	public float getDamage()
	{
		return damage;
	}

}
