/*==============================================================================
 = Class: PrometheumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.living.LivingEvent;

@Deprecated
public class PrometheumArmorEffect extends BaseMetallurgyEffect {

	public PrometheumArmorEffect() {
        super(ModMetals.PROMETHEUM);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[0];
    }

    public void livingEvent(LivingEvent event) {
        if (event instanceof LivingEvent.LivingUpdateEvent) {
            EntityLivingBase entity = event.getEntityLiving();

            if (EventUtils.isEntityWearingArmor(entity, metal) && entity.isPotionActive(MobEffects.POISON))
                entity.removePotionEffect(MobEffects.POISON);
        }
	}

}
