/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationCArmory
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import it.hurts.metallurgy_reforged.integration.mods.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

public class IntegrationCArmory {

	public static void preInit() {
		Material.UNKNOWN.addStats(new TrimMaterialStats(0));
        Material.UNKNOWN.addStats(new CoreMaterialStats(0, 0));
        Material.UNKNOWN.addStats(new PlatesMaterialStats(1, 0, 0));
		
		for (Metal metal : ModMetals.metalList) {
			if ((IntegrationTIC.checkMaterial(metal) && !IntegrationTIC.checkMaterialPreInit(metal)) && metal.getStats().getArmorStats() != null) {
				Material material = TinkerRegistry.getMaterial(metal.getStats().getName());
				
				TinkerRegistry.addMaterialStats(material, 
						MetallurgyConArmorStats.getCoreStats(metal), 
						MetallurgyConArmorStats.getPlatesStats(metal),
						MetallurgyConArmorStats.getTrimStats(metal));
				
			}
		}
	}
	
	public static void init() {
		
	}
	
}
