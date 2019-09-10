/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SublimationChamberTESR
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.render;

import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class SublimationChamberTESR extends TileEntitySpecialRenderer<TileEntityChamber> {

	@Override
	public void render(TileEntityChamber te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{

		ItemStack metal = te.getStackInSlot(TileEntityChamber.METAL_SLOT);

		if (!metal.isEmpty())
		{

			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();

			GlStateManager.translate(x + 0.5, y + 0.25, z + 0.5);
			GlStateManager.scale(1.7, 1.7, 1.7);
			Minecraft.getMinecraft().getRenderItem().renderItem(metal, ItemCameraTransforms.TransformType.GROUND);

			RenderHelper.disableStandardItemLighting();
			GlStateManager.popMatrix();
		}
	}

}
