/*==============================================================================
 = Class: EffectsConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.armor_effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "effect_roster")
@Config.RequiresMcRestart
public class EffectsConfig {

    //Armors
    @Config.Name("Symbiosis I")
    @Config.Comment("Starving consumes equipment while eating restores it")
    public static boolean adamantineEffectAll = true;
    @Config.Name("Symbiosis II")
    @Config.Comment("When taking lethal damage you have a chance (based on how many armor pieces you wear) to survive the hit and lose a piece of armor")
    public static boolean adamantineEffectArmor = true;

    @Config.Name("Sky-High")
    @Config.Comment("Grants extra jumps (from 1 to 4) depending on how many armor pieces the player is wearing")
    public static boolean amordrineEffectArmor = true;
    @Config.Name("Sky-High")
    @Config.Comment("Grants extra jumps (from 1 to 4) depending on how many armor pieces the player is wearing")
    public static boolean amordrineEffectAll = true;

    @Config.Name("Coup de grâce")
    @Config.Comment("Bonus Damage depending on target's health,the more the target has the less health the more damage the weapon does (up to 200% damage boost)")
    public static boolean amordrineEffectWeapon = true;

    @Config.Name("Reactive II")
    @Config.Comment("Deal more damage to armored enemies")
    public static boolean angmallenEffectWeapon = true;

    @Config.Name("Transmute")
    @Config.Comment("Mining an ore  sometimes drops another ore of +1/-1/+0 harvest level (50% chance)")
    public static boolean angmallenEffectPickaxe = true;

    @Config.Name("Reactive III")
    @Config.Comment("Plays a sound sometimes when you're near some rare ore, every armor piece increases the chance to detect rare ores")
    public static boolean angmallenEffectArmor = true;

    @Config.Name("Starlight")
    @Config.Comment("Gives Night Vision and Speed during night time or in the End if the player has the sky above their head depending on how many armor pieces are worn")
    public static boolean astralSilverEffectArmor = true;

    @Config.Name("Extraterrestrial I")
    @Config.Comment("45% more damage if the player is in another dimension")
    public static boolean astralSilverEffectWeapon = true;

    @Config.Name("Extraterrestrial II")
    @Config.Comment("Increased speed when breaking blocks in dimensions that aren't the Overworld")
    public static boolean astralSilverEffectTool = true;

    @Config.Name("Whirlwind")
    @Config.Comment("A whirlwind will save you from taking fall damage pushing you in a random direction")
    public static boolean atlarusEffectArmor = true;

    @Config.Name("Gust of Wind")
    @Config.Comment("A strong gust of wind pushes enemies away from you and breaks leaves and vines in a range of 5 blocks around you")
    public static boolean atlarusEffectWeapon = true;

    @Config.Name("Wind Scythe")
    @Config.Comment("Wind helps you removes bushes and crops around you")
    public static boolean atlarusEffectHoe = true;

    @Config.Name("Mountain I")
    @Config.Comment("Receiving damage sometimes grants resistance but also slowness, at the same time. ( Repeated strikes stack the effect, aka slowness I and resistance I become slowness II and resistance II. Can stack up to 3 times, chance based.)")
    public static boolean blackSteelEffectArmor = true;

    @Config.Name("Mountain II")
    @Config.Comment("Tools & weapons act like armor, for example holding a black steel sword while getting attacked will damage the sword but absorb a small portion of the damage.")
    public static boolean blackSteelEffectWeapon = true;

    @Config.Name("Weight-Controlled Flight")
    @Config.Comment("Makes you float at a certain height depending on how much filled your inventory is. You can press the UP arrow or the DOWN arrow to float respectively up and down.  You have a height limit you can't go over while floating, that limit depends on how full your inventory is (excluding the hotbar). There's also an HUD in the bottom-right corner of the screen that shows you, your height level and your limit.")
    public static boolean krikEffectArmor = true;

    private EffectsConfig()
    {
    }

}
