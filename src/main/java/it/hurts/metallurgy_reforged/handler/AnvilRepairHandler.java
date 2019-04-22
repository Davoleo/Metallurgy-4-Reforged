/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AnvilRepairHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilRepairHandler {

    //TODO Optimize together with tool refactor
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event)
    {
        if (event.getLeft().getItem() instanceof ItemTool)
        {
            for (Metal metal : ModMetals.metalList)
            {
                if (metal.hasToolSet())
                {
                    ItemTool tool = (ItemTool) event.getLeft().getItem();
                    if (event.getRight().getItem() == metal.getIngot() && (metal.toString().equals(tool.getToolMaterialName().toLowerCase())))
                    {
                    }
                }
            }
        }
    }

}
