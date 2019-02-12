package it.hurts.metallurgy_reforged.gui;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.container.ContainerAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
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

public class GuiAlloyer extends GuiContainer{

    //GUI Texture location
    private static final ResourceLocation TEXTURES = new ResourceLocation(Metallurgy.MODID + ":textures/gui/alloyer.png");
    private final InventoryPlayer player; //Player Inventory
    private final TileEntityAlloyer alloyer;//Tile Entity

    private Slot hoveredSlot;
    private int xSize = 175;
    private int ySize = 196;
    private ItemStack draggedStack = ItemStack.EMPTY;
    private int touchUpX;
    private int touchUpY;
    private Slot returningStackDestSlot;
    private long returningStackTime;
    private boolean isRightMouseClick;
    private int dragSplittingRemnant;
    private ItemStack returningStack = ItemStack.EMPTY;
    private int dragSplittingLimit;
    private Slot clickedSlot;


    //    Creiamo il costruttore
    public GuiAlloyer(InventoryPlayer player, TileEntityAlloyer alloyer){
        super(new ContainerAlloyer(player, alloyer));
        this.player = player;
        this.alloyer = alloyer;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

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
                this.drawSlot(slot);

            }
            if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
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

                this.drawSlot(slot);
            }

            if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
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
                this.drawSlot(slot);
            }

            if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled())
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
        ItemStack itemstack = this.draggedStack.isEmpty() ? inventoryplayer.getItemStack() : this.draggedStack;

        if (!itemstack.isEmpty())
        {
            int j2 = 8;
            int k2 = this.draggedStack.isEmpty() ? 8 : 16;
            String s = null;

            if (!this.draggedStack.isEmpty() && this.isRightMouseClick)
            {
                itemstack = itemstack.copy();
                itemstack.setCount(MathHelper.ceil((float)itemstack.getCount() / 2.0F));
            }
            else if (this.dragSplitting && this.dragSplittingSlots.size() > 1)
            {
                itemstack = itemstack.copy();
                itemstack.setCount(this.dragSplittingRemnant);

                if (itemstack.isEmpty())
                {
                    s = "" + TextFormatting.YELLOW + "0";
                }
            }

            this.drawItemStack(itemstack, mouseX - i -8, mouseY - j - k2, s);
        }

        if (!this.returningStack.isEmpty())
        {
            float f = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;

            if (f >= 1.0F)
            {
                f = 1.0F;
                this.returningStack = ItemStack.EMPTY;
            }

            int l2 = this.returningStackDestSlot.xPos - this.touchUpX;
            int i3 = this.returningStackDestSlot.yPos - this.touchUpY;
            int l1 = this.touchUpX + (int)((float)l2 * f);
            int i2 = this.touchUpY + (int)((float)i3 * f);
            this.drawItemStack(this.returningStack, l1, i2, (String)null);
        }

        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
    }

    private boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY)
    {
        return this.isPointInRegion(slotIn.xPos, slotIn.yPos, 17, 17, mouseX, mouseY);
    }

    private void drawItemStack(ItemStack stack, int x, int y, String altText)
    {
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        this.itemRender.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRenderer;
        this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y - (this.draggedStack.isEmpty() ? 0 : 8), altText);
        this.zLevel = 0.0F;
        this.itemRender.zLevel = 0.0F;
    }

    private void drawSlot(Slot slotIn)
    {
        int i = slotIn.xPos;
        int j = slotIn.yPos;
        ItemStack itemstack = slotIn.getStack();
        boolean flag = false;
        boolean flag1 = slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && !this.isRightMouseClick;
        ItemStack itemstack1 = this.mc.player.inventory.getItemStack();
        String s = null;

        if (slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && this.isRightMouseClick && !itemstack.isEmpty())
        {
            itemstack = itemstack.copy();
            itemstack.setCount(itemstack.getCount() / 2);
        }
        else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && !itemstack1.isEmpty())
        {
            if (this.dragSplittingSlots.size() == 1)
            {
                return;
            }

            if (Container.canAddItemToSlot(slotIn, itemstack1, true) && this.inventorySlots.canDragIntoSlot(slotIn))
            {
                itemstack = itemstack1.copy();
                flag = true;
                Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack, slotIn.getStack().isEmpty() ? 0 : slotIn.getStack().getCount());
                int k = Math.min(itemstack.getMaxStackSize(), slotIn.getItemStackLimit(itemstack));

                if (itemstack.getCount() > k)
                {
                    s = TextFormatting.YELLOW.toString() + k;
                    itemstack.setCount(k);
                }
            }
            else
            {
                this.dragSplittingSlots.remove(slotIn);
                this.updateDragSplitting();
            }
        }

        this.zLevel = 100.0F;
        this.itemRender.zLevel = 100.0F;

        if (itemstack.isEmpty() && slotIn.isEnabled())
        {
            TextureAtlasSprite textureatlassprite = slotIn.getBackgroundSprite();

            if (textureatlassprite != null)
            {
                GlStateManager.disableLighting();
                this.drawTexturedModalRect(i, j, textureatlassprite, 17, 17);
                GlStateManager.enableLighting();
                flag1 = true;
            }
        }

        if (!flag1)
        {
            if (flag)
            {
                drawRect(i, j, i + 17, j + 17, -2130706433);
            }

            GlStateManager.enableDepth();
            this.itemRender.renderItemAndEffectIntoGUI(this.mc.player, itemstack, i, j);
            this.itemRender.renderItemOverlayIntoGUI(this.fontRenderer, itemstack, i, j, s);
        }

        this.itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }

    private void updateDragSplitting()
    {
        ItemStack itemstack = this.mc.player.inventory.getItemStack();

        if (!itemstack.isEmpty() && this.dragSplitting)
        {
            if (this.dragSplittingLimit == 2)
            {
                this.dragSplittingRemnant = itemstack.getMaxStackSize();
            }
            else
            {
                this.dragSplittingRemnant = itemstack.getCount();

                for (Slot slot : this.dragSplittingSlots)
                {
                    ItemStack itemstack1 = itemstack.copy();
                    ItemStack itemstack2 = slot.getStack();
                    int i = itemstack2.isEmpty() ? 0 : itemstack2.getCount();
                    Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack1, i);
                    int j = Math.min(itemstack1.getMaxStackSize(), slot.getItemStackLimit(itemstack1));

                    if (itemstack1.getCount() > j)
                    {
                        itemstack1.setCount(j);
                    }

                    this.dragSplittingRemnant -= itemstack1.getCount() - i;
                }
            }
        }
    }



    //    Definiamo la gui disegnandola e posizionando ogni inventario al proprio posto
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String name = this.alloyer.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(name, 10, -22, 16769280);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        //x & y: drawn texture coordinates | textureX & textureY: original texture coordinates | width & height: texture dimensions
        this.drawTexturedModalRect(this.guiLeft, this.guiTop - 31, 0, 0, 175, 196);




        if(TileEntityAlloyer.isBurning(alloyer))
        {
            int k = this.getBurnLeftScaled(17);
            //~~~~~~~~~~~~~~~~~~
            this.drawTexturedModalRect(this.guiLeft + 110, this.guiTop + 30 + 15 -k, 176, 77 - k, 17, k + 2);
        }

        int l = this.getAlloyingProgressScaled(33);
        if (l > 0)
            drawTexturedModalRect( this.guiLeft + 40, this.guiTop + 66 - l, 176, 32 - l, 7, l);


        int m = this.getAlloyingProgressScaled(70);
        drawTexturedModalRect(this.guiLeft + 52, this.guiTop +1, 176, 81, 10, 1 + m);
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