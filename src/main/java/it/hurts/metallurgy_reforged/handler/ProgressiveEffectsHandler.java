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
        if (event.phase != TickEvent.Phase.START)
            return;

        PlayerEffectData data = event.player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

        MetallurgyEffects.effects.forEach(effect -> {

            if (effect instanceof IProgressiveEffect)
            {
                String key = effect.getMetal().toString() + '_' + effect.getCategory();

                ProgressiveDataBundle bundle = data.effectBundles.get(key);

                if (bundle != null && bundle.isEffectInProgress())
                {
                    if (event.player.world.getTotalWorldTime() >= bundle.getPrevStepTime() + ((long) bundle.STEP_TICK_DELAY * bundle.getCurrentStep()))
                    {
                        //Metallurgy.logger.info(bundle.getPrefixKey() + ": Current Step " + bundle.getCurrentStep());
                        ((IProgressiveEffect) effect).onStep(event.player.world, event.player, bundle.getMaxSteps(), bundle.getCurrentStep());

                        //Check if the effect was reset on the last step call to avoid looping and restarting the effect when not needed
                        if (bundle.isEffectInProgress())
                            bundle.incrementStep(null); //Step synchronization should not happen unless it's the kickstart step (if nonnull things break)
                    }
                }
            }
        });
    }
}
