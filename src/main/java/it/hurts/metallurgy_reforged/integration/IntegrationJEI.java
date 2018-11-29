package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.integration.mods.jei.crusher.CrusherRecipeCategory;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.util.recipe.BlockCrusherRecipes;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;

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

    public static IJeiHelpers jeiHelpers;

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
    public void registerIngredients(IModIngredientRegistration registry)
    {

    }

    @Override
    public void register(IModRegistry registry)
    {
        jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipes(BlockCrusherRecipes.getCollection(), CRUSHER);
    }

    private static void setupDrawables(IGuiHelper helper)
    {

    }
}
