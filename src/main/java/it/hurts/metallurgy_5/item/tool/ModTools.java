package it.hurts.metallurgy_5.item.tool;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 13.40
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModTools {

    public static ItemAxeBase adamantine_axe = new ItemAxeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_axe");
    public static ItemHoeBase adamantine_hoe = new ItemHoeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_hoe");
    public static ItemPickaxeBase adamantine_pickaxe = new ItemPickaxeBase(Metallurgy_5.adamantineToolMaterial, "adamantine_pickaxe");
    public static ItemShovelBase adamantine_shovel = new ItemShovelBase(Metallurgy_5.adamantineToolMaterial, "adamantine_shovel");
    public static ItemSwordBase adamantine_sword = new ItemSwordBase(Metallurgy_5.adamantineToolMaterial, "adamantine_sword");

    public static ItemAxeBase astral_silver_axe = new ItemAxeBase(Metallurgy_5.astralSilverToolMaterial, "astral_silver_axe");
    public static ItemHoeBase astral_silver_hoe = new ItemHoeBase(Metallurgy_5.astralSilverToolMaterial, "astral_silver_hoe");
    public static ItemPickaxeBase astral_silver_pickaxe = new ItemPickaxeBase(Metallurgy_5.astralSilverToolMaterial, "astral_silver_pickaxe");
    public static ItemShovelBase astral_silver_shovel = new ItemShovelBase(Metallurgy_5.astralSilverToolMaterial, "astral_silver_shovel");
    public static ItemSwordBase astral_silver_sword = new ItemSwordBase(Metallurgy_5.astralSilverToolMaterial, "astral_silver_sword");

    public static ItemAxeBase carmot_axe = new ItemAxeBase(Metallurgy_5.carmotToolMaterial, "carmot_axe");
    public static ItemHoeBase carmot_hoe = new ItemHoeBase(Metallurgy_5.carmotToolMaterial, "carmot_hoe");
    public static ItemPickaxeBase carmot_pickaxe = new ItemPickaxeBase(Metallurgy_5.carmotToolMaterial, "carmot_pickaxe");
    public static ItemShovelBase carmot_shovel = new ItemShovelBase(Metallurgy_5.carmotToolMaterial, "carmot_shovel");
    public static ItemSwordBase carmot_sword = new ItemSwordBase(Metallurgy_5.carmotToolMaterial, "carmot_sword");

    public static ItemAxeBase mithril_axe = new ItemAxeBase(Metallurgy_5.mithrilToolMaterial, "mithril_axe");
    public static ItemHoeBase mithril_hoe = new ItemHoeBase(Metallurgy_5.mithrilToolMaterial, "mithril_hoe");
    public static ItemPickaxeBase mithril_pickaxe = new ItemPickaxeBase(Metallurgy_5.mithrilToolMaterial, "mithril_pickaxe");
    public static ItemShovelBase mithril_shovel = new ItemShovelBase(Metallurgy_5.mithrilToolMaterial, "mithril_shovel");
    public static ItemSwordBase mithril_sword = new ItemSwordBase(Metallurgy_5.mithrilToolMaterial, "mithril_sword");

    public static ItemAxeBase prometheum_axe = new ItemAxeBase(Metallurgy_5.prometheumToolMaterial, "prometheum_axe");
    public static ItemHoeBase prometheum_hoe = new ItemHoeBase(Metallurgy_5.prometheumToolMaterial, "prometheum_hoe");
    public static ItemPickaxeBase prometheum_pickaxe = new ItemPickaxeBase(Metallurgy_5.prometheumToolMaterial, "prometheum_pickaxe");
    public static ItemShovelBase prometheum_shovel = new ItemShovelBase(Metallurgy_5.prometheumToolMaterial, "prometheum_shovel");
    public static ItemSwordBase prometheum_sword = new ItemSwordBase(Metallurgy_5.prometheumToolMaterial, "prometheum_sword");


    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                adamantine_axe,
                adamantine_hoe,
                adamantine_pickaxe,
                adamantine_shovel,
                adamantine_sword,

                astral_silver_axe,
                astral_silver_hoe,
                astral_silver_pickaxe,
                astral_silver_shovel,
                astral_silver_sword,

                carmot_axe,
                carmot_hoe,
                carmot_pickaxe,
                carmot_shovel,
                carmot_sword,

                mithril_axe,
                mithril_hoe,
                mithril_pickaxe,
                mithril_shovel,
                mithril_sword,

                prometheum_axe,
                prometheum_hoe,
                prometheum_pickaxe,
                prometheum_shovel,
                prometheum_sword
        );
    }


    public static void registerModels()
    {
        adamantine_axe.registerItemModel(adamantine_axe, 0);
        adamantine_hoe.registerItemModel(adamantine_hoe, 0);
        adamantine_pickaxe.registerItemModel(adamantine_pickaxe, 0);
        adamantine_shovel.registerItemModel(adamantine_shovel, 0);
        adamantine_sword.registerItemModel(adamantine_sword, 0);

        astral_silver_axe.registerItemModel(astral_silver_axe, 0);
        astral_silver_hoe.registerItemModel(astral_silver_hoe, 0);
        astral_silver_pickaxe.registerItemModel(astral_silver_pickaxe, 0);
        astral_silver_shovel.registerItemModel(astral_silver_shovel, 0);
        astral_silver_sword.registerItemModel(astral_silver_sword, 0);

        carmot_axe.registerItemModel(carmot_axe, 0);
        carmot_hoe.registerItemModel(carmot_hoe, 0);
        carmot_pickaxe.registerItemModel(carmot_pickaxe, 0);
        carmot_shovel.registerItemModel(carmot_shovel, 0);
        carmot_sword.registerItemModel(carmot_sword, 0);

        mithril_axe.registerItemModel(mithril_axe, 0);
        mithril_hoe.registerItemModel(mithril_hoe, 0);
        mithril_pickaxe.registerItemModel(mithril_pickaxe, 0);
        mithril_shovel.registerItemModel(mithril_shovel, 0);
        mithril_sword.registerItemModel(mithril_sword, 0);

        prometheum_axe.registerItemModel(prometheum_axe, 0);
        prometheum_hoe.registerItemModel(prometheum_hoe, 0);
        prometheum_pickaxe.registerItemModel(prometheum_pickaxe, 0);
        prometheum_shovel.registerItemModel(prometheum_shovel, 0);
        prometheum_sword.registerItemModel(prometheum_sword, 0);
    }
}