/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SubEvent
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.config.ArmorConfig;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.fluid.FluidEvents;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.GauntletEffect;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.GauntletOperation;
import it.hurts.metallurgy_reforged.util.handler.FuelHandler;
import it.hurts.metallurgy_reforged.world.ModLakeWorldGen;
import net.minecraftforge.common.MinecraftForge;

public class SubEvent {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new GauntletOperation());
		MinecraftForge.EVENT_BUS.register(new GauntletEffect());
		MinecraftForge.EVENT_BUS.register(new GeneralConfig.ChangeListener());
		MinecraftForge.EVENT_BUS.register(new ArmorConfig.ChangeListener());
		MinecraftForge.EVENT_BUS.register(new FuelHandler());
		MinecraftForge.EVENT_BUS.register(new FluidEvents());
		MinecraftForge.EVENT_BUS.register(new ModLakeWorldGen());
	}
	
}
