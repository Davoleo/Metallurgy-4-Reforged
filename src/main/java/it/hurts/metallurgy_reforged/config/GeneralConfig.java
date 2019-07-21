/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GeneralConfig
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

	@Config.Name("Disable all tools")
	@Config.Comment("When set to true all tool sets are disabled")
	@Config.RequiresMcRestart
	public static boolean disableAllTools = false;
	
	@Config.Name("Disable all armor set")
	@Config.Comment("When set to true all armor sets are disabled")
	@Config.RequiresMcRestart
	public static boolean disableAllArmors = false;
	
	@Config.Name("Road Speed Multiplier")
	@Config.Comment("Set the road speed multiplier")
	@Config.RangeDouble(min = 1)
	public static double roadSpeed = 1.50D;
	
	@Config.Name("Disable tinker integration")
	@Config.Comment("Set to true to disable Tinkers' Construct Integration")
	public static boolean tinkerIntegraton = false;
	
	@Config.Name("Disable ConArm Integration")
	@Config.Comment("Set to true to disable Construct's Armory Integration")
	public static boolean armoryIntegraton = false;

	@Config.Name("Disable IF Integration")
	@Config.Comment("Set to true to disable Industrial Foregoing Integration")
	public static boolean inForegoingIntegraton = false;

	@Config.Name("Disable Automatic EMC values")
	@Config.Comment("Set to true to disable default EMC values")
	public static boolean projectEIntegration = false;

	@Config.Name("Enable Duplication Trait")
	@Config.Comment("When set to true the duplication trait from tinker integration is enabled")
	public static boolean enableDuplicationTrait = true;

	@Config.Name("Should Tool be repairable in Anvil")
	@Config.Comment("Set this to true to enable the possibility to repair tools in Vanilla Anvil")
	public static boolean enableAnvilToolRepair = false;

	@Config.Name("Should Armor be repairable in Anvil")
	@Config.Comment("Set this to true to enable the possibility to repair armors in Vanilla Anvil")
	public static boolean enableAnvilArmorRepair = false;

	@Config.Name("Lantern entity collision")
	@Config.Comment("Set this to false to disable entity collisions with lantern")
	public static boolean enableLanternCollision = true;

	//Handles Config Synchronization
	public static class ChangeListener {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.getModID().equals(Metallurgy.MODID))
				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
		}
	}

	@Config.Name("Power to the Axes")
	@Config.Comment("Make axes more powerful than swords")
	@Config.RequiresMcRestart
	public static boolean powerAxes = false;
}
