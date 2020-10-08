/*
 * -------------------------------------------------------------------------------------------------------
 * Class: KrikArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.krik.IKrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import javax.annotation.Nullable;

public class KrikArmorEffect extends BaseMetallurgyEffect {

	public KrikArmorEffect()
	{
		super(ModMetals.KRIK);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.krikArmorEffect && super.isEnabled();
	}

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onPlayerTick(EntityPlayer player)
	{
		if (EventUtils.isEntityWearingArmor(player, metal))
		{
			final int STEP = 255 / 27;

			IKrikEffect capability = player.getCapability(KrikEffectProvider.KRIK_EFFECT_CAPABILITY, null);

			if (capability != null)
			{
				int maxLevel = KrikEffect.getMaxLevel(player);
				int level = capability.getHeight();

				if (level <= maxLevel)
				{
					if (player.posY < level * STEP)
					{
						player.motionY = 0.4;
					}
					else if (Math.round(player.posY) == level * STEP)
					{
						player.motionY = 0;
					}
				}
				else
				{
					capability.setHeight(maxLevel);
				}
			}

		}
	}

	@Override
	public void livingEvent(LivingEvent livingEvent)
	{
		if (livingEvent instanceof LivingFallEvent)
		{
			LivingFallEvent event = ((LivingFallEvent) livingEvent);

			if (event.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();

				if (EventUtils.isEntityWearingArmor(player, metal))
					event.setCanceled(true);
			}
		}
	}

}
