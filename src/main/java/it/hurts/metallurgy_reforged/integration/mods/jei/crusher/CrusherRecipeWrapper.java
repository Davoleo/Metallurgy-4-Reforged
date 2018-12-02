package it.hurts.metallurgy_reforged.integration.mods.jei.crusher;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.util.recipe.BlockCrusherRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 29/11/2018 / 23:05
 * Class: CrusherRecipeWrapper
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CrusherRecipeWrapper implements IRecipeWrapper {

    private final ItemStack input;
    private final ItemStack output;

    public CrusherRecipeWrapper(ItemStack output, ItemStack input)
    {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        List<ItemStack> input = Lists.newArrayList();
        input.add(this.input);
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    public static List<CrusherRecipeWrapper> getRecipeInputs()
    {
        ArrayList<CrusherRecipeWrapper> recipes = new ArrayList<>();

        for(Map.Entry<ItemStack, ItemStack> entry : BlockCrusherRecipes.getInstance().getRecipeMap().entrySet())
        {
            recipes.add(new CrusherRecipeWrapper(entry.getKey(), entry.getValue()));
        }

        return recipes;
    }

    public boolean inputIsNotFull()
    {
        return input.getCount() < input.getMaxStackSize();
    }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        return new ArrayList<>();
    }

    @Override
    public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton)
    {
        return false;
    }
}
