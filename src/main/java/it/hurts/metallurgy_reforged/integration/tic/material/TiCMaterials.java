/*==============================================================================
 = Class: TiCMaterials
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.material;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.material.ToolStats;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.util.JsonUtils;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class TiCMaterials {

	public static void addMaterialStats(Metal metal, Material material)
	{
		MetalStats metalS = metal.getStats();

		MetallurgyTiCStats stats = TinkerMetals.getMetal(metal);

		if (stats != null)
		{
			for (AbstractMaterialStats stat : stats.stats)
				registerStat(material, stat);
		}
		else if (metalS != null)
		{
			ToolStats TStats = metalS.getToolStats();
			if (TStats != null)
			{
				registerStats(material,
						MetallurgyTiCStats.getHeadA(metal),
						MetallurgyTiCStats.getHandleA(metal),
						MetallurgyTiCStats.getExtraA(metal),
						MetallurgyTiCStats.getBowA(metal));
			}
		}
	}


	public static void registerStat(Material material, IMaterialStats stats)
	{
		if (!material.hasStats(stats.getIdentifier()))
			TinkerRegistry.addMaterialStats(material, stats);
	}


	public static void registerStats(Material material, IMaterialStats... stats)
	{
		for (IMaterialStats stat : stats)
			TiCMaterials.registerStat(material, stat);
	}

	private static final String TINKER_RENDER_INFO_PATH = "/assets/tconstruct/metallurgy_renders.json";
	static Map<Metal, MaterialRenderInfo.Metal> renderInfos = new HashMap<>();

	//Initialise renderInfos
	static
	{
		try
		{
			Gson gson = new GsonBuilder().create();
			JsonObject renderObj = JsonUtils.fromJson(gson, Files.newBufferedReader(Utils.getPath(TINKER_RENDER_INFO_PATH)), JsonObject.class);

			ModMetals.metalMap.forEach((name, metal) -> {
				if (metal.hasToolSet())
				{
					JsonObject parametersObj = JsonUtils.getJsonObject(renderObj, metal.toString());
					float shinyness = JsonUtils.getFloat(parametersObj, "shinyness");
					float brightness = JsonUtils.getFloat(parametersObj, "brightness");
					float hueshift = JsonUtils.getFloat(parametersObj, "hueshift");
					MaterialRenderInfo.Metal info = new MaterialRenderInfo.Metal(metal.getStats().getColorHex(), shinyness, brightness, hueshift);
					renderInfos.put(metal, info);
				}
			});
		}
		catch (IOException | NullPointerException e)
		{
			e.printStackTrace();
		}
	}
}
