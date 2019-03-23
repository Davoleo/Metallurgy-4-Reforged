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

import it.hurts.metallurgy_reforged.data.Drop;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

//Class used as reference for all the manually registered blocks
public class ModBlocks {

    //Internal state / fields -------------------------------------------------

    //The list of all the blocks of this mod
    public static List<BlockBase> blockList = new ArrayList<>();

    //Constants to declare the block harvest tool
    private static final String p = "pickaxe";
    @SuppressWarnings("unused")
    private static final String s = "shovel";
    @SuppressWarnings("unused")
    private static final String a = "axe";

//	Blocks which drops themselves

    //Mod Blocks with a custom drop
    public static BlockOreDict oreSulfur = new BlockOreDict("sulfur_ore", "oreSulfur", p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE)
            .setCreativeTab(MetallurgyTabs.tabOre)
            .setDrops(new Drop(new ItemStack(ModItems.dustSulfur, 4), 1F));
    public static BlockOreDict orePhosphorite = new BlockOreDict("phosphorite_ore", "orePhosphorite", p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE)
            .setCreativeTab(MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.dustPhosphorus, 3, 1F));
    public static BlockOreDict oreTar = new BlockOreDict("tar_ore", "oreTar", p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE)
            .setCreativeTab(MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.tar,2, 1F), new Drop(ModItems.dustBitumen, 2, 0.5F));
    public static BlockOreDict orePotash = new BlockOreDict("potash_ore", "orePotash", p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE)
            .setCreativeTab(MetallurgyTabs.tabOre)
            .setDrops(new Drop(ModItems.dustPotash, 3, 1F));

    //Other Blocks
    public static BlockOreDict blockBitumen = new BlockOreDict("bitumen_block", "blockBitumen").setCreativeTab(MetallurgyTabs.tabBlock);
    public static BlockOreDict blockCharcoal = new BlockOreDict("charcoal_block", "blockCharcoal").setCreativeTab(MetallurgyTabs.tabBlock);
    public static BlockOreDict blockSulfur = new BlockOreDict("sulfur_block", "blockSulfur").setCreativeTab(MetallurgyTabs.tabBlock);

    //Road
    public static BlockOrientable blockRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "road_block", MetallurgyTabs.tabSpecial).setHardness(3F));
    public static BlockOrientable blockStripedRoad = ((BlockOrientable) new BlockOrientable(Material.ROCK, "striped_road_block", MetallurgyTabs.tabSpecial).setHardness(3F));

    //Tile Entities
    public static BlockCrusher crusher = new BlockCrusher("crusher").setCreativeTab(MetallurgyTabs.tabSpecial);
    public static BlockAlloyer alloyer = new BlockAlloyer("alloyer").setCreativeTab(MetallurgyTabs.tabSpecial);
    public static BlockChamber chamber = new BlockChamber("sublimation_chamber");

    //Registers the blocks in the Forge Registry
    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreSulfur,
                orePhosphorite,
                oreTar,
                orePotash,

                blockBitumen,
                blockCharcoal,
                blockSulfur,

                crusher,
                alloyer,
                chamber,

                blockRoad,
                blockStripedRoad
        );
    }

    //Registers the ItemBlocks in the Forge Registry
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(oreSulfur.createItemBlock(),
                orePhosphorite.createItemBlock(),
                oreTar.createItemBlock(),
                orePotash.createItemBlock(),

                blockBitumen.createItemBlock(),
                blockCharcoal.createItemBlock(),
                blockSulfur.createItemBlock(),

                alloyer.createItemBlock(),
                crusher.createItemBlock(),
                chamber.createItemBlock(),

                blockRoad.createItemBlock(),
                blockStripedRoad.createItemBlock());
    }

    //Registers the models
    public static void registerModels() {
        oreSulfur.registerItemModel(Item.getItemFromBlock(oreSulfur));
        orePhosphorite.registerItemModel(Item.getItemFromBlock(orePhosphorite));
        oreTar.registerItemModel(Item.getItemFromBlock(oreTar));
        orePotash.registerItemModel(Item.getItemFromBlock(orePotash));

        blockBitumen.registerItemModel(Item.getItemFromBlock(blockBitumen));
        blockCharcoal.registerItemModel(Item.getItemFromBlock(blockCharcoal));
        blockSulfur.registerItemModel(Item.getItemFromBlock(blockSulfur));

        crusher.registerItemModel(Item.getItemFromBlock(crusher));
        alloyer.registerItemModel(Item.getItemFromBlock(alloyer));
        chamber.registerItemModel(Item.getItemFromBlock(chamber));

        blockRoad.registerItemModel(Item.getItemFromBlock(blockRoad));
        blockStripedRoad.registerItemModel(Item.getItemFromBlock(blockStripedRoad));
    }

}