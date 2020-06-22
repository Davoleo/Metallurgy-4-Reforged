/*
 * -------------------------------------------------------------------------------------------------------
 * Class: VulcaniteIgnatiusSwordEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.AbstractMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class VulcaniteIgnatiusSwordEffect extends AbstractMetallurgyEffect {

	public VulcaniteIgnatiusSwordEffect(Metal metal)
	{
		super(metal);
	}

	@Override
	protected boolean isEnabled()
	{
		return metal == ModMetals.IGNATIUS ? ToolEffectsConfig.ignatiusSwordEffect : ToolEffectsConfig.vulcaniteSwordEffect;
	}

	@Override
	protected boolean isToolEffect()
	{
		return true;
	}

	@Nullable
	@Override
	protected EnumTools getToolClass()
	{
		return EnumTools.SWORD;
	}

	@Override
	public void onPlayerAttack(EntityPlayer attacker, Entity target)
	{
		if (target instanceof EntityLivingBase)
		{
			EntityLivingBase livingTarget = ((EntityLivingBase) target);
			if (metal == ModMetals.IGNATIUS)
			{
				if (Math.random() * 100 < 25)
				{
					livingTarget.setFire(5);
				}
			}
			else
			{
				if (Math.random() * 100 < 50)
				{
					livingTarget.setFire(5);
				}
			}
		}
	}

}
