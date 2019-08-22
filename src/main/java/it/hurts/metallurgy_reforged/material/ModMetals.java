/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModMetals
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import java.util.ArrayList;
import java.util.List;

import it.hurts.metallurgy_reforged.material.MetalStats.FluidStats;
import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModMetals {
    public static List<Metal> metalList = new ArrayList<>();

    //Disclaimer: These variables might need a balancement update
    //Davoleo isn't responsible for any blast resistance level complains
    public static final float LOW_TIER_BLAST_RESISTANCE = 6F; 							//or maybe 3, I don't remember
    public static final float MID_TIER_BLAST_RESISTANCE = 10F;                         	//Cobblestone-like
    public static final float HIGH_TIER_BLAST_RESISTANCE = 15F;
    public static final float EXTREME_TIER_BLAST_RESISTANCE = 20F;                   	//Obsidian-like
    public static final float UNBREAKABLE_TIER_BLAST_RESISTANCE = 18000000F;           	//Bedrock-like

//    MetalStats("name", "Name", harvestLevelOfMetalBlock, blastResistance, ArmorStats, ToolStats, FluidStats, harvestLevelOfOreBlock).createMetal;
    //damage_parameter = in-game_sword_damage - 3

    //THIS LIST MUST BE IN SORTED IN ALPHABETICAL ORDER IN ORDER TO MAKE THE MOD FUNCTION PROPERLY
    public static final Metal ADAMANTINE = new MetalStats("adamantine", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.ADAMANTINE, ModToolTier.ADAMANTINE, new FluidStats(0xFFA31D1D), 6).createMetal();
    public static final Metal AMORDRINE = new MetalStats("amordrine", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.AMORDRINE, ModToolTier.AMORDRINE, new FluidStats(0xFF9780CB), -1).createMetal();
    public static final Metal ANGMALLEN = new MetalStats("angmallen", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.ANGMALLEN, ModToolTier.ANGMALLEN, new FluidStats(0xFFC09844), -1).createMetal();
    public static final Metal ALDUORITE = new MetalStats("alduorite", 2, MID_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFF9294BE), 3).createMetal();
    public static final Metal ASTRAL_SILVER = new MetalStats("astral_silver", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.ASTRAL_SILVER, ModToolTier.ASTRAL_SILVER, new FluidStats(0xFFBBD5CF), 4).createMetal();
    public static final Metal ATLARUS = new MetalStats("atlarus", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.ATLARUS, ModToolTier.ATLARUS, new FluidStats(0xFFDABC23), 6).createMetal();
    public static final Metal BLACK_STEEL = new MetalStats("black_steel", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.BLACK_STEEL, ModToolTier.BLACK_STEEL, new FluidStats(0xFF2F2F2F), -1).createMetal();
    public static final Metal BRASS = new MetalStats("brass", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.BRASS, ModToolTier.BRASS, new FluidStats(0xFFEAAB00), -1).createMetal();
    public static final Metal BRONZE = new MetalStats("bronze", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.BRONZE, ModToolTier.BRONZE, new FluidStats(0xFFCB8B4B), -1).createMetal();
    public static final Metal CARMOT = new MetalStats("carmot", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.CARMOT, ModToolTier.CARMOT, new FluidStats(0xFFC39C69), 4).createMetal();
    public static final Metal CELENEGIL = new MetalStats("celenegil", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.CELENEGIL, ModToolTier.CELENEGIL, new FluidStats(0xFF4FD673), -1).createMetal();
    public static final Metal CERUCLASE = new MetalStats("ceruclase", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.CERUCLASE, ModToolTier.CERUCLASE, new FluidStats(0xFF578A99), 3).createMetal();
    public static final Metal COPPER = new MetalStats("copper", 2, LOW_TIER_BLAST_RESISTANCE, ModArmorMaterial.COPPER, ModToolTier.COPPER, new FluidStats(0xFFE5770A), 1).createMetal();
    public static final Metal DAMASCUS_STEEL = new MetalStats("damascus_steel", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.DAMASCUS_STEEL, ModToolTier.DAMASCUS_STEEL, new FluidStats(0xFF75663D), -1).createMetal();
    public static final Metal DEEP_IRON = new MetalStats("deep_iron", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.DEEP_IRON, ModToolTier.DEEP_IRON, new FluidStats(0xFF2D394F), 2).createMetal();
    public static final Metal DESICHALKOS = new MetalStats("desichalkos", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.DESICHALKOS, ModToolTier.DESICHALKOS, new FluidStats(0xFF685B9C), -1).createMetal();
    public static final Metal ELECTRUM = new MetalStats("electrum", 2, LOW_TIER_BLAST_RESISTANCE, ModArmorMaterial.ELECTRUM, ModToolTier.ELECTRUM, new FluidStats(0xFFEFEF57), -1).createMetal();
    public static final Metal EXIMITE = new MetalStats("eximite", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.EXIMITE, ModToolTier.EXIMITE, new FluidStats(0xFF5E4191), 3).createMetal();
    public static final Metal HADEROTH = new MetalStats("haderoth", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.HADEROTH, ModToolTier.HADEROTH, new FluidStats(0xFF351F22), -1).createMetal();
    public static final Metal HEPATIZON = new MetalStats("hepatizon", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.HEPATIZON, ModToolTier.HEPATIZON, new FluidStats(0xFF51202E), -1).createMetal();
    public static final Metal IGNATIUS = new MetalStats("ignatius", 2, LOW_TIER_BLAST_RESISTANCE, ModArmorMaterial.IGNATIUS, ModToolTier.IGNATIUS, new FluidStats(0xFFD05631), 1).createMetal();
    public static final Metal INFUSCOLIUM = new MetalStats("infuscolium", 2, MID_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFF6A3359), 2).createMetal();
    public static final Metal INOLASHITE = new MetalStats("inolashite", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.INOLASHITE, ModToolTier.INOLASHITE, new FluidStats(0xFF476267), -1).createMetal();
    public static final Metal KALENDRITE = new MetalStats("kalendrite", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.KALENDRITE, ModToolTier.KALENDRITE, new FluidStats(0xFF9941DD), 4).createMetal();
    public static final Metal KRIK = new MetalStats("krik", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.KRIK, ModToolTier.KRIK, new FluidStats(0xFF415E26), -1).createMetal();
    public static final Metal LEMURITE = new MetalStats("lemurite", 2, LOW_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFFD0D0D0), 2).createMetal();
    public static final Metal LUTETIUM = new MetalStats("lutetium", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.LUTETIUM, null, new FluidStats(0xFF777221), 4).createMetal();
    public static final Metal MANGANESE = new MetalStats("manganese", 2, MID_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFFFFAEDF), 3).createMetal();
    public static final Metal MEUTOITE = new MetalStats("meutoite", 2, HIGH_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFF332E38), 4).createMetal();
    public static final Metal MIDASIUM = new MetalStats("midasium", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.MIDASIUM, ModToolTier.MIDASIUM, new FluidStats(0xFFD5862A), 3).createMetal();
    public static final Metal MITHRIL = new MetalStats("mithril", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.MITHRIL, ModToolTier.MITHRIL, new FluidStats(0xFF6ACBC8), 4).createMetal();
    public static final Metal ORICHALCUM = new MetalStats("orichalcum", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.ORICHALCUM, ModToolTier.ORICHALCUM, new FluidStats(0xFF95B26D), 5).createMetal();
    public static final Metal OSMIUM = new MetalStats("osmium", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.OSMIUM, null, new FluidStats(0xFF717393), 2).createMetal();
    public static final Metal OURECLASE = new MetalStats("oureclase", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.OURECLASE, ModToolTier.OURECLASE, new FluidStats(0xFFC05B4A), 3).createMetal();
    public static final Metal PLATINUM = new MetalStats("platinum", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.PLATINUM, ModToolTier.PLATINUM, new FluidStats(0xFF7193D4), 2).createMetal();
    public static final Metal PROMETHEUM = new MetalStats("prometheum", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.PROMETHEUM, ModToolTier.PROMETHEUM, new FluidStats(0xFF377732), 1).createMetal();
    public static final Metal QUICKSILVER = new MetalStats("quicksilver", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.QUICKSILVER, ModToolTier.QUICKSILVER, new FluidStats(0xFF72BF9F), -1).createMetal();
    public static final Metal RUBRACIUM = new MetalStats("rubracium", 2, MID_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFFB83F56), 4).createMetal();
    public static final Metal SANGUINITE = new MetalStats("sanguinite", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.SANGUINITE, ModToolTier.SANGUINITE, new FluidStats(0xFF541D1D), 6).createMetal();
    public static final Metal SHADOW_IRON = new MetalStats("shadow_iron", 2, LOW_TIER_BLAST_RESISTANCE, ModArmorMaterial.SHADOW_IRON, ModToolTier.SHADOW_IRON, new FluidStats(0xFF473D40), 1).createMetal();
    public static final Metal SHADOW_STEEL = new MetalStats("shadow_steel", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.SHADOW_STEEL, ModToolTier.SHADOW_STEEL, new FluidStats(0xFF979392), -1).createMetal();
    public static final Metal SILVER = new MetalStats("silver", 2, MID_TIER_BLAST_RESISTANCE, ModArmorMaterial.SILVER, ModToolTier.SILVER, new FluidStats(0xFFCDD3D3), 1).createMetal();
    public static final Metal STEEL = new MetalStats("steel", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.STEEL, ModToolTier.STEEL, new FluidStats(0xFF616161), -1).createMetal();
    public static final Metal TARTARITE = new MetalStats("tartarite", 2, EXTREME_TIER_BLAST_RESISTANCE, ModArmorMaterial.TARTARITE, ModToolTier.TARTARITE, new FluidStats(0xFFCA8A32), -1).createMetal();
    public static final Metal TIN = new MetalStats("tin", 2, LOW_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFFA9A295), 1).createMetal();
    public static final Metal VULCANITE = new MetalStats("vulcanite", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.VULCANITE, ModToolTier.VULCANITE, new FluidStats(0xFF641929), 5).createMetal();
    public static final Metal VYROXERES = new MetalStats("vyroxeres", 2, HIGH_TIER_BLAST_RESISTANCE, ModArmorMaterial.VYROXERES, ModToolTier.VYROXERES, new FluidStats(0xFF2BFF00), 4).createMetal();
    public static final Metal ZINC = new MetalStats("zinc", 2, MID_TIER_BLAST_RESISTANCE, null, null, new FluidStats(0xFFCACE84), 1).createMetal();

    public static void registerFluids() {
    	/*
        for (Metal m : metalList) {
            FluidRegistry.registerFluid(m.getMolten());
            m.initFluidBlock();
        }
        */
    }

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        for (Metal m : metalList) {
            registry.register(m.getBlock());
            if (m.getOre() != null) {
                registry.register(m.getOre());
            }
            //registry.register(m.getFluidBlock());
        }
    }

    public static void registerModels() {
        for (Metal m : metalList) {
            ItemUtils.registerCustomItemModel(m.getDust(), 0);
            ItemUtils.registerCustomItemModel(m.getIngot(), 0);
            ItemUtils.registerCustomItemModel(m.getNugget(), 0);
            if (m.getOre() != null) {
                ItemUtils.registerCustomItemModel(Item.getItemFromBlock(m.getOre()), 0);
            }
            ItemUtils.registerCustomItemModel(Item.getItemFromBlock(m.getBlock()), 0);
            //ItemUtils.registerCustomItemModel(Item.getItemFromBlock(m.getFluidBlock()), 0);
        }
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        for (Metal m : metalList) {
            registry.register(BlockUtils.getBlockItemForRegistration(m.getBlock(), MetallurgyTabs.tabBlock));
            
            if (m.getOre() != null) {
                registry.register(BlockUtils.getBlockItemForRegistration(m.getOre(), MetallurgyTabs.tabOre));
            }
            //registry.register(m.getFluidBlock().createItemBlock());
            registry.register(m.getDust());
            registry.register(m.getIngot());
            registry.register(m.getNugget());
        }
    }
}