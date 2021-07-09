/*==============================================================================
 = Class: PacketStartStopAmbienceSound
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.sound.SoundHandler;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketStartStopAmbienceSound implements IMessage {

    private SoundEvent event = SoundEvents.ENTITY_GENERIC_BURN;
    private SoundCategory category = SoundCategory.MASTER;
    private long serializedPos;

    private boolean start;

    public PacketStartStopAmbienceSound()
    {
    }

    /**
     * Starts the sound and keeps track of it in a hash map
     */
    public PacketStartStopAmbienceSound(SoundEvent event, SoundCategory category, BlockPos pos)
    {
        this.event = event;
        this.category = category;
        this.serializedPos = pos.toLong();
        start = true;
    }

    /**
     * Stops the sound in a position
     */
    public PacketStartStopAmbienceSound(BlockPos pos)
    {
        this.serializedPos = pos.toLong();
        start = false;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.serializedPos = buf.readLong();
        this.start = buf.readBoolean();
        if (start)
        {
            this.event = SoundEvent.REGISTRY.getObjectById(buf.readInt());
            this.category = SoundCategory.values()[buf.readInt()];
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(serializedPos);
        buf.writeBoolean(start);
        if (start)
        {
            buf.writeInt(SoundEvent.REGISTRY.getIDForObject(event));
            buf.writeInt(category.ordinal());
        }
    }

    public static class Handler implements IMessageHandler<PacketStartStopAmbienceSound, IMessage> {
        @Override
        public IMessage onMessage(PacketStartStopAmbienceSound message, MessageContext ctx)
        {
            if (message.start)
                SoundHandler.startSound(BlockPos.fromLong(message.serializedPos), message.event, message.category);
            else
                SoundHandler.stopSound(BlockPos.fromLong(message.serializedPos));
            return null;
        }
    }
}
