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
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class AstralSilverToolEffect extends BaseMetallurgyEffect {

    private final EventHandler<PlayerEvent.BreakSpeed> INCREASE_BREAK_SPEED = new EventHandler<>(this::handleBreakSpeed, PlayerEvent.BreakSpeed.class);

    public AstralSilverToolEffect() {
        super(ModMetals.ASTRAL_SILVER);
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{INCREASE_BREAK_SPEED};
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.TOOL;
    }

    private void handleBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntityPlayer().dimension != 0) {
            event.setNewSpeed(event.getOriginalSpeed() * 2);
        }
    }
}
