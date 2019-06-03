/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class BlockUtils {

    public static void initBlock(Block block, String name, CreativeTabs tab, boolean addToList)
    {
        block.setRegistryName(Metallurgy.MODID, name);
        block.setTranslationKey(Metallurgy.MODID + "." + name);
        if (tab != null)
            block.setCreativeTab(tab);
        if (addToList)
            ModBlocks.blockList.add(block);
    }

}
