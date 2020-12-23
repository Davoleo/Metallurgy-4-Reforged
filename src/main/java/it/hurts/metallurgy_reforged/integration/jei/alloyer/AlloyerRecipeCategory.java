/*==============================================================================
 = Class: AlloyerRecipeCategory
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei.alloyer;

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

public class AlloyerRecipeCategory implements IRecipeCategory<AlloyerRecipeWrapper> {

    //Slot IDs
    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    //GUI Background
    private final IDrawableStatic background;

    //Animations
    private final IDrawableAnimated flame;
    private final IDrawableAnimated bar;
    private final IDrawableAnimated moltenMetal;

    public AlloyerRecipeCategory(IGuiHelper guiHelper)
    {
        ResourceLocation texture = new ResourceLocation(Metallurgy.MODID, "textures/gui/alloyer.png");

        background = guiHelper.createDrawable(texture, 0, 0, 176, 108);

        //Burning fuel animation
        IDrawableStatic flameDrawable = guiHelper.createDrawable(texture, 176, 61, 17, 17);
        flame = guiHelper.createAnimatedDrawable(flameDrawable, 200, IDrawableAnimated.StartDirection.TOP, true);

        //Molten metal flow animation
        IDrawableStatic moltenDrawable = guiHelper.createDrawable(texture, 176, 81, 10, 25);
        moltenMetal = guiHelper.createAnimatedDrawable(moltenDrawable, 80, IDrawableAnimated.StartDirection.TOP, false);

        //Process animation
        IDrawableStatic barDrawable = guiHelper.createDrawable(texture, 176, 0, 7, 33);
        bar = guiHelper.createAnimatedDrawable(barDrawable, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft)
    {
        flame.draw(minecraft, 110, 61);
        bar.draw(minecraft, 40, 66);
        moltenMetal.draw(minecraft, 52, 32);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return IntegrationJEI.ALLOYER;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return new ItemStack(ModBlocks.alloyer).getDisplayName();
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return Metallurgy.NAME;
    }

    @Nonnull
    @Override
    public IDrawableStatic getBackground()
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
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull AlloyerRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        IGuiIngredientGroup<ItemStack> group = recipeLayout.getItemStacks();

        group.init(INPUT_SLOT_1, true, 101, 23);
        group.init(INPUT_SLOT_2, true, 122, 23);
        group.init(OUTPUT_SLOT, false, 56, 73);

        group.set(INPUT_SLOT_1, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        group.set(INPUT_SLOT_2, ingredients.getInputs(VanillaTypes.ITEM).get(1));
        group.set(OUTPUT_SLOT, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }

}
