package it.hurts.metallurgy_reforged.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config.metallurgy.category.armor")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/armor")
public class ArmorConfig {
	
//	private static final String[] CATEGORY = { "helmet", "chest","legs","boots" };
	private static final String CATEGORY = "Armor";
	@Config.Ignore
	public static List<Map<String, Boolean>> mapList = new ArrayList<Map<String, Boolean>>();
	
	@Config.Ignore
	public static boolean adamantine = true;
	
	@Config.Ignore
	public static boolean amordrine = true;

	@Config.Ignore
	public static boolean angmallen = true;

	@Config.Ignore
	public static boolean astral_silver = true;

	@Config.Ignore
	public static boolean atlarus = true;

	@Config.Ignore
	public static boolean black_steel = true;

	@Config.Ignore
	public static boolean brass = true;

	@Config.Ignore
	public static boolean bronze = true;

	@Config.Ignore
	public static boolean carmot = true;

	@Config.Ignore
	public static boolean celenegil = true;

	@Config.Ignore
	public static boolean ceruclase = true;

	@Config.Ignore
	public static boolean copper = true;

	@Config.Ignore
	public static boolean damascus_steel = true;

	@Config.Ignore
	public static boolean deep_iron = true;

	@Config.Ignore
	public static boolean desichalkos = true;

	@Config.Ignore
	public static boolean electrum = true;

	@Config.Ignore
	public static boolean eximite = true;

	@Config.Ignore
	public static boolean haderoth = true;

	@Config.Ignore
	public static boolean hepatizon = true;

	@Config.Ignore
	public static boolean ignatius = true;

	@Config.Ignore
	public static boolean inolashite = true;

	@Config.Ignore
	public static boolean kalendrite = true;

	@Config.Ignore
	public static boolean midasium = true;

	@Config.Ignore
	public static boolean mithril = true;

	@Config.Ignore
	public static boolean orichalcum = true;

	@Config.Ignore
	public static boolean oureclase = true;

	@Config.Ignore
	public static boolean platinum = true;

	@Config.Ignore
	public static boolean prometheum = true;

	@Config.Ignore
	public static boolean quicksilver = true;

	@Config.Ignore
	public static boolean sanguinite = true;

	@Config.Ignore
	public static boolean shadow_iron = true;

	@Config.Ignore
	public static boolean shadow_steel = true;

	@Config.Ignore
	public static boolean silver = true;

	@Config.Ignore
	public static boolean steel = true;

	@Config.Ignore
	public static boolean tartarite = true;

	@Config.Ignore
	public static boolean vulcanite = true;

	@Config.Ignore
	public static boolean vyroxeres = true;
	
	@Config.Ignore
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
        	initArmorConfig(cfg);
        } catch (Exception e1) {
            Metallurgy.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
	
	private static void initArmorConfig(Configuration cfg) {
//		Il for annidato andrà a controllare tutti il valore boolean di ogni materiale per un singolo tool ad ogni ripetizione
		
			for(int i = 0; i < allArmor.length; i++) {
				Map<String,Boolean> mappa = new HashMap<String,Boolean>();
				allArmor[i]= cfg.getBoolean(Utils.materialName[i], CATEGORY, allArmor[i], "Set to false to disable " + Utils.materialName[i] + " Armor Set" );
				
				mappa.put(Utils.materialName[i].toUpperCase(), allArmor[i]);
				mapList.add(mappa);
			}
	}

		
		public static class ChangeListener {

			@SubscribeEvent
			public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
				if(eventArgs.getModID().equals(Metallurgy.MODID)) {
					ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
				}
			}
		}
		
}
