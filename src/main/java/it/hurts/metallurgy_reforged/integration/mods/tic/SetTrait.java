package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTraits;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 12 gen 2019
 * Time   : 17:12:09
 *
 ***************************/
public class SetTrait {

	public static void addTraits(Metal metal, Material material){
		
		switch(metal.getStats().getName()) {
//		TraitSuperheat = Incrementa il danno in caso il subente è incendiato
		
			case "midasium" : {
//				TODO magari aumentare il modifiers
				material.addTrait(writable2);
			}
			break;
			
			case "ignatius":{
				material.addTrait(MetallurgyTraits.vulcanTrait, HEAD);
			}
			break;
			
			case "vulcanite":{
				material.addTrait(MetallurgyTraits.vulcanTrait1, HEAD);
				material.addTrait(superheat, EXTRA);
			}
			break;
			
			case "eximite":{
				material.addTrait(enderference, HEAD);
			}
			break;
			
			case "vyroxeres":{
				material.addTrait(poisonous);
			}
			break;
			
			case "mithril":{
//				TODO Glowing Modifiers
			}
			break;
			
			case "damascus_steel":{
				material.addTrait(duritos);
//				TODO Reinforced II
			}
			break;
			
			case "shadow_iron":{
				material.addTrait(MetallurgyTraits.obscureTrait);
			}
			break;
			
			case "sanguinite":{
//				TODO Create Vampirism effect and add it ( ModGlowing )
			}
			break;
			
			case "tartarite":{
//				TODO Withering II
			}
			break;
			
			case "alduorite":{
//				Rompe più velocementi i blocchi di harvest level inferiore al proprio
				material.addTrait(TinkerTraits.unnatural, EXTRA);
				material.addTrait(TinkerTraits.unnatural, HEAD);
			}
			break;
			
			default:{}
			break;	
		}	
	}
}
