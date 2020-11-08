/*==============================================================================
 = Class: BrassKnucklesTEISR
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class BrassKnucklesTEISR extends TileEntityItemStackRenderer {

	public static ItemCameraTransforms.TransformType type = null;

	@Override
	public void renderByItem(@Nonnull ItemStack itemStackIn)
	{
		BrassKnucklesModel model = new BrassKnucklesModel(Minecraft.getMinecraft(), type);
		EntityPlayerSP pl = Minecraft.getMinecraft().player;

		if (pl != null && (type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND))
		{
			float ticks = Minecraft.getMinecraft().isGamePaused() ? 1F : Minecraft.getMinecraft().getRenderPartialTicks();

			// swing variable, starts from 0 and it resets when is equal to 1
			float swing = pl.getSwingProgress(ticks);

			float animationTick = MathHelper.sin(swing * (float) Math.PI);
			GlStateManager.translate(animationTick, 0F, 0F);
			GlStateManager.rotate(animationTick * -30F, 0F, 0F, 1F);
		}

		model.render(itemStackIn);
	}

}
