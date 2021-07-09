/*==============================================================================
 = Class: ItemMetal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.MetalStats;

public class ItemMetal extends ItemBase implements IMetalItem {

    private final ItemTypes type;
    private final MetalStats metal;

    public ItemMetal(MetalStats metal, ItemTypes type)
    {
        super(metal.getName() + "_" + type.getName(), type.getTab());
        this.type = type;
        this.metal = metal;
    }

    public ItemTypes getType()
    {
        return type;
    }

    public MetalStats getMetalStats()
    {
        return metal;
    }

}
