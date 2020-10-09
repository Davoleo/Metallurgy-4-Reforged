/*
 * -------------------------------------------------------------------------------------------------------
 * Class: VulcaniteArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;

public class VulcaniteArmorEffect extends BaseMetallurgyEffect {

	public VulcaniteArmorEffect()
	{
		super(ModMetals.VULCANITE);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.vulcaniteArmorEffect && super.isEnabled();
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

	}

	@Override
	public void livingEvent(LivingEvent event)
	{
		if (event instanceof LivingAttackEvent)
		{
			boolean isFireDamage = ((LivingAttackEvent) event).getSource().isFireDamage();

			if (EventUtils.isEntityWearingArmor(event.getEntityLiving(), metal) && isFireDamage)
			{
				event.setCanceled(true);
			}
		}
		if (event instanceof LivingEvent.LivingUpdateEvent && EventUtils.isEntityWearingArmor(event.getEntityLiving(), metal) && event.getEntityLiving().isBurning())
		{
			event.getEntityLiving().extinguish();
		}
	}
}
