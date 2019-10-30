/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AxeEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AxeEffectHandler {

	@SubscribeEvent
	public static void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
		Item heldItem = event.getHarvester().getHeldItemMainhand().getItem();
		World world = event.getWorld();
		BlockPos pos = event.getPos();

		if (heldItem.equals(ModMetals.IGNATIUS.getTool(EnumTools.AXE)))
		{
			ShovelEffectHandler.dropSmeltedItems(event, world, pos);
		}
	}

}
