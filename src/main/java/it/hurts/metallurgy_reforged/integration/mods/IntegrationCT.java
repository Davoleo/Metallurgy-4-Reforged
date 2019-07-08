/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationCT
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IItemStack;
import it.hurts.metallurgy_reforged.integration.mods.crafttweaker.CompatAlloyer;
import it.hurts.metallurgy_reforged.integration.mods.crafttweaker.CompatCrusher;
import net.minecraft.item.ItemStack;

public class IntegrationCT {

    public static void preInit() {

        CraftTweakerAPI.registerClass(CompatCrusher.class);
        CraftTweakerAPI.registerClass(CompatAlloyer.class);

    }

    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null)
            return ItemStack.EMPTY;
        return (ItemStack) iStack.getInternal();
    }

    public static ItemStack[] toStacks(IItemStack[] iStacks) {
        if (iStacks == null) {
            return new ItemStack[0];
        }
        ItemStack[] ret = new ItemStack[iStacks.length];
        for (int i = 0; i < iStacks.length; i++) {
            ret[i] = toStack(iStacks[i]);
        }
        return ret;
    }
}
