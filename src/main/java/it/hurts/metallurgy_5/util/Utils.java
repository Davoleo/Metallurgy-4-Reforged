package it.hurts.metallurgy_5.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/*************************************************
 * Author: Davoleo
 * Date: 29/09/2018
 * Hour: 23.39
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class Utils {

    public static void editInventoryStackSize(NonNullList<ItemStack> inventory, int slot, int amount)
    {
        if(slot >= 0 && slot < inventory.size() && !inventory.get(slot).isEmpty())
        {
            inventory.get(slot).grow(amount);
            if(inventory.get(slot).getCount() <= 0)
                inventory.set(slot, ItemStack.EMPTY);
        }
    }

}
