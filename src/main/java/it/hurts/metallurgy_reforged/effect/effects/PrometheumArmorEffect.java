/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PrometheumArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.effects;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.AbstractMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;

public class PrometheumArmorEffect extends AbstractMetallurgyEffect {

	public PrometheumArmorEffect()
	{
		super(ModMetals.PROMETHEUM);
	}

	@Override
	protected boolean isEnabled()
	{
		return ArmorEffectsConfig.prometheumArmorEffect;
	}

	@Override
	protected boolean isToolEffect()
	{
		return false;
	}

	@Override
	protected EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onPlayerTick(EntityPlayer player)
	{
		if (EventUtils.isPlayerWearingArmor(player, metal) && player.isPotionActive(MobEffects.POISON))
		{
			player.removePotionEffect(MobEffects.POISON);
		}
	}

}
