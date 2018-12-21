package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.integration.mods.tic.MaterialMetallurgy;
import it.hurts.metallurgy_reforged.integration.mods.tic.MetallurgyMaterials;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Helper : PierKnight100
 * Project: Metallurgy-5
 * Date   : 13 dic 2018
 * Time   : 18:33:14
 *
 ***************************/
public class IntegrationTIC{
	
	public static void preInit() {
		for (Metal metals : ModMetals.metalList) {
			if (checkMaterial(metals)) {
				MaterialMetallurgy material = new MaterialMetallurgy(metals);

				TinkerRegistry.addMaterial(material);
			}

		}
	}

	public static void init() {
		for (Metal metals : ModMetals.metalList) {
			if (checkMaterial(metals)) {
				Material m = TinkerRegistry.getMaterial(metals.getStats().getName());

//				Aggiunge un materiale identificativo per recipe (?)
				m.setRepresentativeItem(metals.getStats().getOreDictName());
				
//				Chiamata al metodo per aggiungere i traits
				MetallurgyMaterials.addTraits(metals, m);
				
//				Aggiunge il melting casting di tutti i fluidi ( aggiunta della possibilità di fare il lingotto ed il blocco )
				TinkerSmeltery.registerOredictMeltingCasting(m.getFluid(), metals.getStats().getOreDictName());
				
//				Aggiunge le varie toolpart
				TinkerSmeltery.registerToolpartMeltingCasting(m);
			}
		}
//		TODO aggiungere un metodo automatico di registrazione delle recipes
		TinkerRegistry.registerAlloy(new FluidStack(ModMetals.BLACK_STEEL.getMolten(), Material.VALUE_Ingot * 4),
				new FluidStack(ModMetals.DEEP_IRON.getMolten(), Material.VALUE_Ingot * 3),
				new FluidStack(ModMetals.INFUSCOLIUM.getMolten(), Material.VALUE_Ingot));

	
	}
	
//	Creato per evitare che vengano aggiunti i nostri liquidi considerando che sono già esistenti nella Tinker Base
	private static boolean checkMaterial(Metal metal) {
		return metal != ModMetals.TIN && metal != ModMetals.COPPER && metal != ModMetals.BRONZE
				&& metal != ModMetals.STEEL && metal != ModMetals.SILVER && metal != ModMetals.ELECTRUM;
	}
}