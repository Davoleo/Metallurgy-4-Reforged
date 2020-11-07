/*==============================================================================
 = Class: ArmorEffectsConfig
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
	@Config.Name("Atlarus Armor Effect")
	@Config.Comment("Full armor saves you from taking fall damage thanks to a gust of wind")
	public static boolean atlarusArmorEffect = true;
	@Config.Name("Carmot Armor Effect")
	@Config.Comment("Full Armor gives Haste")
	public static boolean carmotArmorEffect = true;
	@Config.Name("Celenegil Armor Effect")
	@Config.Comment("Full Armor gives Resistance II")
	public static boolean celenegilArmorEffect = true;
	@Config.Name("Deep Iron Armor Effect")
	@Config.Comment("Full armor makes you walk faster underwater")
	public static boolean deepIronArmorEffect = true;
	@Config.Name("Desichalkos Armor Effect")
	@Config.Comment("Allows the player to take some special blocks from endermen")
	public static boolean desichalkosArmorEffect = true;
	@Config.Name("Desichalkos Effect Special Blocks")
	@Config.Comment("The list of blocks that endermen can show the player when they're wearing a desichalkos armor set")
	public static String[] desichalkosEndermenBlocks = {"minecraft:iron_block", "minecraft:gold_block",
	                                                    "minecraft:obsidian", "minecraft:glowstone",
	                                                    "minecraft:diamond_block", "metallurgy:adamantine_block",
	                                                    "metallurgy:atlarus_block"};
	@Config.Name("Etherium Armor Effect")
	@Config.Comment("Full armor gives the ability you to go through walls")
	public static boolean etheriumArmorEffect = true;
	@Config.Name("Eximite Armor Effect")
	@Config.Comment("Endermen won't notice you when watch them")
	public static boolean eximiteArmorEffect = true;
	@Config.Name("Haderoth Armor Effect")
	@Config.Comment("Allows the player to walk on lava blocks")
	public static boolean haderothArmorEffect = true;
	@Config.Name("Kalendrite Armor Effect")
	@Config.Comment("Full Armor gives Strength I")
	public static boolean kaledriteArmorEffect = true;
	@Config.Name("Krik Armor Effect")
	@Config.Comment("Full Armor makes you float around at a certain height that depends from how full the inventory is")
	public static boolean krikArmorEffect = true;
	@Config.Name("Lutetium Armor Effect")
	@Config.Comment("Knockback reduction depending on how much armor is worn (LV 2)")
	public static boolean lutetiumArmorEffect = true;
	@Config.Name("Mithril Armor Effect")
	@Config.Comment("Makes Entities around you glow")
	public static boolean mithrilArmorEffect = true;
	@Config.Name("Osmium Armor Effect")
	@Config.Comment("Knockback reduction depending on how much armor is worn (LV 1)")
	public static boolean osmiumArmorEffect = true;
	@Config.Name("Platinum Armor Effect")
	@Config.Comment("Gives you night vision")
	public static boolean platinumArmorEffect = true;
	@Config.Name("Prometheum Armor Effect")
	@Config.Comment("Removes poison effect whenever you get it")
	public static boolean prometheumArmorEffect = true;
	@Config.Name("Quicksilver Armor Effect")
	@Config.Comment("Full Armor increases the speed of your actions, such as using bows, eating food, etc")
	public static boolean quicksilverArmorEffect = true;
	@Config.Name("Quicksilver Blacklist Armor Effect")
	@Config.Comment("Blacklist items which can't be speed up")
	public static String[] quickSilverBlacklist = new String[]{
			"bountifulbaubles:magicmirror",
			"aoa3:apoco_shower",
			"aoa3:atomizer",
			"aoa3:beamer",
			"aoa3:blast_chiller",
			"aoa3:blood_drainer",
			"aoa3:bone_blaster",
			"aoa3:bubble_horn",
			"aoa3:confetti_cannon",
			"aoa3:confetti_cluster",
			"aoa3:dark_destroyer",
			"aoa3:darkly_guster",
			"aoa3:death_ray",
			"aoa3:doom_bringer",
			"aoa3:eradicator",
			"aoa3:flowercorne",
			"aoa3:fragment",
			"aoa3:froster",
			"aoa3:gas_blaster",
			"aoa3:ghoul_gasser",
			"aoa3:gold_bringer",
			"aoa3:gravity_blaster",
			"aoa3:hell_horn",
			"aoa3:illusion_revolver",
			"aoa3:illusion_smg",
			"aoa3:ion_blaster",
			"aoa3:iro_miner",
			"aoa3:laser_blaster",
			"aoa3:light_blaster",
			"aoa3:light_spark",
			"aoa3:luna_blaster",
			"aoa3:mecha_blaster",
			"aoa3:mind_blaster",
			"aoa3:moon_destroyer",
			"aoa3:moon_shiner",
			"aoa3:odious",
			"aoa3:orbocron",
			"aoa3:paralyzer",
			"aoa3:party_popper",
			"aoa3:poison_plunger",
			"aoa3:power_ray",
			"aoa3:proton",
			"aoa3:reefer",
			"aoa3:revolution",
			"aoa3:seaocron",
			"aoa3:skullo_blaster",
			"aoa3:soul_drainer",
			"aoa3:soul_spark",
			"aoa3:soul_storm",
			"aoa3:spirit_shower",
			"aoa3:swarmotron",
			"aoa3:toxic_terrorizer",
			"aoa3:vortex_blaster",
			"aoa3:whimsy_winder",
			"aoa3:withers_wrath"
};
	@Config.Name("Shadow Iron Armor Effect")
	@Config.Comment("Absorbed damage is proportional to the darkness")
	public static boolean shadowSteelArmorEffect = true;
	@Config.Name("Vulcanite Armor Effect")
	@Config.Comment("You can't take damage from fire")
	public static boolean vulcaniteArmorEffect = true;

	private ArmorEffectsConfig() {}

}
