/*==============================================================================
 = Class: KrikArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.ClientEventsHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.server.PacketEditPlayerLevel;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;

public class KrikArmorEffect extends BaseMetallurgyEffect {


    public KrikArmorEffect() {
        super(ModMetals.KRIK);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }


    @SubscribeEvent
    public void livingUpdate(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;

        if (!canBeApplied(player))
            return;

        final int STEP = 255 / 27;

        PlayerEffectData capability = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

        if (capability != null) {
            int maxLevel = PlayerEffectData.getKrikMaxLevel(player);
            int level = capability.getKrikHeight();

            if (level <= maxLevel) {
                if (player.posY < level * STEP) {
                    player.motionY = 0.4;
                } else if (Math.round(player.posY) == level * STEP) {
                    player.motionY = 0;
                }
            } else {
                capability.setKrikHeight(maxLevel);
            }
        }


    }

    @SubscribeEvent
    public void cancelFall(LivingFallEvent event) {
        if (canBeApplied(event.getEntityLiving()) && event.getEntityLiving() instanceof EntityPlayer)
            event.setCanceled(true);
    }

    /**
     * Called in {@link ClientEventsHandler}
     */
    @SideOnly(Side.CLIENT)
    public static void changeKrikLevel(EntityPlayer player, PlayerEffectData capability) {
        if (EventUtils.isEntityWearingArmor(player, ModMetals.KRIK) && EffectsConfig.krikEffectArmor) {
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                if (capability != null && capability.getKrikHeight() < PlayerEffectData.getKrikMaxLevel(player)) {
                    PacketManager.network.sendToServer(new PacketEditPlayerLevel(true));
                    capability.setKrikHeight(capability.getKrikHeight() + 1);
                }
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                if (capability != null && capability.getKrikHeight() > 0) {
                    PacketManager.network.sendToServer(new PacketEditPlayerLevel(false));
                    capability.setKrikHeight(capability.getKrikHeight() - 1);
                    //System.out.println(capability.getHeight());
                }
            }
        }
    }

}
