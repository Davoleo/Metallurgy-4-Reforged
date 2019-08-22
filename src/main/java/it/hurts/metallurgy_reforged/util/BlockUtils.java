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

import java.util.Objects;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockUtils {

    public static void initBlock(Block block, String name, ItemGroup tab, boolean addToList)
    {
        block.setRegistryName(Metallurgy.MODID, name);
        //block.setTranslationKey(Metallurgy.MODID + "." + name);
        //if (tab != null)
        //    block.setCreativeTab(tab);
        if (addToList)
            ModBlocks.blockList.add(block);
    }
    
    public static Item getBlockItemForRegistration(Block block, ItemGroup group) {
    	return new BlockItem(block, new Item.Properties().group(group)).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

/*
    public static Direction getFreeFacing(IBlockAccess world, BlockPos pos)
    {
        for (Direction facing : Direction.values())
            if (world.isAirBlock(pos.offset(facing)))
                return facing;
            return null;
    }
    */
}
