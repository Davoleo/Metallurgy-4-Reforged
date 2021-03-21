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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class ProgressiveEffectsHandler {

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (!event.getEntity().world.isRemote && event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().ticksExisted % 10 == 0)
        {
            PlayerEffectData data = event.getEntityLiving().getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);

            MetallurgyEffects.effects.forEach(effect -> {
                if (effect instanceof IProgressiveEffect)
                {

                    String key = effect.getMetal().toString() + '_' + effect.getCategory().toString();

                    ProgressiveDataBundle bundle = data.effectBundles.get(key);
                    if (bundle != null && bundle.isEffectInProgress())
                    {
                        ((IProgressiveEffect) effect).onStep(event.getEntityLiving().world, ((EntityPlayer) event.getEntityLiving()), bundle.getMaxSteps(), bundle.getCurrentStep());

                        //Check if the effect was reset on the last step call to avoid looping and restarting the effect when not needed
                        if (bundle.isEffectInProgress())
                            bundle.incrementStep(((EntityPlayer) event.getEntityLiving()));
                    }
                }
            });
        }
    }
}
