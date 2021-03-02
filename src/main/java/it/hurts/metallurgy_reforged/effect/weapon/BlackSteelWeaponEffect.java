/*==============================================================================
 = Class: BlackSteelWeaponEffect
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
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class BlackSteelWeaponEffect extends BaseMetallurgyEffect {

    public BlackSteelWeaponEffect()
    {
        super(ModMetals.BLACK_STEEL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void onEntityHurtEvent(LivingHurtEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        if (!canBeApplied(entity))
            return;

        float absorbedDamage = event.getAmount() * 0.5F;
        entity.getHeldItemMainhand().damageItem((int) (absorbedDamage * 10), entity);
        event.setAmount(event.getAmount() - absorbedDamage);

        if (!entity.world.isRemote)
        {
            entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.7F, 0.7F);
            for (int i = 0; i < 10; i++)
                spawnParticle(entity, 3F, true, 9);
        }
    }
}