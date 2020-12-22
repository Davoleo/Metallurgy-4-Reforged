/*==============================================================================
 = Class: PacketSpawnVanillaParticles
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSpawnVanillaParticles implements IMessage {

    private int particleId;
    private float x;
    private float y;
    private float z;
    private float motionX;
    private float motionY;
    private float motionZ;

    @SuppressWarnings("unused")
    public PacketSpawnVanillaParticles() {
        //Mandatory Empty Default Constructor
    }

    public PacketSpawnVanillaParticles(int particleId, float x, float y, float z, float motionX, float motionY, float motionZ) {
        this.particleId = particleId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        particleId = buf.readInt();
        x = buf.readFloat();
        y = buf.readFloat();
        z = buf.readFloat();
        motionX = buf.readFloat();
        motionY = buf.readFloat();
        motionZ = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(particleId);
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
        buf.writeFloat(motionX);
        buf.writeFloat(motionY);
        buf.writeFloat(motionZ);
    }

    public static class Handler implements IMessageHandler<PacketSpawnVanillaParticles, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final PacketSpawnVanillaParticles message, final MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                World world = Minecraft.getMinecraft().world;

                EnumParticleTypes particleType = EnumParticleTypes.getParticleFromId(message.particleId);
                assert particleType != null;
                world.spawnParticle(particleType, message.x, message.y, message.z, message.motionX, message.motionY, message.motionZ);
            });
            return null;
        }

    }

}
