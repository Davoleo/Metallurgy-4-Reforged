/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TraitDeeply
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.container.slot.ArmorCustomSlot;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyConArmorStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import java.util.List;

public class TraitDeeply extends AbstractArmorTrait implements IConarmMetallurgyTrait {

	public TraitDeeply()
	{
		super("deeply", TextFormatting.DARK_BLUE);
	}

	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event)
	{
		EntityPlayer pl = event.player;
		if (MetallurgyConArmorStats.hasValidArmorTrait(pl, "deeply") && pl.isInWater() && !pl.isCreative())
		{
			for (int i = 5; i < 9; i++)
			{
				if (!(pl.inventoryContainer.inventorySlots.get(i) instanceof ArmorCustomSlot) && !pl.isCreative())
				{
					//					 Inseriamo nello slot dell'inventario in posizione i un custom slot
					pl.inventoryContainer.inventorySlots.set(i, new ArmorCustomSlot(pl, i - 5, true));
				}

			}
			//			Add effect to Player
			//			pl.setAir(275);
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));

			pl.addTag("deeply_on");

			//		   Checks if the player is tourching ground
			if (pl.onGround)
			{
				pl.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(8);
			}
			else
			{
				//			    Stop player motion
				pl.motionX *= 0.5D;
				pl.motionZ *= 0.5D;
			}

			//			The player can no longer swim upwards
			pl.motionY = -0.6D;

			//			When the player is in the water he can step one block height like a horse
			if (pl.stepHeight != 1.0F)
				pl.stepHeight = 1.0F;
			//turns the stepHeight to normal if the player isn't wearing the deep iron armor or if he is not in water
		}
		else
		{
			if (pl.getTags().contains("deeply_on"))
			{
				if (pl.stepHeight != 0.6F)
					pl.stepHeight = 0.6F;

				if (pl.inventoryContainer.inventorySlots.get(5) instanceof ArmorCustomSlot)
				{
					//		    		 Insert in c the container "vanilla"
					ContainerPlayer c = new ContainerPlayer(pl.inventory, !pl.world.isRemote, pl);
					List<Slot> slots = c.inventorySlots;
					for (int i = 5; i < 9; i++)
					{
						pl.inventoryContainer.inventorySlots.set(i, slots.get(i));
					}
				}

				pl.removeTag("deeply_on");

				if (pl.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= (11 * 20))
					pl.removePotionEffect(MobEffects.NIGHT_VISION);
				if (pl.getActivePotionEffect(MobEffects.WATER_BREATHING).getDuration() <= (11 * 20))
					pl.removePotionEffect(MobEffects.WATER_BREATHING);
				pl.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(1.0);
			}
		}
	}

}
