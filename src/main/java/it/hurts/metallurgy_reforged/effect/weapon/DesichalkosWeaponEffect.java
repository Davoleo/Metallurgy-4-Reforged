/*==============================================================================
 = Class: DesichalkosWeaponEffect
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
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DesichalkosWeaponEffect extends BaseMetallurgyEffect {

    public DesichalkosWeaponEffect()
    {
        super(ModMetals.DESICHALKOS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }


    @SubscribeEvent
    public void buffDamage(LivingHurtEvent event)
    {

        if (event.getSource().getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase) event.getSource().getTrueSource();
            if (!canBeApplied(entity))
                return;

            EntityLivingBase e = event.getEntityLiving();

            float f1 = e.getHealth();
            e.getCombatTracker().trackDamage(event.getSource(), f1, event.getAmount());
            e.setHealth(f1 - event.getAmount()); // Forge: moved to fix MC-121048
            e.setAbsorptionAmount(e.getAbsorptionAmount() - event.getAmount());


            Utils.repeat(30, () -> spawnParticle(e, 2F, true, 9));

            event.setAmount(0.0F);

        }
    }
}
