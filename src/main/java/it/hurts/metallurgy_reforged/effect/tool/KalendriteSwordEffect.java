/*==============================================================================
 = Class: KalendriteSwordEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.SwordHitChanceEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class KalendriteSwordEffect extends SwordHitChanceEffect {

	public KalendriteSwordEffect()
	{
		super(ModMetals.KALENDRITE, 50, () -> new PotionEffect(MobEffects.REGENERATION, 100, 1));
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.kalendriteSwordEffect && super.isEnabled();
	}

	@Override
	public void onPlayerAttack(EntityPlayer attacker, Entity target)
	{
		if (attacker.getHeldItemMainhand().getItem() == metal.getTool(EnumTools.SWORD))
		{
			if (random.nextInt(100) < chance)
			{
				attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
			}
		}
	}

}
