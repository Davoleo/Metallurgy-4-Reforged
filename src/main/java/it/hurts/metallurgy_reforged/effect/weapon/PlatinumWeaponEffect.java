/*==============================================================================
 = Class: PlatinumWeaponEffect
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

public class PlatinumWeaponEffect extends BaseMetallurgyEffect {

	public PlatinumWeaponEffect()
	{
		super(ModMetals.PLATINUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void buffUndeadDamage(LivingHurtEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();
		if (sourceEnt instanceof EntityLivingBase)
		{
			if (!canBeApplied(((EntityLivingBase) sourceEnt)))
				return;

			EntityLivingBase target = event.getEntityLiving();
			if (target.isEntityUndead())
			{
				event.setAmount(event.getAmount() + 4F);
				target.setFire(4);
				for (int i = 0; i < 20; i++)
					spawnParticle(target.world, target.posX, target.posY + target.getEyeHeight(), target.posZ,
							(Math.random() * 0.125) - 0.0625, (Math.random() * 0.125) - 0.0625, (Math.random() * 0.125) - 0.0625,
							1.3F, true, 6);
			}
		}
	}

}
