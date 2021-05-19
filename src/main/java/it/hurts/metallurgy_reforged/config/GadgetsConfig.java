/*==============================================================================
 = Class: GadgetsConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

public class GadgetsConfig {

    @Config.LangKey("config.metallurgy.category.rubracium_gauntlet")
    @Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/gadgets", category = "gauntlet")
    public static class Gauntlet {

        @Config.Name("Gauntlet Durability")
        @Config.Comment("Sets the Maximum Durability for the Rubracium Gauntlet")
        @Config.RequiresMcRestart
        public static int gauntletMaxDamage = 750;

        @Config.Name("Attack Modifier")
        @Config.Comment("Sets the gauntlet attack damage")
        public static double gauntletAttackDamage = 3D;

        @Config.Name("Attack Speed Modifier")
        @Config.Comment("We suggest to set the value to 16 or else the process bar will be rendered")
        @Config.RangeDouble(min = -3D, max = 16D)
        public static double gauntletAttackSpeed = 16D;

        @Config.Name("Gauntlet Hunger Modifier")
        @Config.Comment("Sets the gauntlet hunger modifier [ 1 = 0.5 ]")
        @Config.RangeInt(min = 0)
        public static int gauntletHungerModifier = 1;

        @Config.Name("Gauntlet Block Damage")
        @Config.Comment(value = "Sets the damage dealt to the target by being hit on the block")
        @Config.RangeDouble(min = 0)
        public static double gauntletBlockDamageModifier = 0.5;

        @Config.Name("Gauntlet disable block griefing")
        @Config.Comment("Set to true for disable block griefing")
        public static boolean disableBlockGriefing = false;

    }

    @Config.LangKey("config.metallurgy.category.phosphorus_lamp")
    @Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/gadgets", category = "phosphorus_lamp")
    public static class PhosphorusLamp {

        @Config.Name("Lantern entity collision")
        @Config.Comment("Set this to false to disable entity collisions with lantern")
        public static boolean enableLanternCollision = true;

        @Config.Name("Phosphorus Lantern Light Level")
        @Config.Comment("Sets how bright the lantern should be")
        public static int lanternLightLevel = 8;

    }

    @Config.RequiresMcRestart
    @Config.LangKey("config.metallurgy.category.brass_knuckles")
    @Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/gadgets", category = "brass_knuckles")
    public static class BrassKnuckles {

        @Config.Name("Brass Knuckles Attack Damage")
        @Config.Comment("Affects the damage for each hit when attacking with brass knuckles | value is summed to default items' damage value")
        public static double attackDamage = 1;

        @Config.Name("Brass Knuckles Attack Speed")
        @Config.Comment("Affects the amount of hits of brass knucles in a certain time lapse | value is summed to default items' damage value")
        public static double attackSpeed = 16;

    }
}
