package it.hurts.metallurgy_reforged.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 06/12/2018 / 21:30
 * Class: GeneralConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - � - Davoleo - 2018
 **************************************************/

@Config.LangKey("config.metallurgy.category.general")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/general")
public class GeneralConfig {

	public static int test = 100;
	
	@Config.Ignore
	public static List<Map<String, boolean[]>> mapList = new ArrayList<Map<String, boolean[]>>();
	
	@Config.Name("Enable Alpha Warning Message")
	@Config.Comment("When set to true it shows the alpha warning message when joining the world")
	public static boolean alphaWarning = true;

	@Config.Name("Enable/Disable Adamantine Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	@Config.RequiresMcRestart
	public static boolean[] adamantine = { true,true,true,true,true };
	
	@Config.Name("Enable/Disable Amordrine Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] amordrine = { true,true,true,true,true };

	@Config.Name("Enable/Disable Angmallen Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] angmallen = { true,true,true,true,true };

	@Config.Name("Enable/Disable Astral Silver Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] astral_silver = { true,true,true,true,true };

	@Config.Name("Enable/Disable Atlarus Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] atlarus = { true,true,true,true,true };

	@Config.Name("Enable/Disable Black Steel Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] black_steel = { true,true,true,true,true };

	@Config.Name("Enable/Disable Brass Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] brass = { true,true,true,true,true };

	@Config.Name("Enable/Disable Bronze Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] bronze = { true,true,true,true,true };

	@Config.Name("Enable/Disable Carmot Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] carmot = { true,true,true,true,true };

	@Config.Name("Enable/Disable Celenegil Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] celenegil = { true,true,true,true,true };

	@Config.Name("Enable/Disable Ceruclase Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] ceruclase = { true,true,true,true,true };

	@Config.Name("Enable/Disable Copper Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] copper = { true,true,true,true,true };

	@Config.Name("Enable/Disable Damascus Steel Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] damascus_steel = { true,true,true,true,true };

	@Config.Name("Enable/Disable Deep Iron Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] deep_iron = { true,true,true,true,true };

	@Config.Name("Enable/Disable Desichalkos Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] desichalkos = { true,true,true,true,true };

	@Config.Name("Enable/Disable Electrum Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] electrum = { true,true,true,true,true };

	@Config.Name("Enable/Disable Eximite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] eximite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Haderoth Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] haderoth = { true,true,true,true,true };

	@Config.Name("Enable/Disable Hepatizon Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] hepatizon = { true,true,true,true,true };

	@Config.Name("Enable/Disable Ignatius Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] ignatius = { true,true,true,true,true };

	@Config.Name("Enable/Disable Inolashite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] inolashite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Kalendrite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] kalendrite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Midasium Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] midasium = { true,true,true,true,true };

	@Config.Name("Enable/Disable Mithril Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] mithril = { true,true,true,true,true };

	@Config.Name("Enable/Disable Orichalcum Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] orichalcum = { true,true,true,true,true };

	@Config.Name("Enable/Disable Oureclase Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] oureclase = { true,true,true,true,true };

	@Config.Name("Enable/Disable Platinum Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] platinum = { true,true,true,true,true };

	@Config.Name("Enable/Disable Prometheum Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] prometheum = { true,true,true,true,true };

	@Config.Name("Enable/Disable Quicksilver Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] quicksilver = { true,true,true,true,true };

	@Config.Name("Enable/Disable Sanguinite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] sanguinite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Shadow Iron Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] shadow_iron = { true,true,true,true,true };

	@Config.Name("Enable/Disable Shadow Steel Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] shadow_steel = { true,true,true,true,true };

	@Config.Name("Enable/Disable Silver Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] silver = { true,true,true,true,true };

	@Config.Name("Enable/Disable Steel Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] steel = { true,true,true,true,true };

	@Config.Name("Enable/Disable Tartarite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] tartarite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Vulcanite Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] vulcanite = { true,true,true,true,true };

	@Config.Name("Enable/Disable Vyroxeres Sword/Item")
	@Config.Comment("Enable/Disable axe,hoe,pickaxe,shovel,sword")
	public static boolean[] vyroxeres = { true,true,true,true,true };
	
	public static void fillMap() {
		Map<String,boolean[]> mappa = new HashMap<String,boolean[]>();
		for(int i = 0; i < Utils.materialName.length; i++) {
			mappa.put(Utils.materialName[i], (boolean[]) allTools[i]);
			mapList.add(mappa);
		}
	}
	
	@Config.Ignore
	public static Object[] allTools = {
			adamantine,
			amordrine,
			angmallen,
			astral_silver,
			atlarus,
			black_steel,
			brass,
			bronze,
			carmot,
			celenegil,
			ceruclase,
			copper,
			damascus_steel,
			deep_iron,
			desichalkos,
			electrum,
			eximite,
			haderoth,
			hepatizon,
			ignatius,
			inolashite,
			kalendrite,
			midasium,
			mithril,
			orichalcum,
			oureclase,
			platinum,
			prometheum,
			quicksilver,
			sanguinite,
			shadow_iron,
			shadow_steel,
			silver,
			steel,
			tartarite,
			vulcanite,
			vyroxeres
	};
	
	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.getModID().equals(Metallurgy.MODID)) {
				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
