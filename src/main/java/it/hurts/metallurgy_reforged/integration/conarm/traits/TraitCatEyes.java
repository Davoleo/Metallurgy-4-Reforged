/*==============================================================================
 = Class: TraitCatEyes
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyConArmorStats;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitCatEyes extends AbstractArmorTrait implements IConarmMetallurgyTrait {

    public TraitCatEyes()
    {
        super("cat_eyes", TextFormatting.GREEN);
    }

    @SubscribeEvent
    public void onArmorTick(PlayerTickEvent event)
    {
        if (MetallurgyConArmorStats.hasValidArmorTrait(event.player, "cat_eyes"))
        {
            event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 220, 0, false, false));
            event.player.addTag("cat_eyes");
        }

        if (event.player.isPotionActive(MobEffects.NIGHT_VISION) && !MetallurgyConArmorStats.hasValidArmorTrait(event.player, "cat_eyes"))
        {
            if (event.player.getTags().contains("cat_eyes") && event.player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= (11 * 20))
            {
                event.player.removePotionEffect(MobEffects.NIGHT_VISION);
            }
        }

    }

}
