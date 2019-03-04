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
		float durability = metal.getStats().getArmorStats().getDurability();
		
		return new TrimMaterialStats(durability / 10);
	}
	
//	TODO Modificare lo 0
	public static CoreMaterialStats getCoreStats(Metal metal) {
		float durability = metal.getStats().getArmorStats().getDurability();
		float defensePoint = getDefensePoint(metal.getStats().getArmorStats().getDamageReduction());
		
		return new CoreMaterialStats(durability, defensePoint);
	}
	
	public static PlatesMaterialStats getPlatesStats(Metal metal) {
		float durability = metal.getStats().getArmorStats().getDurability();
		float multiplier = 0.07F;
		float modifier = (float) (Math.sqrt(durability) * multiplier);
		float toughness = metal.getStats().getArmorStats().getToughness();
		
		return new PlatesMaterialStats(modifier, durability, toughness);
	}
	
	private static int getDefensePoint(int[] defensePoint) {
		int maxDefensePoint = 0;
		
		for(int i = 0; i < defensePoint.length; i++) {
			maxDefensePoint += defensePoint[i];
		}
		return maxDefensePoint;
	}
	
}
