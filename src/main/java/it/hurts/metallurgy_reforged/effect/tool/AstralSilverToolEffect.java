/*==============================================================================
 = Class: AstralSilverToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class AstralSilverToolEffect extends BaseMetallurgyEffect {

    public AstralSilverToolEffect() {
        super(ModMetals.ASTRAL_SILVER);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent
    public void handleBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!canBeApplied(event.getEntityPlayer()))
            return;

        if (event.getEntityPlayer().dimension != 0 && event.getEntityPlayer().inventory.getDestroySpeed(event.getState()) > 1) {
            event.setNewSpeed(event.getOriginalSpeed() * 2);
        }
    }
}
