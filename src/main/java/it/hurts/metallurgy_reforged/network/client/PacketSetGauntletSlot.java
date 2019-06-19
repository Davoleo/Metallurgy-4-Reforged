/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PacketSetGauntletSlot
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.container.slot.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSetGauntletSlot implements IMessage{

	private ItemStack gauntlet;
	private boolean put;
	
	public PacketSetGauntletSlot() {
	}
	
	public PacketSetGauntletSlot(ItemStack gauntlet,boolean put)
	{
		this.gauntlet = gauntlet;
		this.put = put;
	}

//	Converte i byte in variabili
	@Override
	public void fromBytes(ByteBuf buf)
	{
		gauntlet = ByteBufUtils.readItemStack(buf);
		put = buf.readBoolean();
	}

//	Converte le variabili in byte
	@Override
	public void toBytes(ByteBuf buf)
	{
		
		ByteBufUtils.writeItemStack(buf, gauntlet);
		buf.writeBoolean(put);
		
	}
//	Il side ricevente ottiene un packet convertito ( da client a server e viceversa )
	public static class Handler implements IMessageHandler<PacketSetGauntletSlot, IMessage>
	{
//		Qui riceve il pacchetto
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketSetGauntletSlot msg, MessageContext ctx)
		{

			EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
			if (entityplayersp != null)
			{
//				Imposta lo stack al braccio sinistro
				entityplayersp.inventory.offHandInventory.set(0, msg.gauntlet);

				if(msg.put)
//					Se msg (put) � true, imposta lo slot bloccato
					entityplayersp.inventoryContainer.inventorySlots.set(45, new OffHandCustomSlot(entityplayersp));
				else
				{
//					Altrimenti trasforma lo slot in slot normale
					ContainerPlayer c = new ContainerPlayer(entityplayersp.inventory, !entityplayersp.world.isRemote, entityplayersp);
					entityplayersp.inventoryContainer.inventorySlots.set(45, c.inventorySlots.get(45));
				}
			}
			return null;
		}
	}

}
