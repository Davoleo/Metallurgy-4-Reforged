/*==============================================================================
 = Class: ModRecipes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.recipe;

import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.RegistrationConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class ModRecipes {

	private static final String GENERATED_RECIPES_PATH = "/assets/metallurgy/recipes/generated";

	/**
	 * Renames Recipes of unregistered items to *.json.disabled to stop Forge from loading them<br>
	 * Called during pre-init (before json recipes are actually loaded)
	 *
	 * @see net.minecraftforge.common.crafting.CraftingHelper#loadRecipes(ModContainer)
	 * @deprecated Won't work when the mod is packaged in a zipped jar archive
	 */
	@Deprecated
	@SuppressWarnings({ "JavadocReference", "ConstantConditions" })
	public static void checkForUnboundRecipes()
	{
		Path blockRecipesPath = Utils.getPath(GENERATED_RECIPES_PATH + "/block");
		Path itemRecipesPath = Utils.getPath(GENERATED_RECIPES_PATH + "/item");
		Path handMadeBlockRecipes = Utils.getPath("/assets/metallurgy/recipes/block");

		if (blockRecipesPath != null && itemRecipesPath != null)
		{
			//Should never happen anyways (Avoids NPE on the next for loops)
			if (!blockRecipesPath.toFile().isDirectory() || !itemRecipesPath.toFile().isDirectory() || !handMadeBlockRecipes.toFile().isDirectory())
				return;

			///- Generated Block Recipes -///
			for (File recipe : blockRecipesPath.toFile().listFiles())
			{
				for (BlockTypes type : BlockTypes.values())
				{
					if (recipe.getName().startsWith(type.getPrefix()))
						enableDisableJsonRecipe(recipe, type.isEnabled());
				}
			}

			///- Generated Item Recipes -///
			for (File itemFile : itemRecipesPath.toFile().listFiles())
			{
				//Check subdirectories
				if (itemFile.isDirectory())
				{
					//Armor Recipes
					if (itemFile.getName().equals("armor"))
					{
						Arrays.stream(itemFile.listFiles())
								.forEach(armor -> enableDisableJsonRecipe(itemFile, RegistrationConfig.categoryItems.enableMetalArmorSets));
					}

					//Tool Recipes
					if (itemFile.getName().equals("tool"))
					{
						for (File toolRecipe : itemFile.listFiles())
						{
							if (toolRecipe.getName().startsWith("axe_"))
								enableDisableJsonRecipe(toolRecipe, RegistrationConfig.categoryItems.enableMetalAxes);
							else if (toolRecipe.getName().startsWith("hoe_"))
								enableDisableJsonRecipe(toolRecipe, RegistrationConfig.categoryItems.enableMetalHoes);
							else if (toolRecipe.getName().startsWith("pickaxe_"))
								enableDisableJsonRecipe(toolRecipe, RegistrationConfig.categoryItems.enableMetalPickaxes);
							else if (toolRecipe.getName().startsWith("shovel_"))
								enableDisableJsonRecipe(toolRecipe, RegistrationConfig.categoryItems.enableMetalShovels);
							else if (toolRecipe.getName().startsWith("sword_"))
								enableDisableJsonRecipe(toolRecipe, RegistrationConfig.categoryItems.enableMetalSwords);
						}
					}

					//Dust Alloy Recipes
					if (itemFile.getName().equals("dust"))
					{
						Arrays.stream(itemFile.listFiles())
								.forEach(dustAlloy -> enableDisableJsonRecipe(dustAlloy, RegistrationConfig.categoryItems.enableMetalDusts));
					}

					//Midasium Dust + Other Metal Dust -> Gold Dust
					if (itemFile.getName().equals("compat"))
					{
						Arrays.stream(itemFile.listFiles())
								.forEach(midToGoldRecipe -> enableDisableJsonRecipe(midToGoldRecipe, RegistrationConfig.categoryItems.enableMetalDusts));
					}

					if (itemFile.getName().equals("shapeless"))
					{
						for (File shapelessRecipe : itemFile.listFiles())
						{
							//Ingot -> Nuggets
							if (shapelessRecipe.getName().startsWith("nugget_shapeless_"))
								enableDisableJsonRecipe(shapelessRecipe, RegistrationConfig.categoryItems.enableMetalNuggets);
							else if (shapelessRecipe.getName().startsWith("ingot_shapeless"))    //Raw Blocks -> Ingot
								enableDisableJsonRecipe(shapelessRecipe, RegistrationConfig.categoryBlocks.enableRawMetalBlocks);
						}
					}
				}
				else if (itemFile.isFile())
				{
					//Nugget -> Ingots
					enableDisableJsonRecipe(itemFile, RegistrationConfig.categoryItems.enableMetalNuggets);
				}
			}

			// TODO: 13/04/2021 There certainly are other handmade json recipes that use items that can be disabled but can't be bothered to add them now
			///- HandMade Block Recipes -///
			for (File recipe : handMadeBlockRecipes.toFile().listFiles())
			{

				//Gold and Iron decoblocks
				if (recipe.getName().startsWith("gold_") || recipe.getName().startsWith("iron_"))
				{
					for (BlockTypes type : BlockTypes.values())
					{
						if (recipe.getName().substring(5).startsWith(type.getPrefix()))
							enableDisableJsonRecipe(recipe, type.isEnabled());
					}
				}
			}
		}
	}

	private static void enableDisableJsonRecipe(File recipe, boolean enabled)
	{
		String newPath = "";

		if (recipe.toString().endsWith(".json") && !enabled)
			newPath = recipe.toString() + ".disabled";

		if (recipe.toString().endsWith(".disabled") && enabled)
			newPath = recipe.toString().replace(".disabled", "");

		if (newPath.equals(""))
			return;

		recipe.renameTo(new File(newPath));
	}

	public static void initFurnaceRecipes()
	{

        //Furnace Recipes
        ModMetals.metalMap.forEach((name, metal) -> {
            if (metal.getOre() != null)
            {
                GameRegistry.addSmelting(metal.getOre(), new ItemStack(metal.getIngot()), 1F);
            }
            if (RegistrationConfig.categoryItems.enableMetalDusts)
                GameRegistry.addSmelting(metal.getDust(), new ItemStack(metal.getIngot()), 0.6F);
        });

        //Dust2Ingot
        GameRegistry.addSmelting(ModItems.dustIron, new ItemStack(Items.IRON_INGOT), 0.6F);
        GameRegistry.addSmelting(ModItems.dustGold, new ItemStack(Items.GOLD_INGOT), 0.6F);

        //Ore2Material
        GameRegistry.addSmelting(ModBlocks.oreTar, new ItemStack(ModItems.tar), 0.5F);
        GameRegistry.addSmelting(ModBlocks.orePhosphorite, new ItemStack(ModItems.phosphorus), 0.5F);
        GameRegistry.addSmelting(ModBlocks.orePotash, new ItemStack(ModItems.potash), 0.5F);
        GameRegistry.addSmelting(ModBlocks.oreSulfur, new ItemStack(ModItems.sulfur), 0.5F);
    }

}
