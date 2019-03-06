package it.hurts.metallurgy_reforged.integration.mods.conarm;

import it.hurts.metallurgy_reforged.material.Metal;
import slimeknights.tconstruct.library.materials.Material;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 5 mar 2019
 * Time   : 21:12:42
 *
 ****************************/
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
