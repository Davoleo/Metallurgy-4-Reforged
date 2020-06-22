/*
 * -------------------------------------------------------------------------------------------------------
 * Class: CelenegilOrichalcumSwordEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;

public class CelenegilOrichalcumSwordEffect extends BaseMetallurgyEffect {

	public CelenegilOrichalcumSwordEffect(Metal metal)
	{
		super(metal);
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public boolean isToolEffect()
	{
		return true;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return EnumTools.SWORD;
	}

	@Override
	public void onPlayerKill(EntityPlayer killer, EntityLivingBase killedEntity)
	{
		//			Celenegil Sword ( Give Speed and Strenght on entity kill )
		if (killer.getHeldItemMainhand().getItem() == ModMetals.CELENEGIL.getTool(EnumTools.SWORD) &&
				(killer.isPotionActive(MobEffects.STRENGTH) ? killer.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8 : false ||
						!killer.isPotionActive(MobEffects.SPEED) || killer.getActivePotionEffect(MobEffects.SPEED).getDuration() < 8))
		{

			killer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 140, 0, false, false));
			killer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
		}

		//Orichalcum Sword ( Give Strenght on entity kill )
		if (killer.getHeldItemMainhand().getItem() == ModMetals.ORICHALCUM.getTool(EnumTools.SWORD) && (!killer.isPotionActive(MobEffects.STRENGTH) || killer.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8))
			killer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0, false, false));
	}

}
