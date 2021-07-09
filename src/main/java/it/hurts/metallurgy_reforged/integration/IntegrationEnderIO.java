/*==============================================================================
 = Class: IntegrationEnderIO
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class IntegrationEnderIO {

	private static final String DEFAULT_XML = "/assets/metallurgy/recipes/metallurgy_enderio_alloys.xml";

	public static void init(String recipeXMLPath)
	{
		Utils.copyFile(Utils.getPath(DEFAULT_XML), recipeXMLPath, !GeneralConfig.enableEnderIOAlloyConfig);
		FMLInterModComms.sendMessage("enderio", "recipe:xml:file", recipeXMLPath);
	}

}
