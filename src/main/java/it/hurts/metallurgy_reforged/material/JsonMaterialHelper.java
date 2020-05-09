/*
 * -------------------------------------------------------------------------------------------------------
 * Class: JsonMaterialHelper
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import com.google.gson.*;
import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.util.JsonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JsonMaterialHelper {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static final String DEFAULT_CONFIG = "/assets/metallurgy/materials.json";

	/**
	 * Reads the JSON config and creates a new MetalStats object for each entry of the JSON Array
	 *
	 * @param resourcePath The path to the JSON Config
	 * @param defaultStats used as fallback when reading user-customized JSON config,
	 *
	 * @return
	 */
	public static Set<MetalStats> readConfig(String resourcePath, Set<MetalStats> defaultStats) throws JsonSyntaxException
	{
		Set<MetalStats> metalStats = new HashSet<>();

		try
		{
			Path path;
			if (resourcePath.equals(DEFAULT_CONFIG))
				path = getPath(resourcePath);
			else
				path = new File(Metallurgy.materialConfig).toPath();

			BufferedReader reader = Files.newBufferedReader(path);
			JsonArray materials = JsonUtils.fromJson(gson, reader, JsonArray.class);

			if (materials != null)
			{
				materials.forEach(jsonElement -> {
					JsonObject jsonMetal = jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;

					if (jsonMetal != null)
					{
						MetalStats metalStat = readMetalFromJson(jsonMetal, defaultStats);
						metalStats.add(metalStat);
					}
				});
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return metalStats;
	}

	private static MetalStats readMetalFromJson(JsonObject metalObj, Set<MetalStats> defaultStats) throws JsonSyntaxException
	{

		if (!metalObj.has("name"))
			return null;

		String name = JsonUtils.getString(metalObj, "name");

		MetalStats defaultStat = getMetalStatsByName(name, defaultStats);

		float hardness = JsonUtils.getFloat(metalObj, "hardness", defaultStat.getHardness());
		float blockBlastResistance = JsonUtils.getFloat(metalObj, "blast_resistance", defaultStat.getHardness());
		int oreHarvest = JsonUtils.getInt(metalObj, "ore_harvest_level", defaultStat.getOreHarvest());
		int color = Integer.parseInt(JsonUtils.getString(metalObj, "color", String.valueOf(defaultStat.getColorHex())), 16);

		ArmorStats armorStats = getArmorStats(metalObj, defaultStat.getArmorStats());
		ToolStats toolStats = getToolStats(metalObj, defaultStat.getToolStats());

		return new MetalStats(name, hardness, blockBlastResistance, armorStats, toolStats, oreHarvest, color);
		// TODO: 05/05/2020 TEST This stuff INVESTIGATE
	}

	private static MetalStats getMetalStatsByName(String name, Set<MetalStats> defaultStats) throws JsonSyntaxException
	{

		if (defaultStats != null)
		{
			for (MetalStats stat : defaultStats)
			{
				if (stat.getName().equals(name))
				{
					return stat;
				}
			}
		}

		return MetalStats.EMPTY_METAL_STATS;
	}

	private static ArmorStats getArmorStats(JsonObject metalStats, ArmorStats fallback) throws JsonSyntaxException
	{

		if (metalStats.has("armor_stats"))
		{
			JsonObject armorStats = JsonUtils.getJsonObject(metalStats, "armor_stats");

			int enchantability = JsonUtils.getInt(armorStats, "enchantability", fallback.getEnchantability());
			int durability = JsonUtils.getInt(armorStats, "durability", fallback.getDurability());
			float toughness = JsonUtils.getFloat(armorStats, "toughness", fallback.getToughness());
			int[] damageReduction = getIntArray(armorStats, "damage_reduction", fallback.getDamageReduction());

			return new ArmorStats(damageReduction, enchantability, durability, toughness);
		}

		return null;
	}

	private static ToolStats getToolStats(JsonObject metalStats, ToolStats fallback) throws JsonSyntaxException
	{

		if (metalStats.has("tool_stats"))
		{
			JsonObject toolStatsObj = JsonUtils.getJsonObject(metalStats, "tool_stats");

			float efficiency = JsonUtils.getFloat(toolStatsObj, "efficiency", fallback.getEfficiency());
			int harvestLevel = JsonUtils.getInt(toolStatsObj, "harvest_level", fallback.getHarvestLevel());
			int enchantability = JsonUtils.getInt(toolStatsObj, "enchantability", fallback.getToolMagic());
			int durability = JsonUtils.getInt(toolStatsObj, "durability", fallback.getMaxUses());
			float damage = JsonUtils.getFloat(toolStatsObj, "damage", fallback.getDamage());

			double maxHealth = JsonUtils.getFloat(toolStatsObj, "max_health", (float) fallback.getMaxHealth());
			double movementSpeed = JsonUtils.getFloat(toolStatsObj, "movement_speed", (float) fallback.getMovementSpeed());
			double attackDamage = JsonUtils.getFloat(toolStatsObj, "attack_damage", (float) fallback.getAttackDamage());
			double attackSpeed = JsonUtils.getFloat(toolStatsObj, "attack_speed", (float) fallback.getAttackSpeed());
			double reachDistance = JsonUtils.getFloat(toolStatsObj, "reach_distance", (float) fallback.getReachDistance());

			ToolStats toolStats = new ToolStats(enchantability, harvestLevel, durability, efficiency, damage);
			toolStats.setAttributes(maxHealth, movementSpeed, attackDamage, attackSpeed, reachDistance);
			return toolStats;
		}

		return null;
	}

	private static int[] getIntArray(JsonObject json, String memberName, int[] fallback) throws JsonSyntaxException
	{

		int[] arr = new int[4];

		if (json.has(memberName))
		{
			JsonArray jsonArray = json.getAsJsonArray(memberName);

			for (int i = 0; i < arr.length; i++)
			{
				JsonElement element = jsonArray.get(i);
				if (JsonUtils.isNumber(element))
				{
					arr[i] = element.getAsInt();
				}
				else
				{
					arr[i] = fallback[i];
				}
			}
		}

		return arr;
	}

	private static Path getPath(String resource)
	{
		FileSystem filesystem;

		try
		{
			URL url = Metallurgy.class.getResource(resource);

			if (url != null)
			{
				URI uri = url.toURI();
				Path path;

				if ("file".equals(uri.getScheme()))
				{
					path = Paths.get(Metallurgy.class.getResource(resource).toURI());
				}
				else
				{
					filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
					path = filesystem.getPath(resource);
				}

				return path;
			}
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static boolean copyConfig()
	{
		Path defaultPath = getPath(JsonMaterialHelper.DEFAULT_CONFIG);

		File userConfigFile = new File(Metallurgy.materialConfig);

		try
		{
			if (!userConfigFile.exists())
			{
				Files.copy(defaultPath, userConfigFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return true;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return false;
	}

}