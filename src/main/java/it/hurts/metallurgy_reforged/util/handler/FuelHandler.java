/*
 * -------------------------------------------------------------------------------------------------------
 * Class: FuelHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util.handler;

import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FuelHandler {

    @SubscribeEvent
    public void registerFuels(FurnaceFuelBurnTimeEvent event)
    {
        ItemStack item = event.getItemStack();

        if (item.getItem().equals(ModItems.tar))
            event.setBurnTime(800);
    }

}
