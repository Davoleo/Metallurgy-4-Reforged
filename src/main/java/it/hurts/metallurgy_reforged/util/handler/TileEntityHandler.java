/*
 * -------------------------------------------------------------------------------------------------------
 * Class: TileEntityHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Metallurgy.MODID + ":crusher"));
        GameRegistry.registerTileEntity(TileEntityAlloyer.class, new ResourceLocation(Metallurgy.MODID + ":alloyer"));
    }
}
