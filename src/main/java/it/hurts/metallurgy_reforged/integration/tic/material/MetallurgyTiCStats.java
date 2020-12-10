/*==============================================================================
 = Class: MetallurgyTiCStats
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.*;

public class MetallurgyTiCStats {

	public final Metal metal;

	public final AbstractMaterialStats[] stats;

	public MetallurgyTiCStats(Metal metal, AbstractMaterialStats... abstractMaterialStats)
	{
		this.metal = metal;
		this.stats = abstractMaterialStats;
		if (metal != null)
		{
			TinkerMetals.metalStatsList.add(this);
		}
	}


	public static HeadMaterialStats getHeadA(Metal metal)
	{
		int durability = metal.getToolMaterial().getMaxUses();
		float speed = metal.getToolMaterial().getEfficiency();
		float attack = metal.getToolMaterial().getAttackDamage();
		int harvestL = metal.getToolMaterial().getHarvestLevel();

		// ? should we change the break speed
		return new HeadMaterialStats(durability / 4, speed, attack, harvestL);
	}

	public static ExtraMaterialStats getExtraA(Metal metal)
	{
		int durability = metal.getToolMaterial().getMaxUses();

		return new ExtraMaterialStats((int) (durability / 5.5));
	}

	public static HandleMaterialStats getHandleA(Metal metal)
	{
		int durability = metal.getToolMaterial().getMaxUses();
		float multiplier = 0.07F;
		float modifier = (float) (Math.sqrt(durability) * multiplier);

		return new HandleMaterialStats(modifier > 2 ? modifier * 0.5F : modifier, durability / 4);
	}

	public static BowMaterialStats getBowA(Metal metal)
	{
		final int MAX_SPEED = 27;

		float drawspeed = (float) ((MAX_SPEED - metal.getToolMaterial().getEfficiency()) / 12.3);
		float range = metal.getToolMaterial().getEfficiency() / 12;
		float bonusdamage = metal.getToolMaterial().getAttackDamage() / 12;

		return new BowMaterialStats(drawspeed, range, bonusdamage);
	}

}
