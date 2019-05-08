/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OreDictHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.block.BlockBase;
import it.hurts.metallurgy_reforged.block.BlockOreDict;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ItemOreDict;
import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {

    public static void init()
    {
        //Ore-dict
        //Blocks
        for(BlockBase b: ModBlocks.blockList) {
            if(b instanceof BlockOreDict)
                ((BlockOreDict) b).initOreDict();
        }
        //Items
        for(Item b: ModItems.itemList) {
            if(b instanceof ItemOreDict)
                ((ItemOreDict) b).initOreDict();

            //Additional oreDict values
            OreDictionary.registerOre("globTar", ModItems.tar);
        }
    }
}
