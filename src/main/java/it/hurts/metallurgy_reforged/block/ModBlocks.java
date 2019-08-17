/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModBlocks
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.block.gadget.BlockPhosphorusLamp;
import it.hurts.metallurgy_reforged.data.Drop;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

//Class used as reference for all the manually registered blocks
@SuppressWarnings("unused")
public class ModBlocks {

    //Internal state / fields -------------------------------------------------

    //The list of all the blocks of this mod
    public static List<Block> blockList = new ArrayList<>();

    //Blocks which drops themselves

    //Mod Blocks with a custom drop
    public static BlockOreDict oreSulfur = new BlockOreDict("sulfur_ore", "oreSulfur", true, Constants.PICKAXE, 2, ModMetals.MID_TIER_BLAST_RESISTANCE, MetallurgyTabs.tabOre)
            .setDrops(new Drop(new ItemStack(ModItems.sulfur, 4), 1F));
    public static BlockOreDict orePhosphorite = new BlockOreDict("phosphorite_ore", "orePhosphorite", true, Constants.PICKAXE, 2, ModMetals.MID_TIER_BLAST_RESISTANCE, MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.phosphorus, 3, 1F));
    public static BlockOreDict oreTar = new BlockOreDict("tar_ore", "oreTar", true, Constants.PICKAXE, 2, ModMetals.MID_TIER_BLAST_RESISTANCE, MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.tar,2, 1F), new Drop(ModItems.bitumen, 2, 0.5F));
    public static BlockOreDict orePotash = new BlockOreDict("potash_ore", "orePotash", true, Constants.PICKAXE, 2, ModMetals.MID_TIER_BLAST_RESISTANCE, MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.potash, 3, 1F));

    //Other Blocks
    public static BlockOreDict blockBitumen = new BlockOreDict("bitumen_block", "blockBitumen", true, MetallurgyTabs.tabBlock);
    public static BlockOreDict blockCharcoal = new BlockOreDict("charcoal_block", "blockCharcoal", true,  MetallurgyTabs.tabBlock);
    public static BlockOreDict blockSulfur = new BlockOreDict("sulfur_block", "blockSulfur", true,  MetallurgyTabs.tabBlock);

    public static BlockPhosphorusLamp blockPhosphorusLamp = new BlockPhosphorusLamp();

    //Road
    public static BlockOrientable blockRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "road_block", MetallurgyTabs.tabSpecial).setHardness(3F));
    public static BlockOrientable blockStripedRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "striped_road_block", MetallurgyTabs.tabSpecial).setHardness(3F));

    //Tile Entities
    public static BlockCrusher crusher = new BlockCrusher("crusher");
    public static BlockAlloyer alloyer = new BlockAlloyer("alloyer");
    //public static BlockLightningRod lightningRod = new BlockLightningRod("lightning_rod");
    public static BlockChamber chamber = new BlockChamber("sublimation_chamber");

    //Registers the blocks in the Forge Registry
    public static void register(IForgeRegistry<Block> registry) {
        for (Block block : blockList)
            registry.register(block);
    }

    //Registers the ItemBlocks in the Forge Registry
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        for (Block block : blockList)
        {
            ItemBlock itemBlock = new ItemBlock(block);
            itemBlock.setRegistryName(block.getRegistryName());
            registry.register(itemBlock);
        }
    }

    //Registers the models
    public static void registerModels() {
        for (Block block : blockList)
            ItemUtils.registerCustomItemModel(Item.getItemFromBlock(block), 0);
    }

}