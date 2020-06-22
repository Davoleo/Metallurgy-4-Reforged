/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CelenegilArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.ArmorPotionEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class CelenegilArmorEffect extends ArmorPotionEffect {

	public CelenegilArmorEffect()
	{
		super(ModMetals.CELENEGIL, null);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.celenegilArmorEffect;
	}

	@Override
	public void onPlayerTick(EntityPlayer player)
	{
		int armorCount = EventUtils.getArmorPiecesCount(player, metal.getArmorSet());

		if (armorCount >= 3)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1, false, false));
		}
		else if (armorCount >= 1)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 0, false, false));
		}
	}

}
