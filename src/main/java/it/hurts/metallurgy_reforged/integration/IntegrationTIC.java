package it.hurts.metallurgy_reforged.integration;

import java.util.Map;

import com.google.common.collect.Maps;

import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 13 dic 2018
 * Time   : 18:33:14
 *
 ***************************/
public class IntegrationTIC{
	
	private final static int B = 1296;
	private final static int I = 144;
	private static Block block;
	private static Item ingot;
	private static Item dust;
	private static FluidMolten molten;

	public static void init() {
		for(Metal metals : ModMetals.metalList) {
			if(TinkerRegistry.getMaterial(metals.getStats().getName()).equals(Material.UNKNOWN)) {
				block = metals.getBlock();
				molten = metals.getMolten();
				ingot = metals.getIngot();
				dust = metals.getDust();
				
				Material material = new Material(metals.getStats().getName(), 0xFFEF0000);
				material.setFluid(molten);
//				Permette di fare i vari tool
				material.setCastable(true);
				material.setRepresentativeItem(ingot);
//				Materiale di riparazione
				material.addItem(ingot);
				
				TinkerRegistry.addMaterial(material);
				TinkerSmeltery.registerToolpartMeltingCasting(material);
				TinkerRegistry.registerMelting(block, molten, B);
				TinkerRegistry.registerMelting(ingot, molten, I);
				TinkerRegistry.registerMelting(dust, molten, I);
				
//				TODO fare in modo di automatizzare le Recipes, aumentare il tempo di cottura in base alla durevolezza dell'ore. (Blocco metallico = 200 + (durevolezzaBlocco*2) * 9)
//				TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Blocks.ICE, bucket), water, 305));
				getRecipe(block, ingot, dust, molten);
			}
		}
	}
	
	public static void getRecipe(Block block, Item ingot, Item dust, FluidMolten molten) {
		TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(block), molten, 769));
		TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(ingot), molten, 534));
		TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(dust), molten, 490));
	}
	
//	TODO Creare un metodo che in base alla durevolezza dell'ore ti restituisce la temperatura necessaria
//	public static int getTemperature() {
//		return (int)MetalStats.getBlockBlastResistance()*2+200;
//	}
}
