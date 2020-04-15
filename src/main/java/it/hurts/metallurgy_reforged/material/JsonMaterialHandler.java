/*
 * -------------------------------------------------------------------------------------------------------
 * Class: JsonMaterialHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.material;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.util.JsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.Set;

public class JsonMaterialHandler {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private static final String DEFAULT_CONFIG = Metallurgy.class.getResource("/assets/metallurgy/materials.json").getPath();

	public static Set<MetalStats> readConfig(String path)
	{
		try
		{
			BufferedReader reader = Files.newBufferedReader(getPath(path));
			JsonArray materials = JsonUtils.fromJson(gson, reader, JsonArray.class);

			if (materials != null)
			{

				materials.forEach(jsonElement -> {
					JsonObject metal = jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : null;
				});

			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
					path = filesystem.getPath("/assets/minecraft/recipes");
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

}
