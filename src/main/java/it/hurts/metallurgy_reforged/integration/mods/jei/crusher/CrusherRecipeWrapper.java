package it.hurts.metallurgy_reforged.integration.mods.jei.crusher;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 29/11/2018 / 23:05
 * Class: CrusherRecipeWrapper
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CrusherRecipeWrapper implements IRecipeWrapper {

    private ItemStack input;
    private ItemStack output;

    public CrusherRecipeWrapper(ItemStack output, ItemStack input)
    {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    public ItemStack getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
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
