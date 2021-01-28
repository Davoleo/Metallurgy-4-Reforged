/*==============================================================================
 = Class: AtlarusArmorEffect
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class AtlarusArmorEffect extends BaseMetallurgyEffect {

    public AtlarusArmorEffect() {
        super(ModMetals.ATLARUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }


    /**
     * prevent fall damage by summoning wind by a random direction
     */
    @SubscribeEvent
    public void deviateProjectiles(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();

        if (!canBeApplied(entity))
            return;

        World world = entity.world;

        if (!world.isRemote)
        {
            world.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(
                    entity.posX - 3, entity.posY - 3, entity.posZ - 3,
                    entity.posX + 3, entity.posY + 3, entity.posZ + 3
            )).stream().filter(projectile -> {
                double velocity = new Vec3d(projectile.posX, projectile.posY, projectile.posZ)
                        .distanceTo(new Vec3d(projectile.prevPosX, projectile.prevPosY, projectile.posZ));
                if (velocity > 0)
                    System.out.println("Velocity: " + velocity);
                return velocity > 0.20;
            })
                    .forEach(projectile -> {
                        Vec3d motionVec = new Vec3d(projectile.motionX, projectile.motionY, projectile.motionZ);
                        Vec3d projToEntity = entity.getPositionVector().subtract(projectile.getPositionVector());
                        //Compute the angle between the two vectors
                        double angle = Math.acos(motionVec.dotProduct(projToEntity) / (motionVec.length() * projToEntity.length()));
                        System.out.println("Angle: " + angle);
                        if (angle < Math.PI / 2)
                        {
                            projToEntity = projToEntity.normalize();
                            projectile.motionX *= -0.1;
                            projectile.motionY += 0.6;
                            projectile.motionZ *= -0.1;
                            projectile.velocityChanged = true;
                            SPacketEntityVelocity packet = new SPacketEntityVelocity(projectile);
                            ((WorldServer) projectile.world).getEntityTracker().sendToTracking(projectile, packet);
                        }
                    });
        }

        //if (entity.fallDistance >= 4F)
        //{
        //    AxisAlignedBB nearCollitions = entity.getEntityBoundingBox().contract(0, 1.7D, 0).offset(0, -4D, 0);
        //    if (world.collidesWithAnyBlock(nearCollitions))
        //    {
        //        double motionX = 2D - Math.random() * 4D;
        //        double motionZ = 2D - Math.random() * 4D;
        //
        //        if (!world.isRemote)
        //        {
        //            entity.motionX = motionX;
        //            entity.motionZ = motionZ;
        //            entity.velocityChanged = true;
        //
        //            if (world instanceof WorldServer)
        //            {
        //                for (int i = 0; i < 15; i++)
        //                {
        //                    double particleX = entity.posX + (Math.random() - 0.5D) * (double) entity.width;
        //                    double particleY = entity.posY + Math.random() * (double) entity.height;
        //                    double particleZ = entity.posZ + (Math.random() - 0.5D) * (double) entity.width;
        //
        //                    PacketSpawnVanillaParticles packetSpawnParticles = new PacketSpawnVanillaParticles(EnumParticleTypes.CLOUD.getParticleID(),
        //                            (float) particleX, (float) particleY, (float) particleZ,
        //                            (float) motionX, -0.25F, (float) motionZ);
        //
        //                    PacketManager.network.sendToAllTracking(packetSpawnParticles,
        //                            new NetworkRegistry.TargetPoint(world.provider.getDimension(), particleX, particleY, particleZ, 0)
        //                    );
        //                }
        //            }
        //        }
        //        entity.fallDistance = 0F;
        //    }
        //
        //}
    }

}
