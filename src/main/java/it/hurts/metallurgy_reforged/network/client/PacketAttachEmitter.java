/*==============================================================================
 = Class: PacketAttachEmitter
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.particle.ParticleOreEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.awt.*;

public class PacketAttachEmitter implements IMessage {

    private double x1, x2;
    private double y1, y2;
    private double z1, z2;
    private int color;
    private float scale;
    private int level;
    private int lifetime;

    public PacketAttachEmitter() {
        //Mandatory Packet Constructor
    }

    public PacketAttachEmitter(AxisAlignedBB aabb, int color, float scale, int level, int lifetime) {
        x1 = aabb.minX;
        x2 = aabb.maxX;
        y1 = aabb.minY;
        y2 = aabb.maxY;
        z1 = aabb.minZ;
        z2 = aabb.maxZ;
        this.color = color;
        this.scale = scale;
        this.level = level;
        this.lifetime = lifetime;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x1 = buf.readDouble();
        x2 = buf.readDouble();
        y1 = buf.readDouble();
        y2 = buf.readDouble();
        z1 = buf.readDouble();
        z2 = buf.readDouble();
        color = buf.readInt();
        scale = buf.readFloat();
        level = buf.readInt();
        lifetime = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(x1);
        buf.writeDouble(x2);
        buf.writeDouble(y1);
        buf.writeDouble(y2);
        buf.writeDouble(z1);
        buf.writeDouble(z2);
        buf.writeInt(color);
        buf.writeFloat(scale);
        buf.writeInt(level);
        buf.writeInt(lifetime);
    }

    public static class Handler implements IMessageHandler<PacketAttachEmitter, IMessage> {
        @Override
        public IMessage onMessage(PacketAttachEmitter message, MessageContext ctx) {

            Minecraft minecraft = Minecraft.getMinecraft();

            minecraft.addScheduledTask(() -> {
                float[] rgb = new Color(message.color).getColorComponents(null);

                AxisAlignedBB box = new AxisAlignedBB(message.x1, message.y1, message.z1, message.x2, message.y2, message.z2);
                ParticleOreEmitter particleOre = new ParticleOreEmitter(minecraft.world, box, message.lifetime, rgb[0], rgb[1], rgb[2], message.level);
                minecraft.effectRenderer.addEffect(particleOre);
            });

            return null;
        }
    }
}
