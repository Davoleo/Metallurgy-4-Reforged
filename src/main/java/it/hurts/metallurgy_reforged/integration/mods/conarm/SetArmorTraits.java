package it.hurts.metallurgy_reforged.integration.mods.conarm;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

public class SetArmorTraits {

	public static void addArmorTrait(Metal metal, Material material){
		switch(metal.getStats().getName()) {
		
			case "quicksilver" : {
				material.addTrait(MetallurgyArmorTraits.quickly);
			}
			break;
			
			default: {}
			break;
		}
	}
	
}
