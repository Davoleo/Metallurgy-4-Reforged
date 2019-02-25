package it.hurts.metallurgy_reforged.util.handler;

import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 12/02/2019 / 21:53
 * Class: FuelHandler
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class FuelHandler {

    @SubscribeEvent
    public void registerFuels(FurnaceFuelBurnTimeEvent event)
    {
        ItemStack item = event.getItemStack();

        if (item.getItem().equals(ModItems.tar))
            event.setBurnTime(800);
    }

}
