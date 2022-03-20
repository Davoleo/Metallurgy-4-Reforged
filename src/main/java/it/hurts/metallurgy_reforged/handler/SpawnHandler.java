/*==============================================================================
 = Class: SpawnHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SpawnHandler {

	private static final TextComponentString GITHUB_REPO = new TextComponentString(Utils.localizeIgnoreFormat("util.github_repo_url"));

	private static Set<Class<? extends Entity>> equipReadyMobClasses = null;

	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (GeneralConfig.mobsThatCanHaveEquipment.length == 0)
			return;

		if (equipReadyMobClasses == null)
		{
			equipReadyMobClasses = Arrays.stream(GeneralConfig.mobsThatCanHaveEquipment).map(mobId -> {
				EntityEntry entry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobId));
				return entry == null ? null : entry.getEntityClass();
			}).collect(Collectors.toSet());
		}

		if (!(event.getEntity() instanceof EntityLivingBase))
			return;
		EntityLivingBase entity = (EntityLivingBase) event.getEntity();


		//To avoid running all of this 2 times and possibly getting desynced enemy armor
		if (event.getWorld().isRemote)
			return;

		//For some reason an implementation of Entity in Flansmod returns null instead of empty lists | that's why I'm proofchecking against null
		//noinspection ConstantConditions
		if (entity.getArmorInventoryList() == null || entity.getHeldEquipment() == null)
			return;

		boolean isEntityValid = equipReadyMobClasses.stream().anyMatch(equipEntityCl -> entity.getClass() == equipEntityCl);

		if (!isEntityValid)
			return;

		Metal metal = EventUtils.getRandomMetalBasedOnDifficulty(event.getWorld());

		if (metal != null)
		{
			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			{
				if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && entity.getItemStackFromSlot(slot).isEmpty())
					entity.setItemStackToSlot(slot, new ItemStack(metal.getArmorPiece(slot)));
			}

			if (metal.hasToolSet() && entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty())
			{
				if (Math.random() < 0.25F)
					entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(metal.getTool(EnumTools.AXE)));
				else
					entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(metal.getTool(EnumTools.SWORD)));
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (!event.player.world.isRemote && GeneralConfig.warning)
		{
			GITHUB_REPO.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, GITHUB_REPO.getText())).setColor(TextFormatting.BLUE);

			event.player.sendMessage(new TextComponentString(Utils.localizeEscapingCustomSequences("util.world_join_message.1")));

			event.player.sendMessage(new TextComponentString(Utils.localizeEscapingCustomSequences("util.world_join_message.3")));
			event.player.sendMessage(GITHUB_REPO);

			event.player.sendMessage(new TextComponentString(Utils.localizeEscapingCustomSequences("util.world_join_message.4")));
		}
	}

}
