/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationTIC
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.tic;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.integration.tic.material.TiCMaterial;
import it.hurts.metallurgy_reforged.item.ItemMetal;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.ItemUtils;
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
import slimeknights.tconstruct.tools.ranged.item.CrossBow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegrationTIC {

	public static List<?> blacklistedMaterials = Arrays.asList(GeneralConfig.tinkerMaterialsBlacklist);
	private static final List<Metal> vanillaTicMetals = new ArrayList<>();

	static
	{
		vanillaTicMetals.add(ModMetals.COPPER);
		vanillaTicMetals.add(ModMetals.BRONZE);
		vanillaTicMetals.add(ModMetals.ZINC);
		vanillaTicMetals.add(ModMetals.TIN);
		vanillaTicMetals.add(ModMetals.SILVER);
		vanillaTicMetals.add(ModMetals.STEEL);
		vanillaTicMetals.add(ModMetals.ELECTRUM);
	}

	public static void preInit()
	{
		ModMetals.metalMap.forEach((name, metal) -> {

			if (checkMaterial(metal) && checkMaterialPreInit(name) && !blacklistedMaterials.contains(name))
			{
				TiCMaterial material = new TiCMaterial(metal);
				TinkerRegistry.addMaterial(material);
			}
		});
	}

	public static void init()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			if (checkMaterial(metal) && !blacklistedMaterials.contains(name))
			{
				Material m = TinkerRegistry.getMaterial(metal.getStats().getName());

				//Add custom traits to TiCon Tools
				SetTinkerTraits.addTraits(metal, m);

				//Add molten fluid cast to ingots, blocks, nuggets and so on...
				if (m.getFluid() == null)
					m.setFluid(metal.getMolten());
				TinkerSmeltery.registerOredictMeltingCasting(m.getFluid(), CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name));

				//register different Tool parts for each material
				TinkerSmeltery.registerToolpartMeltingCasting(m);
			}
		});

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
		else if (stack.getItem() instanceof ItemMetal)
		{
			Metal metal = ItemUtils.getMetalFromItem(stack.getItem());
			if (vanillaTicMetals.contains(metal))
			{
				try
				{
					fluid = ((Fluid) TinkerFluids.class.getDeclaredField(metal.toString()).get(TinkerFluids.class));
				}
				catch (NoSuchFieldException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		}


		int c = stack.getCount();
		return fluid != null ? new FluidStack(fluid, (c <= 0 ? 1 : c) * Material.VALUE_Ingot) : null;
	}

	//Makes sure we don't register Molten Fluids that are registered by default in TiCon
	public static boolean checkMaterial(Metal metal)
	{
		return !vanillaTicMetals.contains(metal);
	}

	/**
	 * Makes sure the molten material we are registering is not already assigned to another mod's material (prevents the "material already registered" crash)
	 *
	 * @param material The checked material name
	 *
	 * @return true if the metal hasn't been registered yet
	 */
	public static boolean checkMaterialPreInit(String material)
	{
		return TinkerRegistry.getMaterial(material).equals(Material.UNKNOWN);
	}

	public static boolean isCrossbow(Item item)
	{
		return item instanceof CrossBow;
	}

}