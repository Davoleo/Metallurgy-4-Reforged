/*
 * -------------------------------------------------------------------------------------------------------
 * Class: DeepIronPickaxeEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nullable;

public class DeepIronPickaxeEffect extends BaseMetallurgyEffect {

	public DeepIronPickaxeEffect()
	{
		super(ModMetals.DEEP_IRON);
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.deepIronPickaxeEffect && super.isEnabled();
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
		return EnumTools.PICKAXE;
	}

	@Override
	public void playerBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer player = event.getEntityPlayer();

		if (player.isInWater() && player.getHeldItemMainhand().getItem() == ModMetals.DEEP_IRON.getTool(EnumTools.PICKAXE))
		{
			event.setNewSpeed(event.getOriginalSpeed() * 3);
		}
	}

}
