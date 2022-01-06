/*==============================================================================
 = Class: VyroxeresWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class VyroxeresWeaponEffect extends BaseMetallurgyEffect {

	public VyroxeresWeaponEffect()
	{
		super(ModMetals.VYROXERES);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void poisonTarget(LivingDamageEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();
		if (sourceEnt instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) sourceEnt);
			if (!canBeApplied(attacker))
				return;

			EntityLivingBase target = event.getEntityLiving();
			PotionEffect poisonEffect = target.getActivePotionEffect(MobEffects.POISON);

			int amplifier = poisonEffect != null ? poisonEffect.getAmplifier() : -1;
			target.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, amplifier + 1));
			for (int i = 0; i < 10; i++)
				spawnParticle(target, amplifier * 0.5F, true, 5);
		}
	}

}
