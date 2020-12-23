/*==============================================================================
 = Class: ItemExtra
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import net.minecraft.creativetab.CreativeTabs;

public class ItemExtra extends ItemBase {

    public ItemExtra(String name, CreativeTabs tab, String modelSubDir)
    {
        super(name, tab, modelSubDir);
        ModItems.extraItems.add(this);
    }

    public ItemExtra(String name, CreativeTabs tab)
    {
        this(name, tab, "");
    }

}
