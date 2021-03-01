/*==============================================================================
 = Class: PacketRenderDeathProtection
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network.client;

import io.netty.buffer.ByteBuf;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.particle.ParticleOreEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Sent to Players tracking the player that is resurrected and to the Resurrected player itself
 *
 * @see it.hurts.metallurgy_reforged.effect.armor.AdamantineArmorEffect
 */
public class PacketRenderDeathProtection implements IMessage {

    private Entity entity;
    private ItemStack stack;

    public PacketRenderDeathProtection() {
        //Mandatory Empty Constructor
    }

    public PacketRenderDeathProtection(Entity entity, ItemStack stack) {
        this.entity = entity;
        this.stack = stack;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        stack = ByteBufUtils.readItemStack(buf);
        entity = Minecraft.getMinecraft().world.getEntityByID(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeInt(entity.getEntityId());
    }

    public static class Handler implements IMessageHandler<PacketRenderDeathProtection, IMessage> {
        @Override
        public IMessage onMessage(PacketRenderDeathProtection message, MessageContext ctx) {

            Minecraft client = Minecraft.getMinecraft();

            Minecraft.getMinecraft().addScheduledTask(() -> {
                //Get the RGB colors of Adamantine
                float[] colors = ModMetals.ADAMANTINE.getStats().getColorRGBValues();
                //Instantiate and start a Particle Ore Emitter
                client.effectRenderer.addEffect(new ParticleOreEmitter(client.world, message.entity.getEntityBoundingBox(), 40, colors[0], colors[1], colors[2], true, ModMetals.ADAMANTINE.getStats().getOreHarvest() - 2));

                //If the entity in the message is the same one that's just died renders the Totem overlay with the armor piece that was sacrificed
                if (message.entity == client.player) {
                    client.entityRenderer.displayItemActivation(message.stack);
                }

            });

            return null;
        }
    }
}
