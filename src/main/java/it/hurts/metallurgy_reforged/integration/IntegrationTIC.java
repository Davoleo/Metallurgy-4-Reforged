package it.hurts.metallurgy_reforged.integration;


import com.google.common.collect.Table;

import it.hurts.metallurgy_reforged.integration.mods.tic.MaterialMetallurgy;
import it.hurts.metallurgy_reforged.integration.mods.tic.MetallurgyMaterials;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.recipe.BlockAlloyerRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
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
		
		for(Table.Cell<ItemStack, ItemStack, ItemStack> entry : BlockAlloyerRecipes.getInstance().getRecipeTable().cellSet()){
			FluidStack output = getFluidFromIngot(entry.getValue());
			FluidStack input1 = getFluidFromIngot(entry.getRowKey());
			FluidStack input2 = getFluidFromIngot(entry.getColumnKey());
			
			if(output != null && input1 != null && input2 != null) {
				TinkerRegistry.registerAlloy(output,input1,input2);
			}
	    }
		System.out.println("Tinker Smeltery Recipes for Metallurgy Loaded");
	}
	
	public static FluidStack getFluidFromIngot(ItemStack item)
    {
        Fluid output = null;

        for (Metal metal : ModMetals.metalList) {
            if(metal.getIngot().getRegistryName().equals(item.getItem().getRegistryName()) && checkMaterial(metal))
                output = metal.getMolten();
        }
        
        if(item.getItem() == ModMetals.BRONZE.getIngot())
            output = TinkerFluids.bronze;
        if(item.getItem() == ModMetals.TIN.getIngot())
            output = TinkerFluids.tin;
        if(item.getItem() == ModMetals.STEEL.getIngot())
            output = TinkerFluids.steel;
        if(item.getItem() == ModMetals.COPPER.getIngot())
            output = TinkerFluids.copper;
        if(item.getItem() == ModMetals.ELECTRUM.getIngot())
            output = TinkerFluids.electrum;
        if(item.getItem() == ModMetals.SILVER.getIngot())
            output = TinkerFluids.silver;    
        if(item.getItem() == Items.IRON_INGOT)
            output = TinkerFluids.iron;
        if(item.getItem() == Items.GOLD_INGOT)
            output = TinkerFluids.gold;
        
        int c = item.getCount();
        
            
        return output != null ? new FluidStack(output, (c <= 0 ? 1 : c) *  Material.VALUE_Ingot) : null;
    }
	
//	Creato per evitare che vengano aggiunti i nostri liquidi considerando che sono già esistenti nella Tinker Base
	private static boolean checkMaterial(Metal metal) {
		return metal != ModMetals.TIN && metal != ModMetals.COPPER && metal != ModMetals.BRONZE
				&& metal != ModMetals.STEEL && metal != ModMetals.SILVER && metal != ModMetals.ELECTRUM
				&& metal != ModMetals.ZINC && metal != ModMetals.BRASS;
	}
}