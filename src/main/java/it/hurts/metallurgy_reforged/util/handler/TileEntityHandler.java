package it.hurts.metallurgy_reforged.util.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.tileentity.TileEntityAlloyer;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 11/09/2018
 * Hour: 18.54
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileEntityHandler {

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Metallurgy.MODID + ":crusher"));
        GameRegistry.registerTileEntity(TileEntityAlloyer.class, new ResourceLocation(Metallurgy.MODID + ":alloyer"));
    }
}
