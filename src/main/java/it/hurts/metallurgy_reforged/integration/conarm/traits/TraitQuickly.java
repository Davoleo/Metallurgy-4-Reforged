/*==============================================================================
 = Class: TraitQuickly
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyConArmorStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TraitQuickly extends AbstractArmorTrait implements IConarmMetallurgyTrait {

    public TraitQuickly()
    {
        super("quickly", TextFormatting.DARK_AQUA);
    }

    @SubscribeEvent
    public void increaseVelocity(LivingEntityUseItemEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = ((EntityPlayer) entity);
            if (MetallurgyConArmorStats.hasValidArmorTrait(player, "quickly"))
                MetallurgyEffects.CARMOT_ARMOR_EFFECT.apply(event, 1);
        }
    }


}
