/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AtlarusArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSpawnParticles;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class AtlarusArmorEffect extends BaseMetallurgyEffect
{
    public AtlarusArmorEffect()
    {
        super(ModMetals.ATLARUS);
    }

    @Override
    public boolean isEnabled()
    {
        return ArmorEffectsConfig.atlarusArmorEffect && super.isEnabled();
    }

    @Override
    public boolean isToolEffect()
    {
        return false;
    }

    @Nullable
    @Override
    public EnumTools getToolClass()
    {
        return null;
    }

    @Override
    public void onPlayerTick(EntityPlayer player)
    {
        World world = player.world;

        if(!player.isCreative() && EventUtils.isPlayerWearingArmor(player, metal) && player.fallDistance >= 4F)
        {
            AxisAlignedBB nearCollitions = player.getEntityBoundingBox().contract(0, 1.7D, 0).offset(0, -4D, 0);
            if(world.collidesWithAnyBlock(nearCollitions))
            {
                double motionX = 2D - Math.random() * 4D;
                double motionZ = 2D - Math.random() * 4D;

                if(!world.isRemote)
                {
                    player.motionX = motionX;
                    player.motionZ = motionZ;
                    player.velocityChanged = true;

                    if(world instanceof WorldServer)
                    {
                        for (int i = 0; i < 15; i++)
                        {
                            double particleX = player.posX + (Math.random() - 0.5D) * (double) player.width;
                            double particleY = player.posY + Math.random() * (double) player.height;
                            double particleZ = player.posZ + (Math.random() - 0.5D) * (double) player.width;

                            PacketSpawnParticles packetSpawnParticles = new PacketSpawnParticles(EnumParticleTypes.CLOUD.getParticleID(),
                                    (float) particleX, (float) particleY, (float) particleZ,
                                    (float) motionX, -0.25F, (float) motionZ);

                            PacketManager.network.sendToAllTracking(packetSpawnParticles,
                                    new NetworkRegistry.TargetPoint(world.provider.getDimension(), particleX, particleY, particleZ, 0)
                            );
                        }
                    }
                }
                player.fallDistance = 0F;
            }

        }
    }
}
