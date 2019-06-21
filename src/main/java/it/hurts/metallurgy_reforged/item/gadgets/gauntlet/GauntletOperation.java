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

import it.hurts.metallurgy_reforged.capabilities.punch.IPunchEffect;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectProvider;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSetGauntletSlot;
import it.hurts.metallurgy_reforged.container.slot.OffHandCustomSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GauntletOperation
{

    @SubscribeEvent
    public static void setOffHnand(LivingEquipmentChangeEvent event)
    {
        ItemStack newStack = event.getTo();
        ItemStack oldStack = event.getFrom();
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

                    PacketManager.packetReq.sendTo(new PacketSetGauntletSlot(copy, true), player);

                    player.inventory.offHandInventory.set(0, copy);

                    boolean flag = offStackCopy.getItem() == ModItems.gauntlet ? oldStack.getItem() != ModItems.gauntlet && newStack.getTagCompound() != offStackCopy.getTagCompound() : !offStackCopy.isEmpty();

                    if(player.ticksExisted > 5 && flag && !player.inventory.addItemStackToInventory(offStackCopy))
                        player.dropItem(offStackCopy, false);


                } else if(player.inventoryContainer.inventorySlots.get(45) instanceof OffHandCustomSlot)
                {
                    ContainerPlayer c = new ContainerPlayer(player.inventory, !player.world.isRemote, player);
                    player.inventoryContainer.inventorySlots.set(45, c.inventorySlots.get(45));
                    player.inventory.offHandInventory.set(0, ItemStack.EMPTY);

                    PacketManager.packetReq.sendTo(new PacketSetGauntletSlot(ItemStack.EMPTY, false), player);

                }
            }
        }
    }


    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
            IPunchEffect effect = entityPlayer.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);

            if(isWearingGauntlet(entityPlayer) && effect != null)
                effect.setGauntletUserDead();
        }
    }

    @SubscribeEvent
    public static void preventGauntletDuplication(PlayerDropsEvent event)
    {
        EntityPlayer pl = event.getEntityPlayer();
        IPunchEffect effect = pl.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
        if(effect != null && effect.isGauntletUserDead())
        {
            int i = 0;
            ItemStack mainHand = pl.getHeldItemMainhand();

            int removeGautletSlot = -1;
            while (removeGautletSlot == -1 && i < event.getDrops().size())
            {
                ItemStack stack = event.getDrops().get(i).getItem();
                if(stack.getItem() == ModItems.gauntlet && (stack.getTagCompound() == null || stack.getTagCompound().equals(mainHand.getTagCompound())))
                    removeGautletSlot = i;
                i++;
            }
            event.getDrops().remove(removeGautletSlot);
        }

    }

    public static boolean isWearingGauntlet(EntityLivingBase pl)
    {
        ItemStack mainHand = pl.getHeldItemMainhand();
        ItemStack offHand = pl.getHeldItemOffhand();
        return offHand.getItem().equals(ModItems.gauntlet) && mainHand.getItem().equals(ModItems.gauntlet);
    }
}
