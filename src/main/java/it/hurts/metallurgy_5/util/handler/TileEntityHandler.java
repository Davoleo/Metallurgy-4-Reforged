package it.hurts.metallurgy_5.util.handler;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.tileentity.TileEntityCrusher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*************************************************
 * Author: Davoleo
 * Date: 11/09/2018
 * Hour: 18.54
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TileEntityHandler {

    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, new ResourceLocation(Metallurgy_5.MODID + ":crusher"));
    }
}
