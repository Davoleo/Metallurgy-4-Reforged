package it.hurts.metallurgy_reforged.gui;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.container.ContainerAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 24 set 2018
 * Time   : 19:07:28
 *
 * Reworked by Davoleo
 ***************************/
public class GuiAlloyer extends GuiContainer{

    //GUI Texture location
	private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy.MODID + ":textures/gui/alloyer.png");
    @SuppressWarnings("unused")
	private final InventoryPlayer player; //Player Inventory
    private final TileEntityAlloyer alloyer; //Tile Entity
    
    //Gui constructor
    public GuiAlloyer(InventoryPlayer player, TileEntityAlloyer alloyer){
        super(new ContainerAlloyer(player, alloyer));
        this.player = player;
        this.alloyer = alloyer;
    }

    //draws the default background and adds the tooltip
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    //draws the foreground layer of the GUI
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String name = this.alloyer.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(name, 10, -22, 16769280);
    }

    //draws the foreground layer of the GUI
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
	//x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
        this.drawTexturedModalRect(this.guiLeft, this.guiTop - 31, 0, 0, 175, 196);

        if(alloyer.isBurning())
        {
            int k = this.getBurnLeftScaled(17);
            this.drawTexturedModalRect(this.guiLeft + 110, this.guiTop + 30 + 16 -k, 176, 77 - k, 17, k + 2);
        }

        int l = this.getAlloyingProgressScaled(33);
        if (l > 0) {
            drawTexturedModalRect(this.guiLeft + 40, this.guiTop + 66 - l, 176, 32 - l, 7, l);
            int m = this.getAlloyingProgressScaled(70);
            drawTexturedModalRect(this.guiLeft + 52, this.guiTop +1, 176, 81, 10, 1 + m);
        }



    }

    //returns the burn animation parameter (scaled by the size of the animated texture)
    private int getBurnLeftScaled(int pixels){
        int i = this.alloyer.getField(1);
        return this.alloyer.getField(0) * pixels / Math.max(i, 200);
    }

    //returns the process animation parameter (scaled by the size of the animated texture)
    private int getAlloyingProgressScaled(int pixels){
        int i = this.alloyer.getField(2);
        int j = this.alloyer.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    
}
