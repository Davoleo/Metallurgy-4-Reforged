/*==============================================================================
 = Class: ShadowSteelArmorEffect
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
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import javax.annotation.Nonnull;

@Deprecated
public class ShadowSteelArmorEffectOld extends BaseMetallurgyEffect {

	public ShadowSteelArmorEffectOld()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}


	public void livingEvent(LivingEvent livingEvent)
	{
		if (livingEvent instanceof LivingHurtEvent)
		{

			LivingHurtEvent event = ((LivingHurtEvent) livingEvent);

			Entity entity = event.getEntity();

			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = ((EntityPlayer) entity);

				if (EventUtils.isWearingFullArmorSet(player, metal))
				{
					float amount = event.getAmount();
					//Decrease the damage amount of 75% of the original damage in case the player is in complete darkness
					amount -= EventUtils.getDarknessLevel(player, 0.75F) * amount;
					event.setAmount(amount);
				}
			}
		}

	}

}
