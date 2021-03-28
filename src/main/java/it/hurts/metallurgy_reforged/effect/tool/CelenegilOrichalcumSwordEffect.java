/*==============================================================================
 = Class: CelenegilOrichalcumSwordEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
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
		return super.isEnabled();
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
		Item celenegilSword = null;
		Item orichalcumSword = null;

		if (ModMetals.CELENEGIL != null)
			celenegilSword = ModMetals.CELENEGIL.getTool(EnumTools.SWORD);
		if (ModMetals.ORICHALCUM != null)
			orichalcumSword = ModMetals.ORICHALCUM.getTool(EnumTools.SWORD);

		if (isEligibleForEffect(killer, celenegilSword))
		{
			killer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 140, 0));
			killer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0));
		}

		if (isEligibleForEffect(killer, orichalcumSword))
			killer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 140, 0));
	}

	private boolean isEligibleForEffect(EntityPlayer player, Item sword)
	{
		if (sword == player.getHeldItemMainhand().getItem())
		{
			if (!player.isPotionActive(MobEffects.STRENGTH) || player.getActivePotionEffect(MobEffects.STRENGTH).getDuration() < 8)
			{
				return true;
			}

			if (metal == ModMetals.CELENEGIL)
			{
				return !player.isPotionActive(MobEffects.SPEED) || player.getActivePotionEffect(MobEffects.SPEED).getDuration() < 8;
			}
		}

		return false;
	}

}
