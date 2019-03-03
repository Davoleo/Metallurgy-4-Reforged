/**
 * 
 */
package it.hurts.metallurgy_reforged.integration.mods;

import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import it.hurts.metallurgy_reforged.integration.mods.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.TinkerRegistry;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 3 mar 2019
 * Time   : 19:39:05
 *
 ****************************/
public class IntegrationCArmory {
	
//	Bisognerà utilizzare questa classe per avere la possibilità di craftare le armor?  public ArmorCore
	
	public static void init() {
		for (Metal metal : ModMetals.metalList) {
			if (IntegrationTIC.checkMaterial(metal) && IntegrationTIC.checkMaterialPreInit(metal)) {
				TiCMaterial material = new TiCMaterial(metal);
				
				TinkerRegistry.addMaterialStats(material, 
						MetallurgyConArmorStats.getCoreStats(metal), 
						MetallurgyConArmorStats.getPlatesStats(metal),
						MetallurgyConArmorStats.getTrimStats(metal));
				
			}
		}
	}
	
}
