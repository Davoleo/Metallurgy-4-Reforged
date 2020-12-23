/*==============================================================================
 = Class: MetalRecipeWrapper
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.recipe.IRecipeMetal;
import it.hurts.metallurgy_reforged.recipe.IngredientMetal;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MetalRecipeWrapper implements IRecipeWrapper {

    private Metal metal;
    protected IRecipeMetal recipe;
    private IJeiHelpers helper;

    public MetalRecipeWrapper(Metal metal, IRecipeMetal recipe, IJeiHelpers helper)
    {
        this.metal = metal;
        this.recipe = recipe;
        this.helper = helper;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        //Get the inputs of the recipe
        NonNullList<Ingredient> inputs = recipe.getIngredients();

        List<List<ItemStack>> inputStacks = new ArrayList<>();

        inputs.forEach(ingredient -> {
            if (ingredient instanceof IngredientMetal)
            {
                inputStacks.add(((IngredientMetal) ingredient).getOreDictStacks(metal));
            }
            else
            {
                inputStacks.add(helper.getStackHelper().toItemStackList(ingredient));
            }
        });

        ingredients.setInputLists(VanillaTypes.ITEM, inputStacks);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutputFromMetal(metal));
    }

}
