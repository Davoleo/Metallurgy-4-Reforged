/*==============================================================================
 = Class: FuelHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FuelHandler {

	@SubscribeEvent
	public static void registerFuels(FurnaceFuelBurnTimeEvent event)
	{
        ItemStack item = event.getItemStack();

        if (item.getItem().equals(ModItems.TAR))
            event.setBurnTime(800);
        if (item.getItem().equals(Item.getItemFromBlock(ModBlocks.blockCharcoal)))
            event.setBurnTime(16000);
        if (item.getItem().equals(ModItems.THERMITE_DUST))
            event.setBurnTime(GeneralConfig.thermiteFuelValue * 200);
    }

}
