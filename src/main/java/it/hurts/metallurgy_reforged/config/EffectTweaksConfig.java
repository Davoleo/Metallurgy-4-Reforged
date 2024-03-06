/*==============================================================================
 = Class: EffectTweaksConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2023.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.effect_tweaks")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "effect_tweaks")
public class EffectTweaksConfig {

    @Config.Name("Hot-Blooded Drinking Damage")
    @Config.Comment("Set to false to disable damage after drinking")
    public static boolean enableIgnatiusDrinkDamage = true;

    @Config.Name("Ethereal BlackList")
    @Config.Comment("You can blacklist blocks that players cannot clip through")
    public static String[] etheriumEffectArmorBlacklist = new String[]{"minecraft:bedrock"};

    @Config.Name("Chaos Crit Damage Upper Bound")
    @Config.Comment("Chaos Crit damage multiplier is a random number between 1.2 and this value")
    @Config.RangeDouble(min = 1.2, max = 50)
    public static double shadowIronWeaponCritUpperBound = 3.2;

}
