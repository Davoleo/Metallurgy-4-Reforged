/*==============================================================================
 = Class: GuiCrusher
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.gui;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.container.ContainerCrusher;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCrusher extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy.MODID + ":textures/gui/crusher.png");
	private final TileEntityCrusher crusher;

	public GuiCrusher(InventoryPlayer player, TileEntityCrusher crusher)
	{
		super(new ContainerCrusher(player, crusher));
		this.crusher = crusher;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String name = this.crusher.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(name, 10, -22, 0xFE3F3F);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1F, 1F, 1F, 1F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		//x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
		this.drawTexturedModalRect(this.guiLeft, this.guiTop - 31, 0, 0, 175, 196);

		if (crusher.isBurning())
		{
			int k = this.getBurnLeftScaled(16);
			this.drawTexturedModalRect(this.guiLeft + 128, this.guiTop + 46 - k, 176, 77 - k, 17, k + 1);
		}

		int l = this.getCrushProgressScaled(33);
		if (l > 0)
		{
			drawTexturedModalRect(this.guiLeft + 93, this.guiTop + 66 - l, 176, 32 - l, 7, l);
			drawTexturedModalRect(this.guiLeft + 60, this.guiTop + 14, 177, 33, 21, 18);
		}
	}

	/**
	 * @param pixels texture height -1
	 */
	private int getBurnLeftScaled(int pixels)
	{
		return this.crusher.getField(0) * pixels / crusher.getField(1);
	}

	/**
	 * @param pixels Texture height
	 */
	private int getCrushProgressScaled(int pixels)
	{
		int crushingTime = this.crusher.getField(2);
		return crushingTime != 0 ? crushingTime * pixels / TileEntityCrusher.TOTAL_CRUSHING_TIME : 0;
	}

}
