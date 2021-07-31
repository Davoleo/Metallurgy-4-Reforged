/*==============================================================================
 = Class: OsmiumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class OsmiumArmorEffect extends BaseMetallurgyEffect {

	public OsmiumArmorEffect()
	{
		super(ModMetals.OSMIUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	/**
	 * @see EntityLivingBase#attackEntityFrom(DamageSource, float) EntityLivingBase#attackEntityFrom (Line 1108)
	 */
	@SubscribeEvent
	public void reverseKnockback(LivingDamageEvent event)
	{
		EntityLivingBase armored = event.getEntityLiving();
		float level = getLevel(armored);

		if (level == 0)
			return;

		Entity attacker = event.getSource().getImmediateSource();

		if (attacker instanceof EntityLivingBase)
		{
			//From EntityLivingBase
			double xDiff = armored.posX - attacker.posX;
			double zDiff = armored.posZ - attacker.posZ;

			while (xDiff * xDiff + zDiff * zDiff < 1.0E-4D)
			{
				zDiff = (Math.random() - Math.random()) * 0.01D;
				xDiff = (Math.random() - Math.random()) * 0.01D;
			}
			((EntityLivingBase) attacker).knockBack(armored, 0.3F * 4 * level, xDiff, zDiff);
		}
	}

}
