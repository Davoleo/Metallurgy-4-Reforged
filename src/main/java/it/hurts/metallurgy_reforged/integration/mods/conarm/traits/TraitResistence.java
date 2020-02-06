/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TraitResistence
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitResistence extends AbstractArmorTrait implements IConarmMetallurgyTrait {

	public TraitResistence()
	{
		super("resistance", TextFormatting.GREEN);
	}

	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event)
	{
		if (MetallurgyConArmorStats.isArmorTrait(event.player, "resistance"))
		{
			event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1, false, false));
		}
	}

}
