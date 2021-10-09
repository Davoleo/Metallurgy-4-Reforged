/*==============================================================================
 = Class: OureclaseWeaponEffect
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class OureclaseWeaponEffect extends BaseMetallurgyEffect {

	public OureclaseWeaponEffect()
	{
		super(ModMetals.OURECLASE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void doubleDamage(LivingHurtEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();
		if (sourceEnt instanceof EntityLivingBase)
		{
			if (!canBeApplied(((EntityLivingBase) sourceEnt)))
				return;

			//If the enemy has full health
			if (event.getEntityLiving().getHealth() == event.getEntityLiving().getMaxHealth())
			{
				//Double the amount of damage
				event.setAmount(event.getAmount() * 2);
				for (int i = 0; i < 10; i++)
					spawnParticle(event.getEntity(), 3F, false, 5);
			}
		}
	}

}
