package it.hurts.metallurgy_reforged.integration.mods.jei.crusher;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.integration.IntegrationJEI;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
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

    //Slot IDs
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    //gui background
    private final IDrawableStatic background;

    //Animations
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated bar;

    public CrusherRecipeCategory(IGuiHelper helper) {
        ResourceLocation texture = new ResourceLocation(Metallurgy.MODID, "textures/gui/crusher.png");

//      name = helper.createDrawable(guiLocation, xInitGui, yInitGUi, sizeX, sizeY);
        background = helper.createDrawable(texture, 0, 0, 176, 108);

//		Definiamo l'animazione della fiamma
        IDrawableStatic flameDrawable = helper.createDrawable(texture, 176, 61, 17, 17);
        flame = helper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

//		Definiamo l'animazione della Process Bar
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

    @SuppressWarnings({"rawtypes", "deprecation"})
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull CrusherRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients)
    {
        IGuiIngredientGroup group = recipeLayout.getItemStacks();

        group.init(INPUT_SLOT, true, 60, 27);
        group.init(OUTPUT_SLOT, false, 66, 66);

        group.set(INPUT_SLOT, ingredients.getInputs(ItemStack.class).get(0));
        group.set(OUTPUT_SLOT, ingredients.getOutputs(ItemStack.class).get(0));
    }
}
