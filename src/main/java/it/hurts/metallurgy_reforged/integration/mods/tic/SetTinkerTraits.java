/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SetTrait
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.tools.TinkerTraits;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

public class SetTinkerTraits {

	public static void addTraits(Metal metal, Material material){

		switch(metal.getStats().getName()) {
//		TraitSuperheat = Increased damage to enemies that are on fire
		
			case "midasium" : {
//				TODO magari aumentare il modifiers
				material.addTrait(writable2);
				material.addTrait(MetallurgyTinkerTraits.duplicaitonTrait);
			}
			break;
			
			case "ignatius":{
				material.addTrait(MetallurgyTinkerTraits.vulcanTrait, HEAD);
			}
			break;
			
			case "vulcanite":{
				material.addTrait(MetallurgyTinkerTraits.vulcanTrait1, HEAD);
				material.addTrait(superheat, HANDLE);
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
				material.addTrait(MetallurgyTinkerTraits.obscureTrait);
			}
			break;
			
			case "sanguinite":{
				material.addTrait(MetallurgyTinkerTraits.lifeStealTrait, HEAD);
				material.addTrait(MetallurgyTinkerTraits.lifeStealTrait, HANDLE);
			}
			break;
			
			case "tartarite":{
				material.addTrait(MetallurgyTinkerTraits.witherTrait, HEAD);
			}
			break;
			
			case "alduorite":{
//				Rompe pi� velocementi i blocchi di harvest level inferiore al proprio
				material.addTrait(TinkerTraits.unnatural, EXTRA);
				material.addTrait(TinkerTraits.unnatural, HEAD);
			}
			break;
			
			case "desichalkos":{
				material.addTrait(MetallurgyTinkerTraits.kingDiceTrait, EXTRA);
			}
			break;
			
			default:{}
			break;	
		}	
	}
}
