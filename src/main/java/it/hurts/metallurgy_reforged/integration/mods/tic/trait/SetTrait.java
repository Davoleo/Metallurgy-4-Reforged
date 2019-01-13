package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

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
		
			case "midasium" : {
//				TODO magari aumentare il modifiers
				material.addTrait(writable2, EXTRA);
				material.addTrait(writable2);
			}
			break;
			
			case "vulcanite":{
//				Add autosmelt to tool and Fire Aspect to Sword Head
			}
			break;
			
			case "eximite":{
				material.addTrait(enderference, HEAD);
				material.addTrait(enderference);
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
//				TODO Reinforced II
			}
			break;
			
			case "shadow_iron":{
//				TODO Create Blindness Traits and add it
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
			
			default:{}
			break;	
		}	
	}
}
