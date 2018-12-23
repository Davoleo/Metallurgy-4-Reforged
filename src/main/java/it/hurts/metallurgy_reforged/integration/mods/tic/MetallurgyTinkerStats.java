package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

/***************************
 *
 * Author : ItHurtsLikeHell, PierKnight100
 * Project: Metallurgy-5
 * Date   : 23 dic 2018
 * Time   : 19:40:02
 *
 ***************************/
public class MetallurgyTinkerStats {
	public final Metal metal;
	
	public final AbstractMaterialStats[] stats;
	
	public MetallurgyTinkerStats(Metal metal,AbstractMaterialStats...abstractMaterialStats) {
		this.metal = metal;
		this.stats = abstractMaterialStats;
		TinkerMetals.metalStatsList.add(this);
	}
	
	
	public static HeadMaterialStats getHeadA(Metal metal){
		return new HeadMaterialStats(metal.getToolMaterial().getMaxUses(), metal.getToolMaterial().getEfficiency(), metal.getToolMaterial().getAttackDamage(), metal.getToolMaterial().getHarvestLevel());
	}
	
	public static ExtraMaterialStats getExtraA(Metal metal){
		return new ExtraMaterialStats(metal.getToolMaterial().getMaxUses() / 20);
	}
	
	public static HandleMaterialStats getHandleA(Metal metal){
		return new HandleMaterialStats(0.1F,metal.getToolMaterial().getMaxUses());
	}
}
