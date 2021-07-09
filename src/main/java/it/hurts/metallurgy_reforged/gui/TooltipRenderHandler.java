/*==============================================================================
 = Class: TooltipRenderHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.gui;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipRenderHandler {

	@SubscribeEvent
	public static void tooltipRenderColor(RenderTooltipEvent.Color event)
	{
		Item item = event.getStack().getItem();
		Metal metal = ItemUtils.getMetalFromItem(item);
		if (metal != null)
		{
			int color = metal.getStats().getColorHex();
			//Move the alpha channel 6 digits (24 / 4) to the left and add the rgb color to it
			int argb = (0xFF << 24) + color;
			event.setBorderStart(argb);
			event.setBorderEnd(argb);
		}
	}

}
