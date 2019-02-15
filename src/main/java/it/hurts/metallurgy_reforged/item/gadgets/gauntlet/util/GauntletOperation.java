package it.hurts.metallurgy_reforged.item.gadgets.gauntlet.util;

import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.gadgets.gauntlet.ItemGauntlet;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSetGauntletSlot;
import it.hurts.metallurgy_reforged.util.customSlot.OffHandCustomSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/***************************
 *
 * Author : ItHurtsLikeHell, PierKnight100
 * Project: Metallurgy-4-Reforged
 * Date   : 05 gen 2019
 * Time   : 13:51:58
 *
 ***************************/
public class GauntletOperation {

	@SubscribeEvent
	public void setOffHnand(LivingEquipmentChangeEvent event) {
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
