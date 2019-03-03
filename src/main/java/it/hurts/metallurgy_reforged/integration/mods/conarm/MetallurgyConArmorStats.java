/**
 * 
 */
package it.hurts.metallurgy_reforged.integration.mods.conarm;

import c4.conarm.lib.materials.ArmorMaterials;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import it.hurts.metallurgy_reforged.material.Metal;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 3 mar 2019
 * Time   : 18:27:52
 *
 ****************************/
public class MetallurgyConArmorStats extends ArmorMaterials{
	
	public static TrimMaterialStats getTrimStats(Metal metal) {
		return new TrimMaterialStats(metal.getStats().getArmorStats().getDurability() / 10);
	}
	
//	TODO Modificare lo 0
	public static CoreMaterialStats getCoreStats(Metal metal) {
		return new CoreMaterialStats(metal.getStats().getArmorStats().getDurability(), 0);
	}
	
//	TODO Modificare lo 0
	public static PlatesMaterialStats getPlatesStats(Metal metal) {
		return new PlatesMaterialStats(0 ,metal.getStats().getArmorStats().getDurability(), metal.getStats().getArmorStats().getToughness());
	}
	
}
