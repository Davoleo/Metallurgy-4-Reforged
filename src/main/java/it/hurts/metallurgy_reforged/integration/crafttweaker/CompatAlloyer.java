/*==============================================================================
 = Class: CompatAlloyer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static it.hurts.metallurgy_reforged.recipe.AlloyerRecipes.getInstance;

@ZenClass("mods.metallurgyreforged.Alloyer")
public class CompatAlloyer {

    @ZenMethod
    public static void addRecipe(IIngredient input1, IIngredient input2, IItemStack output)
    {
        CraftTweakerAPI.apply(new Add(input1, input2, output));
    }

    @ZenMethod
    public static void addRecipe(IIngredient input1, IIngredient input2, IItemStack output, float xp)
    {
        CraftTweakerAPI.apply(new Add(input1, input2, output, xp));
    }

    public static class Add implements IAction {

        private IIngredient input1;
        private IIngredient input2;
        private IItemStack output;
        private float xp;

        Add(IIngredient input1, IIngredient input2, IItemStack output)
        {
            this(input1, input2, output, 0F);
        }

        Add(IIngredient input1, IIngredient input2, IItemStack output, float xp)
        {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
            this.xp = xp;
        }

        @Override
        public void apply()
        {
            ItemStack[] inputStacks1 = IntegrationCT.toStacks(input1.getItemArray());
            ItemStack[] inputStacks2 = IntegrationCT.toStacks(input2.getItemArray());
            ItemStack outputStack = IntegrationCT.toStack(output);

            for (ItemStack key1 : inputStacks1)
                for (ItemStack key2 : inputStacks2)
                    getInstance().addCustomAlloyRecipe(key1, key2, outputStack, xp);
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
            getInstance().removeAlloyRecipe(outputStack);
        }

        @Override
        public String describe()
        {
            return "Removing Metallurgy-Reforged Alloyer Recipe for " + output.getDisplayName();
        }

    }

}
