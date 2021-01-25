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
import it.hurts.metallurgy_reforged.util.Utils;
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
        if (getLevel(event.getEntityLiving()) == 1)
            return;

        EntityLivingBase armored = event.getEntityLiving();

        if (armored.ticksExisted % 20 != 0)
            return;

        BlockPos pos = armored.getPosition();
        AxisAlignedBB box = new AxisAlignedBB(pos.getX() - RADIUS, pos.getY() - (RADIUS / 2D), pos.getZ() - RADIUS,
                pos.getX() + RADIUS, pos.getY() + (RADIUS / 2D), pos.getZ() + RADIUS);

        armored.world.getEntitiesWithinAABB(EntityLivingBase.class, box)
                .forEach(entity -> {
                    if (!(entity instanceof EntityPlayer))
                    {
                        double distance = entity.getPosition().getDistance(pos.getX(), pos.getY(), pos.getZ());
                        if (distance < 3)
                        {
                            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 3));
                            entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 40, 3));
                        }
                        else if (distance < 5)
                        {
                            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 2));
                            entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 40, 2));
                        }
                        else if (distance < 8)
                        {
                            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 1));
                            entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 40, 1));
                        }
                        else
                        {
                            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 0));
                            entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 40, 0));
                        }

                        Utils.repeat(8, () -> spawnParticle(entity, 2, 5));
                    }

                    //Every second 50% chance to extinguish an entity that is on fire
                    if ((entity.getLastDamageSource() == DamageSource.IN_FIRE || entity.getLastDamageSource() == DamageSource.ON_FIRE) && armored.getRNG().nextBoolean())
                    {
                        entity.extinguish();
                        armored.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.AMBIENT, 1, 1);
                    }
                });


    }

}
