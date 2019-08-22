/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorConfig
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.config;

public class ArmorConfig {
	
	private static final String CATEGORY = "Armor";

	public static boolean adamantine = true;
	public static boolean amordrine = true;
	public static boolean angmallen = true;
	public static boolean astral_silver = true;
	public static boolean atlarus = true;
	public static boolean black_steel = true;
	public static boolean brass = true;
	public static boolean bronze = true;
	public static boolean carmot = true;
	public static boolean celenegil = true;
	public static boolean ceruclase = true;
	public static boolean copper = true;
	public static boolean damascus_steel = true;
	public static boolean deep_iron = true;
	public static boolean desichalkos = true;
	public static boolean electrum = true;
	public static boolean eximite = true;
	public static boolean haderoth = true;
	public static boolean hepatizon = true;
	public static boolean ignatius = true;
	public static boolean inolashite = true;
	public static boolean kalendrite = true;
	public static boolean krik = true;
	public static boolean lutetium = true;
	public static boolean midasium = true;
	public static boolean mithril = true;
	public static boolean orichalcum = true;
	public static boolean osmium = true;
	public static boolean oureclase = true;
	public static boolean platinum = true;
	public static boolean prometheum = true;
	public static boolean quicksilver = true;
	public static boolean sanguinite = true;
	public static boolean shadow_iron = true;
	public static boolean shadow_steel = true;
	public static boolean silver = true;
	public static boolean steel = true;
	public static boolean tartarite = true;
	public static boolean vulcanite = true;
	public static boolean vyroxeres = true;

	public static boolean[] allArmor = {
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
			krik,
			lutetium,
			midasium,
			mithril,
			orichalcum,
			osmium,
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

//	public static void readConfig(Configuration cfg) {
//        try {
//        	initArmorConfig(cfg);
//        } catch (Exception e1) {
//            Metallurgy.logger.log(Level.ERROR, "Problem loading config file!", e1);
//        } finally {
//            if (cfg.hasChanged()) {
//                cfg.save();
//            }
//        }
//    }
//	
//	private static void initArmorConfig(Configuration cfg) {
//			for(int i = 0; i < allArmor.length; i++)
//				allArmor[i]= cfg.getBoolean(Utils.modMaterialNames[i], CATEGORY, allArmor[i], "Set to false to disable " + Utils.getName(Utils.modMaterialNames[i]) + " Armor Set" );
//	}
//
//
//	public static class ChangeListener {
//
//		@SubscribeEvent
//		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
//			if(eventArgs.getModID().equals(Metallurgy.MODID)) {
//				ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
//			}
//		}
//	}
		
}
