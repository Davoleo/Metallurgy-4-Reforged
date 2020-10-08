/*
 * -------------------------------------------------------------------------------------------------------
 * Class: KeyboardHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.capabilities.krik.IKrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.server.PacketEditPlayerLevel;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class KeyboardHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onKeyInput(InputEvent.KeyInputEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		IKrikEffect capability = player.getCapability(KrikEffectProvider.KRIK_EFFECT_CAPABILITY, null);

		if (EventUtils.isEntityWearingArmor(player, ModMetals.KRIK) && ArmorEffectsConfig.krikArmorEffect)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			{
				if (capability != null && capability.getHeight() < KrikEffect.getMaxLevel(player))
				{
					PacketManager.network.sendToServer(new PacketEditPlayerLevel(true));
					capability.setHeight(capability.getHeight() + 1);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				if (capability != null && capability.getHeight() > 0)
				{
					PacketManager.network.sendToServer(new PacketEditPlayerLevel(false));
					capability.setHeight(capability.getHeight() - 1);
					//System.out.println(capability.getHeight());
				}
			}
		}
	}

}
