/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CrusherRecipeWrapper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.jei.crusher;

import it.hurts.metallurgy_reforged.recipe.CrusherRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CrusherRecipeWrapper implements IRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;

	public CrusherRecipeWrapper(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}

	public static List<CrusherRecipeWrapper> getRecipeInputs()
	{
		ArrayList<CrusherRecipeWrapper> recipes = new ArrayList<>();

		CrusherRecipes.getInstance().getRecipeMap().forEach((input, output) -> {
			recipes.add(new CrusherRecipeWrapper(input, output));
		});

		return recipes;
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients)
	{
		ingredients.setInput(VanillaTypes.ITEM, input);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
	{
		return false;
	}

}
