/*==============================================================================
 = Class: ShadowIronWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.config.EffectTweaksConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class ShadowIronWeaponEffect extends BaseMetallurgyEffect {

	public ShadowIronWeaponEffect()
	{
		super(ModMetals.SHADOW_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void randomCrit(LivingHurtEvent event)
	{
		if (event.getSource().getImmediateSource() instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = (EntityLivingBase) event.getSource().getImmediateSource();
			if (canBeApplied(attacker) && Utils.random.nextInt(6) == 0) {
				double critMultiplier = MathHelper.clampedLerp(1.2, EffectTweaksConfig.shadowIronWeaponCritUpperBound, Math.random());
				event.setAmount((float) (event.getAmount() * critMultiplier));

				for (int i = 0; i < 20; i++)
					spawnParticle(event.getEntityLiving(), 1.5F, true, 6);

			}
		}
	}

}
