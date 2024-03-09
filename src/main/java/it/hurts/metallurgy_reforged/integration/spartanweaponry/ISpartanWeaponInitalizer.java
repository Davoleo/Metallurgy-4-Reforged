/*==============================================================================
 = Class: SpartanWeaponInitalizer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.spartanweaponry;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@FunctionalInterface
public interface ISpartanWeaponInitalizer {

    Item create(ToolMaterialEx toolMaterialEx, String modid, CreativeTabs creativeTab, WeaponProperty... properties);

}
