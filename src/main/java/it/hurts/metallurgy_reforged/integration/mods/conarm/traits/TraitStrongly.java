/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TraitStrongly
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTraitLeveled;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitStrongly extends AbstractArmorTraitLeveled{
	
	public TraitStrongly(int levels) {
		super("strongly", String.valueOf(levels), 0xffffff, 2, levels);

	}
	
	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event){
		if(MetallurgyConArmorStats.isArmorTraitLeveled(event.player, "strongly", 2)) {
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, MetallurgyConArmorStats.getLevelForEffect(event.player, "strongly", 2), false, false));
		}
	}

}
