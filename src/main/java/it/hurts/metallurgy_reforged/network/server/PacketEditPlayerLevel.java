/*==============================================================================
 = Class: PacketEditPlayerLevel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.server;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.capabilities.krik.IKrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEditPlayerLevel implements IMessage {

	public boolean increase;

	@SuppressWarnings("unused")
	public PacketEditPlayerLevel()
	{
		//Mandatory Empty Default Constructor
	}

	public PacketEditPlayerLevel(boolean increase)
	{
		this.increase = increase;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(increase);
	}

	public static class Handler implements IMessageHandler<PacketEditPlayerLevel, IMessage> {

		@Override
		public IMessage onMessage(PacketEditPlayerLevel message, MessageContext ctx)
		{

			EntityPlayer player = ctx.getServerHandler().player;
			IKrikEffect capability = player.getCapability(KrikEffectProvider.KRIK_EFFECT_CAPABILITY, null);

			if (capability != null)
			{
				if (message.increase)
					capability.setHeight(capability.getHeight() + 1);
				else
					capability.setHeight(capability.getHeight() - 1);
			}

			return null;
		}

	}

}
