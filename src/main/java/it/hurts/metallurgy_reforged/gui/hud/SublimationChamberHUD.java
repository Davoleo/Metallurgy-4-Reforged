/*
 * -------------------------------------------------------------------------------------------------------
 * Class: SublimationChamberHUD
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.gui.hud;

import it.hurts.metallurgy_reforged.recipe.BlockSublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.Map;

public class SublimationChamberHUD {

    public static void render(RenderGameOverlayEvent.Post event, Minecraft minecraft, IBlockState state, BlockPos pos) {

        World world = minecraft.world;

        if (!(world.getTileEntity(pos) instanceof TileEntityChamber))
            return;

        ScaledResolution resolution = event.getResolution();
        int x = resolution.getScaledWidth() / 2;
        int y = resolution.getScaledHeight() / 2;

        TileEntityChamber te = ((TileEntityChamber) world.getTileEntity(pos));
        Map<ItemStack, PotionEffect> recipes = BlockSublimationRecipes.getInstance().recipesMap();

        ItemStack metal = te.getStackInSlot(TileEntityChamber.METAL_SLOT);

        if (!metal.isEmpty()) {
            RenderHelper.enableStandardItemLighting();
            minecraft.getRenderItem().renderItemIntoGUI(metal, x - 40, y);
            RenderHelper.disableStandardItemLighting();
        }

        if (te.potionEffect != null)
        {
            minecraft.fontRenderer.drawStringWithShadow((te.activeTime / 20) + " seconds", x, y - 40, 0xFFFFFF);

            PotionEffect effect = recipes.get(metal);
            int effectIndex = effect.getPotion().getStatusIconIndex();

            GlStateManager.pushMatrix();

            minecraft.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
            drawTexturedModalRect(x + 40, y, effectIndex % 8 * 18, 198 + effectIndex / 8 * 18, 18, 18);

            GlStateManager.popMatrix();
        }
    }

    public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((x), (y + height), 1D).tex(((float)(textureX) * 0.00390625F), ((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x + width), (y + height), 1D).tex(((float)(textureX + width) * 0.00390625F), ((float)(textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x + width), (y), 1D).tex(((float)(textureX + width) * 0.00390625F), ((float)(textureY) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x), (y), 1D).tex(((float)(textureX) * 0.00390625F), ((float)(textureY) * 0.00390625F)).endVertex();
        tessellator.draw();
    }

}
