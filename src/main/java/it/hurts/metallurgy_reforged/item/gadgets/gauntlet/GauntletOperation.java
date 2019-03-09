/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GauntletOperation
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadgets.gauntlet;

import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSetGauntletSlot;
import it.hurts.metallurgy_reforged.container.slot.OffHandCustomSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GauntletOperation {

	@SubscribeEvent
	public static void setOffHnand(LivingEquipmentChangeEvent event) {
		ItemStack newStack = event.getTo();
		ItemStack offStack = event.getEntityLiving().getHeldItemOffhand();

		Entity entity = event.getEntityLiving();

		if(entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			if(event.getSlot() == EntityEquipmentSlot.MAINHAND)
			{
				if(newStack.getItem() instanceof ItemGauntlet)
				{

					ItemStack offStackCopy = offStack.copy();

					ItemStack copy = newStack.copy();			
					player.inventoryContainer.inventorySlots.set(45, new OffHandCustomSlot(player));				
					player.setHeldItem(EnumHand.OFF_HAND,copy);				
					PacketManager.packetReq.sendTo(new PacketSetGauntletSlot(copy,true), player);

					if(!offStackCopy.isEmpty() && !offStackCopy.isItemEqualIgnoreDurability(new ItemStack(ModItems.gauntlet)) && !player.inventory.addItemStackToInventory(offStackCopy))
						player.dropItem(offStackCopy, false);
				}
				else if(player.inventoryContainer.inventorySlots.get(45) instanceof OffHandCustomSlot)
				{
					ContainerPlayer c = new ContainerPlayer(player.inventory, !player.world.isRemote, player);
					player.inventoryContainer.inventorySlots.set(45, c.inventorySlots.get(45));
					player.setHeldItem(EnumHand.OFF_HAND,ItemStack.EMPTY);		
					PacketManager.packetReq.sendTo(new PacketSetGauntletSlot(ItemStack.EMPTY,false), player);

				}
			}
		}
	}
	
	public static boolean isWearingGauntlet(EntityLivingBase pl)
    {
        ItemStack mainHand = pl.getHeldItemMainhand();
        ItemStack offHand = pl.getHeldItemOffhand();
        return offHand.getItem().equals(ModItems.gauntlet) && mainHand.getItem().equals(ModItems.gauntlet);
    }
}
