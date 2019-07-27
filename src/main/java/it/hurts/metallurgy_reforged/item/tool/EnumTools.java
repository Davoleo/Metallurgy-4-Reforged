/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EnumTools
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

public enum EnumTools {
    AXE("axe", ItemAxeBase.class),
    HOE("hoe", ItemHoeBase.class),
    PICKAXE("pickaxe", ItemPickaxeBase.class),
    SHOVEL("shovel", ItemShovelBase.class),
    SWORD("sword", ItemSwordBase.class);

    private String name;
    private Class<?> toolClass;

    EnumTools(String name, Class<?> toolClass)
    {
        this.name = name;
        this.toolClass = toolClass;
    }

    public String getName()
    {
        return name;
    }

    public Class<?> getToolClass()
    {
        return toolClass;
    }
}
