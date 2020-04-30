/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModMetals
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModMetals {

	public static Map<String, Metal> metalMap = new HashMap<>();

	public static final Metal ADAMANTINE = metalMap.get("adamantine");
	public static final Metal ALDUORITE = metalMap.get("alduorite");
	public static final Metal AMORDRINE = metalMap.get("amordrine");
	public static final Metal ANGMALLEN = metalMap.get("angmallen");
	public static final Metal ASTRAL_SILVER = metalMap.get("astral_silver");
	public static final Metal ATLARUS = metalMap.get("atlarus");
	public static final Metal BLACK_STEEL = metalMap.get("black_steel");
	public static final Metal BRASS = metalMap.get("brass");
	public static final Metal BRONZE = metalMap.get("bronze");
	public static final Metal CARMOT = metalMap.get("carmot");
	public static final Metal CELENEGIL = metalMap.get("celenegil");
	public static final Metal CERUCLASE = metalMap.get("ceruclase");
	public static final Metal COPPER = metalMap.get("copper");
	public static final Metal DAMASCUS_STEEL = metalMap.get("damascus_steel");
	public static final Metal DEEP_IRON = metalMap.get("deep_iron");
	public static final Metal DESICHALKOS = metalMap.get("desichalkos");
	public static final Metal ELECTRUM = metalMap.get("electrum");
	public static final Metal ETHERIUM = metalMap.get("etherium");
	public static final Metal EXIMITE = metalMap.get("eximite");
	public static final Metal HADEROTH = metalMap.get("haderoth");
	public static final Metal HEPATIZON = metalMap.get("hepatizon");
	public static final Metal IGNATIUS = metalMap.get("ignatius");
	public static final Metal INFUSCOLIUM = metalMap.get("infuscolium");
	public static final Metal INOLASHITE = metalMap.get("inolashite");
	public static final Metal KALENDRITE = metalMap.get("kalendrite");
	public static final Metal KRIK = metalMap.get("krik");
	public static final Metal LEMURITE = metalMap.get("lemurite");
	public static final Metal LUTETIUM = metalMap.get("lutetium");
	public static final Metal MANGANESE = metalMap.get("manganese");
	public static final Metal MEUTOITE = metalMap.get("meutoite");
	public static final Metal MIDASIUM = metalMap.get("midasium");
	public static final Metal MITHRIL = metalMap.get("mithril");
	public static final Metal ORICHALCUM = metalMap.get("orichalcum");
	public static final Metal OSMIUM = metalMap.get("osmium");
	public static final Metal OURECLASE = metalMap.get("oureclase");
	public static final Metal PLATINUM = metalMap.get("platinum");
	public static final Metal PROMETHEUM = metalMap.get("prometheum");
	public static final Metal QUICKSILVER = metalMap.get("quicksilver");
	public static final Metal RUBRACIUM = metalMap.get("rubracium");
	public static final Metal SANGUINITE = metalMap.get("sanguinite");
	public static final Metal SHADOW_IRON = metalMap.get("shadow_iron");
	public static final Metal SHADOW_STEEL = metalMap.get("shadow_steel");
	public static final Metal SILVER = metalMap.get("silver");
	public static final Metal STEEL = metalMap.get("steel");
	public static final Metal TARTARITE = metalMap.get("tartarite");
	public static final Metal TIN = metalMap.get("tin");
	public static final Metal VULCANITE = metalMap.get("vulcanite");
	public static final Metal VYROXERES = metalMap.get("vyroxeres");
	public static final Metal ZINC = metalMap.get("zinc");

	public static void init()
	{

		//TODO copy the default file over to the config folder

		Set<MetalStats> defaultStats = JsonMaterialHandler.readConfig(JsonMaterialHandler.DEFAULT_CONFIG, null);
		Set<MetalStats> playerStats = JsonMaterialHandler.readConfig("/", defaultStats);

		playerStats.forEach(MetalStats::createMetal);
	}
	//TODO: Init Metal Fluid stuff in an alternative way

}