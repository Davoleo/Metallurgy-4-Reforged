/*==============================================================================
 = Class: SubEvent
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.fluid.FluidEvents;
import it.hurts.metallurgy_reforged.handler.*;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyArmorTraits;
import it.hurts.metallurgy_reforged.item.gadget.gauntlet.GauntletEffect;
import it.hurts.metallurgy_reforged.item.gadget.gauntlet.GauntletEquipHandler;
import it.hurts.metallurgy_reforged.world.ModLakeWorldGen;
import net.minecraftforge.common.MinecraftForge;

public class SubEvent {

	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(GadgetsHandler.class);
		MinecraftForge.EVENT_BUS.register(GauntletEquipHandler.class);
		MinecraftForge.EVENT_BUS.register(GauntletEffect.class);
		MinecraftForge.EVENT_BUS.register(GeneralConfig.ChangeListener.class);
		MinecraftForge.EVENT_BUS.register(FuelHandler.class);
		MinecraftForge.EVENT_BUS.register(FluidEvents.class);
		MinecraftForge.EVENT_BUS.register(ModLakeWorldGen.class);
		MinecraftForge.EVENT_BUS.register(SpawnHandler.class);
		MinecraftForge.EVENT_BUS.register(TileEntityHandler.class);
		MinecraftForge.EVENT_BUS.register(ProgressiveEffectsHandler.class);

		//Loads all Effect Handlers
		MetallurgyEffects.effects.values().forEach(effect -> {
			if (effect.isEnabled())
				MinecraftForge.EVENT_BUS.register(effect);
		});

		if (ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegration)
		{
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
			MinecraftForge.EVENT_BUS.register(MetallurgyArmorTraits.blindness);
		}
	}

}
