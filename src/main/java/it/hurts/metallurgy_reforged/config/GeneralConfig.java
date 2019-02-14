package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/12/2018 / 21:30
 * Class: GeneralConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Config.LangKey("config.metallurgy.category.general")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/general")
public class GeneralConfig {
	
	@Config.Name("Enable Alpha Warning Message")
	@Config.Comment("When set to true it shows the alpha warning message when joining the world")
	@Config.RequiresMcRestart
	public static boolean alphaWarning = true;

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
	@Config.RangeDouble(min = 1)
	public static boolean tinkerIntegraton = false;

	//Handles Config Synchronization
	public static class ChangeListener {
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.getModID().equals(Metallurgy.MODID))
				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
		}
	}
}
