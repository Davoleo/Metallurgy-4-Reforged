/*==============================================================================
 = Class: PacketAmordrineJump
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
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.client.PacketAttachEmitter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAmordrineJump implements IMessage {

	private int maxJumps;

	public PacketAmordrineJump()
	{
		//Mandatory empty constructor
	}

	public PacketAmordrineJump(int maxJumps)
	{
		this.maxJumps = maxJumps;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		maxJumps = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(maxJumps);
	}

	public static class Handler implements IMessageHandler<PacketAmordrineJump, PacketAttachEmitter> {

		@Override
		public PacketAttachEmitter onMessage(PacketAmordrineJump message, MessageContext ctx)
		{
			EntityPlayerMP player = ctx.getServerHandler().player;

			PlayerEffectData capability = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

			if (capability != null)
			{
				int currentJumps = capability.getAmordrineJumps();
				if (currentJumps < message.maxJumps)
				{
					capability.setAmordrineJumps(currentJumps + 1);
					player.jump();
					player.motionY += 0.15;
					player.velocityChanged = true;
					player.fallDistance = 0;
					player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDERDRAGON_FLAP, SoundCategory.PLAYERS, 0.5F, 2F);
					AxisAlignedBB playerBox = player.getEntityBoundingBox();
					AxisAlignedBB feetBox = new AxisAlignedBB(playerBox.minX, playerBox.minY, playerBox.minZ, playerBox.maxX, playerBox.minY, playerBox.maxZ).grow(0.7D, 0D, 0.7D);

					System.out.println(((Math.random() - 0.5) * 0.5));
					return new PacketAttachEmitter(feetBox,
							0, -0.03D, 0,
							ModMetals.AMORDRINE.getStats().getColorHex(),
							1, 4 - currentJumps, 10);

				}
			}

			return null;
		}

	}

}
