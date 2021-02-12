/*==============================================================================
 = Class: EffectsConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.armor_effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "effect_roster")
@Config.RequiresMcRestart
public class EffectsConfig {

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

    @Config.Name("Cadence")
    @Config.Comment("Mining  a block will mine surrounding blocks of the same type in a spheric range")
    public static boolean carmotEffectTool = true;
    @Config.Name("Power User")
    @Config.Comment("You can comsume and use items faster depending on how many armor pieces you wear; if you wear a full set you also get a Haste III buff")
    public static boolean carmotEffectArmor = true;
    @Config.Name("Abattoir")
    @Config.Comment("Attacking a mob will also attack mobs of the same type in a radius around the initial strike")
    public static boolean carmotEffectWeapon = true;

    @Config.Name("Escalation")
    @Config.Comment("Continuously breaking blocks without stopping doesn't use durability and increases mining speed. (Resets after pausing for more than a second, the unbreakable effect is enabled after 5 blocks mined)")
    public static boolean celenegilEffectTool = true;
    @Config.Name("Perseverance")
    @Config.Comment("After being attacked five times over a short period, all the enemies are knocked back away from the player and the player receives a very short regen buff and all negative effects are cleared.")
    public static boolean celenegilEffectArmor = true;
    @Config.Name("Glory Seeker")
    @Config.Comment("On right click, Grants a small damage buff to the weapon damage but when you damage an entity without killing it, it'll go on a short cooldown while it will give the player some short speed and strength buff if they manage to kill the entity")
    public static boolean celenegilEffectWeapon = true;

    @Config.Name("Flash Freeze")
    @Config.Comment("Has a chance to completely freeze an enemy in place on hit.")
    public static boolean ceruclaseEffectWeapon = true;
    @Config.Name("Blizzard")
    @Config.Comment("Mobs are slowed, fatigued and weakened depending on how much armor you wear, both players and entities are extinguished if they were on fire.")
    public static boolean ceruclaseEffectArmor = true;
    @Config.Name("Cold Snap")
    @Config.Comment("Blocks with harvest level of 0 are harvested instantly. (Dirt, sand, Wood)")
    public static boolean ceruclaseEffectTool = true;

    @Config.Name("Royal Blood")
    @Config.Comment("A minion will be spawned to aid you in your fights against enemies as soon as you take damage (minion health will scale with the armor count)")
    public static boolean damascusSteelEffectArmor = true;
    @Config.Name("Brilliance I")
    @Config.Comment("Mobs drop significantly more experience.")
    public static boolean damascusSteelEffectWeapon = true;
    @Config.Name("Brilliance II")
    @Config.Comment("Mining ores sometimes grants a bit of experience depending on the harvest level.")
    public static boolean damascusSteelEffectPickaxe = true;

    @Config.Name("Aquatic")
    @Config.Comment("Grants Water Breathing and improved Mobility in water depending on how many armor pieces are worn (it also grants night vision with 2 or more armor pieces).")
    public static boolean deepIronEffectArmor = true;
    @Config.Name("Diver")
    @Config.Comment("Improved tool harvest speed when underwater (3 times the normal underwater speed)")
    public static boolean deepIronEffectTool = true;
    @Config.Name("Diver")
    @Config.Comment("Enhanced weapon damage when underwater (+3 hearts of damage)")
    public static boolean deepIronEffectWeapon = true;

    @Config.Name("Wormhole")
    @Config.Comment("Tool Reach is 3 blocks higher than normal (can't be disabled here) - block drops are teleported to the player inventory directly")
    public static boolean desichalkosEffectTool = true;

    @Config.Name("Nullifier")
    @Config.Comment("When attacking, the damage is dealt straight to the opponent's health bar, unaffected by armor or any resistance the opponent might have. ")
    public static boolean desichalkosEffectWeapon = true;

    @Config.Name("Weight-Controlled Flight")
    @Config.Comment("Makes you float at a certain height depending on how much filled your inventory is. You can press the UP arrow or the DOWN arrow to float respectively up and down.  You have a height limit you can't go over while floating, that limit depends on how full your inventory is (excluding the hotbar). There's also an HUD in the bottom-right corner of the screen that shows you, your height level and your limit.")
    public static boolean krikEffectArmor = true;

    private EffectsConfig()
    {
    }

}
