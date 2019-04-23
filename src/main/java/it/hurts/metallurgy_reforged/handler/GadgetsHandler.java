/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GadgetsHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GadgetsHandler {
	
	private static MovementInput inputCheck;
	
//	Road Speed Effect
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void roadSpeed(PlayerTickEvent event) {
		if ((event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockRoad
				|| event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockStripedRoad)
				&& event.phase == TickEvent.Phase.START && event.side.isClient() && event.player.onGround)
		{
			if(inputCheck == null)
				inputCheck = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);

			inputCheck.updatePlayerMoveState();

			if((inputCheck.moveForward != 0 || inputCheck.moveStrafe != 0))
			{
				event.player.motionX *= GeneralConfig.roadSpeed;
				event.player.motionZ *= GeneralConfig.roadSpeed;
			}
		}
	}

	@SubscribeEvent
	public static void invisibilityEffect(PlayerTickEvent event)
	{
		for (EnumHand hand : EnumHand.values())
		{
			EntityPlayer player = event.player;

			if (player.getHeldItem(hand).getItem() == ModItems.invisibilityShield)
			{
//
//				player.setInvisible(true);
//
//				if (player.world.isRemote)
//					for (int i = 0; i < 1000; i++)
//						player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX + Math.random(), player.posY + Math.random(), player.posX + Math.random());
			}
		}
	}
}
