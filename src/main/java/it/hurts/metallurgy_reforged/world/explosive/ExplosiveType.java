/*==============================================================================
 = Class: ExplosiveType
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.explosive;

import javax.annotation.Nullable;

public enum ExplosiveType {
	VULCANITE(6, false, true);

	final float strength;
	final boolean causesFire;
	final boolean damagesTerrain;

	ExplosiveType(int strength, boolean causesFire, boolean damagesTerrain)
	{
		this.strength = strength;
		this.causesFire = causesFire;
		this.damagesTerrain = damagesTerrain;
	}


	@Nullable
	public static ExplosiveType byIndex(int idx)
	{
		if (idx < 0 || idx >= ExplosiveType.values().length)
			return null;

		return ExplosiveType.values()[idx];
	}

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
