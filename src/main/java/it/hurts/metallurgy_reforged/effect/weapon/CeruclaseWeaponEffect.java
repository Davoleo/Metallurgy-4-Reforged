/*==============================================================================
 = Class: CeruclaseWeaponEffect
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

public class CeruclaseWeaponEffect extends BaseMetallurgyEffect {

    public CeruclaseWeaponEffect()
    {
        super(ModMetals.CERUCLASE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event)
    {

        if (event.getSource().getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase attacker = ((EntityLivingBase) event.getSource().getTrueSource());

            if (!canBeApplied(attacker))
                return;

            if (attacker.getRNG().nextInt(100) < 75)
            {
                event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1000));
            }
        }
    }
}
