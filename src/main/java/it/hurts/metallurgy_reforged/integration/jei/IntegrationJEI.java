/*==============================================================================
 = Class: IntegrationJEI
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.jei;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.gui.GuiAlloyer;
import it.hurts.metallurgy_reforged.gui.GuiCrusher;
import it.hurts.metallurgy_reforged.integration.jei.alloyer.AlloyerRecipeCategory;
import it.hurts.metallurgy_reforged.integration.jei.alloyer.AlloyerRecipeWrapper;
import it.hurts.metallurgy_reforged.integration.jei.chamber.ChamberRecipeCategory;
import it.hurts.metallurgy_reforged.integration.jei.chamber.ChamberRecipeWrapper;
import it.hurts.metallurgy_reforged.integration.jei.crusher.CrusherRecipeCategory;
import it.hurts.metallurgy_reforged.integration.jei.crusher.CrusherRecipeWrapper;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class IntegrationJEI implements IModPlugin {

	public static final String CRUSHER = "metallurgy.crusher";
	public static final String ALLOYER = "metallurgy.alloyer";
	public static final String SUBLIMATION_CHAMBER = "metallurgy.sublimation_chamber";

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();

		registry.addRecipeCategories(new CrusherRecipeCategory(guiHelper));
		registry.addRecipeCategories(new AlloyerRecipeCategory(guiHelper));
		registry.addRecipeCategories(new ChamberRecipeCategory(guiHelper));
	}


	@Override
	public void register(IModRegistry registry)
	{
		//Crusher Compat
		//registers the recipes through a list built by the method "getRecipeInputs"
		registry.addRecipes(CrusherRecipeWrapper.getRecipeInputs(), CRUSHER);
		//sets the machine icon as the jei recipe icon
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.crusher), CRUSHER);
		//Maps the area you need to click in to view all the machine Recipes
		registry.addRecipeClickArea(GuiCrusher.class, 93, 32, 7, 33, CRUSHER);

		//Alloyer Compat
		registry.addRecipes(AlloyerRecipeWrapper.getRecipeInputs(), ALLOYER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.alloyer), ALLOYER);
		registry.addRecipeClickArea(GuiAlloyer.class, 40, 32, 7, 33, ALLOYER);

		//Sublimation Chamber Compat
		registry.addRecipes(ChamberRecipeWrapper.getRecipeInputs(), SUBLIMATION_CHAMBER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.chamber), SUBLIMATION_CHAMBER);

		registry.addIngredientInfo(new ItemStack(ModBlocks.oreTar), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
		registry.addIngredientInfo(new ItemStack(ModItems.tar), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
		registry.addIngredientInfo(new ItemStack(ModItems.bitumen), VanillaTypes.ITEM, "description.jei_compat.tar_processing");
		registry.addIngredientInfo(new ItemStack(ModItems.invisibilityShield), VanillaTypes.ITEM, "description.jei_compat.invisibility_shield");
		registry.addIngredientInfo(new ItemStack(ModItems.dustThermite), VanillaTypes.ITEM, "description.jei_compat.thermite");
		registry.addIngredientInfo(ModFluids.THERMITE.getFluidStack(), VanillaTypes.FLUID, "description.jei_compat.thermite");

		List<ItemStack> krikArmor = new ArrayList<>();

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
			{
				krikArmor.add(new ItemStack(ModMetals.KRIK.getArmorPiece(slot)));
			}
		}

		registry.addIngredientInfo(krikArmor, VanillaTypes.ITEM, "description.jei_compat.krik_armor");

		//registry.addIngredientInfo(new ItemStack(ModFluids.TAR.getFluidBlock()), ItemStack.class, "description.jei_compat.tar_processing");

		List<OreDetectorWrapper> oreDetectorRecipes = new ArrayList<>();
		for (int i = 0; i <= 3; i++)
			oreDetectorRecipes.add(new OreDetectorWrapper(i));

		registry.addRecipes(oreDetectorRecipes, VanillaRecipeCategoryUid.CRAFTING);
	}

}
