/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModRecipes
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util.recipe;

import it.hurts.metallurgy_reforged.block.BlockBase;
import it.hurts.metallurgy_reforged.block.BlockOreDict;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ItemBase;
import it.hurts.metallurgy_reforged.item.ItemOreDict;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    public static void init()
    {
        //Ore-dict
        //Blocks
        for(BlockBase b: ModBlocks.blockList) {
            if(b instanceof BlockOreDict)
                ((BlockOreDict) b).initOreDict();
        }
        //Items
        for(ItemBase b: ModItems.itemList) {
            if(b instanceof ItemOreDict)
                ((ItemOreDict) b).initOreDict();
        }

        //Furnace Recipes
        for(Metal m: ModMetals.metalList) {
            if(m.getOre() != null) {
                GameRegistry.addSmelting(m.getOre(), new ItemStack(m.getIngot()), 1F);
            }
            GameRegistry.addSmelting(m.getDust(), new ItemStack(m.getIngot()), 0.6F);
        }

        //Dust2Ingot
        GameRegistry.addSmelting(ModItems.dustIron, new ItemStack(Items.IRON_INGOT), 0.6F);
        GameRegistry.addSmelting(ModItems.dustGold, new ItemStack(Items.GOLD_INGOT), 0.6F);

        //Ore2Material
        GameRegistry.addSmelting(ModBlocks.oreTar, new ItemStack(ModItems.tar), 0.5F);
        GameRegistry.addSmelting(ModBlocks.orePhosphorite, new ItemStack(ModItems.dustPhosphorus), 0.5F);
        GameRegistry.addSmelting(ModBlocks.orePotash, new ItemStack(ModItems.dustPotash), 0.5F);
        GameRegistry.addSmelting(ModBlocks.oreSulfur, new ItemStack(ModItems.dustSulfur), 0.5F);

        //Additional oreDict values
        OreDictionary.registerOre("globTar", ModItems.tar);
    }

}
