/*==============================================================================
 = Class: TriggerDispatcher
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.advancement;

import it.hurts.metallurgy_reforged.advancement.trigger.AdvancementGrantedTrigger;
import it.hurts.metallurgy_reforged.advancement.trigger.JackOfAllTradesTrigger;
import it.hurts.metallurgy_reforged.advancement.trigger.YinYangTrigger;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Objects;

import static it.hurts.metallurgy_reforged.advancement.ModAdvancements.Triggers.STRONG_OPPONENT;

public class TriggerDispatcher {

	@SubscribeEvent
	public static void dispatchEquipmentTriggers(LivingEquipmentChangeEvent event)
	{

		if (event.getSlot().getSlotType() != EntityEquipmentSlot.Type.ARMOR)
			return;

		if (event.getEntityLiving() instanceof EntityPlayerMP)
		{
			Metal[] wornMetals = new Metal[4];

			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			{
				if (slot.getSlotType() == EntityEquipmentSlot.Type.HAND)
					continue;

				ItemStack equip = slot != event.getSlot() ? event.getEntityLiving().getItemStackFromSlot(slot) : event.getTo();
				wornMetals[slot.getIndex()] = ItemUtils.getMetalFromItem(equip.getItem());
			}

			//Jack of all Trades
			boolean allDifferent = Arrays.stream(wornMetals)
					.filter(Objects::nonNull)
					.distinct()
					.count() == 4;
			if (allDifferent)
			{
				JackOfAllTradesTrigger trigger = ModAdvancements.Triggers.JACK_OF_ALL_TRADES;
				trigger.trigger((EntityPlayerMP) event.getEntityLiving(), new CommonCriterionInstances.AlwaysTrue(trigger.getId()));
			}

			//Yin Yang
			int yin = 0;
			int yang = 0;
			for (Metal metal : wornMetals)
			{
				if (metal == ModMetals.SANGUINITE)
					++yin;
				else if (metal == ModMetals.AMORDRINE)
					++yang;
			}

			if (yin == 2 && yang == 2)
			{
				YinYangTrigger trigger = ModAdvancements.Triggers.YIN_YANG;
				trigger.trigger((EntityPlayerMP) event.getEntityLiving(), new CommonCriterionInstances.AlwaysTrue(trigger.getId()));
			}

		}
	}

	@SubscribeEvent
	public static void dispatchKillingTriggers(LivingDeathEvent event)
	{
		if (event.getSource().getTrueSource() instanceof EntityPlayerMP)
		{
			EntityPlayerMP killer = (EntityPlayerMP) event.getSource().getTrueSource();

			ItemStack enemyChestplate = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if (ItemUtils.getMetalFromItem(enemyChestplate.getItem()) != null)
				STRONG_OPPONENT.trigger(killer, new CommonCriterionInstances.AlwaysTrue(STRONG_OPPONENT.getId()));
		}
	}

	@SubscribeEvent
	public static void dispatchAdvancementGrantedTriggers(AdvancementEvent event)
	{
		if (event.getEntityPlayer() instanceof EntityPlayerMP)
		{
			ModAdvancements.Triggers.ADVANCEMENT_GRANTED.trigger(
					(EntityPlayerMP) event.getEntityPlayer(),
					new AdvancementGrantedTrigger.Instance(event.getAdvancement().getId())
			);
		}
	}

}
