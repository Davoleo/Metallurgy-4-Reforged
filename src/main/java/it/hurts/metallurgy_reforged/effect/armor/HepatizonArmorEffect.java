/*==============================================================================
 = Class: HepatizonArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class HepatizonArmorEffect extends BaseMetallurgyEffect {

    public HepatizonArmorEffect()
    {
        super(ModMetals.HEPATIZON);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void decreasePlayerVisibility(PlayerEvent.Visibility event)
    {
        float level = getLevel(event.getEntityPlayer());

        if (level == 0)
            return;

        event.modifyVisibility(1 - (level / 1.25));
        //System.out.println(event.getVisibilityModifier());
    }
}
