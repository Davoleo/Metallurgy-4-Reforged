/*==============================================================================
 = Class: PlatinumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import com.google.common.collect.ImmutableMap;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Map;

public class PlatinumArmorEffect extends BaseMetallurgyEffect {

	private final Map<Potion, Potion> potionPurificationMap = ImmutableMap.of(
			MobEffects.POISON, MobEffects.REGENERATION,
			MobEffects.SLOWNESS, MobEffects.SPEED,
			MobEffects.WEAKNESS, MobEffects.STRENGTH
	);

	public PlatinumArmorEffect()
	{
		super(ModMetals.PLATINUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void purifyEffects(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		float level = getLevel(entity) * 2;
		if (level == 0)
			return;

		potionPurificationMap.forEach((badPotion, goodPotion) -> {
			PotionEffect potionEffect = entity.getActivePotionEffect(badPotion);

			if (potionEffect != null)
			{
				//Duration of the purified effects (50%..200% of the original value depending on the armor level)
				int newDuration = (int) (level * potionEffect.getDuration());
				//Remove the negative effect
				entity.removePotionEffect(badPotion);
				//Add it's counterpart with the new duration and same amplifier
				entity.addPotionEffect(new PotionEffect(goodPotion, newDuration, potionEffect.getAmplifier()));
				//Spawn Particles
				Vec3d halvedLookVec = entity.getLookVec().scale(0.5);
				for (int i = 0; i < 2; i++)
					spawnParticle(entity.world,
							entity.posX + halvedLookVec.x, entity.posY + 1.1F, entity.posZ + halvedLookVec.z,
							1F, true, 5);
				for (int i = 0; i < 5; i++)
					spawnParticle(entity, 1, true, 5);
			}
		});

	}

}
