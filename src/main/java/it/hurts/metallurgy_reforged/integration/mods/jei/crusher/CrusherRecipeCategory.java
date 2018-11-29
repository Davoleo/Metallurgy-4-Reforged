package it.hurts.metallurgy_reforged.integration.mods.jei.crusher;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/11/2018 / 21:57
 * Class: CrusherRecipeCategory
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipeWrapper> {

    private static final String UID = "m5:crusher";

    private final IDrawableStatic background;

    public CrusherRecipeCategory(IGuiHelper guiHelper)
    {
        ResourceLocation texture = new ResourceLocation(Metallurgy.MODID, "textures/gui/jei/recipeTemplateCrusher.png");
        this.background = guiHelper.createDrawable(texture, 0, 0, 116, 54);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return Utils.localize("gui.jei_compat.crusher.name");
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return Metallurgy.NAME;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon()
    {
        return null;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull CrusherRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
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
