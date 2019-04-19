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
    @Config.Name("Adamantine Armor Effect")
    @Config.Comment("Consumes Experience to fill the hunger bar")
    public static boolean adamantineArmorEffect = true;
    @Config.Name("Amordrine Armor Effect")
    @Config.Comment("Full Armor gives Strength II")
    public static boolean amordrineArmorEffect = true;
    @Config.Name("Angmallen Armor Effect")
    @Config.Comment("Full Armor gives Luck")
    public static boolean angmallenArmorEffect = true;
    @Config.Name("Astral Silver Armor Effect")
    @Config.Comment("Full Armor gives Jump Boost")
    public static boolean astralSilverArmorEffect = true;
    @Config.Name("Carmot Armor Effect")
    @Config.Comment("Full Armor gives Haste")
    public static boolean carmotArmorEffect = true;
    @Config.Name("Celenegil Armor Effect")
    @Config.Comment("Full Armor gives Resistance II")
    public static boolean celenegilArmorEffect = true;
    @Config.Name("Deep Iron Armor Effect")
    @Config.Comment("You move faster underwater")
    public static boolean deepIronArmorEffect = true;
    @Config.Name("Eximite Helmet Effect")
    @Config.Comment("Endermen won't notice you when watch them")
    public static boolean eximiteArmorEffect = true;
    @Config.Name("Kalendrite Armor Effect")
    @Config.Comment("Full Armor gives Strength I")
    public static boolean kaledriteArmorEffect = true;
    @Config.Name("Krik Armor Effect")
    @Config.Comment("Full Armor makes you float around at a certain height that depends from how full the inventory is")
    public static boolean krikArmorEffect = true;
    @Config.Name("Mithril Armor Effect")
    @Config.Comment("Makes Entities around you glow")
    public static boolean mithrilArmorEffect = true;
    @Config.Name("Platinum Armor Effect")
    @Config.Comment("The helmet gives you night vision")
    public static boolean platinumArmorEffect = true;
    @Config.Name("Prometheum Armor Effect")
    @Config.Comment("Removes poison effect whenever you get it")
    public static boolean prometheumArmorEffect = true;
    @Config.Name("Quicksilver Armor Effect")
    @Config.Comment("Movements speed up")
    public static boolean quicksilverArmorEffect = true;
    @Config.Name("Shadow Steel Armor Effect")
    @Config.Comment("Absorbed damage is proportional to the darkness")
    public static boolean shadowSteelArmorEffect = true;
    @Config.Name("Vulcanite Armor Effect")
    @Config.Comment("You can't take damage from fire")
    public static boolean vulcaniteArmorEffect = true;

    private ArmorEffectsConfig(){}

}
