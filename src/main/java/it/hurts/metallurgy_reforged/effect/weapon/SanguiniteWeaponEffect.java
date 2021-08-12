/*==============================================================================
 = Class: SanguiniteWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import com.google.common.util.concurrent.AtomicDouble;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class SanguiniteWeaponEffect extends BaseMetallurgyEffect {

	public SanguiniteWeaponEffect()
	{
		super(ModMetals.SANGUINITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	private void devour(DamageSource source, AtomicDouble damageOrMaxHealth, boolean deathEvent)
	{
		Entity sourceEnt = source.getImmediateSource();

		if (sourceEnt instanceof EntityPlayer)
		{
			EntityPlayer attacker = ((EntityPlayer) sourceEnt);
			if (!canBeApplied(attacker))
				return;

			if (!deathEvent)
			{
				//BERRY CRAZY (https://youtu.be/bI2-ioFv3UA)
				int voracityLevel = 20 - attacker.getFoodStats().getFoodLevel();
				damageOrMaxHealth.set(Math.min(damageOrMaxHealth.get() + voracityLevel, 20));
			}
			else
			{
				int newLevel = attacker.getFoodStats().getFoodLevel() + Math.round(damageOrMaxHealth.floatValue() / 5F);
				attacker.getFoodStats().setFoodLevel(newLevel);
				attacker.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 80));
			}
		}
	}

	@SubscribeEvent
	public void devourHit(LivingHurtEvent event)
	{
		AtomicDouble amount = new AtomicDouble(event.getAmount());
		devour(event.getSource(), amount, false);
		event.setAmount(amount.floatValue());
		for (int i = 0; i < 10; i++)
			spawnParticle(event.getEntity(), 2F, true, 6);
	}

	@SubscribeEvent
	public void devourKill(LivingDeathEvent event)
	{
		devour(event.getSource(), new AtomicDouble(event.getEntityLiving().getMaxHealth()), true);
	}

}
