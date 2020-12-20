/*==============================================================================
 = Class: KrikArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.krik.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.krik.PlayerEffectData;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.server.PacketEditPlayerLevel;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;

public class KrikArmorEffect extends BaseMetallurgyEffect {

    private static final EventHandler<LivingEvent.LivingUpdateEvent> LEVITATE_EFFECT = new EventHandler<>(KrikArmorEffect::livingUpdate, LivingEvent.LivingUpdateEvent.class);
    private static final EventHandler<LivingFallEvent> CANCEL_FALL = new EventHandler<>(KrikArmorEffect::cancelFall, LivingFallEvent.class);

    public KrikArmorEffect() {
        super(ModMetals.KRIK);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{LEVITATE_EFFECT, CANCEL_FALL};
    }

	private static void livingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
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
				}
				else {
					capability.setKrikHeight(maxLevel);
				}
			}
		}

	}

	private static void cancelFall(LivingFallEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer)
			event.setCanceled(true);
	}

	/**
	 * Called in {@link it.hurts.metallurgy_reforged.handler.KeyboardHandler}
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
