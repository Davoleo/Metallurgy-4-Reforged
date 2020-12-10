/*==============================================================================
 = Class: IntegrationSilentGems
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.silentgems;

import it.hurts.metallurgy_reforged.material.ModMetals;
import net.silentchaos512.gems.api.tool.part.ToolPartRegistry;

public class IntegrationSilentGems {

	public static void init()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			if (metal.hasToolSet())
			{
				ToolPartRegistry.putPart(new MetallurgyToolPart(new MetallurgyPartProperties(metal.getStats(), metal.getIngot())));
			}
		});
	}

}
