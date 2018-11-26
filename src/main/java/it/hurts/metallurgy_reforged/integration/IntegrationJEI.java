package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.integration.mods.jei.CategoryCrusher;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
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

    public static final String idCrusher = "metallurgy_reforged.crusher";

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

        registry.addRecipeCategories(
                new CategoryCrusher(guiHelper)
        );
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry)
    {

    }

    @Override
    public void register(IModRegistry registry)
    {

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {

    }
}
