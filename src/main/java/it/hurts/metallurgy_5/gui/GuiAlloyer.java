package it.hurts.metallurgy_5.gui;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.container.ContainerCrusher;
import it.hurts.metallurgy_5.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
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
 ***************************/
public class GuiAlloyer extends GuiContainer{

//	Inizializziamo la locazione della texture
	private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy_5.MODID + ":textures/gui/alloyer.png");
//	Inizializziamo il player
    private final InventoryPlayer player;
//    Inizializziamo l'alloyer
    private final TileEntityAlloyer alloyer;
    
//    Creiamo il costruttore
    public GuiAlloyer(InventoryPlayer player, TileEntityAlloyer alloyer){
        super(new ContainerAlloyer(player, alloyer));
        this.player = player;
        this.alloyer = alloyer;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
//    Definiamo la gui disegnandola e posizionando ogni inventario al proprio posto
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String name = this.alloyer.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(name, (this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2) + 3, 8, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
    }
	
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityAlloyer.isBurning(alloyer))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 -k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getAlloyingProgressScaled(24);
        //x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
        this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 36, 176, 14, l + 1, 16);
    }
    
    private int getBurnLeftScaled(int pixels){
        int i = this.alloyer.getField(1);
        if(i == 0)
            i = 200;
        return this.alloyer.getField(0) * pixels / i;
    }

    private int getAlloyingProgressScaled(int pixels){
        int i = this.alloyer.getField(2);
        int j = this.alloyer.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    
}
