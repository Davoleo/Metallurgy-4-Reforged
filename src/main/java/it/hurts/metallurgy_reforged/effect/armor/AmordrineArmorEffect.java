/*==============================================================================
 = Class: AmordrineArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.krik.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.krik.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.server.PacketAmordrineJump;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class AmordrineArmorEffect extends BaseMetallurgyEffect {

    private final EventHandler<LivingEvent.LivingUpdateEvent> RESET_JUMP_COUNT = new EventHandler<>(this::resetJumpCount, LivingEvent.LivingUpdateEvent.class);

    public AmordrineArmorEffect() {
        super(ModMetals.AMORDRINE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{RESET_JUMP_COUNT};
    }

    public void resetJumpCount(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            PlayerEffectData capability = event.getEntity().getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);
            if (capability != null) {
                if (event.getEntity().onGround && capability.getAmordrineJumps() > 0) {
                    capability.resetAmordrineJumps();
                }
            }
        }
    }

    /**
     * Called in {@link it.hurts.metallurgy_reforged.handler.KeyboardHandler}
     */
    @SideOnly(Side.CLIENT)
    public static void onPlayerJump(EntityPlayer player) {

        final int jumps = EventUtils.getArmorPiecesCount(player, ModMetals.AMORDRINE);

        if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed() && jumps > 0 && !player.onGround) {
            PacketAmordrineJump packet = new PacketAmordrineJump(jumps);
            PacketManager.network.sendToServer(packet);
        }
    }

}