package it.hurts.metallurgy_reforged.util;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.FakePlayer;

/*************************************************
 * Author: Davoleo
 * Date: 29/09/2018
 * Hour: 23.39
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class Utils {

    @SuppressWarnings("unused")
    public static void editInventoryStackSize(NonNullList<ItemStack> inventory, int slot, int amount)
    {
        if(slot >= 0 && slot < inventory.size() && !inventory.get(slot).isEmpty())
        {
            inventory.get(slot).grow(amount);
            if(inventory.get(slot).getCount() <= 0)
                inventory.set(slot, ItemStack.EMPTY);
        }
    }

    public static void giveExperience(EntityPlayer thePlayer, float experience) {
        int intExp = (int) experience;
        float fractional = experience - intExp;
        if (fractional > 0.0F) {
            if ((float) Math.random() < fractional) {
                intExp++;
            }
        }
        while (intExp > 0) {
            int j = EntityXPOrb.getXPSplit(intExp);
            intExp -= j;
            thePlayer.world.spawnEntity(new EntityXPOrb(thePlayer.world, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
        }

    }

    public static String localize(String unlocalized)
    {
        return new TextComponentTranslation(unlocalized).getFormattedText();
    }

    public static boolean isFakePlayer(EntityPlayer player) {
        return player instanceof FakePlayer || (player != null);
    }
}
