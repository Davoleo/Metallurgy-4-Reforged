/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorPotionEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public abstract class ArmorPotionEffect extends AbstractMetallurgyEffect {

	protected PotionEffect effect;

	public ArmorPotionEffect(Metal metal, PotionEffect effect)
	{
		super(metal);
		this.effect = effect;
	}

	@Override
	public abstract boolean isEnabled();

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onPlayerTick(EntityPlayer player)
	{
		if (EventUtils.isPlayerWearingArmor(player, metal))
		{
			player.addPotionEffect(effect);
		}
	}

}
