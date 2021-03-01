/*==============================================================================
 = Class: EximiteWeaponEffect
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class EximiteWeaponEffect extends BaseMetallurgyEffect {

    public EximiteWeaponEffect()
    {
        super(ModMetals.EXIMITE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void increaseAttackDamageInTheEnd(LivingHurtEvent event)
    {
        Entity attacker = event.getSource().getTrueSource();

        if (attacker instanceof EntityLivingBase && canBeApplied(((EntityLivingBase) attacker)))
        {
            if (event.getEntityLiving().dimension == 1)
            {
                //constant 150% damage boost
                event.setAmount(event.getAmount() * 1.5F);
                for (int i = 0; i < 15; i++)
                {
                    spawnParticle(event.getEntityLiving(), 2F, 6);
                }
            }
        }
    }

    @SubscribeEvent
    public void increaseLootInTheEnd(LivingDropsEvent event)
    {
        if (event.getEntityLiving().dimension == 1)
        {
            event.getDrops().forEach(item -> {
                //Drop count can increase of 0, 1 or 2 items
                item.getItem().setCount(item.getItem().getCount() + Utils.random.nextInt(3));
            });
        }
    }
}
