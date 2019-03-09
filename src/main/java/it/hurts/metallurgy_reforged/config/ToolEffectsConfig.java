/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ToolEffectsConfig
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

@Config.LangKey("config.metallurgy.category.tool_effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "tools")
public class ToolEffectsConfig {

    //Pickaxes
    @Config.Name("Deep Iron PickaxeEffectHandler Effect")
    @Config.Comment("Underwater mining is not slowed down")
    public static boolean deepIronPickaxeEffect = true;

    //Tools
    @Config.Name("Shadow Steel Tools Effect")
    @Config.Comment("The tool speed is proportional to the darkness")
    public static boolean shadowSteelToolSpeedEffect = true;

    //Swords
    @Config.Name("Desichalkos SwordEffectHandler Effect")
    @Config.Comment("Gives some random effect to the target")
    public static boolean desichalkosSwordEffect = true;
    @Config.Name("Ignatius SwordEffectHandler Effect")
    @Config.Comment("Fire aspect")
    public static boolean ignatiusSwordEffect = true;
    @Config.Name("Kalendrite SwordEffectHandler Effect")
    @Config.Comment("Chance to regenerate your life on hit")
    public static boolean kalendriteSwordEffect = true;
    @Config.Name("Shadow Iron SwordEffectHandler Effect")
    @Config.Comment("Chance to blind the target")
    public static boolean shadowIronSwordEffect = true;
    @Config.Name("Shadow Steel SwordEffectHandler Effect")
    @Config.Comment("Speed and Damage is proportional to the darkness")
    public static boolean shadowSteelSwordEffect = true;
    @Config.Name("Tartarite SwordEffectHandler Effect")
    @Config.Comment("Withers the target")
    public static boolean tartariteSwordEffect = true;
    @Config.Name("Vulcanite SwordEffectHandler Effect")
    @Config.Comment("Fire Aspect")
    public static boolean vulcaniteSwordEffect = true;
    @Config.Name("Vyroxeres SwordEffectHandler Effect")
    @Config.Comment("Poisons the target")
    public static boolean vyroxeresSwordEffect = true;
}
