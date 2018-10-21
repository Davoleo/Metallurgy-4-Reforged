package it.hurts.metallurgy_5.gui;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.container.ContainerCrusher;
import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/*************************************************
 * Author: Davoleo
 * Date: 03/09/2018
 * Hour: 22.21
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class GuiCrusher extends GuiContainer {

    //TODO : Fucking fix this Davoleo

    private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy_5.MODID + ":textures/gui/crusher.png");
    private final InventoryPlayer player;
    private final TileEntityCrusher crusher;

    public GuiCrusher(InventoryPlayer player, TileEntityCrusher crusher)
    {
        super(new ContainerCrusher(player, crusher));
        this.player = player;
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
        this.fontRenderer.drawString(name, (this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2) + 3, 8, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityCrusher.isBurning(crusher))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 -k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCrushProgressScaled(24);
        //x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
        this.drawTexturedModalRect(this.guiLeft + 82, this.guiTop + 33, 176, 14, l + 1, 20);
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.crusher.getField(1);
        if(i == 0)
            i = 200;
        return this.crusher.getField(0) * pixels / i;
    }

    private int getCrushProgressScaled(int pixels)
    {
        int i = this.crusher.getField(2);
        int j = this.crusher.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
