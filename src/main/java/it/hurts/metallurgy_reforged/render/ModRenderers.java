/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModRenderers
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.render;

import it.hurts.metallurgy_reforged.tileentity.TileEntityChamber;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModRenderers {

    @SideOnly(Side.CLIENT)
    public static void registerRenderers() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChamber.class, new SublimationChamberTESR());

    }

}
