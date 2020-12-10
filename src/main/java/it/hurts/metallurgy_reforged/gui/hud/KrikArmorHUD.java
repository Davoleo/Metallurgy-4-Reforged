/*==============================================================================
 = Class: KrikArmorHUD
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.gui.hud;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffect;
import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectProvider;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class KrikArmorHUD {

	public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Metallurgy.MODID, "textures/gui/krik_overlay.png");

	public static void render(RenderGameOverlayEvent.Post event, Minecraft minecraft)
	{
		ScaledResolution resolution = event.getResolution();

		int playerYLevel = minecraft.player.getCapability(KrikEffectProvider.KRIK_EFFECT_CAPABILITY, null).getHeight();
		int maxLevel = KrikEffect.getMaxLevel(minecraft.player);

		//System.out.println(playerYLevel + " | " +  maxLevel);

		GlStateManager.pushMatrix();

		minecraft.getTextureManager().bindTexture(OVERLAY_TEXTURE);
		//Draw Bar border
		Utils.drawTexturedModalRect(resolution.getScaledWidth() - 15, resolution.getScaledHeight() - 60, 0, 0, 8, 58);
		//Draw Bar
		Utils.drawTexturedModalRect(resolution.getScaledWidth() - 13, resolution.getScaledHeight() - 4 - playerYLevel * 2, 10, 0, 4, playerYLevel * 2);
		//Draw Limit
		Utils.drawTexturedModalRect(resolution.getScaledWidth() - 13, resolution.getScaledHeight() - 6 - maxLevel * 2, 22, 0, 4, 2);

		GlStateManager.popMatrix();
	}

}
