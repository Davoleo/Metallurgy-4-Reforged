/*==============================================================================
 = Class: IgnatiusArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class IgnatiusArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public IgnatiusArmorEffect()
    {
        super(ModMetals.IGNATIUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public void onStep(World world, EntityPlayer entity, int maxSteps, int step)
    {
        ProgressiveDataBundle bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).ignatiusArmorBundle;

        if (!entity.isInLava())
            bundle.resetProgress(entity);

        // TODO: 20/03/2021 Fix server/Client progress desync
        //currentStep >= (0.25 | 0.5 | 0.75 | 1) * 40
        int lavaImmunityTimespan = (int) (getLevel(entity) * maxSteps);
        if (step >= lavaImmunityTimespan)
        {
            bundle.resetProgress(entity);
            entity.getArmorInventoryList().forEach(
                    piece -> entity.getCooldownTracker().setCooldown(piece.getItem(), 200)
            );
        }

        System.out.println("Current Step: " + step);
    }

    @SubscribeEvent
    public void lavaBath(LivingAttackEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();

        if (!canBeApplied(entity))
            return;

        DamageSource source = event.getSource();
        ProgressiveDataBundle bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).ignatiusArmorBundle;

        if (!bundle.isEffectInProgress() && source == DamageSource.LAVA)
        {
            if (entity instanceof EntityPlayer)
            {
                ItemStack armorpiece = entity.getArmorInventoryList().iterator().next();
                if (((EntityPlayer) entity).getCooldownTracker().getCooldown(armorpiece.getItem(), 0) > 0)
                    return;
            }

            //Kickstart the timer
            bundle.incrementStep(entity instanceof EntityPlayer ? ((EntityPlayer) entity) : null);
            event.setCanceled(true);
        }

        if (source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE || (source == DamageSource.LAVA && bundle.isEffectInProgress()))
        {
            if (source != DamageSource.ON_FIRE)
                event.getEntityLiving().heal(event.getAmount());
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void dealWaterDamage(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        if (!canBeApplied(entity))
            return;

        //if fire resistance is not active and the entity is either under the rain or underwater deal damage
        if (entity.isWet() && !entity.isPotionActive(MobEffects.FIRE_RESISTANCE))
        {
            if (entity.ticksExisted % 10 == 0)
            {
                entity.attackEntityFrom(DamageSource.GENERIC, 1F);
                entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1F, false);
            }

            spawnParticle(entity, 5F, true, 5);
        }
    }

}