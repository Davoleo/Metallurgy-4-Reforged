/*==============================================================================
 = Class: CrusherRecipeCategory
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei.crusher;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.integration.jei.IntegrationJEI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipeWrapper> {

    //Slot IDs
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    //gui background
    private final IDrawableStatic background;

    //Animations
    private final IDrawableAnimated flame;
    private final IDrawableAnimated bar;

    public CrusherRecipeCategory(IGuiHelper helper)
    {
        ResourceLocation texture = new ResourceLocation(Metallurgy.MODID, "textures/gui/crusher.png");

        background = helper.createDrawable(texture, 0, 0, 176, 108);

        //Burning Fuel Animation
        IDrawableStatic flameDrawable = helper.createDrawable(texture, 176, 61, 17, 17);
        flame = helper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

        //Progress Bar Animation
        IDrawableStatic barDrawable = helper.createDrawable(texture, 176, 0, 7, 33);
        bar = helper.createAnimatedDrawable(barDrawable, 140, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public void drawExtras(Minecraft minecraft)
    {
        flame.draw(minecraft, 128, 61);
        bar.draw(minecraft, 93, 65);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return IntegrationJEI.CRUSHER;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return new ItemStack(ModBlocks.crusher).getDisplayName();
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
        IGuiIngredientGroup<ItemStack> group = recipeLayout.getItemStacks();

        group.init(INPUT_SLOT, true, 60, 27);
        group.init(OUTPUT_SLOT, false, 66, 66);

        group.set(INPUT_SLOT, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        group.set(OUTPUT_SLOT, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }

}
