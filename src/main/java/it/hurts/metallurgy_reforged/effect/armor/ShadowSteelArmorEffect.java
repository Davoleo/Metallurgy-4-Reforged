/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ShadowSteelArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nullable;

public class ShadowSteelArmorEffect extends BaseMetallurgyEffect {

	public ShadowSteelArmorEffect()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.shadowSteelArmorEffect;
	}

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onEntityHurt(LivingHurtEvent event)
	{
		Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer) entity);

			if (EventUtils.isPlayerWearingArmor(player, metal))
			{
				float amount = event.getAmount();
				//Decrease the damage amount of 75% of the original damage in case the player is in complete darkness
				amount -= Utils.getLightArmorPercentage(player, 0.75F) * amount;
				event.setAmount(amount);
			}
		}

	}

}
