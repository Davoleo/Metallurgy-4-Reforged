/*==============================================================================
 = Class: EtheriumWeaponEffect
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
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class EtheriumWeaponEffect extends BaseMetallurgyEffect {

	public EtheriumWeaponEffect()
	{
		super(ModMetals.ETHERIUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void extraDamage(LivingDamageEvent event)
	{
		Entity attacker = event.getSource().getImmediateSource();
		if (!(attacker instanceof EntityLivingBase))
			return;

		EntityLivingBase livingAttacker = ((EntityLivingBase) attacker);

		if (!canBeApplied(livingAttacker))
			return;

		//33% chance
		if (Utils.random.nextInt(3) < 1)
		{
			//Original + 2..4
			float augment = 2 + Utils.random.nextInt(3);
			event.setAmount(event.getAmount() + augment);

			if (livingAttacker.getHealth() < livingAttacker.getMaxHealth())
			{
				livingAttacker.heal(augment);
			}
			else
			{
				// TODO: 02/03/2021 Fix Absorption hearts not being refilled when the effect is still active
				if (!livingAttacker.isPotionActive(MobEffects.ABSORPTION))
					livingAttacker.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 0));
			}

			Vec3d targetVec = event.getEntityLiving().getPositionVector().add(0, event.getEntityLiving().height / 2, 0);
			Vec3d motionVec = targetVec.subtractReverse(livingAttacker.getPositionVector().add(0, event.getEntityLiving().height / 2, 0)).normalize();
			Utils.repeat(15, () -> {
				double r1 = -0.3 + Utils.random.nextFloat() * 0.5;
				double r2 = -0.3 + Utils.random.nextFloat() * 0.5;
				double r3 = -0.3 + Utils.random.nextFloat() * 0.5;
				spawnParticle(attacker.world, targetVec.x + r1, targetVec.y + r2, targetVec.z + r3,
						motionVec.x * 0.2, motionVec.y * 0.2, motionVec.z * 0.2, 2F, true, 2);
			});
		}
	}

}
