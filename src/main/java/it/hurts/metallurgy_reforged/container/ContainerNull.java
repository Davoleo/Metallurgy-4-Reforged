package it.hurts.metallurgy_reforged.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 26/12/2018 / 17:56
 * Class: ContainerNull
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ContainerNull extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        return null;
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack stack) {
    }
}
