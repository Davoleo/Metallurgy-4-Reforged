package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import static slimeknights.tconstruct.library.materials.MaterialTypes.*;
import static slimeknights.tconstruct.tools.TinkerTraits.*;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
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
		if(metal == ModMetals.MIDASIUM) {
			material.addTrait(writable2, EXTRA);
			material.addTrait(writable2);
		}else if(metal == ModMetals.VULCANITE)
			material.addTrait(autosmelt);
	}
}
