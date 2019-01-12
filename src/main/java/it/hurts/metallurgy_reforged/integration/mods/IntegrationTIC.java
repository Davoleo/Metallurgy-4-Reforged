package it.hurts.metallurgy_reforged.integration.mods;


import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.integration.mods.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.integration.mods.tic.trait.SetTrait;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.recipe.BlockAlloyerRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Helper : PierKnight100, Davoleo
 * Project: Metallurgy-5
 * Date   : 13 dic 2018
 * Time   : 18:33:14
 *
 ***************************/
public class IntegrationTIC{
	
	public static void preInit() {
		for (Metal metals : ModMetals.metalList) {
			if (checkMaterial(metals)) {
				TiCMaterial material = new TiCMaterial(metals);

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
				SetTrait.addTraits(metals, m);
				
//				Aggiunge il melting casting di tutti i fluidi ( aggiunta della possibilità di fare il lingotto ed il blocco )
				TinkerSmeltery.registerOredictMeltingCasting(m.getFluid(), metals.getStats().getOreDictName());
				
//				Aggiunge le varie toolpart
				TinkerSmeltery.registerToolpartMeltingCasting(m);
			}
	    }
	}

	public static void postInit() {
		for(Table.Cell<ItemStack, ItemStack, ItemStack> entry : BlockAlloyerRecipes.getInstance().getRecipeTable().cellSet())
		{

			FluidStack output = getFluidFromIngot(entry.getValue());
			FluidStack input1 = getFluidFromIngot(entry.getRowKey());
			FluidStack input2 = getFluidFromIngot(entry.getColumnKey());
			if(output != null && input1 != null && input2 != null && output.getFluid() != TinkerFluids.bronze && output.getFluid() != TinkerFluids.electrum)
					TinkerRegistry.registerAlloy(output,input1,input2);

		}
		System.out.println("Tinker Smeltery Recipes for Metallurgy Loaded");
	}

	public static FluidStack getFluidFromIngot(ItemStack item)
	{
		int c = item.getCount();
		MeltingRecipe recipe = TinkerRegistry.getMelting(item);
		return recipe != null ? new FluidStack(recipe.output.getFluid(),(c <= 0 ? 1 : c) * Material.VALUE_Ingot) : null;
	}

//	Creato per evitare che vengano aggiunti i nostri liquidi considerando che sono già esistenti nella Tinker Base
	private static boolean checkMaterial(Metal metal) {
		return metal != ModMetals.TIN && metal != ModMetals.COPPER && metal != ModMetals.BRONZE
				&& metal != ModMetals.STEEL && metal != ModMetals.SILVER && metal != ModMetals.ELECTRUM
				&& metal != ModMetals.ZINC && metal != ModMetals.BRASS;
	}
}