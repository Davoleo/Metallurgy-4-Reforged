/*==============================================================================
 = Class: ClientEventsHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.armor.AmordrineArmorEffect;
import it.hurts.metallurgy_reforged.effect.armor.KrikArmorEffect;
import it.hurts.metallurgy_reforged.effect.armor.SanguiniteArmorEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ClientEventsHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onKeyInput(InputEvent.KeyInputEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		PlayerEffectData capability = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

		AmordrineArmorEffect.onPlayerJump(player);

		KrikArmorEffect.changeKrikLevel(player, capability);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onEntityRender(RenderLivingEvent.Pre<EntityLivingBase> event)
	{
		SanguiniteArmorEffect.renderLivingCorpse(event.getEntity(), event.getX(), event.getY(), event.getZ());
	}

}
