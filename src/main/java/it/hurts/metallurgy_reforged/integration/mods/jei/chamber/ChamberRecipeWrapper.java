/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ChamberRecipeWrapper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.jei.chamber;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ChamberRecipeWrapper implements IRecipeWrapper {

	private ItemStack input;

	public ChamberRecipeWrapper(ItemStack input)
	{
		this.input = input;
	}

	public ItemStack getInput()
	{
		return input;
	}

	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInput(VanillaTypes.ITEM, input);
	}

}
