/*==============================================================================
 = Class: QuicksilverToolEffect
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
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class QuicksilverToolEffect extends BaseMetallurgyEffect {

	public QuicksilverToolEffect()
	{
		super(ModMetals.QUICKSILVER);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@SubscribeEvent
	public void normalizeBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		if (!canBeApplied(event.getEntityPlayer()))
			return;

		if (EventUtils.canHarvest(event.getEntityPlayer().getHeldItemMainhand(), event.getState()))
		{
			World world = event.getEntityPlayer().world;

			float newSpeed = (event.getOriginalSpeed() * event.getState().getBlockHardness(world, event.getPos())) / 2F;
			event.setNewSpeed(newSpeed);
			spawnParticle(world, event.getPos(), 1F, true, 5, 0, 0, 0);
		}
	}

}
