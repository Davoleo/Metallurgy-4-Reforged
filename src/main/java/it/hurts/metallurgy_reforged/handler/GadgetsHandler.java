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
import it.hurts.metallurgy_reforged.item.gadget.shield.ItemBuckler;
import it.hurts.metallurgy_reforged.item.gadget.ItemOreDetector;
import it.hurts.metallurgy_reforged.item.gadget.shield.ItemInvisibilityShield;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GadgetsHandler {

	private static MovementInput inputCheck;

	public static long ticks = 0;

	private static double prevFactor = 0;
	public static double prevFactorToUse = 0;

	/**
	 * A global client ticking method that updates a long variable
	 * <br><br>
	 * Uses as of 21/07/2020:<br>
	 * - in {@link ItemOreDetector}
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			ticks++;

			//METAL DETECTOR SECTION -----------------------------
			double radiant = Math.toRadians(GadgetsHandler.ticks + Minecraft.getMinecraft().getRenderPartialTicks());
			double factor = ((Math.sin(radiant * 6) + 1.0F) * 0.5D);
			double factorToUse = factor - prevFactor >= 0 ? factor : 1 - factor;

			if (prevFactorToUse - factorToUse > 0.3)
				ItemOreDetector.indexColor++;

			prevFactor = factor;
			prevFactorToUse = factorToUse;
			//--------------------------------------------------------

			if (ticks >= Long.MAX_VALUE)
			{
				ticks = 0;
			}
		}
	}

	/**
	 * Handles fast walking on Metallurgy road blocks
	 *
	 * @param event the player tick event we're handling
	 */
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

	/**
	 * Cancels player render when using a Lemurite shield to become invisible
	 *
	 * @param event player render event that we're canceling
	 *
	 * @see ItemInvisibilityShield
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void invisibilityEffect(RenderPlayerEvent.Pre event)
	{
		if (event.getEntityPlayer().getActiveItemStack().getItem().equals(ModItems.invisibilityShield))
			event.setCanceled(true);
	}

	/**
	 * Handles Mob AI Disabling when using a Lemurite shield to become invisible
	 *
	 * @param event living entities update event we're listening to
	 *
	 * @see ItemInvisibilityShield
	 */
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

	/**
	 * Handles in-world rendering of redstone-related blocks when you're wearing an Etherium Monocle
	 *
	 * @param event The world render event we're listening to
	 *
	 * @see it.hurts.metallurgy_reforged.item.gadget.ItemEtheriumMonocle
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	@SuppressWarnings("deprecation")
	public static void renderRedstoneComponentsThroughBlocks(RenderWorldLastEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		World world = Minecraft.getMinecraft().world;

		if (player == null || !EventUtils.isPlayerWearingSpecificArmorPiece(player, EntityEquipmentSlot.HEAD, ModItems.etheriumMonocle))
			return;

		BlockPos playerPos = new BlockPos(player.getPositionEyes(event.getPartialTicks()));

		final int RADIUS = 8;

		Iterable<BlockPos> posList = BlockPos.getAllInBox(playerPos.add(-RADIUS, -RADIUS, -RADIUS), playerPos.add(RADIUS, RADIUS, RADIUS));

		List<BlockPos> sortedPositions = StreamSupport.stream(posList.spliterator(), false).sorted((o1, o2) ->
		{
			double d1 = playerPos.distanceSq(o1);
			double d2 = playerPos.distanceSq(o2);
			if(d1 == d2)
				return 0;
			return d1 > d2 ? -1 : 1;
		}).collect(Collectors.toList());

		for (BlockPos blockPos : sortedPositions)
		{
			IBlockState state = world.getBlockState(blockPos);
			if (state.getBlock().getCreativeTab() == CreativeTabs.REDSTONE
					|| state.getBlock().getItem(world, blockPos, state).getItem().getCreativeTab() == CreativeTabs.REDSTONE)
			{
				GlStateManager.pushMatrix();

				Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) event.getPartialTicks();
				double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) event.getPartialTicks();
				double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) event.getPartialTicks();

				double offsetX = blockPos.getX() - d0;
				double offsetY = blockPos.getY() - d1;
				double offsetZ = blockPos.getZ() - d2;

				BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();

				GlStateManager.disableDepth();

				GlStateManager.translate(offsetX, offsetY, offsetZ);
				GlStateManager.rotate(-90F, 0F, 1F, 0F);
				state = state.getActualState(world, blockPos);
				blockRendererDispatcher.getBlockModelRenderer().renderModelBrightness(blockRendererDispatcher.getModelForState(state), state, 1F, true);

				GlStateManager.enableDepth();
				GlStateManager.popMatrix();
			}
		}
	}

	/**
	 * Denies mob spawn if the entity spawns in range of a phosphorus lamp
	 *
	 * @param event The entity spawn check event we're listening to
	 *
	 * @see it.hurts.metallurgy_reforged.block.gadget.BlockPhosphorusLamp
	 */
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

	/**
	 * Handles ore outline rendering when using a metal detector to detect nearby ores
	 *
	 * @param event the world render event we're listening to
	 *
	 * @see ItemOreDetector
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void metalDetectorOresRender(RenderWorldLastEvent event)
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

	/**
	 * Makes the player walk at the normal speed when holding a Buckler
	 * the game multiplies it by 0.2, and we multiply it by 5 to neutralize the slowing effect
	 * @param event fired on any movement input of the player
	 */
	@SubscribeEvent
	public static void onPlayerInput(InputUpdateEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();

		if (player.getActiveItemStack().getItem() instanceof ItemBuckler)
		{
			MovementInput input = event.getMovementInput();
			input.moveForward *= 5;
			input.moveStrafe *= 5;
		}
	}

	/**
	 * Allows the player to attack entities and punch air when holding a buckler
	 * @param event Any input from the user on either the keyboard or the mouse
	 * @see Minecraft#clickMouse()
	 */
	@SuppressWarnings("JavadocReference")
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onDeviceInput(InputEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();

		if (minecraft.player == null)
			return;

		if (minecraft.player.getActiveItemStack().getItem() instanceof ItemBuckler)
		{
			while (minecraft.gameSettings.keyBindAttack.isPressed())
			{
				//From Minecraft#clickMouse
				switch (minecraft.objectMouseOver.typeOfHit)
				{
					case ENTITY:
						minecraft.playerController.attackEntity(minecraft.player, minecraft.objectMouseOver.entityHit);
						break;
					case MISS:
						minecraft.player.resetCooldown();
						ForgeHooks.onEmptyLeftClick(minecraft.player);
				}

				minecraft.player.swingArm(EnumHand.MAIN_HAND);
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
