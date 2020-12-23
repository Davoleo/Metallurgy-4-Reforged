/*==============================================================================
 = Class: AlloyerRecipeWrapper
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei.alloyer;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.model.AlloySample;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
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

        for (Table.Cell<AlloySample, AlloySample, AlloySample> entry : AlloyerRecipes.getInstance().getRecipeTable().cellSet())
        {
            List<ItemStack> inputList1 = getInputList(entry.getRowKey().getStack());
            List<ItemStack> inputList2 = getInputList(entry.getColumnKey().getStack());

            List<List<ItemStack>> inputs = Lists.newArrayList(inputList1, inputList2);
            recipes.add(new AlloyerRecipeWrapper(inputs, entry.getValue().getStack()));
        }

        return recipes;
    }

    /**
     * @param originalStack The stack from which this method takes oreDict IDs and names
     * @param originalStack The stack from which this method takes oreDict IDs and names
     * @return a list of all the oredict sibling stacks of the originalStack
     */
    private static List<ItemStack> getInputList(ItemStack originalStack)
    {
        List<ItemStack> inputList = NonNullList.create();

        for (int id : OreDictionary.getOreIDs(originalStack))
        {
            List<ItemStack> input = OreDictionary.getOres(OreDictionary.getOreName(id));
            input.forEach(stack ->
            {
                ItemStack copy = stack.copy();
                copy.setCount(originalStack.getCount());
                inputList.add(copy);
            });
        }
        if (inputList.isEmpty())
            return Collections.singletonList(originalStack);
        else
            return inputList;
    }

    @Override
    public boolean handleClick(@Nonnull Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
    {
        return false;
    }

}
