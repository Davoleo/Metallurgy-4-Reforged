/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationJEI
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class IntegrationJEI implements IModPlugin {

    public static final String CRUSHER = "metallurgy.crusher";
    public static final String ALLOYER = "metallurgy.alloyer";

//    @Override
//    public void registerCategories(IRecipeCategoryRegistration registry)
//    {
//        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
//
//        registry.addRecipeCategories(new CrusherRecipeCategory(guiHelper));
//        registry.addRecipeCategories(new AlloyerRecipeCategory(guiHelper));
//    }


//    @Override
//    public void register(IModRegistry registry)
//    {
//        //registers the recipes through a list built by the method "getRecipeInputs"
//        registry.addRecipes(CrusherRecipeWrapper.getRecipeInputs(), CRUSHER);
//        //sets the machine icon as the jei recipe icon
//        registry.addRecipeCatalyst(new ItemStack(ModBlocks.crusher), CRUSHER);
//        //Maps the area you need to click in to view all the machine Recipes
//        registry.addRecipeClickArea(GuiCrusher.class, 93, 32, 7, 33, CRUSHER);
//
//        registry.addRecipes(AlloyerRecipeWrapper.getRecipeInputs(), ALLOYER);
//
//        registry.addRecipeCatalyst(new ItemStack(ModBlocks.alloyer), ALLOYER);
//
//        registry.addRecipeClickArea(GuiAlloyer.class, 40, 32, 7, 33, ALLOYER);
//
//        registry.addIngredientInfo(new ItemStack(ModBlocks.oreTar), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
//        registry.addIngredientInfo(new ItemStack(ModItems.tar), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
//        registry.addIngredientInfo(new ItemStack(ModItems.bitumen), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
//        registry.addIngredientInfo(new ItemStack(ModItems.invisibilityShield), VanillaTypes.ITEM, "description.jei_compat.invisibility_shield");
//        registry.addIngredientInfo(new ItemStack(ModItems.dustThermite), VanillaTypes.ITEM, "description.jei_compat.thermite");
//        registry.addIngredientInfo(ModFluids.THERMITE.getFluidStack(), VanillaTypes.FLUID, "description.jei_compat.thermite");
//
//        //registry.addIngredientInfo(new ItemStack(ModFluids.TAR.getFluidBlock()), ItemStack.class, "description.jei_compat.tar_processing");
//    }


	@Override
	public ResourceLocation getPluginUid() {
		// TODO Auto-generated method stub
		return null;
	}
}
