package it.hurts.metallurgy_reforged.integration.mods.tic.material;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.material.ToolStats;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

import static slimeknights.tconstruct.library.materials.MaterialTypes.HEAD;
import static slimeknights.tconstruct.tools.TinkerTraits.magnetic;
import static slimeknights.tconstruct.tools.TinkerTraits.magnetic2;

/***************************
*
* Author : ItHurtsLikeHell
* Helper : PierKnight100
* Project: Metallurgy-5
* Date   : 13 dic 2018
* Time   : 18:33:14
*
***************************/

public class TiCMaterials {
	
	
	
	public static void addMaterialStats(Metal metal, Material material){	
		MetalStats metalS = metal.getStats();
		
		MetallurgyTiCStats stats = TinkerMetals.getMetal(metal);
		
		if(stats != null)
		{
			for(AbstractMaterialStats stat : stats.stats)
					TinkerRegistry.addMaterialStats(material, stat);
		}		
		else if(metalS != null)
		{
			ToolStats TStats = metalS.getToolStats();
			if(TStats != null)
			{
				TinkerRegistry.addMaterialStats(material, MetallurgyTiCStats.getHeadA(metal));
				TinkerRegistry.addMaterialStats(material, MetallurgyTiCStats.getHandleA(metal));
				TinkerRegistry.addMaterialStats(material, MetallurgyTiCStats.getExtraA(metal));
			}
		}	
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
