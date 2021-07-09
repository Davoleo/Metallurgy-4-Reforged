/*==============================================================================
 = Class: PacketEditPlayerLevel
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.server;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
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
			PlayerEffectData capability = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

			if (capability != null)
			{
				if (message.increase)
					capability.krikHeight += 1;
				else
					capability.krikHeight -= 1;
			}

			return null;
		}

	}

}
