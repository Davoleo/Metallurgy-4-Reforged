package it.hurts.metallurgy_reforged.integration.mods.jei;

import it.hurts.metallurgy_reforged.util.Utils;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/11/2018 / 21:43
 * Class: CategoryBase
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CategoryBase implements IRecipeCategory {

    private final String localizedTitle, uid;

    public CategoryBase(String unlocalizedTitle, String uid)
    {
        this.localizedTitle = Utils.localize(unlocalizedTitle);
        this.uid = uid;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {

    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return null;
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return null;
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return this.uid;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return this.localizedTitle;
    }

    @Nullable
    @Override
    public IDrawable getIcon()
    {
        return null;
    }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        return null;
    }
}
