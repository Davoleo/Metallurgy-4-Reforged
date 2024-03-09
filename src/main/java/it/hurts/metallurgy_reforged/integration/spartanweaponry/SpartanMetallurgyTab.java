/*==============================================================================
 = Class: SpartanMetallurgyTab
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2024.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.spartanweaponry;

import it.hurts.metallurgy_reforged.integration.IntegrationSW;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SpartanMetallurgyTab extends MetallurgyTabs {

    public SpartanMetallurgyTab() {
        super(9, IntegrationSW.MODID);
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(IntegrationSW.spartanMetals.get(0).getWeapon(SpartanWeaponType.KATANA));
    }
}
