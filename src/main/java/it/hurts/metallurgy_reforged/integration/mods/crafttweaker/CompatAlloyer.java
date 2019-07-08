/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CompatAlloyer
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.crafttweaker;

import com.google.common.collect.Table;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import it.hurts.metallurgy_reforged.integration.mods.IntegrationCT;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static it.hurts.metallurgy_reforged.recipe.BlockAlloyerRecipes.getInstance;

@ZenClass("mods.metallurgyreforged.Alloyer")
public class CompatAlloyer {

    @ZenMethod
    public static void addRecipe(IIngredient input1, IIngredient input2, IItemStack output)
    {
        CraftTweakerAPI.apply(new Add(input1, input2, output));
    }

    public static class Add implements IAction {

        private IIngredient input1;
        private IIngredient input2;
        private IItemStack output;

        Add(IIngredient input1, IIngredient input2, IItemStack output)
        {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
        }

        @Override
        public void apply()
        {
            ItemStack[] inputStacks1 = IntegrationCT.toStacks(input1.getItemArray());
            ItemStack[] inputStacks2 = IntegrationCT.toStacks(input2.getItemArray());
            ItemStack outputStack = IntegrationCT.toStack(output);

            for (ItemStack inputStack1 : inputStacks1)
            {
                for (ItemStack inputStack2 : inputStacks2)
                {
                    getInstance().getRecipeTable().put(inputStack1, inputStack2, outputStack);
                }
            }
        }

        @Override
        public String describe()
        {
            return "Adding Metallurgy-Reforged Alloyer Recipe for " + output.getDisplayName();
        }
    }

    @ZenMethod
    public static void removeRecipe(IItemStack output)
    {
        CraftTweakerAPI.apply(new Remove(output));
    }

    private static class Remove implements IAction {

        private IItemStack output;

        Remove(IItemStack output)
        {
            this.output = output;
        }

        @Override
        public void apply()
        {
            ItemStack outputStack = IntegrationCT.toStack(output);

            for (Table.Cell<ItemStack, ItemStack, ItemStack> recipe : getInstance().getRecipeTable().cellSet())
            {
                if (recipe.getValue() == outputStack)
                {
                    getInstance().getRecipeTable().remove(recipe.getRowKey(), recipe.getColumnKey());
                }
            }
        }

        @Override
        public String describe()
        {
            return "Removing Metallurgy-Reforged Alloyer Recipe for " + output.getDisplayName();
        }
    }

}
