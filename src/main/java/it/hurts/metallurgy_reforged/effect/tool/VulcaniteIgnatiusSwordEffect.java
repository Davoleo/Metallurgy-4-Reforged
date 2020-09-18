/*
 * -------------------------------------------------------------------------------------------------------
 * Class: VulcaniteIgnatiusSwordEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class VulcaniteIgnatiusSwordEffect extends BaseMetallurgyEffect
{

    public VulcaniteIgnatiusSwordEffect(Metal metal)
    {
        super(metal);
    }

    @Override
    public boolean isEnabled()
    {
        if (!super.isEnabled())
            return false;

        return metal == ModMetals.IGNATIUS ? ToolEffectsConfig.ignatiusSwordEffect : ToolEffectsConfig.vulcaniteSwordEffect;
    }

    @Override
    public boolean isToolEffect()
    {
        return true;
    }

    @Nullable
    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.SWORD;
    }

    @Override
    public void onPlayerAttack(EntityPlayer attacker, Entity target)
    {
        Item sword = attacker.getHeldItemMainhand().getItem();

        if(sword == ModMetals.IGNATIUS.getTool(EnumTools.SWORD) || sword == ModMetals.VULCANITE.getTool(EnumTools.SWORD))
        {

            if(target instanceof EntityLivingBase)
            {
                EntityLivingBase livingTarget = ((EntityLivingBase) target);
                if(metal == ModMetals.IGNATIUS)
                {
                    if(Math.random() * 100 < 60)
                    {
                        livingTarget.setFire(5);
                    }
                }
                else
                {

                    World world = attacker.world;

                    double y = attacker.posY + (double) attacker.getEyeHeight() + attacker.getLookVec().scale(attacker.getDistance(target)).y;
                    Vec3d hitPos = new Vec3d(target.posX, y, target.posZ);
                    AxisAlignedBB fireBox = new AxisAlignedBB(hitPos, hitPos).grow(target.width* 1.5D, 0.5D, target.width * 1.5D);
                    for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(attacker, fireBox))
                        entity.setFire(3);

                    livingTarget.setFire(5);

                    if(world.isRemote)
                    {
                        for (int i = 0; i < 40; i++)
                        {
                            double range = target.width * 0.3D;
                            double x = (range / 2D) - Math.random() * range;
                            double z = (range / 2D) - Math.random() * range;
                            world.spawnParticle(EnumParticleTypes.FLAME, hitPos.x, hitPos.y, hitPos.z, x, 0, z);

                        }
                    }
                    else
                    {
                        world.playSound(null, hitPos.x, hitPos.y, hitPos.z, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1F, 0.8F);

                    }

                }
            }
        }
    }

}
