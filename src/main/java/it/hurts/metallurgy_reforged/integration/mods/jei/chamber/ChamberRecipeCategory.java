/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ChamberRecipeCategory
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.jei.chamber;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.integration.mods.jei.IntegrationJEI;
import it.hurts.metallurgy_reforged.recipe.BlockSublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ChamberRecipeCategory implements IRecipeCategory<ChamberRecipeWrapper> {

    public static final int INPUT_SLOT = TileEntityChamber.METAL_SLOT;

    private final IDrawableStatic background;
    //private final IDrawableStatic effect;
    private final IDrawableAnimated flame;

    private int effectIndex;

    public ChamberRecipeCategory(IGuiHelper helper) {
        ResourceLocation texture = new ResourceLocation(Metallurgy.MODID, "textures/gui/sublimation_chamber_jei.png");

        this.background = helper.createDrawable(texture, 0, 0, 175, 81);
        //this.effect = helper.createDrawable(GuiContainer.INVENTORY_BACKGROUND, effectIndex % 8 * 18, 198 + effectIndex / 8 * 18, 18, 18);

        IDrawableStatic flameDrawable = helper.createDrawable(texture, 176, 0, 14, 14);
        flame = helper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        flame.draw(minecraft, 79, 53);
        //effect.draw(minecraft, 110, 31);
    }

    @Nonnull
    @Override
    public String getUid() {
        return IntegrationJEI.SUBLIMATION_CHAMBER;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return new ItemStack(ModBlocks.crusher).getDisplayName();
    }

    @Nonnull
    @Override
    public String getModName() {
        return Metallurgy.NAME;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull ChamberRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {

        IGuiIngredientGroup group = recipeLayout.getItemStacks();

        group.init(INPUT_SLOT, true, 46, 30);
        group.set(INPUT_SLOT, ingredients.getInputs(VanillaTypes.ITEM).get(0));

        effectIndex = BlockSublimationRecipes.getInstance().recipesMap().get(recipeWrapper.getInput()).getPotion().getStatusIconIndex();

    }
}
