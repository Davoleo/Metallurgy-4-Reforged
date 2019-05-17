/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModTools
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.config.ToolConfig;
import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.Tooltips;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModTools {

    public static final List<Item> toolList = new ArrayList<>();

    public static boolean isCreativeTabIconAvailable = false;

    public static ItemAxeBase adamantine_axe = new ItemAxeBase(ModMetals.ADAMANTINE.getToolMaterial(), "adamantine_axe");
    public static ItemHoeBase adamantine_hoe = new ItemHoeBase(ModMetals.ADAMANTINE.getToolMaterial(), "adamantine_hoe");
    public static ItemPickaxeBase adamantine_pickaxe = new ItemPickaxeBase(ModMetals.ADAMANTINE.getToolMaterial(), "adamantine_pickaxe");
    public static ItemShovelBase adamantine_shovel = new ItemShovelBase(ModMetals.ADAMANTINE.getToolMaterial(), "adamantine_shovel");
    public static ItemSwordBase adamantine_sword = new ItemSwordBase(ModMetals.ADAMANTINE.getToolMaterial(), "adamantine_sword");

    public static ItemAxeBase amordrine_axe = new ItemAxeBase(ModMetals.AMORDRINE.getToolMaterial(), "amordrine_axe");
    public static ItemHoeBase amordrine_hoe = new ItemHoeBase(ModMetals.AMORDRINE.getToolMaterial(), "amordrine_hoe");
    public static ItemPickaxeBase amordrine_pickaxe = new ItemPickaxeBase(ModMetals.AMORDRINE.getToolMaterial(), "amordrine_pickaxe");
    public static ItemShovelBase amordrine_shovel = new ItemShovelBase(ModMetals.AMORDRINE.getToolMaterial(), "amordrine_shovel");
    public static ItemSwordBase amordrine_sword = new ItemSwordBase(ModMetals.AMORDRINE.getToolMaterial(), "amordrine_sword");

    public static ItemAxeBase angmallen_axe = new ItemAxeBase(ModMetals.ANGMALLEN.getToolMaterial(), "angmallen_axe");
    public static ItemHoeBase angmallen_hoe = new ItemHoeBase(ModMetals.ANGMALLEN.getToolMaterial(), "angmallen_hoe");
    public static ItemPickaxeBase angmallen_pickaxe = new ItemPickaxeBase(ModMetals.ANGMALLEN.getToolMaterial(), "angmallen_pickaxe");
    public static ItemShovelBase angmallen_shovel = new ItemShovelBase(ModMetals.ANGMALLEN.getToolMaterial(), "angmallen_shovel");
    public static ItemSwordBase angmallen_sword = new ItemSwordBase(ModMetals.ANGMALLEN.getToolMaterial(), "angmallen_sword");

    public static ItemAxeBase astral_silver_axe = new ItemAxeBase(ModMetals.ASTRAL_SILVER.getToolMaterial(), "astral_silver_axe");
    public static ItemHoeBase astral_silver_hoe = new ItemHoeBase(ModMetals.ASTRAL_SILVER.getToolMaterial(), "astral_silver_hoe");
    public static ItemPickaxeBase astral_silver_pickaxe = new ItemPickaxeBase(ModMetals.ASTRAL_SILVER.getToolMaterial(), "astral_silver_pickaxe");
    public static ItemShovelBase astral_silver_shovel = new ItemShovelBase(ModMetals.ASTRAL_SILVER.getToolMaterial(), "astral_silver_shovel");
    public static ItemSwordBase astral_silver_sword = new ItemSwordBase(ModMetals.ASTRAL_SILVER.getToolMaterial(), "astral_silver_sword");

    public static ItemAxeBase atlarus_axe = new ItemAxeBase(ModMetals.ATLARUS.getToolMaterial(), "atlarus_axe");
    public static ItemHoeBase atlarus_hoe = new ItemHoeBase(ModMetals.ATLARUS.getToolMaterial(), "atlarus_hoe");
    public static ItemPickaxeBase atlarus_pickaxe = new ItemPickaxeBase(ModMetals.ATLARUS.getToolMaterial(), "atlarus_pickaxe");
    public static ItemShovelBase atlarus_shovel = new ItemShovelBase(ModMetals.ATLARUS.getToolMaterial(), "atlarus_shovel");
    public static ItemSwordBase atlarus_sword = new ItemSwordBase(ModMetals.ATLARUS.getToolMaterial(), "atlarus_sword");

    public static ItemAxeBase black_steel_axe = new ItemAxeBase(ModMetals.BLACK_STEEL.getToolMaterial(), "black_steel_axe");
    public static ItemHoeBase black_steel_hoe = new ItemHoeBase(ModMetals.BLACK_STEEL.getToolMaterial(), "black_steel_hoe");
    public static ItemPickaxeBase black_steel_pickaxe = new ItemPickaxeBase(ModMetals.BLACK_STEEL.getToolMaterial(), "black_steel_pickaxe");
    public static ItemShovelBase black_steel_shovel = new ItemShovelBase(ModMetals.BLACK_STEEL.getToolMaterial(), "black_steel_shovel");
    public static ItemSwordBase black_steel_sword = new ItemSwordBase(ModMetals.BLACK_STEEL.getToolMaterial(), "black_steel_sword");

    public static ItemAxeBase brass_axe = new ItemAxeBase(ModMetals.BRASS.getToolMaterial(), "brass_axe");
    public static ItemHoeBase brass_hoe = new ItemHoeBase(ModMetals.BRASS.getToolMaterial(), "brass_hoe");
    public static ItemPickaxeBase brass_pickaxe = new ItemPickaxeBase(ModMetals.BRASS.getToolMaterial(), "brass_pickaxe");
    public static ItemShovelBase brass_shovel = new ItemShovelBase(ModMetals.BRASS.getToolMaterial(), "brass_shovel");
    public static ItemSwordBase brass_sword = new ItemSwordBase(ModMetals.BRASS.getToolMaterial(), "brass_sword");

    public static ItemAxeBase bronze_axe = new ItemAxeBase(ModMetals.BRONZE.getToolMaterial(), "bronze_axe");
    public static ItemHoeBase bronze_hoe = new ItemHoeBase(ModMetals.BRONZE.getToolMaterial(), "bronze_hoe");
    public static ItemPickaxeBase bronze_pickaxe = new ItemPickaxeBase(ModMetals.BRONZE.getToolMaterial(), "bronze_pickaxe");
    public static ItemShovelBase bronze_shovel = new ItemShovelBase(ModMetals.BRONZE.getToolMaterial(), "bronze_shovel");
    public static ItemSwordBase bronze_sword = new ItemSwordBase(ModMetals.BRONZE.getToolMaterial(), "bronze_sword");

    public static ItemAxeBase carmot_axe = new ItemAxeBase(ModMetals.CARMOT.getToolMaterial(), "carmot_axe");
    public static ItemHoeBase carmot_hoe = new ItemHoeBase(ModMetals.CARMOT.getToolMaterial(), "carmot_hoe");
    public static ItemPickaxeBase carmot_pickaxe = new ItemPickaxeBase(ModMetals.CARMOT.getToolMaterial(), "carmot_pickaxe");
    public static ItemShovelBase carmot_shovel = new ItemShovelBase(ModMetals.CARMOT.getToolMaterial(), "carmot_shovel");
    public static ItemSwordBase carmot_sword = new ItemSwordBase(ModMetals.CARMOT.getToolMaterial(), "carmot_sword");

    public static ItemAxeBase celenegil_axe = new ItemAxeBase(ModMetals.CELENEGIL.getToolMaterial(), "celenegil_axe");
    public static ItemHoeBase celenegil_hoe = new ItemHoeBase(ModMetals.CELENEGIL.getToolMaterial(), "celenegil_hoe");
    public static ItemPickaxeBase celenegil_pickaxe = new ItemPickaxeBase(ModMetals.CELENEGIL.getToolMaterial(), "celenegil_pickaxe");
    public static ItemShovelBase celenegil_shovel = new ItemShovelBase(ModMetals.CELENEGIL.getToolMaterial(), "celenegil_shovel");
    public static ItemSwordBase celenegil_sword = new ItemSwordBase(ModMetals.CELENEGIL.getToolMaterial(), "celenegil_sword").setTooltip(Tooltips.CELENEGIL_SWORD_EFFECT);

    public static ItemAxeBase ceruclase_axe = new ItemAxeBase(ModMetals.CERUCLASE.getToolMaterial(), "ceruclase_axe");
    public static ItemHoeBase ceruclase_hoe = new ItemHoeBase(ModMetals.CERUCLASE.getToolMaterial(), "ceruclase_hoe");
    public static ItemPickaxeBase ceruclase_pickaxe = new ItemPickaxeBase(ModMetals.CERUCLASE.getToolMaterial(), "ceruclase_pickaxe");
    public static ItemShovelBase ceruclase_shovel = new ItemShovelBase(ModMetals.CERUCLASE.getToolMaterial(), "ceruclase_shovel");
    public static ItemSwordBase ceruclase_sword = new ItemSwordBase(ModMetals.CERUCLASE.getToolMaterial(), "ceruclase_sword").setTooltip(Tooltips.CERUCLASE_SWORD_EFFECT);

    public static ItemAxeBase copper_axe = new ItemAxeBase(ModMetals.COPPER.getToolMaterial(), "copper_axe");
    public static ItemHoeBase copper_hoe = new ItemHoeBase(ModMetals.COPPER.getToolMaterial(), "copper_hoe");
    public static ItemPickaxeBase copper_pickaxe = new ItemPickaxeBase(ModMetals.COPPER.getToolMaterial(), "copper_pickaxe");
    public static ItemShovelBase copper_shovel = new ItemShovelBase(ModMetals.COPPER.getToolMaterial(), "copper_shovel");
    public static ItemSwordBase copper_sword = new ItemSwordBase(ModMetals.COPPER.getToolMaterial(), "copper_sword");

    public static ItemAxeBase damascus_steel_axe = new ItemAxeBase(ModMetals.DAMASCUS_STEEL.getToolMaterial(), "damascus_steel_axe");
    public static ItemHoeBase damascus_steel_hoe = new ItemHoeBase(ModMetals.DAMASCUS_STEEL.getToolMaterial(), "damascus_steel_hoe");
    public static ItemPickaxeBase damascus_steel_pickaxe = new ItemPickaxeBase(ModMetals.DAMASCUS_STEEL.getToolMaterial(), "damascus_steel_pickaxe").setTooltip( Tooltips.DEEP_IRON_PICKAXE_EFFECT);
    public static ItemShovelBase damascus_steel_shovel = new ItemShovelBase(ModMetals.DAMASCUS_STEEL.getToolMaterial(), "damascus_steel_shovel");
    public static ItemSwordBase damascus_steel_sword = new ItemSwordBase(ModMetals.DAMASCUS_STEEL.getToolMaterial(), "damascus_steel_sword");

    public static ItemAxeBase deep_iron_axe = new ItemAxeBase(ModMetals.DEEP_IRON.getToolMaterial(), "deep_iron_axe");
    public static ItemHoeBase deep_iron_hoe = new ItemHoeBase(ModMetals.DEEP_IRON.getToolMaterial(), "deep_iron_hoe");
    public static ItemPickaxeBase deep_iron_pickaxe = new ItemPickaxeBase(ModMetals.DEEP_IRON.getToolMaterial(), "deep_iron_pickaxe");
    public static ItemShovelBase deep_iron_shovel = new ItemShovelBase(ModMetals.DEEP_IRON.getToolMaterial(), "deep_iron_shovel");
    public static ItemSwordBase deep_iron_sword = new ItemSwordBase(ModMetals.DEEP_IRON.getToolMaterial(), "deep_iron_sword");

    public static ItemAxeBase desichalkos_axe = new ItemAxeBase(ModMetals.DESICHALKOS.getToolMaterial(), "desichalkos_axe");
    public static ItemHoeBase desichalkos_hoe = new ItemHoeBase(ModMetals.DESICHALKOS.getToolMaterial(), "desichalkos_hoe");
    public static ItemPickaxeBase desichalkos_pickaxe = new ItemPickaxeBase(ModMetals.DESICHALKOS.getToolMaterial(), "desichalkos_pickaxe");
    public static ItemShovelBase desichalkos_shovel = new ItemShovelBase(ModMetals.DESICHALKOS.getToolMaterial(), "desichalkos_shovel");
    public static ItemSwordBase desichalkos_sword = new ItemSwordBase(ModMetals.DESICHALKOS.getToolMaterial(), "desichalkos_sword").setTooltip(Tooltips.DESICHALKOS_SWORD_EFFECT);

    public static ItemAxeBase electrum_axe = new ItemAxeBase(ModMetals.ELECTRUM.getToolMaterial(), "electrum_axe");
    public static ItemHoeBase electrum_hoe = new ItemHoeBase(ModMetals.ELECTRUM.getToolMaterial(), "electrum_hoe");
    public static ItemPickaxeBase electrum_pickaxe = new ItemPickaxeBase(ModMetals.ELECTRUM.getToolMaterial(), "electrum_pickaxe");
    public static ItemShovelBase electrum_shovel = new ItemShovelBase(ModMetals.ELECTRUM.getToolMaterial(), "electrum_shovel");
    public static ItemSwordBase electrum_sword = new ItemSwordBase(ModMetals.ELECTRUM.getToolMaterial(), "electrum_sword");

    public static ItemAxeBase eximite_axe = new ItemAxeBase(ModMetals.EXIMITE.getToolMaterial(), "eximite_axe");
    public static ItemHoeBase eximite_hoe = new ItemHoeBase(ModMetals.EXIMITE.getToolMaterial(), "eximite_hoe");
    public static ItemPickaxeBase eximite_pickaxe = new ItemPickaxeBase(ModMetals.EXIMITE.getToolMaterial(), "eximite_pickaxe");
    public static ItemShovelBase eximite_shovel = new ItemShovelBase(ModMetals.EXIMITE.getToolMaterial(), "eximite_shovel");
    public static ItemSwordBase eximite_sword = new ItemSwordBase(ModMetals.EXIMITE.getToolMaterial(), "eximite_sword");

    public static ItemAxeBase haderoth_axe = new ItemAxeBase(ModMetals.HADEROTH.getToolMaterial(), "haderoth_axe");
    public static ItemHoeBase haderoth_hoe = new ItemHoeBase(ModMetals.HADEROTH.getToolMaterial(), "haderoth_hoe");
    public static ItemPickaxeBase haderoth_pickaxe = new ItemPickaxeBase(ModMetals.HADEROTH.getToolMaterial(), "haderoth_pickaxe");
    public static ItemShovelBase haderoth_shovel = new ItemShovelBase(ModMetals.HADEROTH.getToolMaterial(), "haderoth_shovel");
    public static ItemSwordBase haderoth_sword = new ItemSwordBase(ModMetals.HADEROTH.getToolMaterial(), "haderoth_sword");

    public static ItemAxeBase hepatizon_axe = new ItemAxeBase(ModMetals.HEPATIZON.getToolMaterial(), "hepatizon_axe");
    public static ItemHoeBase hepatizon_hoe = new ItemHoeBase(ModMetals.HEPATIZON.getToolMaterial(), "hepatizon_hoe");
    public static ItemPickaxeBase hepatizon_pickaxe = new ItemPickaxeBase(ModMetals.HEPATIZON.getToolMaterial(), "hepatizon_pickaxe");
    public static ItemShovelBase hepatizon_shovel = new ItemShovelBase(ModMetals.HEPATIZON.getToolMaterial(), "hepatizon_shovel");
    public static ItemSwordBase hepatizon_sword = new ItemSwordBase(ModMetals.HEPATIZON.getToolMaterial(), "hepatizon_sword");

    public static ItemAxeBase ignatius_axe = new ItemAxeBase(ModMetals.IGNATIUS.getToolMaterial(), "ignatius_axe");
    public static ItemHoeBase ignatius_hoe = new ItemHoeBase(ModMetals.IGNATIUS.getToolMaterial(), "ignatius_hoe");
    public static ItemPickaxeBase ignatius_pickaxe = new ItemPickaxeBase(ModMetals.IGNATIUS.getToolMaterial(), "ignatius_pickaxe");
    public static ItemShovelBase ignatius_shovel = new ItemShovelBase(ModMetals.IGNATIUS.getToolMaterial(), "ignatius_shovel");
    public static ItemSwordBase ignatius_sword = new ItemSwordBase(ModMetals.IGNATIUS.getToolMaterial(), "ignatius_sword").setTooltip(Tooltips.IGNATIUS_SWORD_EFFECT);

    public static ItemAxeBase inolashite_axe = new ItemAxeBase(ModMetals.INOLASHITE.getToolMaterial(), "inolashite_axe");
    public static ItemHoeBase inolashite_hoe = new ItemHoeBase(ModMetals.INOLASHITE.getToolMaterial(), "inolashite_hoe");
    public static ItemPickaxeBase inolashite_pickaxe = new ItemPickaxeBase(ModMetals.INOLASHITE.getToolMaterial(), "inolashite_pickaxe");
    public static ItemShovelBase inolashite_shovel = new ItemShovelBase(ModMetals.INOLASHITE.getToolMaterial(), "inolashite_shovel");
    public static ItemSwordBase inolashite_sword = new ItemSwordBase(ModMetals.INOLASHITE.getToolMaterial(), "inolashite_sword");

    public static ItemAxeBase kalendrite_axe = new ItemAxeBase(ModMetals.KALENDRITE.getToolMaterial(), "kalendrite_axe");
    public static ItemHoeBase kalendrite_hoe = new ItemHoeBase(ModMetals.KALENDRITE.getToolMaterial(), "kalendrite_hoe");
    public static ItemPickaxeBase kalendrite_pickaxe = new ItemPickaxeBase(ModMetals.KALENDRITE.getToolMaterial(), "kalendrite_pickaxe");
    public static ItemShovelBase kalendrite_shovel = new ItemShovelBase(ModMetals.KALENDRITE.getToolMaterial(), "kalendrite_shovel");
    public static ItemSwordBase kalendrite_sword = new ItemSwordBase(ModMetals.KALENDRITE.getToolMaterial(), "kalendrite_sword").setTooltip(Tooltips.KALENDRITE_SWORD_EFFECT);

    public static ItemAxeBase krik_axe = new ItemAxeBase(ModMetals.KRIK.getToolMaterial(), "krik_axe");
    public static ItemHoeBase krik_hoe = new ItemHoeBase(ModMetals.KRIK.getToolMaterial(), "krik_hoe");
    public static ItemPickaxeBase krik_pickaxe = new ItemPickaxeBase(ModMetals.KRIK.getToolMaterial(), "krik_pickaxe");
    public static ItemShovelBase krik_shovel = new ItemShovelBase(ModMetals.KRIK.getToolMaterial(), "krik_shovel");
    public static ItemSwordBase krik_sword = new ItemSwordBase(ModMetals.KRIK.getToolMaterial(), "krik_sword");

    public static ItemAxeBase midasium_axe = new ItemAxeBase(ModMetals.MIDASIUM.getToolMaterial(), "midasium_axe").setTooltip(Tooltips.MIDASIUM_TOOL_EFFECT);
    public static ItemHoeBase midasium_hoe = new ItemHoeBase(ModMetals.MIDASIUM.getToolMaterial(), "midasium_hoe");
    public static ItemPickaxeBase midasium_pickaxe = new ItemPickaxeBase(ModMetals.MIDASIUM.getToolMaterial(), "midasium_pickaxe").setTooltip(Tooltips.MIDASIUM_TOOL_EFFECT);
    public static ItemShovelBase midasium_shovel = new ItemShovelBase(ModMetals.MIDASIUM.getToolMaterial(), "midasium_shovel").setTooltip(Tooltips.MIDASIUM_TOOL_EFFECT);
    public static ItemSwordBase midasium_sword = new ItemSwordBase(ModMetals.MIDASIUM.getToolMaterial(), "midasium_sword").setTooltip(Tooltips.MIDASIUM_TOOL_EFFECT);

    public static ItemAxeBase mithril_axe = new ItemAxeBase(ModMetals.MITHRIL.getToolMaterial(), "mithril_axe");
    public static ItemHoeBase mithril_hoe = new ItemHoeBase(ModMetals.MITHRIL.getToolMaterial(), "mithril_hoe");
    public static ItemPickaxeBase mithril_pickaxe = new ItemPickaxeBase(ModMetals.MITHRIL.getToolMaterial(), "mithril_pickaxe");
    public static ItemShovelBase mithril_shovel = new ItemShovelBase(ModMetals.MITHRIL.getToolMaterial(), "mithril_shovel");
    public static ItemSwordBase mithril_sword = new ItemSwordBase(ModMetals.MITHRIL.getToolMaterial(), "mithril_sword").setTooltip(Tooltips.MITHRIL_SWORD_EFFECT);

    public static ItemAxeBase orichalcum_axe = new ItemAxeBase(ModMetals.ORICHALCUM.getToolMaterial(), "orichalcum_axe");
    public static ItemHoeBase orichalcum_hoe = new ItemHoeBase(ModMetals.ORICHALCUM.getToolMaterial(), "orichalcum_hoe");
    public static ItemPickaxeBase orichalcum_pickaxe = new ItemPickaxeBase(ModMetals.ORICHALCUM.getToolMaterial(), "orichalcum_pickaxe");
    public static ItemShovelBase orichalcum_shovel = new ItemShovelBase(ModMetals.ORICHALCUM.getToolMaterial(), "orichalcum_shovel");
    public static ItemSwordBase orichalcum_sword = new ItemSwordBase(ModMetals.ORICHALCUM.getToolMaterial(), "orichalcum_sword").setTooltip(Tooltips.ORICHALCUM_SWORD_EFFECT);

    public static ItemAxeBase oureclase_axe = new ItemAxeBase(ModMetals.OURECLASE.getToolMaterial(), "oureclase_axe");
    public static ItemHoeBase oureclase_hoe = new ItemHoeBase(ModMetals.OURECLASE.getToolMaterial(), "oureclase_hoe");
    public static ItemPickaxeBase oureclase_pickaxe = new ItemPickaxeBase(ModMetals.OURECLASE.getToolMaterial(), "oureclase_pickaxe");
    public static ItemShovelBase oureclase_shovel = new ItemShovelBase(ModMetals.OURECLASE.getToolMaterial(), "oureclase_shovel");
    public static ItemSwordBase oureclase_sword = new ItemSwordBase(ModMetals.OURECLASE.getToolMaterial(), "oureclase_sword");

    public static ItemAxeBase platinum_axe = new ItemAxeBase(ModMetals.PLATINUM.getToolMaterial(), "platinum_axe");
    public static ItemHoeBase platinum_hoe = new ItemHoeBase(ModMetals.PLATINUM.getToolMaterial(), "platinum_hoe");
    public static ItemPickaxeBase platinum_pickaxe = new ItemPickaxeBase(ModMetals.PLATINUM.getToolMaterial(), "platinum_pickaxe");
    public static ItemShovelBase platinum_shovel = new ItemShovelBase(ModMetals.PLATINUM.getToolMaterial(), "platinum_shovel");
    public static ItemSwordBase platinum_sword = new ItemSwordBase(ModMetals.PLATINUM.getToolMaterial(), "platinum_sword");

    public static ItemAxeBase prometheum_axe = new ItemAxeBase(ModMetals.PROMETHEUM.getToolMaterial(), "prometheum_axe");
    public static ItemHoeBase prometheum_hoe = new ItemHoeBase(ModMetals.PROMETHEUM.getToolMaterial(), "prometheum_hoe");
    public static ItemPickaxeBase prometheum_pickaxe = new ItemPickaxeBase(ModMetals.PROMETHEUM.getToolMaterial(), "prometheum_pickaxe");
    public static ItemShovelBase prometheum_shovel = new ItemShovelBase(ModMetals.PROMETHEUM.getToolMaterial(), "prometheum_shovel");
    public static ItemSwordBase prometheum_sword = new ItemSwordBase(ModMetals.PROMETHEUM.getToolMaterial(), "prometheum_sword");

    public static ItemAxeBase quicksilver_axe = new ItemAxeBase(ModMetals.QUICKSILVER.getToolMaterial(), "quicksilver_axe");
    public static ItemHoeBase quicksilver_hoe = new ItemHoeBase(ModMetals.QUICKSILVER.getToolMaterial(), "quicksilver_hoe");
    public static ItemPickaxeBase quicksilver_pickaxe = new ItemPickaxeBase(ModMetals.QUICKSILVER.getToolMaterial(), "quicksilver_pickaxe");
    public static ItemShovelBase quicksilver_shovel = new ItemShovelBase(ModMetals.QUICKSILVER.getToolMaterial(), "quicksilver_shovel");
    public static ItemSwordBase quicksilver_sword = new ItemSwordBase(ModMetals.QUICKSILVER.getToolMaterial(), "quicksilver_sword");

    public static ItemAxeBase sanguinite_axe = new ItemAxeBase(ModMetals.SANGUINITE.getToolMaterial(), "sanguinite_axe");
    public static ItemHoeBase sanguinite_hoe = new ItemHoeBase(ModMetals.SANGUINITE.getToolMaterial(), "sanguinite_hoe");
    public static ItemPickaxeBase sanguinite_pickaxe = new ItemPickaxeBase(ModMetals.SANGUINITE.getToolMaterial(), "sanguinite_pickaxe");
    public static ItemShovelBase sanguinite_shovel = new ItemShovelBase(ModMetals.SANGUINITE.getToolMaterial(), "sanguinite_shovel");
    public static ItemSwordBase sanguinite_sword = new ItemSwordBase(ModMetals.SANGUINITE.getToolMaterial(), "sanguinite_sword").setTooltip(Tooltips.SANGUINITE_SWORD_EFFECT);

    public static ItemAxeBase shadow_iron_axe = new ItemAxeBase(ModMetals.SHADOW_IRON.getToolMaterial(), "shadow_iron_axe");
    public static ItemHoeBase shadow_iron_hoe = new ItemHoeBase(ModMetals.SHADOW_IRON.getToolMaterial(), "shadow_iron_hoe");
    public static ItemPickaxeBase shadow_iron_pickaxe = new ItemPickaxeBase(ModMetals.SHADOW_IRON.getToolMaterial(), "shadow_iron_pickaxe");
    public static ItemShovelBase shadow_iron_shovel = new ItemShovelBase(ModMetals.SHADOW_IRON.getToolMaterial(), "shadow_iron_shovel");
    public static ItemSwordBase shadow_iron_sword = new ItemSwordBase(ModMetals.SHADOW_IRON.getToolMaterial(), "shadow_iron_sword").setTooltip(Tooltips.SHADOW_IRON_SWORD_EFFECT);

    public static ItemAxeBase shadow_steel_axe = new ItemAxeBase(ModMetals.SHADOW_STEEL.getToolMaterial(), "shadow_steel_axe").setTooltip(Tooltips.SHADOW_STEEL_TOOL_EFFECT);
    public static ItemHoeBase shadow_steel_hoe = new ItemHoeBase(ModMetals.SHADOW_STEEL.getToolMaterial(), "shadow_steel_hoe");
    public static ItemPickaxeBase shadow_steel_pickaxe = new ItemPickaxeBase(ModMetals.SHADOW_STEEL.getToolMaterial(), "shadow_steel_pickaxe").setTooltip(Tooltips.SHADOW_STEEL_TOOL_EFFECT);
    public static ItemShovelBase shadow_steel_shovel = new ItemShovelBase(ModMetals.SHADOW_STEEL.getToolMaterial(), "shadow_steel_shovel").setTooltip(Tooltips.SHADOW_STEEL_TOOL_EFFECT);
    public static ItemSwordBase shadow_steel_sword = new ItemSwordBase(ModMetals.SHADOW_STEEL.getToolMaterial(), "shadow_steel_sword").setTooltip(Tooltips.SHADOW_STEEL_SWORD_EFFECT);

    public static ItemAxeBase silver_axe = new ItemAxeBase(ModMetals.SILVER.getToolMaterial(), "silver_axe");
    public static ItemHoeBase silver_hoe = new ItemHoeBase(ModMetals.SILVER.getToolMaterial(), "silver_hoe");
    public static ItemPickaxeBase silver_pickaxe = new ItemPickaxeBase(ModMetals.SILVER.getToolMaterial(), "silver_pickaxe");
    public static ItemShovelBase silver_shovel = new ItemShovelBase(ModMetals.SILVER.getToolMaterial(), "silver_shovel");
    public static ItemSwordBase silver_sword = new ItemSwordBase(ModMetals.SILVER.getToolMaterial(), "silver_sword");

    public static ItemAxeBase steel_axe = new ItemAxeBase(ModMetals.STEEL.getToolMaterial(), "steel_axe");
    public static ItemHoeBase steel_hoe = new ItemHoeBase(ModMetals.STEEL.getToolMaterial(), "steel_hoe");
    public static ItemPickaxeBase steel_pickaxe = new ItemPickaxeBase(ModMetals.STEEL.getToolMaterial(), "steel_pickaxe");
    public static ItemShovelBase steel_shovel = new ItemShovelBase(ModMetals.STEEL.getToolMaterial(), "steel_shovel");
    public static ItemSwordBase steel_sword = new ItemSwordBase(ModMetals.STEEL.getToolMaterial(), "steel_sword");

    public static ItemAxeBase tartarite_axe = new ItemAxeBase(ModMetals.TARTARITE.getToolMaterial(), "tartarite_axe");
    public static ItemHoeBase tartarite_hoe = new ItemHoeBase(ModMetals.TARTARITE.getToolMaterial(), "tartarite_hoe");
    public static ItemPickaxeBase tartarite_pickaxe = new ItemPickaxeBase(ModMetals.TARTARITE.getToolMaterial(), "tartarite_pickaxe");
    public static ItemShovelBase tartarite_shovel = new ItemShovelBase(ModMetals.TARTARITE.getToolMaterial(), "tartarite_shovel");
    public static ItemSwordBase tartarite_sword = new ItemSwordBase(ModMetals.TARTARITE.getToolMaterial(), "tartarite_sword").setTooltip(Tooltips.TARTARITE_SWORD_EFFECT);

    public static ItemAxeBase vulcanite_axe = new ItemAxeBase(ModMetals.VULCANITE.getToolMaterial(), "vulcanite_axe");
    public static ItemHoeBase vulcanite_hoe = new ItemHoeBase(ModMetals.VULCANITE.getToolMaterial(), "vulcanite_hoe");
    public static ItemPickaxeBase vulcanite_pickaxe = new ItemPickaxeBase(ModMetals.VULCANITE.getToolMaterial(), "vulcanite_pickaxe");
    public static ItemShovelBase vulcanite_shovel = new ItemShovelBase(ModMetals.VULCANITE.getToolMaterial(), "vulcanite_shovel");
    public static ItemSwordBase vulcanite_sword = new ItemSwordBase(ModMetals.VULCANITE.getToolMaterial(), "vulcanite_sword").setTooltip(Tooltips.VULCANITE_SWORD_EFFECT);

    public static ItemAxeBase vyroxeres_axe = new ItemAxeBase(ModMetals.VYROXERES.getToolMaterial(), "vyroxeres_axe");
    public static ItemHoeBase vyroxeres_hoe = new ItemHoeBase(ModMetals.VYROXERES.getToolMaterial(), "vyroxeres_hoe");
    public static ItemPickaxeBase vyroxeres_pickaxe = new ItemPickaxeBase(ModMetals.VYROXERES.getToolMaterial(), "vyroxeres_pickaxe");
    public static ItemShovelBase vyroxeres_shovel = new ItemShovelBase(ModMetals.VYROXERES.getToolMaterial(), "vyroxeres_shovel");
    public static ItemSwordBase vyroxeres_sword = new ItemSwordBase(ModMetals.VYROXERES.getToolMaterial(), "vyroxeres_sword").setTooltip(Tooltips.VYROXERES_SWORD_EFFECT);

    public static void register(IForgeRegistry<Item> registry){

        if (!GeneralConfig.disableAllTools) {

            int c = 0;
            for (int i = 0; i < ToolConfig.allTools.length; i++) {
                String toolSet = Utils.modMaterialNames[i];

                for (int j = 0; j < 5; j++) {

                    System.out.println(toolList.get(i + j).getRegistryName() + "-" + toolSet + " boolean: " + ToolConfig.allTools[i][j]);

                    if (ToolConfig.allTools[i][j])
                        registry.register(toolList.get(c));

                    c++;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
        if (!GeneralConfig.disableAllTools)
        {
            int c = 0;

            for (int i = 0; i < ToolConfig.allTools.length; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    IHasModel item = (IHasModel) toolList.get(c);

                    if (ToolConfig.allTools[i][j])
                        ModelLoader.setCustomModelResourceLocation((Item) item, 0, new ModelResourceLocation(Metallurgy.MODID + ":" + item.getCategory() + "/", "inventory"));

                    c++;

                }
            }
        }
    }

    protected static boolean isAxeEffectActive(ItemAxeBase tool)
    {
        String material = tool.getToolMaterialName();

        switch (material)
        {
            case "SHADOW_STEEL":
                if (ToolEffectsConfig.shadowSteelToolSpeedEffect)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    protected static boolean isPickaxeEffectActive(ItemPickaxeBase tool)
    {
        String material = tool.getToolMaterialName();

        switch (material)
        {
            case "DEEP_IRON":
                if (ToolEffectsConfig.deepIronPickaxeEffect)
                    return true;
                break;
            case "SHADOW_STEEL":
                if (ToolEffectsConfig.shadowSteelToolSpeedEffect)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    protected static boolean isShovelEffectActive(ItemShovelBase tool)
    {
        String material = tool.getToolMaterialName();

        switch (material)
        {
            case "SHADOW_STEEL":
                if (ToolEffectsConfig.shadowSteelToolSpeedEffect)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }

    protected static boolean isSwordEffectActive(ItemSwordBase tool)
    {
        String material = tool.getToolMaterialName();

        switch (material)
        {
            case "DEEP_IRON":
                if(ToolEffectsConfig.deepIronSwordEffect)
                    return true;
                break;
            case "DESICHALKOS":
                if (ToolEffectsConfig.desichalkosSwordEffect)
                    return true;
                break;
            case "IGNATIUS":
                if (ToolEffectsConfig.ignatiusSwordEffect)
                    return true;
                break;
            case "KALENDRITE":
                if (ToolEffectsConfig.kalendriteSwordEffect)
                    return true;
                break;
            case "SHADOW_IRON":
                if (ToolEffectsConfig.shadowIronSwordEffect)
                    return true;
                break;
            case "SHADOW_STEEL":
                if (ToolEffectsConfig.shadowSteelSwordEffect)
                    return true;
                break;
            case "TARTARITE":
                if (ToolEffectsConfig.tartariteSwordEffect)
                    return true;
                break;
            case "VULCANITE":
                if (ToolEffectsConfig.vulcaniteSwordEffect)
                    return true;
                break;
            case "VYROXERES":
                if (ToolEffectsConfig.vyroxeresSwordEffect)
                    return true;
                break;
            default:
                return false;
        }
        return false;
    }
}