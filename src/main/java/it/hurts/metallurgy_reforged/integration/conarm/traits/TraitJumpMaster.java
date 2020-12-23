/*==============================================================================
 = Class: TraitJumpMaster
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

public class TraitJumpMaster extends AbstractArmorTrait implements IConarmMetallurgyTrait {

    public TraitJumpMaster()
    {
        super("jump_master", TextFormatting.GRAY);
    }

    @SubscribeEvent
    public void onArmorTick(PlayerTickEvent event)
    {
        if (MetallurgyConArmorStats.hasValidArmorTrait(event.player, "jump_master"))
            event.player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 40, 1, false, false));
    }

}
