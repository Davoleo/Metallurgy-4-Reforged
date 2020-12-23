/*==============================================================================
 = Class: OreDetectorWrapper
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class OreDetectorWrapper implements ICustomCraftingRecipeWrapper {

    private final int size;

    public OreDetectorWrapper(int size)
    {
        this.size = size;
    }

    //Setting the outputs manually in the setRecipe Method because otherwise JEI thinks all the detector items are the same
    // and doesn't loop over the different detectors
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IIngredients ingredients)
    {
        List<ItemStack> outputs = Lists.newArrayList();
        //Set the inputs
        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        for (int i = 1; i <= inputs.size(); i++)
            recipeLayout.getItemStacks().set(i, inputs.get(i - 1));

        ItemStack detector = new ItemStack(ModItems.oreDetector);

        if (size != 0)
        {
            for (int i = 0; i < inputs.get(1).size(); i++)
            {
                List<ItemStack> currentRecipeIngots = Lists.newArrayList();

                //skipping the first cycle because the first slot is taken by the ore detector
                for (int j = 1; j <= size; j++)
                {
                    currentRecipeIngots.add(inputs.get(j).get(i));
                }

                ItemOreDetector.addIngotsToDetector(detector, currentRecipeIngots);
                outputs.add(detector.copy());
            }
        }
        else
        {
            recipeLayout.getItemStacks().addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
                if (!input)
                {
                    tooltip.add(Utils.localize("tooltip.metallurgy.clear_detector_warning"));
                }
            });

            outputs.add(new ItemStack(ModItems.oreDetector));
        }

        //Setting the outputs (0 is the output slot id)
        recipeLayout.getItemStacks().set(0, outputs);
        recipeLayout.setShapeless();

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        //The List of the 9 Lists of possible itemstacks of the recipe
        List<List<ItemStack>> inputs = Lists.newArrayList();

        //Add the detector item in the first slot
        inputs.add(Collections.singletonList(new ItemStack(ModItems.oreDetector)));

        if (size != 0)
        {
            List<ItemStack> metalCombs = Lists.newArrayList();
            ModMetals.metalMap.forEach((name, metal) -> {
                if (!metal.isAlloy())
                {
                    metalCombs.add(new ItemStack(metal.getIngot()));
                }
            });

            for (int i = 0; i < size; i++)
            {
                //offsets the metal list by 1
                Collections.rotate(metalCombs, 1);
                inputs.add(Lists.newArrayList(metalCombs));
            }
        }

        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
    }

}
