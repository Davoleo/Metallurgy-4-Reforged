package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.gui.GuiCrusher;
import it.hurts.metallurgy_reforged.integration.mods.jei.crusher.CrusherRecipeCategory;
import it.hurts.metallurgy_reforged.integration.mods.jei.crusher.CrusherRecipeWrapper;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;
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

    public static final String CRUSHER = "m5.crusher";
    public static final String ALLOYER = "m5.alloyer";

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
    {
        subtypeRegistry.useNbtForSubtypes(
                ModArmors.deep_iron_boots,
                ModArmors.platinum_helmet,
                Item.getItemFromBlock(ModBlocks.crusher),
                Item.getItemFromBlock(ModBlocks.alloyer)
        );
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new CrusherRecipeCategory(guiHelper));
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
    }
}
