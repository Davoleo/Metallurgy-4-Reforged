/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GadgetsHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.block.gadget.PhosphorusLampSavedData;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GadgetsHandler {

	private static MovementInput inputCheck;

	public static long ticks = 0;

	private static double prevFactor = 0;
	public static double prevFactorToUse = 0;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			ticks++;

			double radiant = Math.toRadians(GadgetsHandler.ticks + Minecraft.getMinecraft().getRenderPartialTicks());
			double factor = ((Math.sin(radiant * 6) + 1.0F) * 0.5D);
			double factorToUse = factor - prevFactor >= 0 ? factor : 1 - factor;

			if (prevFactorToUse - factorToUse > 0.3)
				ItemOreDetector.indexColor++;

			prevFactor = factor;
			prevFactorToUse = factorToUse;

			if (ticks >= Long.MAX_VALUE)
			{
				ticks = 0;
			}
		}
	}

	//Road Speed Effect
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void roadSpeed(PlayerTickEvent event)
	{
		if ((event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockRoad
				|| event.player.world.getBlockState(new BlockPos(event.player.posX, event.player.posY - 0.5D, event.player.posZ)).getBlock() == ModBlocks.blockStripedRoad)
				&& event.phase == TickEvent.Phase.START && event.side.isClient() && event.player.onGround)
		{
			if (inputCheck == null)
				inputCheck = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);

			inputCheck.updatePlayerMoveState();

			if ((inputCheck.moveForward != 0 || inputCheck.moveStrafe != 0))
			{
				event.player.motionX *= GeneralConfig.roadSpeed;
				event.player.motionZ *= GeneralConfig.roadSpeed;
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void invisibilityEffect(RenderPlayerEvent.Pre event)
	{
		if (event.getEntityPlayer().getActiveItemStack().getItem().equals(ModItems.invisibilityShield))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void disableAI(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityLiving)
		{
			EntityLiving entity = ((EntityLiving) event.getEntityLiving());
			if (entity.getAttackTarget() instanceof EntityPlayer && entity.getAttackTarget().getActiveItemStack().getItem().equals(ModItems.invisibilityShield))
				entity.setAttackTarget(null);
		}
	}

	@SubscribeEvent
	public static void denySpawn(LivingSpawnEvent.CheckSpawn event)
	{
		PhosphorusLampSavedData dataManager = PhosphorusLampSavedData.getInstance(event.getWorld());

		if (dataManager.isEntityInRange(event.getEntity().getPosition(), event.getWorld()))
		{
			//Debug println
			//System.out.println(event.getEntity().getName() + " SPAWN DENIED!");
			event.setResult(Event.Result.DENY);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderWorldLastEvent(RenderWorldLastEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;

		if (player == null)
			return;

		ItemStack detector = ItemStack.EMPTY;
		for (EnumHand hand : EnumHand.values())
		{
			if (player.getHeldItem(hand).getItem() == ModItems.oreDetector)
				detector = player.getHeldItem(hand);
		}

		if (detector.isEmpty())
			return;

		List<Metal> metalList = ItemOreDetector.getDetectorMetals(detector);

		if (metalList.isEmpty())
			return;

		if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown())
		{
			World world = Minecraft.getMinecraft().world;
			BlockPos playerPos = player.getPosition();
			final int RADIUS = 6;

			Iterable<BlockPos> posList = BlockPos.getAllInBox(playerPos.add(-RADIUS, -RADIUS, -RADIUS), playerPos.add(RADIUS, RADIUS, RADIUS));

			for (BlockPos blockPos : posList)
			{
				IBlockState state = world.getBlockState(blockPos);
				Metal metal = oreMatchesMetal(state, metalList);

				if (metal != null)
				{
					//System.out.println(blockPos);

					float[] rgb = metal.getStats().getColorRGBValues();
					double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) event.getPartialTicks();
					double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) event.getPartialTicks();
					double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) event.getPartialTicks();

					double offsetX = d0 - blockPos.getX();
					double offsetY = d1 - blockPos.getY();
					double offsetZ = d2 - blockPos.getZ();

					GlStateManager.pushMatrix();
					GlStateManager.translate(-offsetX, -offsetY, -offsetZ);
					GlStateManager.disableDepth();

					GlStateManager.disableTexture2D();
					RenderHelper.disableStandardItemLighting();

					RenderGlobal.drawSelectionBoundingBox(Block.FULL_BLOCK_AABB, rgb[0], rgb[1], rgb[2], 1);

					RenderHelper.enableStandardItemLighting();
					GlStateManager.enableTexture2D();

					GlStateManager.enableDepth();
					GlStateManager.translate(offsetX, offsetY, offsetZ);
					GlStateManager.popMatrix();
				}
			}
		}
	}

	private static Metal oreMatchesMetal(IBlockState state, List<Metal> metals)
	{

		for (Metal metal : metals)
		{
			if (state.getBlock() == metal.getOre())
			{
				return metal;
			}
		}

		return null;
	}

}
