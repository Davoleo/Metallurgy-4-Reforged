/*==============================================================================
 = Class: PlatinumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.ArmorPotionEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PlatinumArmorEffect extends ArmorPotionEffect {

	public PlatinumArmorEffect()
	{
		super(ModMetals.PLATINUM, null, 0);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.platinumArmorEffect && super.isEnabled();
	}

	@Override
	public void livingEvent(LivingEvent event)
	{
		//Overwriting the normal potion effect event handling
		//This effects needs some specific controls to work correctly
	}

	@Override
	public void onPlayerTick(EntityPlayer player)
	{
		if (EventUtils.isEntityWearingArmor(player, metal)) {
			if (player.world.getTotalWorldTime() % 40 == 0)
			{
				PotionEffect effect = new PotionEffect(MobEffects.NIGHT_VISION, 280, 0, false, false);
				player.addPotionEffect(effect);
				player.addTag("platinum_effect");
			}
		}
		else if (player.getTags().contains("platinum_effect"))
		{
			player.removeTag("platinum_effect");
			if (player.isPotionActive(MobEffects.NIGHT_VISION))
				player.removePotionEffect(MobEffects.NIGHT_VISION);
		}
	}

}
