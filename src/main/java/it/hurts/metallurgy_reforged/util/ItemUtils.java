/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemUtils {

    public static void initItem(Item item, String name, CreativeTabs tab)
    {
        item.setTranslationKey(Metallurgy.MODID + "." + name);
        item.setRegistryName(Metallurgy.MODID, name);
        item.setCreativeTab(tab);
        ModItems.itemList.add(item);
    }

}
