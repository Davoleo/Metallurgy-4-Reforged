/*
 * -------------------------------------------------------------------------------------------------------
 * Class: HudHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.gui.hud;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudHandler {

    @SubscribeEvent
    public static void renderOverlay(RenderGameOverlayEvent.Post event) {

        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
            return;

        Minecraft minecraft = Minecraft.getMinecraft();
        if (minecraft.currentScreen instanceof GuiChat)
            return;

        RayTraceResult rayTrace = minecraft.objectMouseOver;
        if (rayTrace == null || rayTrace.typeOfHit != RayTraceResult.Type.BLOCK)
            return;

        IBlockState state = minecraft.world.getBlockState(rayTrace.getBlockPos());
        Block block = state.getBlock();

        if (block == ModBlocks.chamber)
            SublimationChamberHUD.render(event, minecraft, state, rayTrace.getBlockPos());

    }

}
