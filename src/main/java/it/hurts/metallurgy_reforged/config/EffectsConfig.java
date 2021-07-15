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

//Variables are accessed through reflections most of the times
@SuppressWarnings("unused")
@Config.LangKey("config.metallurgy.category.effects")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "effect_roster")
@Config.RequiresMcRestart
public class EffectsConfig {

	@Config.Name("Symbiosis I (Adamantine Items)")
	@Config.LangKey("tooltip.metallurgy.effect.adamantine_all")
	public static boolean adamantineEffectAll = true;
	@Config.Name("Symbiosis II (Adamantine Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.adamantine_armor")
	public static boolean adamantineEffectArmor = true;

	@Config.Name("Sky-High (Amordrine Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.amordrine_armor")
	public static boolean amordrineEffectArmor = true;
	@Config.Name("Sky-High (Amordrine Items)")
	@Config.LangKey("tooltip.metallurgy.effect.amordrine_all")
	public static boolean amordrineEffectAll = true;
	@Config.Name("Coup de grâce (Amordrine Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.amordrine_weapon")
	public static boolean amordrineEffectWeapon = true;

	@Config.Name("Reactive II (Angmallen Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.angmallen_weapon")
	public static boolean angmallenEffectWeapon = true;
	@Config.Name("Transmute (Angmallen Pickaxe)")
	@Config.LangKey("tooltip.metallurgy.effect.angmallen_pickaxe")
	public static boolean angmallenEffectPickaxe = true;
	@Config.Name("Reactive III (Angmallen Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.angmallen_armor")
	public static boolean angmallenEffectArmor = true;

	@Config.Name("Starlight (Astral Silver Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.astral_silver_armor")
	public static boolean astralSilverEffectArmor = true;
	@Config.Name("Extraterrestrial I (Astral Silver Weapon)")
	@Config.LangKey("tooltip.metallurgy.effect.astral_silver_weapon")
	public static boolean astralSilverEffectWeapon = true;
	@Config.Name("Extraterrestrial II (Astral Silver Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.astral_silver_tool")
	public static boolean astralSilverEffectTool = true;

	@Config.Name("Whirlwind (Atlarus Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.atlarus_armor")
	public static boolean atlarusEffectArmor = true;
	@Config.Name("Gust of Wind (Atlarus Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.atlarus_weapon")
	public static boolean atlarusEffectWeapon = true;
	@Config.Name("Wind Scythe (Atlarus Hoe)")
	@Config.LangKey("tooltip.metallurgy.effect.atlarus_hoe")
	public static boolean atlarusEffectHoe = true;

	@Config.Name("Mountain I (Black Steel Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.black_steel_armor")
	public static boolean blackSteelEffectArmor = true;
	@Config.Name("Mountain II (Black Steel Weapon)")
	@Config.LangKey("tooltip.metallurgy.effect.black_steel_weapon")
	public static boolean blackSteelEffectWeapon = true;

	@Config.Name("Cadence (Carmot Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.carmot_tool")
	public static boolean carmotEffectTool = true;
	@Config.Name("Power User (Carmot Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.carmot_armor")
	public static boolean carmotEffectArmor = true;
	@Config.Name("Abattoir (Carmot Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.carmot_weapon")
	public static boolean carmotEffectWeapon = true;

	@Config.Name("Escalation (Celenegil Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.celenegil_tool")
	public static boolean celenegilEffectTool = true;
	@Config.Name("Perseverance (Celenegil Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.celenegil_armor")
	public static boolean celenegilEffectArmor = true;
	@Config.Name("Glory Seeker (Celenegil Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.celenegil_weapon")
	public static boolean celenegilEffectWeapon = true;

	@Config.Name("Flash Freeze (Ceruclase Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.ceruclase_weapon")
	public static boolean ceruclaseEffectWeapon = true;
	@Config.Name("Blizzard (Ceruclase Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.ceruclase_armor")
	public static boolean ceruclaseEffectArmor = true;
	@Config.Name("Cold Snap (Ceruclase Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.ceruclase_tool")
	public static boolean ceruclaseEffectTool = true;

	@Config.Name("Royal Blood (Damascus Steel Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.damascus_steel_armor")
	public static boolean damascusSteelEffectArmor = true;
	@Config.Name("Brilliance I (Damascus Steel Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.damascus_steel_weapon")
	public static boolean damascusSteelEffectWeapon = true;
	@Config.Name("Brilliance II (Damascus Steel Pickaxe)")
	@Config.LangKey("tooltip.metallurgy.effect.damascus_steel_pickaxe")
	public static boolean damascusSteelEffectPickaxe = true;

	@Config.Name("Aquatic (Deep Iron Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.deep_iron_armor")
	public static boolean deepIronEffectArmor = true;
	@Config.Name("Diver (Deep Iron Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.deep_iron_tool")
	public static boolean deepIronEffectTool = true;
	@Config.Name("Diver (Deep Iron Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.deep_iron_weapon")
	public static boolean deepIronEffectWeapon = true;

	@Config.Name("Wormhole (Desichalkos Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.desichalkos_tool")
	public static boolean desichalkosEffectTool = true;
	@Config.Name("Nullifier (Desichalkos Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.desichalkos_weapon")
	public static boolean desichalkosEffectWeapon = true;
	@Config.Name("Orb (Desichalkos Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.desichalkos_armor")
	public static boolean desichalkosEffectArmor = true;

