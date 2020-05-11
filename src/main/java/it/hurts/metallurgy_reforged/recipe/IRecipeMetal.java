/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IRecipeMetal
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.recipe;

import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeMetal extends IRecipe {

	ItemStack getOutputFromMetal(Metal metal);

}
