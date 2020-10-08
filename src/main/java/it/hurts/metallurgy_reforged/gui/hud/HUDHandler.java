/*
 * -------------------------------------------------------------------------------------------------------
 * Class: HUDHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.gui.hud;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUDHandler {

	@SubscribeEvent
	public static void renderOverlay(RenderGameOverlayEvent.Post event)
	{

		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
			return;

		if (ModMetals.KRIK == null)
			return;

		Minecraft minecraft = Minecraft.getMinecraft();
		if (minecraft.currentScreen instanceof GuiChat)
			return;

		RayTraceResult rayTrace = minecraft.objectMouseOver;
		if (rayTrace == null)
			return;

		EntityPlayer player = minecraft.player;

		if (rayTrace.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			IBlockState state = minecraft.world.getBlockState(rayTrace.getBlockPos());
			Block block = state.getBlock();

			if (block == ModBlocks.chamber)
			{
				SublimationChamberHUD.render(event, minecraft, rayTrace.getBlockPos());
			}
		}

		if (EventUtils.isEntityWearingArmor(player, ModMetals.KRIK))
		{
			KrikArmorHUD.render(event, minecraft);
		}

	}

}
