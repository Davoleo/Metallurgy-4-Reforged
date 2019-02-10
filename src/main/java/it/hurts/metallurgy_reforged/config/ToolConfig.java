package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolConfig {
	
	private static final String[] CATEGORY = {"Axe","Hoe", "Pickaxe","Shovel","Sword"};

	public static List<Map<String, boolean[]>> mapList = new ArrayList<Map<String, boolean[]>>();

	public static boolean[] adamantine = { true,true,true,true,true };

	public static boolean[] amordrine = { true,true,true,true,true };

	public static boolean[] angmallen = { true,true,true,true,true };

	public static boolean[] astral_silver = { true,true,true,true,true };

	public static boolean[] atlarus = { true,true,true,true,true };

	public static boolean[] black_steel = { true,true,true,true,true };

	public static boolean[] brass = { true,true,true,true,true };

	public static boolean[] bronze = { true,true,true,true,true };

	public static boolean[] carmot = { true,true,true,true,true };

	public static boolean[] celenegil = { true,true,true,true,true };

	public static boolean[] ceruclase = { true,true,true,true,true };

	public static boolean[] copper = { true,true,true,true,true };

	public static boolean[] damascus_steel = { true,true,true,true,true };

	public static boolean[] deep_iron = { true,true,true,true,true };

	public static boolean[] desichalkos = { true,true,true,true,true };

	public static boolean[] electrum = { true,true,true,true,true };

	public static boolean[] eximite = { true,true,true,true,true };

	public static boolean[] haderoth = { true,true,true,true,true };

	public static boolean[] hepatizon = { true,true,true,true,true };

	public static boolean[] ignatius = { true,true,true,true,true };

	public static boolean[] inolashite = { true,true,true,true,true };

	public static boolean[] kalendrite = { true,true,true,true,true };

	public static boolean[] midasium = { true,true,true,true,true };

	public static boolean[] mithril = { true,true,true,true,true };

	public static boolean[] orichalcum = { true,true,true,true,true };

	public static boolean[] oureclase = { true,true,true,true,true };

	public static boolean[] platinum = { true,true,true,true,true };

	public static boolean[] prometheum = { true,true,true,true,true };

	public static boolean[] quicksilver = { true,true,true,true,true };

	public static boolean[] sanguinite = { true,true,true,true,true };

	public static boolean[] shadow_iron = { true,true,true,true,true };

	public static boolean[] shadow_steel = { true,true,true,true,true };

	public static boolean[] silver = { true,true,true,true,true };

	public static boolean[] steel = { true,true,true,true,true };

	public static boolean[] tartarite = { true,true,true,true,true };

	public static boolean[] vulcanite = { true,true,true,true,true };

	public static boolean[] vyroxeres = { true,true,true,true,true };
	
	public static void fillMap() {
		Map<String,boolean[]> mappa = new HashMap<String,boolean[]>();
		for(int i = 0; i < Utils.materialName.length; i++) {
			mappa.put(Utils.materialName[i], (boolean[]) allTools[i]);
			mapList.add(mappa);
		}
	}

	public static boolean[][] allTools = {
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
	
	public static void readConfig(Configuration cfg) {
        try {
        	initToolConfig(cfg);
        } catch (Exception e1) {
            Metallurgy.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
	
	private static void initToolConfig(Configuration cfg) {
//		Il for annidato andrà a controllare tutti il valore boolean di ogni materiale per un singolo tool ad ogni ripetizione
		
		for(int j = 0; j < 5; j++) {
			for(int i = 0; i < allTools.length; i++) {
				allTools[i][j] = cfg.getBoolean(Utils.materialName[i], CATEGORY[j],  allTools[i][j], "Set to false to disable " + CATEGORY[j]);
			}
		}
		
		for(int i = 0; i < allTools.length; i++) {
			Map<String,boolean[]> mappa = new HashMap<String,boolean[]>();

//			Il nome dei materiali è in UPPERCASE
			mappa.put(Utils.materialName[i].toUpperCase(), allTools[i]);
			mapList.add(mappa);
		}
	}

}
