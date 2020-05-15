/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OreDetectorWrapper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.jei;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.ModMetals;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class OreDetectorWrapper implements ICraftingRecipeWrapper {

	private int size;

	public OreDetectorWrapper(int size)
	{
		this.size = size;
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients)
	{
		//The List of the 9 Lists of possible itemstacks of the recipe
		List<List<ItemStack>> inputs = Lists.newArrayList();
		List<ItemStack> outputs = Lists.newArrayList();

		//Add the detector item in the first slot
		inputs.add(Collections.singletonList(new ItemStack(ModItems.oreDetector)));

		List<ItemStack> metalCombs = Lists.newArrayList();
		ModMetals.metalMap.forEach((name, metal) -> metalCombs.add(new ItemStack(metal.getIngot())));

		for (int i = 0; i < size; i++)
		{
			//offsets the metal list by 1
			Collections.rotate(metalCombs, 1);
			inputs.add(metalCombs);
		}

		for (int i = 0; i < metalCombs.size(); i++)
		{
			ItemStack detector = new ItemStack(ModItems.oreDetector);

			List<ItemStack> currentRecipeIngots = Lists.newArrayList();

			//skipping the first cycle because the first slot is taken by the ore detector
			for (int j = 1; j <= size; j++)
			{
				currentRecipeIngots.add(inputs.get(j).get(i));
			}

			ItemOreDetector.addIngotsToDetector(detector, currentRecipeIngots);
			outputs.add(detector);
		}

		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);

	}

}
