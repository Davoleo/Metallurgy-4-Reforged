/*==============================================================================
 = Class: TinkerMetals
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * ## INFO ##
 * <br>
 * To delete all tool parts :
 * ' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME); '
 * <br>
 * To add a single part
 * ' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME, new HeadMaterialStats / HandleMaterialStats / ExtraMaterialStats '
 * <br>
 * To add a single part automatically
 * ' public static MetallurgyTiCStats NAME = new MetallurgyTiCStats(ModMetals.NAME,  MetallurgyTiCStats.getHeadA / getExtraA / getHandleA (ModMetals.NAME)); '
 */

public class TinkerMetals {

	public static List<MetallurgyTiCStats> metalStatsList = new ArrayList<>();

	public static MetallurgyTiCStats ALDUORITE = new MetallurgyTiCStats(ModMetals.ALDUORITE,
			new HeadMaterialStats(600, 14F, 5, 4),
			new HandleMaterialStats(0.55F, 215),
			new ExtraMaterialStats(40));

	public static MetallurgyTiCStats INFUSCOLIUM = new MetallurgyTiCStats(ModMetals.INFUSCOLIUM,
			new HeadMaterialStats(180, 25F, 6, 3),
			new HandleMaterialStats(0.45F, 190),
			new ExtraMaterialStats(15),
			new BowMaterialStats((float) (Math.sqrt(2)), 25 / 12F, 6 / 12F));

	public static MetallurgyTiCStats RUBRACIUM = new MetallurgyTiCStats(ModMetals.RUBRACIUM,
			new HandleMaterialStats(0.75F, 300),
			new ExtraMaterialStats(47));

	public static MetallurgyTiCStats MIDASIUM = new MetallurgyTiCStats(ModMetals.MIDASIUM,
			new HeadMaterialStats(300, 10F, 4, 3),
			new HandleMaterialStats(0.60F, 200),
			new ExtraMaterialStats(30));

	public static MetallurgyTiCStats TARTARITE = new MetallurgyTiCStats(ModMetals.TARTARITE,
			new HeadMaterialStats(400, 16F, 10F, 8),
			MetallurgyTiCStats.getHandleA(ModMetals.TARTARITE),
			MetallurgyTiCStats.getExtraA(ModMetals.TARTARITE));

	public static MetallurgyTiCStats PLATINUM = new MetallurgyTiCStats(ModMetals.PLATINUM,
			MetallurgyTiCStats.getHeadA(ModMetals.PLATINUM),
			new ExtraMaterialStats(157),
			new HandleMaterialStats(1.11F, 45));

	@Nullable
	public static MetallurgyTiCStats getMetal(Metal metal)
	{
		for (MetallurgyTiCStats stat : metalStatsList)
		{
			if (stat.metal.equals(metal))
				return stat;
		}
		return null;
	}

}
