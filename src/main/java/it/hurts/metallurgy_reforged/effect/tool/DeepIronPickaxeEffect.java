/*==============================================================================
 = Class: DeepIronPickaxeEffect
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


public class DeepIronPickaxeEffect extends BaseMetallurgyEffect {

    public DeepIronPickaxeEffect() {
        super(ModMetals.DEEP_IRON);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.TOOL;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[0];
    }

    public void playerBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntityPlayer().isWet())
            event.setNewSpeed(event.getOriginalSpeed() * 3);
    }

}
