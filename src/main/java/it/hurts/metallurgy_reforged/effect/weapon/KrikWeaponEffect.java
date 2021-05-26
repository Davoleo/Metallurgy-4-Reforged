/*==============================================================================
 = Class: KrikWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class KrikWeaponEffect extends BaseMetallurgyEffect {

    public KrikWeaponEffect()
    {
        super(ModMetals.KRIK);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void applyDoubleDamageAndLevitation(LivingHurtEvent event)
    {
        if (event.getSource().getImmediateSource() instanceof EntityLivingBase)
        {
            EntityLivingBase attacker = ((EntityLivingBase) event.getSource().getTrueSource());

            if (!canBeApplied(attacker))
                return;

            if (!event.getEntityLiving().onGround)
            {
                event.setAmount(event.getAmount() * 2);
                for (int i = 0; i < 8; i++)
                    spawnParticle(event.getEntityLiving(), 2.5F, false, 4);
            }

            event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 25));
        }
    }
}
