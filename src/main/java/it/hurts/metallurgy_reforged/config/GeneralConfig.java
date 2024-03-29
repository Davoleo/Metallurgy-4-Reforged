/*==============================================================================
 = Class: GeneralConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config.metallurgy.category.general")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/general")
public class GeneralConfig {

	@Config.Name("Enable On World Join Message")
	@Config.Comment("When set to true it shows the warning message when joining the world")
	@Config.RequiresMcRestart
	public static boolean warning = true;

	@Config.Name("Road Speed Multiplier")
	@Config.Comment("Set the road speed multiplier")
	@Config.RangeDouble(min = 1)
	public static double roadSpeed = 1.50D;

	@Config.Name("Tinkers' Construct integration")
	@Config.Comment("Set to false to disable Tinkers' Construct Integration")
	public static boolean tiCIntegration = true;

	@Config.Name("Tinkers' Materials Blacklist")
	@Config.Comment("Add a material in this array to blacklist it during game boot - material name format is \"snake_case\" (e.g. 'osmium' or 'damascus_steel')")
	@Config.RequiresMcRestart
	public static String[] tinkerMaterialsBlacklist = new String[]{ };

	@Config.Name("Disable Construct's Armory integration")
	@Config.Comment("Set to false to disable Construct's Armory Integration")
	public static boolean conarmIntegration = true;

	@Config.Name("Industrial Foregoing integration")
	@Config.Comment("Set to false to disable Industrial Foregoing integration")
	public static boolean inForegoingIntegration = true;

	@Config.Name("ProjectE Integration")
	@Config.Comment("Set to false to disable default EMC values (ProjectE)")
	public static boolean projectEIntegration = true;

	@Config.Name("Enable Duplication Trait")
	@Config.Comment("When set to true the duplication trait from tinker integration is enabled")
	public static boolean enableDuplicationTrait = true;

	@Config.Name("Should Tool be repairable in Anvil")
	@Config.Comment("Set this to true to be able to repair tools in Vanilla Anvil")
	public static boolean enableAnvilToolRepair = true;

	@Config.Name("Should Armor be repairable in Anvil")
	@Config.Comment("Set this to true to enable the possibility to repair armors in Vanilla Anvil")
	public static boolean enableAnvilArmorRepair = true;

	@Config.Name("Enable Ore Particles")
	@Config.Comment("Set this to true to enable ore particles (Particles that are bigger depending on the harvest tier of the ore) [default: false]")
	public static boolean enableOreParticles = true;

	@Config.Name("Should High Tier Ore emit light")
	@Config.Comment("Set this to true to make ore blocks that have a harvest level higher than 5 to emit light [default: false]")
	@Config.RequiresWorldRestart
	public static boolean enableOreLight = true;

	@Config.Name("OreDict Crusher Recipes")
	@Config.Comment("When set to true the mod will load new recipes from the OreDictionary (this could cause some incompatibility issues when removing recipes with CraftTweaker disable this if you see that some recipes are not being removed)")
	@Config.RequiresMcRestart
	public static boolean enableOreDictCrusherRecipes = true;

	@Config.Name("Enable Custom Materials Stats Configuration file")
	@Config.Comment("Loads the `material.json` file in the config folder that allows players to edit any material stat in the mod and to disable specific metals")
	@Config.RequiresMcRestart
	public static boolean enableCustomMaterialStatsConfig = false;

	@Config.Name("Enable EnderIO alloying recipes xml file")
	@Config.Comment("Loads the `metallurgy_enderio_alloys.xml` file in the config folder that allows players to edit alloy recipes that you can make in the Alloy Smelter from EnderIO")
	@Config.RequiresMcRestart
	public static boolean enableEnderIOAlloyConfig = false;

	@Deprecated
	@Config.Ignore
	@Config.Name("Enable Metallurgy Tweaks Scripts replacing")
	@Config.Comment("Loads the scripts from the jar and copies them into CraftTweakers scripts folder, this option should be enabled to receive updates for Metallurgy Tweaks, but may be disabled if you want to tweak Metallurgy Tweaks config file without it getting replaced")
	public static boolean enableMetallurgyTweaksUpdateReplace = true;

	@Config.Name("Mob that can spawn equipped")
	@Config.Comment("A list of mob ids that can spawn with metallurgy armor and/or tools")
	public static String[] mobsThatCanHaveEquipment = {
			"minecraft:zombie",
			"minecraft:skeleton",
			"minecraft:wither_skeleton",
			"minecraft:stray",
			"minecraft:husk"
	};

	@Config.Name("Metal equipment spawn blacklist")
	@Config.Comment("A list of metals which cannot be equipped by mobs. \n Metal names must be written in snake case.")
	public static String[] metalsThatCannotBeEquipped = {};

	@Config.Name("Hide Stats Tooltip [Tools & Armor]")
	@Config.Comment("Hides extra stats information that is usually displayed when you hold the shift key down when hovering over armor and tools")
	public static boolean hideStatsTooltip = false;

	@Config.Name("Metal Colored Tooltip Borders for metal items")
	@Config.Comment("Disabling this option will prevent modification to tooltip borders for metal items (useful when the modpack has another system to modify tooltips that conflicts with Metallurgy or hinders the consistency of modpack tooltips).")
	public static boolean metalColoredTooltipBorders = true;

	@Config.Name("Silent's Gems Integration")
	@Config.Comment("Set to false to disable Silent's Gems integration")
	public static boolean silentGemsIntegration = true;

	@Config.Name("Spartan Weaponry Integration")
	@Config.Comment("Set to false to disable Spartan Weaponry integration (WARNING: integration items use up a lot of IDs!)")
	public static boolean spartanWeaponryIntegration = true;

	//Handles Config Synchronization
	public static class ChangeListener {

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if (eventArgs.getModID().equals(Metallurgy.MODID))
				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
		}

	}

}
