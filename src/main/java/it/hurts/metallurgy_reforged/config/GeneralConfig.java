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

	@Config.Name("Enable/Disable all tools")
	@Config.Comment("When set to false it disables all tools")
	@Config.RequiresMcRestart
	public static boolean disableAllTools = true;
	
	@Config.Name("Enable/Disable all armor set")
	@Config.Comment("When set to false it disables all armor sets")
	@Config.RequiresMcRestart
	public static boolean disableAllArmors = true;
	
	@Config.Name("Road Speed Multiplier")
	@Config.Comment("Sets the road speed")
	@Config.RangeDouble(min = 1)
	public static double roadSpeed = 1.50D;
	
	@Config.Name("Disable the tinker integration")
	@Config.Comment("Sets to true for disable")
	public static boolean tinkerIntegraton = false;
	
	@Config.Name("Disable the constructs' armory integration")
	@Config.Comment("Sets to true for disable")
	public static boolean armoryIntegraton = false;
	
	@Config.Name("The spawnrate percentage of tar lake in the overworld")
	@Config.Comment("Sets to 0 to disable")
	@Config.RangeInt(min = 0, max = 100)
	public static int tarLakePercentage = 25;

	//Handles Config Synchronization
	public static class ChangeListener {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.getModID().equals(Metallurgy.MODID))
				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
		}
	}
}
