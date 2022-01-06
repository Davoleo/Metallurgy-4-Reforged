/*==============================================================================
 = Class: AlloyerRecipeWrapper
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei.alloyer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.model.MetalSample;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AlloyerRecipeWrapper implements IRecipeWrapper {

	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public AlloyerRecipeWrapper(List<List<ItemStack>> inputs, ItemStack output)
	{
		this.inputs = inputs;
		this.output = output;
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients)
	{
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	public static List<AlloyerRecipeWrapper> getRecipeInputs()
	{
		ArrayList<AlloyerRecipeWrapper> recipes = new ArrayList<>();

		for (Table.Cell<MetalSample, MetalSample, MetalSample> entry : AlloyerRecipes.getInstance().getRecipeTable().cellSet())
		{
			List<ItemStack> inputList1 = ImmutableList.copyOf(entry.getRowKey().getOredictedStacks());
			List<ItemStack> inputList2 = ImmutableList.copyOf(entry.getColumnKey().getOredictedStacks());

			List<List<ItemStack>> inputs = ImmutableList.of(inputList1, inputList2);
			recipes.add(new AlloyerRecipeWrapper(inputs, entry.getValue().getStack()));
		}

		return recipes;
	}

	@Override
	public boolean handleClick(@Nonnull Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
	{
		return false;
	}

}
