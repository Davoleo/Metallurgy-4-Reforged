/*==============================================================================
 = Class: KrikArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.krik.IKrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class KrikArmorEffect extends BaseMetallurgyEffect {

	private static final EventInstance<LivingEvent.LivingUpdateEvent> LEVITATE_EFFECT = new EventInstance<>(KrikArmorEffect::livingUpdate, LivingEvent.LivingUpdateEvent.class);
	private static final EventInstance<LivingFallEvent> CANCEL_FALL = new EventInstance<>(KrikArmorEffect::cancelFall, LivingFallEvent.class);


	public KrikArmorEffect()
	{
		super(ModMetals.KRIK);
	}

	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@Override
	public EventInstance<? extends LivingEvent>[] getEvents()
	{
		return new EventInstance[]{LEVITATE_EFFECT, CANCEL_FALL};
	}

	private static void livingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
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

	private static void cancelFall(LivingFallEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
			event.setCanceled(true);
	}

}
