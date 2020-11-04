/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModMetals
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.Utils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModMetals {

	//@PierKnight100's Slangs
	//NullerPointer | Buggo | Oredirect | WRUAPPER

	public static Map<String, Metal> metalMap = new HashMap<>();

	public static Metal ADAMANTINE;
	public static Metal ALDUORITE;
	public static Metal AMORDRINE;
	public static Metal ANGMALLEN;
	public static Metal ASTRAL_SILVER;
	public static Metal ATLARUS;
	public static Metal BLACK_STEEL;
	public static Metal BRASS;
	public static Metal BRONZE;
	public static Metal CARMOT;
	public static Metal CELENEGIL;
	public static Metal CERUCLASE;
	public static Metal COPPER;
	public static Metal DAMASCUS_STEEL;
	public static Metal DEEP_IRON;
	public static Metal DESICHALKOS;
	public static Metal ELECTRUM;
	public static Metal ETHERIUM;
	public static Metal EXIMITE;
	public static Metal HADEROTH;
	public static Metal HEPATIZON;
	public static Metal IGNATIUS;
	public static Metal INFUSCOLIUM;
	public static Metal INOLASHITE;
	public static Metal KALENDRITE;
	public static Metal KRIK;
	public static Metal LEMURITE;
	public static Metal LUTETIUM;
	public static Metal MANGANESE;
	public static Metal MEUTOITE;
	public static Metal MIDASIUM;
	public static Metal MITHRIL;
	public static Metal ORICHALCUM;
	public static Metal OSMIUM;
	public static Metal OURECLASE;
	public static Metal PLATINUM;
	public static Metal PROMETHEUM;
	public static Metal QUICKSILVER;
	public static Metal RUBRACIUM;
	public static Metal SANGUINITE;
	public static Metal SHADOW_IRON;
	public static Metal SHADOW_STEEL;
	public static Metal SILVER;
	public static Metal STEEL;
	public static Metal TARTARITE;
	public static Metal TIN;
	public static Metal VULCANITE;
	public static Metal VYROXERES;
	public static Metal ZINC;

	public static void init()
	{
		Path defaultConfigPath = Utils.getPath(JsonMaterialHelper.DEFAULT_CONFIG);
		Set<MetalStats> defaultStats = JsonMaterialHelper.readConfig(JsonMaterialHelper.DEFAULT_CONFIG, null);

		boolean copied = Utils.copyFile(defaultConfigPath, Metallurgy.materialConfig);

		Set<MetalStats> playerStats = defaultStats;

		//If the configuration file was already copied and the custom stats loader was enabled
		if (GeneralConfig.enableCustomMaterialStatsConfig && !copied)
		{
			try
			{
				playerStats = JsonMaterialHelper.readConfig(Metallurgy.materialConfig, defaultStats);
			}
			catch (JsonSyntaxException e)
			{
				Metallurgy.logger.error("There was an error while loading custom stats for Metallurgy Materials (CHECK YOUR JSON CONFIG FOR MISTAKES)");
				Metallurgy.logger.error("Error Message: " + e.getMessage());
				Metallurgy.logger.warn("Metallurgy will now load the default material stats automatically...");
			}
			catch (JsonParseException e)
			{
				Metallurgy.logger.error("There was an error while loading the Metallurgy Materials json config (Your JSON file was invalid!)");
				Metallurgy.logger.error("Below this line you can read the error StackTrace to try to debug the error: ");
				e.printStackTrace();
				Metallurgy.logger.warn("Metallurgy will now load the default material stats automatically...");
			}

		}

		playerStats.forEach(MetalStats::createMetal);
	}

}