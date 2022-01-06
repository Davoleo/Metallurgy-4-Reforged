/*==============================================================================
 = Class: AmordrineWeaponEffect
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
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class AmordrineWeaponEffect extends BaseMetallurgyEffect {

	public AmordrineWeaponEffect()
	{
		super(ModMetals.AMORDRINE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void onMobAttacked(LivingHurtEvent event)
	{
		Entity attacker = event.getSource().getTrueSource();

		if (event.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			if (!canBeApplied(((EntityLivingBase) attacker)))
				return;

			//Higher Percentage means lower entity life
			float percentage = 1F - event.getEntityLiving().getHealth() / event.getEntityLiving().getMaxHealth();
			float originalAMount = event.getAmount();
			event.setAmount(originalAMount + originalAMount * percentage * 2);

			if (!event.getEntity().world.isRemote)
				for (int i = 0; i < 25; i++)
					spawnParticle(event.getEntityLiving(), 1F + percentage * 2F, true, MathHelper.floor(percentage * 9));
		}
	}

}
