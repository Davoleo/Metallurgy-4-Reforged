/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationTIC
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic;

import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.integration.mods.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.BucketCastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.Arrays;
import java.util.List;

public class IntegrationTIC {

	public static List<?> blacklistedMaterials = Arrays.asList(GeneralConfig.tinkerMaterialsBlacklist);

	public static void preInit()
	{
		for (Metal metal : ModMetals.metalList)
		{
			if (checkMaterial(metal) && checkMaterialPreInit(metal) && !blacklistedMaterials.contains(metal.toString()))
			{
				TiCMaterial material = new TiCMaterial(metal);

				TinkerRegistry.addMaterial(material);
			}
		}
	}

	public static void init()
	{
		for (Metal metal : ModMetals.metalList)
		{
			if (checkMaterial(metal) && !blacklistedMaterials.contains(metal.toString()))
			{
				Material m = TinkerRegistry.getMaterial(metal.getStats().getName());

				//				Chiamata al metodo per aggiungere i traits
				SetTinkerTraits.addTraits(metal, m);

				//				Aggiunge il melting casting di tutti i fluidi ( aggiunta della possibilit� di fare il lingotto ed il blocco )
				if (m.getFluid() == null)
					m.setFluid(metal.getMolten());
				TinkerSmeltery.registerOredictMeltingCasting(m.getFluid(), metal.getStats().getOreDictName());

				//				Aggiunge le varie toolpart
				TinkerSmeltery.registerToolpartMeltingCasting(m);
			}
		}

		TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(ModItems.dustThermite, 1000), ModFluids.THERMITE, 400));
		TinkerRegistry.registerTableCasting(new BucketCastingRecipe(Items.BUCKET));
	}

	public static void postInit()
	{
		for (Table.Cell<ItemStack, ItemStack, ItemStack> entry : AlloyerRecipes.getInstance().getRecipeTable().cellSet())
		{
			FluidStack output = getFluidFromIngot(entry.getValue());
			FluidStack input1 = getFluidFromIngot(entry.getRowKey());
			FluidStack input2 = getFluidFromIngot(entry.getColumnKey());
			if (output != null && input1 != null && input2 != null && output.getFluid() != TinkerFluids.bronze && output.getFluid() != TinkerFluids.electrum)
				TinkerRegistry.registerAlloy(output, input1, input2);
		}
		Metallurgy.logger.info("Tinker Smeltery Recipes for Metallurgy Loaded");

		MetallurgyTinkerFuels.init();
	}

	public static FluidStack getFluidFromIngot(ItemStack stack)
	{

		MeltingRecipe recipe = TinkerRegistry.getMelting(stack);
		Item item = stack.getItem();
		Fluid fluid = recipe != null ? recipe.getResult().getFluid() : null;

		if (Items.IRON_INGOT.equals(item))
			fluid = TinkerFluids.iron;
		else if (Items.GOLD_INGOT.equals(item))
			fluid = TinkerFluids.gold;
		else if (ModMetals.COPPER.getIngot().equals(item))
			fluid = TinkerFluids.copper;
		else if (ModMetals.BRONZE.getIngot().equals(item))
			fluid = TinkerFluids.bronze;
		else if (ModMetals.ZINC.getIngot().equals(item))
			fluid = TinkerFluids.zinc;
		else if (ModMetals.TIN.getIngot().equals(item))
			fluid = TinkerFluids.tin;
		else if (ModMetals.SILVER.getIngot().equals(item))
			fluid = TinkerFluids.silver;
		else if (ModMetals.STEEL.getIngot().equals(item))
			fluid = TinkerFluids.steel;


		int c = stack.getCount();
		return fluid != null ? new FluidStack(fluid, (c <= 0 ? 1 : c) * Material.VALUE_Ingot) : null;
	}

	//	Creato per evitare che vengano aggiunti i nostri liquidi considerando che sono gi� esistenti nella Tinker Base
	public static boolean checkMaterial(Metal metal)
	{
		return metal != ModMetals.TIN && metal != ModMetals.COPPER && metal != ModMetals.BRONZE
				&& metal != ModMetals.STEEL && metal != ModMetals.SILVER && metal != ModMetals.ELECTRUM
				&& metal != ModMetals.ZINC && metal != ModMetals.BRASS;
	}

	public static boolean checkMaterialPreInit(Metal metal)
	{
		return TinkerRegistry.getMaterial(metal.toString()).equals(Material.UNKNOWN);
	}

}