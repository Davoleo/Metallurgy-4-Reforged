/*==============================================================================
 = Class: EffectHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EffectHandler {

	@SubscribeEvent
	public static void onAttack(AttackEntityEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.onPlayerAttack(player, event.getTarget());
		}
	}

	@SubscribeEvent
	public static void livingEvent(LivingEvent event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.livingEvent(event);
		}
	}

	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event)
	{
		Entity attacker = event.getSource().getImmediateSource();
		if (attacker instanceof EntityPlayer)
		{
			EntityPlayer killer = (EntityPlayer) attacker;
			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			{
				effect.onPlayerKill(killer, event.getEntityLiving());
			}
		}
	}

	@SubscribeEvent
	public static void onEntityKillDrop(LivingDropsEvent event)
	{
		DamageSource source = event.getSource();
		Entity entity = source.getTrueSource();

		//Makes sure the killer is a player and the killed entity is not a player
		if (entity instanceof EntityPlayer && !(event.getEntity() instanceof EntityPlayer))
		{
			EntityPlayer killer = (EntityPlayer) entity;
			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			{
				effect.onEntityKillDrop(event.getDrops(), killer);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START)
		{
			EntityPlayer player = event.player;

			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			{
				effect.onPlayerTick(player);
			}

		}
	}


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onEntityRender(RenderLivingEvent.Pre<EntityLivingBase> event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.onEntitiesRender(event.getEntity(), event.getRenderer(), event.getPartialRenderTick(), event.getX(), event.getY(), event.getZ());
		}
	}

	@SubscribeEvent
	public static void onEntityEnteringChunk(EntityEvent.EnteringChunk event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.onEntityEnteringChunk(event.getEntity());
		}
	}

	@SubscribeEvent
	public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{
		if (event.getHarvester() != null)
		{
			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			{

				effect.onBlockHarvested(event);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.playerBreakSpeed(event);
		}
	}


	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
		{
			effect.onPlayerInteract(event);
		}
	}

	@SubscribeEvent
	public static void onPlayerCollision(GetCollisionBoxesEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			{
				effect.onPlayerCollision(event);
			}
		}
	}

}
