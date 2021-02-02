/*==============================================================================
 = Class: DamascusSteelArmorEffect
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DamascusSteelArmorEffect extends BaseMetallurgyEffect {

    public DamascusSteelArmorEffect()
    {
        super(ModMetals.DAMASCUS_STEEL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void spawnPier(LivingDamageEvent event)
    {
        float level = getLevel(event.getEntityLiving());

        if (level == 0)
            return;

        // TODO: 02/02/2021 finish this
        //event.getEntityLiving().getEntityData()

    }

}
