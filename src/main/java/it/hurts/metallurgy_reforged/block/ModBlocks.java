package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 28 ago 2018
 * Time   : 18:24:07
 *
 ***************************/

public class ModBlocks {
    public static List<BlockBase> blockList = new ArrayList<>();

    private static String p = "pickaxe";
    @SuppressWarnings("unused")
    private static String s = "shovel";
    @SuppressWarnings("unused")
    private static String a = "axe";

//	Blocks wich drops itself

    //	ModBlocks with a custom ITEM drop
    public static BlockOreDict oreSulfur = new BlockOreDict("sulfur_ore", "oreSulfur", ModItems.dustSulfur, p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE).setCreativeTab(MetallurgyTabs.tabOre);
    public static BlockOreDict orePhosphorite = new BlockOreDict("phosphorite_ore", "orePhosphorite", ModItems.dustPhosphorus, p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE).setCreativeTab(MetallurgyTabs.tabOre);
    public static BlockOreDict oreBitumen = new BlockOreDict("bitumen_ore", "oreBitumen", ModItems.dustBitumen, p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE).setCreativeTab(MetallurgyTabs.tabOre);
    public static BlockOreDict orePotash = new BlockOreDict("potash_ore", "orePotash", ModItems.dustPotash, p, 2, ModMetals.MID_TIER_BLAST_RESISTANCE).setCreativeTab(MetallurgyTabs.tabOre);

    //  Various Blocks
    public static BlockOreDict blockBitumen = new BlockOreDict("bitumen_block", "blockBitumen").setCreativeTab(MetallurgyTabs.tabBlock);
    public static BlockOreDict blockCharcoal = new BlockOreDict("charcoal_block", "blockCharcoal").setCreativeTab(MetallurgyTabs.tabBlock);
    public static BlockOreDict blockSulfur = new BlockOreDict("sulfur_block", "blockSulfur").setCreativeTab(MetallurgyTabs.tabBlock);

    //Road
    //I know it's in the machine tab, don't bother me about it .-.
    public static BlockOrientable blockRoad = new BlockOrientable(Material.ROCK, "road_block", MetallurgyTabs.tabSpecial);
    public static BlockOrientable blockStripedRoad = new BlockOrientable(Material.ROCK, "striped_road_block", MetallurgyTabs.tabSpecial);

    //	Tile Entities
    public static BlockCrusher crusher = new BlockCrusher("crusher").setCreativeTab(MetallurgyTabs.tabSpecial);
    public static BlockAlloyer alloyer = new BlockAlloyer("alloyer").setCreativeTab(MetallurgyTabs.tabSpecial);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreSulfur,
                orePhosphorite,
                oreBitumen,
                orePotash,

                blockBitumen,
                blockCharcoal,
                blockSulfur,

                crusher,
                alloyer,

                blockRoad,
                blockStripedRoad
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(oreSulfur.createItemBlock(),
                orePhosphorite.createItemBlock(),
                oreBitumen.createItemBlock(),
                orePotash.createItemBlock(),

                blockBitumen.createItemBlock(),
                blockCharcoal.createItemBlock(),
                blockSulfur.createItemBlock(),

                alloyer.createItemBlock(),
                crusher.createItemBlock(),

                blockRoad.createItemBlock(),
                blockStripedRoad.createItemBlock());
    }

    public static void registerModels() {
        oreSulfur.registerItemModel(Item.getItemFromBlock(oreSulfur));
        orePhosphorite.registerItemModel(Item.getItemFromBlock(orePhosphorite));
        oreBitumen.registerItemModel(Item.getItemFromBlock(oreBitumen));
        orePotash.registerItemModel(Item.getItemFromBlock(orePotash));

        blockBitumen.registerItemModel(Item.getItemFromBlock(blockBitumen));
        blockCharcoal.registerItemModel(Item.getItemFromBlock(blockCharcoal));
        blockSulfur.registerItemModel(Item.getItemFromBlock(blockSulfur));

        crusher.registerItemModel(Item.getItemFromBlock(crusher));
        alloyer.registerItemModel(Item.getItemFromBlock(alloyer));

        blockRoad.registerItemModel(Item.getItemFromBlock(blockRoad));
        blockStripedRoad.registerItemModel(Item.getItemFromBlock(blockStripedRoad));
    }

}