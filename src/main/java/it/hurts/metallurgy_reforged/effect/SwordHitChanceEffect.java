/*==============================================================================
 = Class: SwordHitChanceEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;
import java.util.Random;

public class SwordHitChanceEffect extends BaseMetallurgyEffect {

	protected Random random = new Random();
	protected int chance;
	protected PotionEffect effect;

	public SwordHitChanceEffect(Metal metal, int chance, PotionEffect effect)
	{
		super(metal);
		this.chance = chance;
		this.effect = effect;
	}

	@Override
	public boolean isEnabled() {
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
	public void onPlayerAttack(EntityPlayer attacker, Entity target)
	{
		if (target instanceof EntityLivingBase)
		{
			EntityLivingBase livingTarget = ((EntityLivingBase) target);
			if (livingTarget.getHeldItemMainhand().getItem() == metal.getTool(getToolClass()))
			{
				if (random.nextInt(100) < chance)
				{
					livingTarget.addPotionEffect(effect);
				}
			}
		}
	}

}
