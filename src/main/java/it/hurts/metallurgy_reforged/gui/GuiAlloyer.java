package it.hurts.metallurgy_reforged.gui;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.container.ContainerAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 24 set 2018
 * Time   : 19:07:28
 *
 * Reworked by Davoleo
 ***************************/
public class GuiAlloyer extends GuiContainer {

    //GUI Texture location
	private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy.MODID + ":textures/gui/alloyer.png");
    @SuppressWarnings("unused")
	private final InventoryPlayer player; //Player Inventory
    private final TileEntityAlloyer alloyer; //Tile Entity

    private int xSize = 175;
    private int ySize = 196;
    private Slot hoveredSlot;

    private GuiContainerHelper guiCHelper;

    //Gui constructor
    public GuiAlloyer(InventoryPlayer player, TileEntityAlloyer alloyer){
        super(new ContainerAlloyer(player, alloyer));
        this.player = player;
        this.alloyer = alloyer;
        guiCHelper = new GuiContainerHelper(new ContainerAlloyer(player, alloyer));
    }

    //draws the default background and adds the tooltip
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int i = this.guiLeft;
        int j = this.guiTop;
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        this.renderHoveredToolTip(mouseX, mouseY);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)i, (float)j, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        this.hoveredSlot = null;
        int k = 240;
        int l = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        for (int i1 = 0; i1 < 2; ++i1)
        {
            Slot slot =  this.inventorySlots.inventorySlots.get(i1);
            if (slot.isEnabled()) {
                guiCHelper.drawSlot(slot);

            }
            if (guiCHelper.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
            {
                this.hoveredSlot = slot;
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                int j1 = slot.xPos;
                int k1 = slot.yPos;
                GlStateManager.colorMask(true, true, true, false);
                this.drawGradientRect(j1-1, k1, j1 + 17, k1 + 2, -2130706433, -2130706433);
                this.drawGradientRect(j1 , k1 + 2, j1 + 1 + 17, k1 + 2 + 11, -2130706433, -2130706433);
                this.drawGradientRect(j1-2 , k1 +13 , j1 + 17, k1  + 13 + 2, -2130706433, -2130706433);
                this.drawGradientRect(j1-2  , k1 + 15 , j1 -1 + 17, k1  + 15  + 2, -2130706433, -2130706433);
                GlStateManager.colorMask(true, true, true, true);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }
        }

        for(int i1 = 2;i1 < 3; ++i1)
        {
            Slot slot = this.inventorySlots.inventorySlots.get(i1);

            if (slot.isEnabled())
            {

                guiCHelper.drawSlot(slot);
            }

            if (guiCHelper.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
            {
                this.hoveredSlot = slot;
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                int j1 = slot.xPos;
                int k1 = slot.yPos;
                GlStateManager.colorMask(true, true, true, false);
                this.drawGradientRect(j1+2 , k1, j1 + 12 +2, k1+ 4 , -2130706433, -2130706433);
                this.drawGradientRect(j1 +1 , k1+4, j1  + 14+1, k1 + 6 +4, -2130706433, -2130706433);
                this.drawGradientRect(j1, k1  + 10, j1  + 16 , k1 + 4 +10, -2130706433, -2130706433);
                GlStateManager.colorMask(true, true, true, true);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }

        }

        for (int i1 = 3; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
            Slot slot = this.inventorySlots.inventorySlots.get(i1);

            if (slot.isEnabled())
            {
                guiCHelper.drawSlot(slot);
            }

            if (guiCHelper.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
            {
                this.hoveredSlot = slot;
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                int j1 = slot.xPos;
                int k1 = slot.yPos;
                GlStateManager.colorMask(true, true, true, false);
                this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
                GlStateManager.colorMask(true, true, true, true);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }
        }

        RenderHelper.disableStandardItemLighting();
        this.drawGuiContainerForegroundLayer(mouseX, mouseY);
        RenderHelper.enableGUIStandardItemLighting();
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiContainerEvent.DrawForeground(this, mouseX, mouseY));
        InventoryPlayer inventoryplayer = this.mc.player.inventory;
        ItemStack itemstack = guiCHelper.draggedStack.isEmpty() ? inventoryplayer.getItemStack() : guiCHelper.draggedStack;

        if (!itemstack.isEmpty())
        {
            int j2 = 8;
            int k2 = guiCHelper.draggedStack.isEmpty() ? 8 : 16;
            String s = null;

            if (!guiCHelper.draggedStack.isEmpty() && guiCHelper.isRightMouseClick)
            {
                itemstack = itemstack.copy();
                itemstack.setCount(MathHelper.ceil((float)itemstack.getCount() / 2.0F));
            }
            else if (this.dragSplitting && this.dragSplittingSlots.size() > 1)
            {
                itemstack = itemstack.copy();
                itemstack.setCount(guiCHelper.dragSplittingRemnant);

                if (itemstack.isEmpty())
                {
                    s = "" + TextFormatting.YELLOW + "0";
                }
            }

            guiCHelper.drawItemStack(itemstack, mouseX - i -8, mouseY - j - k2, s);
        }

        if (!guiCHelper.returningStack.isEmpty())
        {
            float f = (float)(Minecraft.getSystemTime() - guiCHelper.returningStackTime) / 100.0F;

            if (f >= 1.0F)
            {
                f = 1.0F;
                guiCHelper.returningStack = ItemStack.EMPTY;
            }

            int l2 = guiCHelper.returningStackDestSlot.xPos - guiCHelper.touchUpX;
            int i3 = guiCHelper.returningStackDestSlot.yPos - guiCHelper.touchUpY;
            int l1 = guiCHelper.touchUpX + (int)((float)l2 * f);
            int i2 = guiCHelper.touchUpY + (int)((float)i3 * f);
            guiCHelper.drawItemStack(guiCHelper.returningStack, l1, i2, (String)null);
        }

        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
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
