package it.hurts.metallurgy_reforged.integration.mods.jei;

import it.hurts.metallurgy_reforged.integration.IntegrationJEI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/11/2018 / 21:57
 * Class: CategoryCrusher
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CategoryCrusher extends CategoryBase{

    private final IDrawable background;

    public CategoryCrusher(IGuiHelper guiHelper)
    {
        super("jei.category.crusher", IntegrationJEI.idCrusher);
        ResourceLocation location = new ResourceLocation("m5", "textures/gui/jei/recipeTemplateCrusher.png");
        background = guiHelper.createDrawable(location, 0, 0, 116, 54);
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();
        group.init(0, true, 61, -4);
        group.init(1, true, 129, 48);
        group.init(2, false, 67, 36);
        group.init(3, false, 48, 36);
        group.init(4, false, 29, 36);

        group.set(ingredients);
    }
}
