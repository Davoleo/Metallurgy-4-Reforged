/*==============================================================================
 = Class: ProgressiveEffectsHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract class ProgressiveEffectsHandler {

    @SubscribeEvent
    public static void onLivingUpdate(TickEvent.PlayerTickEvent event)
    {

        if (event.phase == TickEvent.PlayerTickEvent.Phase.START && event.player.ticksExisted % 10 == 0)
        {
            PlayerEffectData data = event.player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

            MetallurgyEffects.effects.forEach(effect -> {
                if (effect instanceof IProgressiveEffect)
                {
                    String key = effect.getMetal().toString() + '_' + effect.getCategory().toString();

                    ProgressiveDataBundle bundle = data.effectBundles.get(key);
                    if (bundle != null && bundle.isEffectInProgress())
                    {
                        ((IProgressiveEffect) effect).onStep(event.player.world, bundle.getPos(), bundle.getState(), bundle.getMaxSteps(), bundle.getCurrentStep());
                        bundle.incrementStep();
                    }
                }
            });
        }
    }
}
