/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ShapedMetalRecipeWrapper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.jei;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.recipe.ShapedMetalRecipe;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class ShapedMetalRecipeWrapper extends MetalRecipeWrapper implements IShapedCraftingRecipeWrapper {

	public ShapedMetalRecipeWrapper(Metal metal, ShapedMetalRecipe recipe, IJeiHelpers helper)
	{
		super(metal, recipe, helper);
	}

	@Override
	public int getWidth()
	{
		return ((IShapedRecipe) recipe).getRecipeWidth();
	}

	@Override
	public int getHeight()
	{
		return ((IShapedRecipe) recipe).getRecipeHeight();
	}

}
