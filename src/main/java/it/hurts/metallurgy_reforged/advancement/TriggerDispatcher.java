/*==============================================================================
 = Class: TriggerDispatcher
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;
import java.util.stream.StreamSupport;

public class TriggerDispatcher {

	@SubscribeEvent
	public static void dispatchEquipmentTriggers(LivingEquipmentChangeEvent event)
	{

		if (event.getSlot().getSlotType() != EntityEquipmentSlot.Type.ARMOR)
			return;

		if (event.getEntityLiving() instanceof EntityPlayerMP)
		{
			boolean allDifferent = StreamSupport.stream(event.getEntityLiving().getArmorInventoryList().spliterator(), false)
					.map(piece -> {
						if (piece == event.getFrom())
							return ItemUtils.getMetalFromItem(event.getTo().getItem());
						return ItemUtils.getMetalFromItem(piece.getItem());
					})
					.filter(Objects::nonNull)
					.distinct()
					.count() == 4;

			if (allDifferent)
			{
				JackOfAllTradesTrigger trigger = ModAdvancements.Triggers.JACK_OF_ALL_TRADES;
				trigger.trigger((EntityPlayerMP) event.getEntityLiving(), new CommonCriterionInstances.AlwaysTrue(trigger.getId()));
			}
		}
	}

}
