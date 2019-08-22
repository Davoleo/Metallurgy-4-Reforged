/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PickaxeEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PickaxeEffectHandler {
	
	@SubscribeEvent
	public static void onBreakBlock(PlayerEvent.BreakSpeed event)
	{
		PlayerEntity pl = event.getPlayer();
		ItemStack mainHandStack = pl.getHeldItemMainhand();

		if(pl.isInWater() && mainHandStack.getItem() == ModMetals.DEEP_IRON.getTool(EnumTools.PICKAXE) && ToolEffectsConfig.deepIronPickaxeEffect) {
			event.setNewSpeed(event.getOriginalSpeed() * 3);
		}


//		set tools break speed based on light except for hoe and sword
		if(ToolEffectsConfig.shadowSteelToolSpeedEffect && mainHandStack.getItem() == ModMetals.SHADOW_STEEL.getTool(EnumTools.PICKAXE)) {
			float percentage = Utils.getLightArmorPercentage(pl,100F);
			float speed = event.getNewSpeed()  * percentage / 40F;
			event.setNewSpeed(event.getOriginalSpeed() + speed);
		}
	}
}
