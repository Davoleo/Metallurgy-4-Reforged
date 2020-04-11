/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GenerationStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

public class GenerationStats {

	int dimension;
	int veinSize;
	int chance;
	int minY, maxY;

	public GenerationStats(int veinSize, int chance, int minY, int maxY, int dimension)
	{
		this.veinSize = veinSize;
		this.chance = chance;
		this.minY = minY;
		this.maxY = maxY;
		this.dimension = dimension;
	}

	public int getVeinSize()
	{
		return veinSize;
	}

	public int getChance()
	{
		return chance;
	}

	public int getMinY()
	{
		return minY;
	}

	public int getMaxY()
	{
		return maxY;
	}

	public int getDimension()
	{
		return dimension;
	}

}
