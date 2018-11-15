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
        this.fontRenderer.drawString(name, 10, -22, 16747293);
    }


    //TODO : Change the crusher animation to the new one
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        //x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
        this.drawTexturedModalRect(this.guiLeft, this.guiTop - 31, 0, 0, 175, 196);

        if(TileEntityCrusher.isBurning(crusher))
        {
            int k = this.getBurnLeftScaled(17);
            this.drawTexturedModalRect(this.guiLeft + 128, this.guiTop + 46 - k, 176, 77 - k, 17, k + 1);
        }

        int l = this.getCrushProgressScaled(33);
        if (l > 0) {
            drawTexturedModalRect(this.guiLeft + 93, this.guiTop + 66 - l, 176, 32 - l, 7, l);
            drawTexturedModalRect(this.guiLeft + 60, this.guiTop + 14, 177, 33, 21, 18);
        }
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
