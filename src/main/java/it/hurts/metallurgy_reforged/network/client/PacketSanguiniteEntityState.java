/*==============================================================================
 = Class: PacketSyncNBT
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSanguiniteEntityState implements IMessage {

	private int entityId;
	private int state;

	public PacketSanguiniteEntityState()
	{
		//Mandatory Default Constructor
	}

	public PacketSanguiniteEntityState(int entityId, int state)
	{
		this.entityId = entityId;
		this.state = state;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		entityId = buf.readInt();
		state = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(entityId);
		buf.writeInt(state);
	}

	public static class Handler implements IMessageHandler<PacketSanguiniteEntityState, IMessage> {

		@Override
		public IMessage onMessage(PacketSanguiniteEntityState message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.entityId);
				if (entity != null)
				{
					entity.getEntityData().setInteger("corpse_state", message.state);
					entity.ticksExisted = 0;
				}
			});
			return null;
		}

	}

}
