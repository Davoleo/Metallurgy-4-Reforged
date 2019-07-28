/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GauntletEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget.gauntlet;

import it.hurts.metallurgy_reforged.capabilities.punch.IPunchEffect;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectProvider;
import it.hurts.metallurgy_reforged.config.GauntletConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

public class GauntletEffect
{

    @SubscribeEvent
    public static void addPunchEffect(AttackEntityEvent event)
    {
        EntityPlayer pl = event.getEntityPlayer();
        Entity entity = event.getTarget();
        if(entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            if(GauntletOperation.isWearingGauntlet(pl) && entityLivingBase.deathTime <= 0)
            {
                FoodStats foodStats = pl.getFoodStats();

                int doubleFoodModifier = GauntletConfig.gauntletHungerModifier * 2;


                if(foodStats.getFoodLevel() >= doubleFoodModifier || pl.isCreative())
                {

                    IPunchEffect effect = entityLivingBase.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
                    if(effect != null && (effect.getHitTicks() <= 0 || effect.getDelayHit() > 0))
                    {
                        effect.setDelayHit(5);
                        effect.setPunchingPlayer(pl);
                        effect.setRotPitchPlayer(pl.rotationPitch);
                        effect.setRotYawPlayer(pl.getRotationYawHead());


                        if(entityLivingBase instanceof EntityLiving)
                        {
                            EntityLiving livingEntity = (EntityLiving) entityLivingBase;
                            if(!livingEntity.isAIDisabled())
                            {
                                effect.setNoAI(livingEntity.isAIDisabled());
                                livingEntity.setNoAI(true);
                            }
                        }
                    }
                    if(!pl.isCreative() && !pl.world.isRemote)
                    {
                        int foodDifference = doubleFoodModifier - Math.round(foodStats.getSaturationLevel());

                        if(foodStats.getSaturationLevel() <= 0.0F)
                        {
                            foodStats.addStats(-doubleFoodModifier,0F);
                        }
                        else if(foodDifference > 0.0F)
                        {

                            foodStats.addStats(1, -foodStats.getSaturationLevel());
                            foodStats.addStats(-foodDifference,0F);
                        }
                        else
                        {
                            int foodLevel = foodStats.getFoodLevel();
                            foodStats.addStats(1, -GauntletConfig.gauntletHungerModifier);
                            foodStats.setFoodLevel(foodLevel);
                        }
                    }


                    Random rand = new Random();

                    for (int i = 0; i < 20; ++i)
                        entity.world.spawnParticle(EnumParticleTypes.CRIT, entity.posX + (rand.nextDouble() - 0.5D) * ((double) entity.width * 1.5D), entity.posY + rand.nextDouble() * ((double) entity.height * 1.5D), entity.posZ + (rand.nextDouble() - 0.5D) * ((double) entity.width * 1.5D), 0.0D, 0.0D, 0.0D);
                } else
                    pl.sendStatusMessage(new TextComponentTranslation("effect.metallurgy.punch_effect_tired"), true);

            }

        }
    }


    //	Event tick entity
    @SubscribeEvent
    public static void applyPunchThrowEffects(LivingUpdateEvent event)
    {

        EntityLivingBase entity = event.getEntityLiving();
        IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);

        if(effect != null)
        {
            if(effect.getDelayHit() > 0)
            {
                effect.addHitTicks();
                effect.setDelayHit(effect.getDelayHit() - 1);
                entity.motionX = 0D;
                entity.motionY = 0D;
                entity.motionZ = 0D;
                entity.hurtResistantTime = 11;

            } else if(effect.getHitTicks() > 0 && entity.deathTime <= 0)
            {
                EntityPlayer pl = effect.getPunchingPlayer(entity.world);
                if(entity instanceof EntityLiving)
                    ((EntityLiving) entity).setNoAI(effect.isAIDisabled());

                if(pl != null)
                    throwEntity(entity);
                effect.setPunchingPlayer(null);
            }

            //		check if entity has been punched
            if(effect.getKnockbackTicks() > 0)
            {
                entity.motionX = effect.getKnockbackMotionVec().x;
                entity.motionY = effect.getKnockbackMotionVec().y;
                entity.motionZ = effect.getKnockbackMotionVec().z;

                Random rand = new Random();

                if(effect.getHitTicks() > 10)
                {
                    for (int i = 0; i < 20; ++i)
                        entity.world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX + (rand.nextDouble() - 0.5D) * ((double) entity.width * 1.5D), entity.posY + rand.nextDouble() * ((double) entity.height * 1.5D), entity.posZ + (rand.nextDouble() - 0.5D) * ((double) entity.width * 1.5D), 0.0D, 0.0D, 0.0D);

                    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.4D, 0.4D, 0.4D);
                    if(!entity.isDead && !GauntletConfig.disableBlockGriefing)
                    {
                        //				destroy blocks and damage the punched entity (the damage is based from block's hardness
                        for (double i = axisalignedbb.minX; i < axisalignedbb.maxX; i += 0.1D)
                        {
                            for (double j = axisalignedbb.minY; j < axisalignedbb.maxY; j += 0.1D)
                            {
                                for (double k = axisalignedbb.minZ; k < axisalignedbb.maxZ; k += 0.1D)
                                {

                                    BlockPos pos = new BlockPos(i, j, k);
                                    if(!entity.world.isAirBlock(pos))
                                    {
                                        IBlockState state = entity.world.getBlockState(pos);
                                        float hardness = state.getBlockHardness(entity.world, pos);
                                        if(hardness >= 0 && (!state.getMaterial().isLiquid()))
                                        {
                                            if(!entity.world.isRemote)
                                                entity.world.destroyBlock(pos, true);
                                            entity.hurtResistantTime = 4;
                                            if(GauntletConfig.gauntletBlockDamageModifier != 0)
                                                entity.attackEntityFrom(DamageSource.causeMobDamage(entity.getLastAttackedEntity()), (float) GauntletConfig.gauntletBlockDamageModifier);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                //			adds the punch effect ticks
                effect.addKnockbackTTicks();
                //	if the knockback ticks reaches his limit,the entity will lose his "effect"
                if(effect.getKnockbackTicks() > effect.getHitTicks() / 4)
                    effect.endEffect(entity);

            }
        }
    }

    //		checks if the players isn't holding an item and if he is wearing
    //		apply effect if the player has a minimum food level or if he is in creative
    private static void throwEntity(EntityLivingBase entity)
    {
        IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
        effect.setKnockbackTicks(1);
    }

}
