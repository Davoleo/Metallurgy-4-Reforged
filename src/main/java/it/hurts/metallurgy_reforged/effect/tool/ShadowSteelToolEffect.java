/*==============================================================================
 = Class: ShadowSteelToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nullable;

public class ShadowSteelToolEffect extends BaseMetallurgyEffect {

	public ShadowSteelToolEffect()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.shadowSteelToolSpeedEffect && super.isEnabled();
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
		return null;
	}

	@Override
	public void playerBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer player = event.getEntityPlayer();

		if (isShadowSteelTool(player.getHeldItemMainhand().getItem()))
		{
			float percentage = Utils.getLightArmorPercentage(player, 100F);
			float speed = event.getNewSpeed() * percentage / 40F;
			event.setNewSpeed(event.getOriginalSpeed() + speed);
		}
	}

	private boolean isShadowSteelTool(Item heldItem)
	{
		return heldItem == metal.getTool(EnumTools.AXE) ||
				heldItem == metal.getTool(EnumTools.PICKAXE) ||
				heldItem == metal.getTool(EnumTools.SHOVEL);
	}

}
