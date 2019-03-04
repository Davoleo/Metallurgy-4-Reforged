/**
 * 
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

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 3 mar 2019
 * Time   : 19:39:05
 *
 ****************************/
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
