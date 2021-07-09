/*==============================================================================
 = Class: IntegrationCArmory
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm;


import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import it.hurts.metallurgy_reforged.integration.tic.IntegrationTIC;
import it.hurts.metallurgy_reforged.integration.tic.material.TiCMaterials;
import it.hurts.metallurgy_reforged.material.ModMetals;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

public class IntegrationCArmory {

	public static void preInit()
	{
		Material.UNKNOWN.addStats(new TrimMaterialStats(0));
		Material.UNKNOWN.addStats(new CoreMaterialStats(0, 0));
		Material.UNKNOWN.addStats(new PlatesMaterialStats(1, 0, 0));

		ModMetals.metalMap.forEach((name, metal) -> {
			if (IntegrationTIC.checkMaterial(metal) && metal.getStats().getArmorStats() != null)
			{
				Material material = TinkerRegistry.getMaterial(name);
				TiCMaterials.registerStats(material, MetallurgyConArmorStats.getCoreStats(metal), MetallurgyConArmorStats.getPlatesStats(metal), MetallurgyConArmorStats.getTrimStats(metal));
			}
		});
	}


	public static void init()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			if (IntegrationTIC.checkMaterial(metal))
			{
				Material m = TinkerRegistry.getMaterial(name);
				//Add Custom Armor Traits
				SetArmorTraits.addArmorTrait(metal, m);
			}
		});
	}

}
