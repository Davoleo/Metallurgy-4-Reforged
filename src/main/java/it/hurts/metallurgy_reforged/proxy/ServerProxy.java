/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ServerProxy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("unused")
public class ServerProxy implements IProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		//Nothing to do only on Server-Side
	}

}
