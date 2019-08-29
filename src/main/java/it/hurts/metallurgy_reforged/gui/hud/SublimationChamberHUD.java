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

import it.hurts.metallurgy_reforged.block.BlockChamber;
import it.hurts.metallurgy_reforged.recipe.BlockSublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
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

        if (state.getValue(BlockChamber.ACTIVE))
        {
            minecraft.fontRenderer.drawStringWithShadow((te.activeTime / 20) + " seconds", x, y - 40, 0xFFFFFF);

            PotionEffect effect = recipes.get(metal);
            //effect.getPotion().getStatusIconIndex()       (?)
            // TODO: 29/08/2019 render potion effect icon
            //minecraft.fontRenderer.drawStringWithShadow(effect.getEffectName(), x + 40, y + 20, effect.getPotion().getLiquidColor());
        }








    }

}
