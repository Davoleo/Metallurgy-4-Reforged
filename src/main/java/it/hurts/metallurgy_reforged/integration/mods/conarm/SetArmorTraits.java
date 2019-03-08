package it.hurts.metallurgy_reforged.integration.mods.conarm;

import c4.conarm.lib.materials.ArmorMaterialType;
import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

public class SetArmorTraits {

	public static void addArmorTrait(Metal metal, Material material){
		switch(metal.getStats().getName()) {
		
			case "quicksilver" : {
				material.addTrait(MetallurgyArmorTraits.quickly);
			}
			break;
			
			case "amordrine" : {
				material.addTrait(MetallurgyArmorTraits.stronglyAmordrine);
			}
			break;
			
			case "kalendrite" : {
				material.addTrait(MetallurgyArmorTraits.stronglyKalendrite);
			}
			break;
			
			case "astral_silver" : {
				material.addTrait(MetallurgyArmorTraits.jumpMaster);
			}
			break;
			
			case "celenegil" : {
				material.addTrait(MetallurgyArmorTraits.resistance);
			}
			break;
			
			case "deep_iron" : {
				material.addTrait(MetallurgyArmorTraits.deeply);
			}
			break;
			
			case "vulcanite" : {
				material.addTrait(MetallurgyArmorTraits.volcano);
			}
			break;
			
			case "adamantine" : {
				material.addTrait(MetallurgyArmorTraits.foodly);
			}
			break;
			
			case "platinum" : {
				material.addTrait(MetallurgyArmorTraits.catEyes, ArmorMaterialType.CORE);
			}
			break;
			
			case "prometheum" : {
				material.addTrait(MetallurgyArmorTraits.prometheum, ArmorMaterialType.PLATES);
			}
			break;
			
			default: {}
			break;
		}
	}
	
}
