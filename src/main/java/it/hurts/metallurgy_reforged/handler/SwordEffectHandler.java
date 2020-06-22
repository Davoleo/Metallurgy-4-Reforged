/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SwordEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SwordEffectHandler {

	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		Item heldSword = player.getHeldItemMainhand().getItem();

	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event)
	{
		// TODO: 10/06/2020 Filter the EntityLivingBase instances
		Entity attacker = event.getSource().getImmediateSource();
		if (attacker instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) attacker;


		}
	}

	//Midasium Effect
	@SubscribeEvent
	public static void duplicationSwordEffect(LivingDropsEvent event)
	{

		DamageSource source = event.getSource();
		Entity entity = source.getTrueSource();

		if (entity instanceof EntityPlayer && !(event.getEntity() instanceof EntityPlayer))
		{
			EntityPlayer pl = (EntityPlayer) entity;


		}
	}


	@SubscribeEvent
	public static void entityHurtEvent(LivingHurtEvent event)
	{

	}

	@SubscribeEvent
	public static void onSwordTick(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			EntityPlayer player = event.player;
		}
	}

}
