/*==============================================================================
 = Class: CeruclaseArmorEffect
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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CeruclaseArmorEffect extends BaseMetallurgyEffect {

    private final int RADIUS = 10;

    public CeruclaseArmorEffect()
    {
        super(ModMetals.CERUCLASE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void extinguishAndSlow(LivingEvent.LivingUpdateEvent event)
    {
        if (getLevel(event.getEntityLiving()) != 1)
            return;

        EntityLivingBase armored = event.getEntityLiving();

        BlockPos pos = armored.getPosition();
        AxisAlignedBB box = new AxisAlignedBB(pos.getX() - RADIUS, pos.getY() - (RADIUS / 2D), pos.getZ() - RADIUS,
                pos.getX() + RADIUS, pos.getY() + (RADIUS / 2D), pos.getZ() + RADIUS);

        armored.world.getEntitiesWithinAABB(EntityLivingBase.class, box,
                entity -> armored instanceof EntityPlayer ? entity instanceof EntityLiving : entity instanceof EntityPlayer)
                .forEach(entity -> {
                    double distance = entity.getPosition().getDistance(pos.getX(), pos.getY(), pos.getZ());

                    //We're going to check in a circular radius instead of a square
                    if (distance <= RADIUS)
                    {
                        // MATH > MathHelper
                        int amplifier = (int) Math.round((10 - distance) / 4);
                        entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, amplifier));
                        entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 10, amplifier));
                    }

                    spawnParticle(entity, 2, 5);

                    //Every second 50% chance to extinguish an entity that is on fire
                    if ((entity.getLastDamageSource() == DamageSource.IN_FIRE || entity.getLastDamageSource() == DamageSource.ON_FIRE) && armored.getRNG().nextBoolean())
                    {
                        entity.extinguish();
                        armored.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT, 1, 1);
                    }
                });


    }

}
