package it.hurts.metallurgy_reforged.integration.mods.tic;

import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.library.utils.HarvestLevels.DIAMOND;
import static slimeknights.tconstruct.tools.TinkerTraits.magnetic;
import static slimeknights.tconstruct.tools.TinkerTraits.magnetic2;

import java.util.List;

import com.google.common.collect.Lists;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

/***************************
*
* Author : ItHurtsLikeHell
* Helper : PierKnight100
* Project: Metallurgy-5
* Date   : 13 dic 2018
* Time   : 18:33:14
*
***************************/

public class MetallurgyMaterials {
	
	public static void addMaterialStats(Metal metal,Material material)
	{
//		TODO Automatizzare
		List<IMaterialStats> stats = Lists.newArrayList();
			stats.add( new HeadMaterialStats(200, 6.00f, 2F, DIAMOND));
			stats.add(new HandleMaterialStats(0.1f, 60));
		    stats.add(new ExtraMaterialStats(50));
	      
		
		
		
		for(IMaterialStats stat : stats)
			TinkerRegistry.addMaterialStats(material, stat);
	}
	
//	TODO add traits
	public static void addTraits(Metal metal,Material material)
	{
		if(metal == ModMetals.ASTRAL_SILVER) {
		material.addTrait(magnetic2, HEAD);
		material.addTrait(magnetic);
		}
	}
	
     
}
