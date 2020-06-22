/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PacketMovePlayer
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketMovePlayer implements IMessage {

	public PacketMovePlayer() { }

	@Override
	public void fromBytes(ByteBuf buf) { }

	@Override
	public void toBytes(ByteBuf buf) { }

	public static class Handler implements IMessageHandler<PacketMovePlayer, IMessage> {

		@Override
		public IMessage onMessage(PacketMovePlayer message, MessageContext ctx)
		{
			ctx.getServerHandler().player.motionY = 0.2;
			return null;
		}

	}

}
