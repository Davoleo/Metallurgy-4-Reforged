/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TraitDeeply
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.conarm.traits;

import java.util.List;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.mods.conarm.MetallurgyConArmorStats;
import it.hurts.metallurgy_reforged.util.custom_slot.ArmorCustomSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitDeeply extends AbstractArmorTrait{

	public TraitDeeply() {
		super("deeply", TextFormatting.DARK_BLUE);
	}

	//FIXME THIS IS SO FUCKING BROKEN
	//FIXME Armor gets stuck in the slot even out of the water
	//FIXME Player movements are slippery (especially underwater)
	//FIXME IT'S TOOoOoO OP
	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event){	
		EntityPlayer pl = event.player;
		if(MetallurgyConArmorStats.isThatArmorTrait(pl, "deeply")) {
			for(int i = 5;i < 9; i++)
			 {
				 if(!(pl.inventoryContainer.inventorySlots.get(i) instanceof ArmorCustomSlot) && !pl.isCreative()) {
//					 Inseriamo nello slot dell'inventario in posizione i un custom slot
                    pl.inventoryContainer.inventorySlots.set(i, new ArmorCustomSlot(pl, i - 5, true));
                }
					 
			 }			
//			Add effect to Player
			pl.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			pl.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));
			
//		   Checks if the player is tourching ground
          if(pl.onGround) {
				//adds more motion in his movement
			  if(pl.motionX <= 3D)
			    pl.motionX *= 1.1D;
			  if(pl.motionZ <= 3D)
			    pl.motionZ *= 1.1D;
			}
			else
			{
//			    Stop player motion
				pl.motionX = 0D;
				pl.motionZ = 0D;
			}

//			The player can no longer swim upwards
			pl.motionY = -0.3D;

//			When the player is in the water he can step one block height like a horse
			if(pl.stepHeight != 1.0F)
				pl.stepHeight = 1.0F;
		}
		else //turns the stepHeight to normal if the player isn't wearing the deep iron armor or if he is not in water
		{
		  if(pl.stepHeight != 0.6F)
			pl.stepHeight = 0.6F;
		  	  
		    	 if(pl.inventoryContainer.inventorySlots.get(5) instanceof ArmorCustomSlot)
		    	 { 
//		    		 Insert in c the container "vanilla"
		    		 ContainerPlayer c = new ContainerPlayer(pl.inventory, !pl.world.isRemote, pl);
	    			 List<Slot> slots = c.inventorySlots;
		    		 for(int i = 5;i < 9; i++)
					 {
		    			 pl.inventoryContainer.inventorySlots.set(i, slots.get(i));
					 }
		    	 }
		    
		  
		}
	}
	
}
