/*==============================================================================
 = Class: PacketMetallurgyExplosion
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2022.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.world.explosive.ExplosiveType;
import it.hurts.metallurgy_reforged.world.explosive.MetallurgyExplosion;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PacketMetallurgyExplosion implements IMessage {

	private double x;
	private double y;
	private double z;

	private float motionX;
	private float motionY;
	private float motionZ;

	private List<BlockPos> affectedBlockPositions;

	private ExplosiveType explosionType;

	public PacketMetallurgyExplosion()
	{
		//Mandatory empty constructor
	}

	public PacketMetallurgyExplosion(double x, double y, double z, @Nullable Vec3d motion, List<BlockPos> affectedBlockPositions, ExplosiveType explosionType)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.affectedBlockPositions = affectedBlockPositions;
		this.explosionType = explosionType;

		if (motion != null)
		{
			this.motionX = (float) motion.x;
			this.motionY = (float) motion.y;
			this.motionZ = (float) motion.z;
		}
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();

		this.motionX = buf.readFloat();
		this.motionY = buf.readFloat();
		this.motionZ = buf.readFloat();

		this.explosionType = ExplosiveType.byIndex(buf.readInt());

		int size = buf.readInt();
		this.affectedBlockPositions = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
		{
			affectedBlockPositions.add(BlockPos.fromLong(buf.readLong()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);

		buf.writeFloat(motionX);
		buf.writeFloat(motionY);
		buf.writeFloat(motionZ);

		buf.writeInt(this.explosionType.ordinal());

		buf.writeInt(affectedBlockPositions.size());
		affectedBlockPositions.forEach(pos -> buf.writeLong(pos.toLong()));
	}

	public static class Handler implements IMessageHandler<PacketMetallurgyExplosion, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketMetallurgyExplosion message, MessageContext ctx)
		{
			Minecraft client = Minecraft.getMinecraft();
			client.addScheduledTask(() -> {
				MetallurgyExplosion explosion = new MetallurgyExplosion(client.world, null, message.x, message.y, message.z, message.affectedBlockPositions, message.explosionType);
				explosion.doExplosionB(true);
				client.player.motionX += message.motionX;
				client.player.motionY += message.motionY;
				client.player.motionZ += message.motionZ;
			});

			return null;
		}

	}

}
