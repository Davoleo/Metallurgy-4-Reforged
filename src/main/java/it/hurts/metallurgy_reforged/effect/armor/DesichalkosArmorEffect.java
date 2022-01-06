/*==============================================================================
 = Class: DesichalkosArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DesichalkosArmorEffect extends BaseMetallurgyEffect {

	public DesichalkosArmorEffect()
	{
		super(ModMetals.DESICHALKOS);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void updateTimeWithoutDamage(LivingEvent.LivingUpdateEvent event)
	{

		int level = getLevel(event.getEntityLiving());
		if (level == 0)
			return;

		PlayerEffectData effectData = event.getEntityLiving().getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

		if (effectData == null)
			return;

		if (effectData.desichalkosAbsorbLevel > level)
			effectData.desichalkosAbsorbLevel = level;

		if (effectData.desichalkosTimeWithoutTakingDamage < 200)
			effectData.desichalkosTimeWithoutTakingDamage += 1;
		else if (event.getEntity().ticksExisted % 140 == 0 && effectData.desichalkosAbsorbLevel < level)
		{
			event.getEntityLiving().world.playSound(null, event.getEntityLiving().getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.AMBIENT, 1.5F, 1.3F);
			for (int i = 0; i < 30; i++)
				spawnParticle(event.getEntityLiving(), 3F, true, 2);

			effectData.desichalkosAbsorbLevel += 1;
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		PlayerEffectData effectData = event.getEntityLiving().getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

		if (effectData == null)
			return;

		if (effectData.desichalkosAbsorbLevel > 0)
		{
			event.setCanceled(true);

			for (int i = 0; i < 30; i++)
				spawnParticle(event.getEntityLiving(), 3F, true, Math.min(effectData.desichalkosAbsorbLevel * 2, 9));
			effectData.desichalkosAbsorbLevel -= 1;

			event.getEntityLiving().world.playSound(null, event.getEntityLiving().getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.AMBIENT, 1.5F, 0.3F);
		}

		effectData.desichalkosTimeWithoutTakingDamage = 0;


	}

}
