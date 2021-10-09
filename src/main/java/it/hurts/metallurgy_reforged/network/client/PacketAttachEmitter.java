/*==============================================================================
 = Class: PacketAttachEmitter
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.particle.ParticleOreEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class PacketAttachEmitter implements IMessage {

	private double x1, x2;
	private double y1, y2;
	private double z1, z2;
	private int color;
	private float scale;
	private int level;
	private int lifetime;
	private boolean dynamic;

	private double motionX;
	private double motionY;
	private double motionZ;
	private boolean hasMotion = false;

	public PacketAttachEmitter()
	{
		//Mandatory Packet Constructor
	}

	public PacketAttachEmitter(AxisAlignedBB aabb, double motionX, double motionY, double motionZ, int color, float scale, boolean dynamic, int level, int lifetime)
	{
		this(aabb, color, scale, dynamic, level, lifetime);
		this.hasMotion = true;
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}

	public PacketAttachEmitter(AxisAlignedBB aabb, int color, float scale, boolean dynamic, int level, int lifetime)
	{
		x1 = aabb.minX;
		x2 = aabb.maxX;
		y1 = aabb.minY;
		y2 = aabb.maxY;
		z1 = aabb.minZ;
		z2 = aabb.maxZ;
		this.color = color;
		this.scale = scale;
		this.level = level;
		this.dynamic = dynamic;
		this.lifetime = lifetime;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		x1 = buf.readDouble();
		x2 = buf.readDouble();
		y1 = buf.readDouble();
		y2 = buf.readDouble();
		z1 = buf.readDouble();
		z2 = buf.readDouble();
		color = buf.readInt();
		scale = buf.readFloat();
		dynamic = buf.readBoolean();
		level = buf.readInt();
		lifetime = buf.readInt();

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
		buf.writeDouble(x1);
		buf.writeDouble(x2);
		buf.writeDouble(y1);
		buf.writeDouble(y2);
		buf.writeDouble(z1);
		buf.writeDouble(z2);
		buf.writeInt(color);
		buf.writeFloat(scale);
		buf.writeBoolean(dynamic);
		buf.writeInt(level);
		buf.writeInt(lifetime);

		buf.writeBoolean(hasMotion);
		if (hasMotion)
		{
			buf.writeDouble(motionX);
			buf.writeDouble(motionY);
			buf.writeDouble(motionZ);
		}
	}

	public static class Handler implements IMessageHandler<PacketAttachEmitter, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketAttachEmitter message, MessageContext ctx)
		{

			Minecraft minecraft = Minecraft.getMinecraft();

			minecraft.addScheduledTask(() -> {
				float[] rgb = new Color(message.color).getColorComponents(null);

				AxisAlignedBB box = new AxisAlignedBB(message.x1, message.y1, message.z1, message.x2, message.y2, message.z2);
				ParticleOreEmitter particleOre;
				if (message.hasMotion)
					particleOre = new ParticleOreEmitter(minecraft.world, box, message.motionX, message.motionY, message.motionZ, message.lifetime, rgb[0], rgb[1], rgb[2], message.dynamic, message.level);
				else
					particleOre = new ParticleOreEmitter(minecraft.world, box, message.lifetime, rgb[0], rgb[1], rgb[2], message.dynamic, message.level);

				minecraft.effectRenderer.addEffect(particleOre);
			});

			return null;
		}

	}

}