	@Config.Name("Magnet (Electrum Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.electrum_weapon")
	public static boolean electrumEffectWeapon = true;
	@Config.Name("Voltage Control (Electrum Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.electrum_tool")
	public static boolean electrumEffectTool = true;
	@Config.Name("Static (Electrum Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.electrum_armor")
	public static boolean electrumEffectArmor = true;

	@Config.Name("Ethereal (Etherium Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.etherium_armor")
	public static boolean etheriumEffectArmor = true;
	@Config.Name("Siphon (Etherium Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.etherium_weapon")
	public static boolean etheriumEffectWeapon = true;

	@Config.Name("End Domestic (Eximite Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.eximite_armor")
	public static boolean eximiteEffectArmor = true;
	@Config.Name("Outworlder (Eximite Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.eximite_weapon")
	public static boolean eximiteEffectWeapon = true;

	@Config.Name("Adaptability (Haderoth Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.haderoth_armor")
	public static boolean haderothEffectArmor = true;
	@Config.Name("Apex (Haderoth weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.haderoth_weapon")
	public static boolean haderothEffectWeapon = true;
	@Config.Name("Metamorph (Haderoth Items)")
	@Config.LangKey("tooltip.metallurgy.effect.haderoth_all")
	public static boolean haderothEffectAll = true;

	@Config.Name("Disarm (Hepatizon Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.hepatizon_weapon")
	public static boolean hepatizonEffectWeapon = true;
	@Config.Name("Cloak & Dagger (Hepatizon Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.hepatizon_armor")
	public static boolean hepatizonEffectArmor = true;

	@Config.Name("Molten Core (Ignatius Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.ignatius_tool")
	public static boolean ignatiusEffectTool = true;
	@Config.Name("Molten Core (Ignatius Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.ignatius_weapon")
	public static boolean ignatiusEffectWeapon = true;
	@Config.Name("Hot-Blooded (Ignatius Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.ignatius_armor")
	public static boolean ignatiusEffectArmor = true;

	@Config.Name("Time Cleave (Inolashite Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.inolashite_weapon")
	public static boolean inolashiteEffectWeapon = true;
	@Config.Name("Time Blink (Inolashite Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.inolashite_tool")
	public static boolean inolashiteEffectTool = true;
	@Config.Name("Warp (Inolashite Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.inolashite_armor")
	public static boolean inolashiteEffectArmor = true;

	@Config.Name("Tranquil (Kalendrite Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.kalendrite_armor")
	public static boolean kalendriteEffectArmor = true;
	@Config.Name("Restoration (Kalendrite Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.kalendrite_tool")
	public static boolean kalendriteEffectTool = true;

	@Config.Name("Weight-Controlled Flight (Krik Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.krik_armor")
	public static boolean krikEffectArmor = true;
	@Config.Name("Krik-Drives (Krik Tools)")
	@Config.LangKey("tooltip.metallurgy.effect.krik_tool")
	public static boolean krikEffectTool = true;
	@Config.Name("Flak (Krik Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.krik_weapon")
	public static boolean krikEffectWeapon = true;

	@Config.Name("Midas (Midasium hoe)")
	@Config.LangKey("tooltip.metallurgy.effect.midasium_hoe")
	public static boolean midasiumEffectHoe = true;
	@Config.Name("Greed (Midasium Weapon)")
	@Config.LangKey("tooltip.metallurgy.effect.midasium_weapon")
	public static boolean midasiumEffectWeapon = true;
	@Config.Name("Greed (Midasium Tool)")
	@Config.LangKey("tooltip.metallurgy.effect.midasium_tool")
	public static boolean midasiumEffectTool = true;

	@Config.Name("Multiply (Mithril Pickaxe)")
	@Config.LangKey("tooltip.metallurgy.effect.mithril_pickaxe")
	public static boolean mithrilEffectPickaxe = true;
	@Config.Name("Magic Aspect (Mithril Weapon)")
	@Config.LangKey("tooltip.metallurgy.effect.mithril_weapon")
	public static boolean mithrilEffectWeapon = true;
	@Config.Name("Arcane (Mithril Items)")
	@Config.LangKey("tooltip.metallurgy.effect.mithril_all")
	public static boolean mithrilEffectAll = true;

	@Config.Name("Champion (Orichalcum Items)")
	@Config.LangKey("tooltip.metallurgy.effect.orichalcum_all")
	public static boolean orichalcumEffectAll = true;
	@Config.Name("Berserker (Orichalcum Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.orichalcum_armor")
	public static boolean orichalcumEffectArmor = true;

	@Config.Name("Crushing (Oureclase Pickaxe)")
	@Config.LangKey("tooltip.metallurgy.effect.oureclase_pickaxe")
	public static boolean oureclaseEffectPickaxe = true;
	@Config.Name("Pulverize (Oureclase Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.oureclase_weapon")
	public static boolean oureclaseEffectWeapon = true;
	@Config.Name("Stampede (Oureclase Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.oureclase_armor")
	public static boolean oureclaseEffectArmor = true;

	@Config.Name("Purification (Platinum Armor)")
	@Config.LangKey("tooltip.metallurgy.effect.platinum_armor")
	public static boolean platinumEffectArmor = true;
	@Config.Name("Cathartic Strike (Platinum Weapons)")
	@Config.LangKey("tooltip.metallurgy.effect.platinum_weapon")
	public static boolean platinumEffectWeapon = true;

	private EffectsConfig()
	{
	}

}