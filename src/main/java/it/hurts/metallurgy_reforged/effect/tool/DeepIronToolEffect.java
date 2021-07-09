/*==============================================================================
 = Class: DeepIronToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;


public class DeepIronToolEffect extends BaseMetallurgyEffect {

	public DeepIronToolEffect()
	{
		super(ModMetals.DEEP_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@SubscribeEvent
	public void playerBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		if (!canBeApplied(event.getEntityPlayer()))
			return;

		if (event.getEntityPlayer().isInWater())
			event.setNewSpeed(event.getOriginalSpeed() * 3);
	}

}
