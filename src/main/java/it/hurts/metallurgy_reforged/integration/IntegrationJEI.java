package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.gui.GuiAlloyer;
import it.hurts.metallurgy_reforged.gui.GuiCrusher;
import it.hurts.metallurgy_reforged.integration.mods.jei.alloyer.AlloyerRecipeCategory;
import it.hurts.metallurgy_reforged.integration.mods.jei.alloyer.AlloyerRecipeWrapper;
import it.hurts.metallurgy_reforged.integration.mods.jei.crusher.CrusherRecipeCategory;
import it.hurts.metallurgy_reforged.integration.mods.jei.crusher.CrusherRecipeWrapper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/11/2018 / 21:32
 * Class: IntegrationJEI
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

@JEIPlugin
public class IntegrationJEI implements IModPlugin {

    public static final String CRUSHER = "metallurgy.crusher";
    public static final String ALLOYER = "metallurgy.alloyer";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new CrusherRecipeCategory(guiHelper));
        registry.addRecipeCategories(new AlloyerRecipeCategory(guiHelper));
    }


    @Override
    public void register(IModRegistry registry)
    {
        //registers the recipes through a list built by the method "getRecipeInputs"
        registry.addRecipes(CrusherRecipeWrapper.getRecipeInputs(), CRUSHER);
        //sets the machine icon as the jei recipe icon
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.crusher), CRUSHER);
        //Maps the area you need to click in to view all the machine Recipes
        registry.addRecipeClickArea(GuiCrusher.class, 93, 32, 7, 33, CRUSHER);

        registry.addRecipes(AlloyerRecipeWrapper.getRecipeInputs(), ALLOYER);

        registry.addRecipeCatalyst(new ItemStack(ModBlocks.alloyer), ALLOYER);

        registry.addRecipeClickArea(GuiAlloyer.class, 40, 32, 7, 33, ALLOYER);
    }
}
