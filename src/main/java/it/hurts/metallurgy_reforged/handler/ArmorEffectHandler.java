/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ArmorEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.container.slot.ArmorCustomSlot;
import it.hurts.metallurgy_reforged.entity.ai.AIFindPlayerWithoutHelmet;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumAction;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class ArmorEffectHandler {

	//	The speed sword modifier UUID
	public static final UUID SHADOW_STEEL_ARMOR_MODIFIER_UUID = UUID.fromString("9bfd3581-6559-468f-a5a5-66c46ff7b70c");

	public static List<UUID> glowingEntities = new ArrayList<>();

	@SubscribeEvent
	public static void onArmorTick(PlayerTickEvent event)
	{
		EntityPlayer pl = event.player; //The Player
		World world = pl.world;
		//		Astral Silver Armor (Jump Boost)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.ASTRAL_SILVER.getArmorSet()) && ArmorEffectsConfig.astralSilverArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false));

		//		Celenegil Armor (Resistence)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.CELENEGIL.getArmorSet()) && ArmorEffectsConfig.celenegilArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 3, false, false));


		//		Deep Iron Armor (Swimming Speed when the player is in water and on ground)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.DEEP_IRON.getArmorSet())
				&& event.player.isInWater() && ArmorEffectsConfig.deepIronArmorEffect)
		{

			//			Slot index of Armor : 5 - 6 - 7 - 8
			for (int i = 5; i < 9; i++)
			{
				if (!(pl.inventoryContainer.inventorySlots.get(i) instanceof ArmorCustomSlot) && !pl.isCreative())
				{
					//					Inseriamo nello slot dell'inventario in posizione i un custom slot
					pl.inventoryContainer.inventorySlots.set(i, new ArmorCustomSlot(pl, i - 5, true));
				}

			}
			//			Add effect to Player
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));

			pl.addTag("deep_iron_effect");

			//		 	Checks if the player is tourching ground
			if (pl.onGround)
			{
				pl.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(8);
			}
			else
			{
				//				Stop player motion
				pl.motionX *= 0.5D;
				pl.motionZ *= 0.5D;
			}

			//			The player can no longer swim upwards
			pl.motionY = -0.6D;

			//			When the player is in the water he can step one block height like a horse
			if (pl.stepHeight != 1.0F)
				pl.stepHeight = 1.0F;
			//		turns the stepHeight to normal if the player isn't wearing the deep iron armor or if he is not in water
		}
		else
		{
			if (pl.getTags().contains("deep_iron_effect"))
			{
				pl.removeTag("deep_iron_effect");
				if (pl.stepHeight != 0.6F)
					pl.stepHeight = 0.6F;

				if (pl.inventoryContainer.inventorySlots.get(5) instanceof ArmorCustomSlot)
				{
					//		    		Insert in c the container "vanilla"
					ContainerPlayer c = new ContainerPlayer(pl.inventory, !pl.world.isRemote, pl);
					List<Slot> slots = c.inventorySlots;
					for (int i = 5; i < 9; i++)
					{
						pl.inventoryContainer.inventorySlots.set(i, slots.get(i));
					}
				}

				if (pl.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= (11 * 20))
					pl.removePotionEffect(MobEffects.NIGHT_VISION);
				if (pl.getActivePotionEffect(MobEffects.WATER_BREATHING).getDuration() <= (11 * 20))
					pl.removePotionEffect(MobEffects.WATER_BREATHING);
				pl.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(EntityLivingBase.SWIM_SPEED.getDefaultValue());
			}


		}

		//		Vulcanite Armor (Fire Immunity) //Removes Fire Render
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.VULCANITE.getArmorSet()) && event.player.isBurning() && ArmorEffectsConfig.vulcaniteArmorEffect)
			event.player.extinguish();

		//		Angmallen Armor (Luck I for Vampirism)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.ANGMALLEN.getArmorSet()) && ArmorEffectsConfig.angmallenArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 80, 0, false, false));


		//		Kalendrite Armor (Strenght I)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.KALENDRITE.getArmorSet()) && ArmorEffectsConfig.kaledriteArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 0, false, false));


		//		Amordrine Armor (Strenght II)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.AMORDRINE.getArmorSet()) && ArmorEffectsConfig.amordrineArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 1, false, false));


		//		Adamantine Armor (Saturation)
		if (ArmorEffectsConfig.adamantineArmorEffect && !world.isRemote && EventUtils.isPlayerWearingArmor(event.player, ModMetals.ADAMANTINE.getArmorSet()))
		{
			FoodStats foodStat = pl.getFoodStats();
			int amount = 2;
			//quantity experience to remove
			float removeTot = (float) amount / (float) pl.xpBarCap();
			//check if the player needs food ,if he has enough experience and if the tick is a multiple of 20 (which means that the effect will be applied every second)
			if (pl instanceof EntityPlayerMP && pl.canEat(false) && (pl.experience >= removeTot || pl.experienceLevel > 0) && pl.ticksExisted % 20 == 0)
			{
				EntityPlayerMP mp = (EntityPlayerMP) pl;
				Random rand = new Random();
				mp.experience -= removeTot;

				if (mp.experienceTotal - amount >= 0)
					mp.experienceTotal -= amount;

				if (mp.experience < 0.0F)
				{
					mp.experience = 1F - mp.experience;
					mp.addExperienceLevel(-1);
				}

				//add Food Level
				foodStat.addStats(1, 0.5F);
				//update experience count on the client side
				mp.connection.sendPacket(new SPacketSetExperience(mp.experience, mp.experienceTotal, mp.experienceLevel));
				//play generic eat sound
				mp.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, mp.posX, mp.posY + mp.getEyeHeight(), mp.posZ, 0.3F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F));

			}
		}

		//		Krik effect
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.KRIK.getArmorSet()) && ArmorEffectsConfig.krikArmorEffect)
		{
			int filledSlots = 0;
			int stackCount = 0;

			for (int i = 5; i < 9; i++)
			{
				if (!(pl.inventoryContainer.inventorySlots.get(i) instanceof ArmorCustomSlot) && !pl.isCreative())
				{
					pl.inventoryContainer.inventorySlots.set(i, new ArmorCustomSlot(pl, i - 5, true));
				}
			}

			for (int i = 9; i < 36; i++)
			{
				KrikEffectHandler k = new KrikEffectHandler(event.player, i);

				if (k.getHasStack())
				{
					filledSlots++;

					if (stackCount < 28)
						stackCount += k.getStack().getCount();
				}
			}

			//			We need to save the Y of player and check with the couter and Y + 1
			if (stackCount >= 27)
			{

				if (event.player.lastTickPosY < 255 - (filledSlots * 10))
					event.player.motionY = 0.1;
				else if (!event.player.onGround && event.player.lastTickPosY >= 255 - (filledSlots * 10) - 1 && event.player.lastTickPosY < 255 - (filledSlots * 10) + 1)
					event.player.motionY = 0;
				if (pl.onGround)
				{
					if (pl.inventoryContainer.inventorySlots.get(5) instanceof ArmorCustomSlot)
					{
						//		    		Insert in c the container "vanilla"
						ContainerPlayer c = new ContainerPlayer(pl.inventory, !pl.world.isRemote, pl);
						List<Slot> slots = c.inventorySlots;
						for (int i = 5; i < 9; i++)
						{
							pl.inventoryContainer.inventorySlots.set(i, slots.get(i));
						}
					}
				}
			}
		}

		//		Platinum Armor (Night Vision, Needed Vanishing Curse)
		if (EventUtils.isPlayerWearingSpecificArmorPiece(event.player, 3, ModMetals.PLATINUM.getArmor(EntityEquipmentSlot.HEAD)) && ArmorEffectsConfig.platinumArmorEffect)
		{
			event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
			event.player.addTag("platinum_effect");
		}
		else if (event.player.getTags().contains("platinum_effect"))
		{
			event.player.removeTag("platinum_effect");
			if (event.player.isPotionActive(MobEffects.NIGHT_VISION) && event.player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= (11 * 20))
			{
				event.player.removePotionEffect(MobEffects.NIGHT_VISION);
			}
		}

		//		Carmot Armor (Haste I)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.CARMOT.getArmorSet()) && ArmorEffectsConfig.carmotArmorEffect)
			event.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 60, 0, false, false));


		//		Prometheum Armor (No potion, need to implement a new Effect)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.PROMETHEUM.getArmorSet()) && ArmorEffectsConfig.prometheumArmorEffect)
			event.player.removePotionEffect(MobEffects.POISON);

		//		Shadow Iron Armor (No Blindness)
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.SHADOW_IRON.getArmorSet()))
			event.player.removePotionEffect(MobEffects.BLINDNESS);

		//		removes the blindness effect when wearing shadow steel armor
		if (EventUtils.isPlayerWearingArmor(event.player, ModMetals.SHADOW_STEEL.getArmorSet()) && pl.isPotionActive(MobEffects.BLINDNESS))
			pl.removeActivePotionEffect(MobEffects.BLINDNESS);

	}


	//	Increase the speed of item action [ Aggiungere la possibilità di scelta della velocità della quicksilver ]
	@SubscribeEvent
	public static void increaseVelocity(LivingEntityUseItemEvent.Start ev)
	{
		if (ev.getEntityLiving() instanceof EntityPlayer && EventUtils.isPlayerWearingArmor((EntityPlayer) ev.getEntityLiving(), ModMetals.QUICKSILVER.getArmorSet()) && ArmorEffectsConfig.quicksilverArmorEffect)
		{
			if (!ev.getItem().getItem().getRegistryName().equals(new ResourceLocation("tconstruct:crossbow")))
			{
				if (ev.getItem().getItem().getItemUseAction(ev.getItem()) == EnumAction.BOW)
					ev.setDuration(ev.getDuration() - 6);
				else
					ev.setDuration(Math.round(ev.getDuration() / 2F));
			}
		}
	}

	//	Mithril Armor
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void glowingArmorEffect(RenderLivingEvent.Pre<EntityLivingBase> event)
	{
		EntityPlayer pl = Minecraft.getMinecraft().player;
		EntityLivingBase entity = event.getEntity();

		//Check if player exists and the Rendered Entity isn't the player himself
		if (pl != null && !entity.equals(pl))

			//checks if the player wears The Mithril Armor, the rendered entity is not glowing and it's within 30 blocks from the player, the effect is not disabled in the config
			if (EventUtils.isPlayerWearingArmor(pl, ModMetals.MITHRIL.getArmorSet())
					&& entity.getDistance(Minecraft.getMinecraft().player) < 30D
					&& !entity.isGlowing()
					&& ArmorEffectsConfig.mithrilArmorEffect)
			{
				entity.setGlowing(true);
				glowingEntities.add(entity.getUniqueID());
			}
			//Removes the effect only if metallurgy adds it
			else if (glowingEntities.contains(entity.getUniqueID()))
			{
				entity.setGlowing(false);
				glowingEntities.remove(entity.getUniqueID());
			}

	}

	//replaces the enderman's AI Eximite Helmet
	@SubscribeEvent
	public static void constructEntity(EntityEvent.EnteringChunk event)
	{

		//check if spawned entity is an enderman
		if (event.getEntity() instanceof EntityEnderman && ArmorEffectsConfig.eximiteArmorEffect)
		{

			EntityEnderman end = (EntityEnderman) event.getEntity();
			EntityAIBase aifindPlayer = null;
			int priority = 0;
			Iterator<EntityAITaskEntry> entries = end.targetTasks.taskEntries.iterator();
			while (entries.hasNext())
			{
				EntityAITaskEntry entry = entries.next();
				if (entry.action instanceof EntityAINearestAttackableTarget)
					//checks if the AI Class is the AIFindPlayer Class(The Class Used to check if player is watching an enderman)
					if (entry.action.getClass().getName().contains("EntityEnderman$AIFindPlayer"))
					{
						aifindPlayer = entry.action;
						priority = entry.priority;
					}

			}
			//if the AI class isn't null it will replace the original AI with the AIFindPlayerWithoutHelmet( a new custom AI similar to the original one)
			if (aifindPlayer != null)
			{
				end.targetTasks.removeTask(aifindPlayer);
				end.targetTasks.addTask(priority, new AIFindPlayerWithoutHelmet(aifindPlayer));
			}
		}
	}

	//		FireImmunity
	@SubscribeEvent
	public static void cancelFireDamage(LivingAttackEvent event)
	{
		if (event.getEntity() instanceof EntityPlayer && event.getSource().isFireDamage() && EventUtils.isPlayerWearingArmor((EntityPlayer) event.getEntity(), ModMetals.VULCANITE.getArmorSet()))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public static void cancelPlayerFallDamage(LivingFallEvent event)
	{

		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();

			if (EventUtils.isPlayerWearingArmor(player, ModMetals.KRIK.getArmorSet()))
				event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void reduceKnockback(LivingKnockBackEvent e)
	{
		if (e.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) e.getEntity();

			int osmiumMultiplier = EventUtils.getArmorPiecesCount(player, ModMetals.OSMIUM.getArmorSet());

			int lutetiumMultiplier = EventUtils.getArmorPiecesCount(player, ModMetals.LUTETIUM.getArmorSet());

			float strenght = e.getOriginalStrength();

			float multiplier;

			multiplier = (float) (((4 - osmiumMultiplier) * 0.17) + ((4 - lutetiumMultiplier) * 0.138));

			e.setStrength(e.getOriginalStrength() * multiplier);
		}
	}

}
