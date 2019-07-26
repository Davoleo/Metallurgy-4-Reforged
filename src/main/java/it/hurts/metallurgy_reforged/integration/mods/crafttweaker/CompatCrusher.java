/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CompatCrusher
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import it.hurts.metallurgy_reforged.integration.mods.IntegrationCT;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static it.hurts.metallurgy_reforged.recipe.BlockCrusherRecipes.getInstance;

@ZenClass("mods.metallurgyreforged.Crusher")
public class CompatCrusher {

    @ZenMethod
    public static void addRecipe(IIngredient input, IItemStack output)
    {
        CraftTweakerAPI.apply(new Add(input, output));
    }

    @ZenMethod
    public static void addRecipe(IIngredient input, IItemStack output, float xp)
    {
        CraftTweakerAPI.apply(new Add(input, output));
    }

    private static class Add implements IAction {

        private IIngredient input;
        private IItemStack output;
        private float xp;

        Add(IIngredient input, IItemStack output)
        {
            this(input, output, 0F);
        }

        Add(IIngredient input, IItemStack output, float xp)
        {
            this.input = input;
            this.output = output;
            this.xp = xp;
        }

        @Override
        public void apply()
        {
            ItemStack[] inputStacks = IntegrationCT.toStacks(input.getItemArray());
            ItemStack outputStack = IntegrationCT.toStack(output);

            for (ItemStack inputStack : inputStacks)
                getInstance().addCrushingRecipe(inputStack, outputStack, xp);
        }

        @Override
        public String describe()
        {
            return "Adding Metallurgy-Reforged Crusher Recipe for " + output.getDisplayName();
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
            getInstance().removeCrushingRecipe(IntegrationCT.toStack(output));
        }

        @Override
        public String describe()
        {
            return "Removing Metallurgy-Reforged Crusher Recipe for " + output.getDisplayName();
        }
    }

}
