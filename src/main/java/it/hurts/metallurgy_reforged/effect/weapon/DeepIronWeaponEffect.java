/*==============================================================================
 = Class: DeepIronWeaponEffect
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DeepIronWeaponEffect extends BaseMetallurgyEffect {

	public DeepIronWeaponEffect()
	{
		super(ModMetals.DEEP_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event)
	{
		if (event.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) event.getSource().getTrueSource();
			if (!canBeApplied(entity))
				return;

			if (entity.isInWater())
			{
				event.setAmount(event.getAmount() + 6);
				for (int i = 0; i < 5; i++)
					spawnParticle(event.getEntityLiving(), 0.6F, false, 5);
			}
		}
	}

}
