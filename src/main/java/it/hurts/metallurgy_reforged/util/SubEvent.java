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
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyArmorTraits;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.GauntletEffect;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.GauntletOperation;
import it.hurts.metallurgy_reforged.handler.FuelHandler;
import it.hurts.metallurgy_reforged.handler.ArmorEffectHandler;
import it.hurts.metallurgy_reforged.handler.GadgetsHandler;
import it.hurts.metallurgy_reforged.handler.PickaxeEffectHandler;
import it.hurts.metallurgy_reforged.handler.SwordEffectHandler;
import it.hurts.metallurgy_reforged.world.ModLakeWorldGen;
import net.minecraftforge.common.MinecraftForge;

public class SubEvent {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(ArmorEffectHandler.class);
		MinecraftForge.EVENT_BUS.register(GadgetsHandler.class);
		MinecraftForge.EVENT_BUS.register(SwordEffectHandler.class);
		MinecraftForge.EVENT_BUS.register(PickaxeEffectHandler.class);
		MinecraftForge.EVENT_BUS.register(GauntletOperation.class);
		MinecraftForge.EVENT_BUS.register(GauntletEffect.class);
		MinecraftForge.EVENT_BUS.register(GeneralConfig.ChangeListener.class);
		MinecraftForge.EVENT_BUS.register(ArmorConfig.ChangeListener.class);
		MinecraftForge.EVENT_BUS.register(FuelHandler.class);
		MinecraftForge.EVENT_BUS.register(FluidEvents.class);
		MinecraftForge.EVENT_BUS.register(ModLakeWorldGen.class);

		if(ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegraton) {
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.quickly);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.stronglyAmordrine);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.stronglyKalendrite);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.jumpMaster);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.resistance);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.deeply);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.volcano);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.foodly);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.catEyes);
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.prometheum);
		}
	}
	
}
