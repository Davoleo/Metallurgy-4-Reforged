/*==============================================================================
 = Class: PacketSpawnOreParticles
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.particle.ParticleOre;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.awt.*;

public class PacketSpawnOreParticles implements IMessage {

	private double x;
	private double y;
	private double z;
	private int color;
	private float scale;
	private int level;
	private double motionX;
	private double motionY;
	private double motionZ;

	private boolean hasMotion = false;


	public PacketSpawnOreParticles()
	{
		//Mandatory Packet Constructor
	}

	public PacketSpawnOreParticles(double x, double y, double z, double motionX, double motionY, double motionZ, int color, float scale, int level)
	{
		this(x, y, z, color, scale, level);
		this.hasMotion = true;
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	public PacketSpawnOreParticles(double x, double y, double z, int color, float scale, int level)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
		this.scale = scale;
		this.level = level;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
		color = buf.readInt();
		scale = buf.readFloat();
		level = buf.readInt();

		hasMotion = buf.readBoolean();
		if (hasMotion)
		{
			this.motionX = buf.readDouble();
			this.motionY = buf.readDouble();
			this.motionZ = buf.readDouble();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeInt(color);
		buf.writeFloat(scale);
		buf.writeInt(level);

		buf.writeBoolean(hasMotion);
		if (hasMotion)
		{
			buf.writeDouble(motionX);
			buf.writeDouble(motionY);
			buf.writeDouble(motionZ);
		}
	}

	public static class Handler implements IMessageHandler<PacketSpawnOreParticles, IMessage> {

		@Override
		public IMessage onMessage(PacketSpawnOreParticles message, MessageContext ctx)
		{

			Minecraft minecraft = Minecraft.getMinecraft();

			minecraft.addScheduledTask(() -> {
				float[] rgb = new Color(message.color).getColorComponents(null);
				ParticleOre particleOre;

				if (message.hasMotion)
					particleOre = new ParticleOre(minecraft.world, message.x, message.y, message.z, message.motionX, message.motionY, message.motionZ, message.scale, rgb[0], rgb[1], rgb[2], message.level);
				else
					particleOre = new ParticleOre(minecraft.world, message.x, message.y, message.z, message.scale, rgb[0], rgb[1], rgb[2], message.level);

				minecraft.effectRenderer.addEffect(particleOre);
			});

			return null;
		}

	}

}
