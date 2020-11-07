/*==============================================================================
 = Class: SpawnHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
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

public class SpawnHandler {

	private static final TextComponentString GITHUB_REPO = new TextComponentString(Utils.localizeIgnoreFormat("util.github_repo_url"));

	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if (GeneralConfig.mobsThatCanHaveEquipment.length == 0)
			return;

		boolean isEntityValid = Arrays.stream(GeneralConfig.mobsThatCanHaveEquipment).anyMatch(entityId -> {
			EntityEntry entry = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entityId));
			return entry.getEntityClass() == event.getEntity().getClass();
		});

		Metal metal = EventUtils.getRandomMetalBasedOnDifficulty(event.getWorld());

		Entity entity = event.getEntity();


		if (!event.getWorld().isRemote && metal != null && isEntityValid)
		{

			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			{
				if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
					entity.setItemStackToSlot(slot, new ItemStack(metal.getArmorPiece(slot)));
			}


			if (metal.hasToolSet() && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHeldItemMainhand().isEmpty())
			{
				if (Math.random() < 0.25F)
				{
					entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(metal.getTool(EnumTools.AXE)));
				}
				else
				{
					entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(metal.getTool(EnumTools.SWORD)));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (!event.player.world.isRemote && GeneralConfig.warning)
		{
			GITHUB_REPO.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, GITHUB_REPO.getText())).setColor(TextFormatting.BLUE);

			event.player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.1")));

			event.player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.3")));
			event.player.sendMessage(GITHUB_REPO);

			event.player.sendMessage(new TextComponentString(Utils.localize("util.world_join_message.4")));
		}
	}

}
