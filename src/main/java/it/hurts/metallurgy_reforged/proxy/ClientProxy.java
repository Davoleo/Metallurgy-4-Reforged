/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ClientProxy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.proxy;

import it.hurts.metallurgy_reforged.gui.hud.HUDHandler;
import it.hurts.metallurgy_reforged.handler.KeyboardHandler;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

@SuppressWarnings("unused")
public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		MinecraftForge.EVENT_BUS.register(KeyboardHandler.class);
		MinecraftForge.EVENT_BUS.register(HUDHandler.class);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			List<Metal> metals = ItemOreDetector.getDetectorMetals(stack);

			if (tintIndex <= metals.size())
			{
				return metals.get(tintIndex - 1).getStats().getColorHex();
			}
			return -1;

		}, ModItems.oreDetector);
	}


}
