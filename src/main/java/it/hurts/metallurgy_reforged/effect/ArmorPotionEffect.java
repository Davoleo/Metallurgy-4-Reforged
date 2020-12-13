/*==============================================================================
 = Class: ArmorPotionEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ArmorPotionEffect extends BaseMetallurgyEffect {

	private final Potion potion;
	private int amplifier;

	public ArmorPotionEffect(Metal metal, Potion potion, int amplifier) {
		super(metal);
        this.potion = potion;
        this.amplifier = amplifier;
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
            boolean refreshEffect = event.getEntityLiving().world.getTotalWorldTime() % 40 == 0;

            if (EventUtils.isEntityWearingArmor(event.getEntityLiving(), metal) && refreshEffect)
                event.getEntityLiving().addPotionEffect(new PotionEffect(potion, 60, amplifier, false, false));
        }
	}

}
