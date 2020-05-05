/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GauntletOperation
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget.gauntlet;

import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GauntletOperation {

	@SubscribeEvent
	public static void setInHand(LivingEquipmentChangeEvent event)
	{
		ItemStack newStack = event.getTo();

		Entity entity = event.getEntityLiving();

		if (entity instanceof EntityPlayerMP && event.getSlot().getSlotType() == EntityEquipmentSlot.Type.HAND)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			EntityEquipmentSlot oppositeSlot = event.getSlot() == EntityEquipmentSlot.OFFHAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND;

			if (newStack.getItem() == ModItems.gauntlet)
			{

				if (player.getItemStackFromSlot(oppositeSlot) == ItemStack.EMPTY)
				{
					ItemStack offStack = newStack.getCount() > 1 ? newStack.splitStack(1) : getOtherGauntlet(player, newStack);
					player.setItemStackToSlot(oppositeSlot, offStack);

					//Sync the client with the server
					player.connection.sendPacket(new SPacketEntityEquipment(player.getEntityId(), oppositeSlot, offStack));
				}

			}
		}
	}

	private static ItemStack getOtherGauntlet(EntityPlayerMP player, ItemStack firstGauntlet)
	{
		//For Every slot in the player inventory
		for (int i = 0; i < player.inventory.mainInventory.size(); i++)
		{
			//Filter out the case when the gauntlet you're looking for is the same one that you're already holding in the "main" hand
			if (i != player.inventory.currentItem)
			{
				ItemStack otherGauntlet = player.inventory.mainInventory.get(i);

				//Check if the main Gauntlet is the same as the moved one
				if (ItemStack.areItemsEqual(firstGauntlet, otherGauntlet))
				{

					//Create a copy of the moved Gauntlet
					ItemStack remainingStack = otherGauntlet.copy();

					//if the Gauntlet stack has more than one items shrink both of the stacks
					if (remainingStack.getCount() > 1)
					{
						otherGauntlet.shrink(1);
						remainingStack.shrink(1);
					}
					else
					{
						remainingStack = ItemStack.EMPTY;
					}

					//Set the remaining item after moving the gauntlet
					player.inventory.mainInventory.set(i, remainingStack);
					//Update the client to reflect the server change
					player.connection.sendPacket(new SPacketSetSlot(-2, i, remainingStack));
					return otherGauntlet;
				}
			}
		}

		return ItemStack.EMPTY;
	}

	public static boolean isWearingGauntlet(EntityLivingBase pl)
	{
		ItemStack mainHand = pl.getHeldItemMainhand();
		ItemStack offHand = pl.getHeldItemOffhand();
		return offHand.getItem().equals(ModItems.gauntlet) && mainHand.getItem().equals(ModItems.gauntlet);
	}

}
