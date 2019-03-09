/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorEffectsConfig
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.armor_effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "armor")
public class ArmorEffectsConfig {

    //Armors
    @Config.Name("Adamantine ArmorEffectHandler Effect")
    @Config.Comment("Consumes Experience to fill the hunger bar")
    public static boolean adamantineArmorEffect = true;
    @Config.Name("Amordrine ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Strength II")
    public static boolean amordrineArmorEffect = true;
    @Config.Name("Angmallen ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Luck")
    public static boolean angmallenArmorEffect = true;
    @Config.Name("Astral Silver ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Jump Boost")
    public static boolean astralSilverArmorEffect = true;
    @Config.Name("Carmot ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Haste")
    public static boolean carmotArmorEffect = true;
    @Config.Name("Celenegil ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Resistance II")
    public static boolean celenegilArmorEffect = true;
    @Config.Name("Deep Iron ArmorEffectHandler Effect")
    @Config.Comment("You move faster underwater")
    public static boolean deepIronArmorEffect = true;
    @Config.Name("Eximite Helmet Effect")
    @Config.Comment("Endermen won't notice you when watch them")
    public static boolean eximiteArmorEffect = true;
    @Config.Name("Kalendrite ArmorEffectHandler Effect")
    @Config.Comment("Full ArmorEffectHandler gives Strength I")
    public static boolean kaledriteArmorEffect = true;
    @Config.Name("Mithril ArmorEffectHandler Effect")
    @Config.Comment("Makes Entities around you glow")
    public static boolean mithrilArmorEffect = true;
    @Config.Name("Platinum ArmorEffectHandler Effect")
    @Config.Comment("The helmet gives you night vision")
    public static boolean platinumArmorEffect = true;
    @Config.Name("Prometheum ArmorEffectHandler Effect")
    @Config.Comment("Removes poison effect whenever you get it")
    public static boolean prometheumArmorEffect = true;
    @Config.Name("Quicksilver ArmorEffectHandler Effect")
    @Config.Comment("Movements speed up")
    public static boolean quicksilverArmorEffect = true;
    @Config.Name("Shadow Steel ArmorEffectHandler Effect")
    @Config.Comment("Absorbed damage is proportional to the darkness")
    public static boolean shadowSteelArmorEffect = true;
    @Config.Name("Vulcanite ArmorEffectHandler Effect")
    @Config.Comment("You can't take damage from fire")
    public static boolean vulcaniteArmorEffect = true;

    private ArmorEffectsConfig(){}

}
