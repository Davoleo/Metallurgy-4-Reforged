/*==============================================================================
 = Class: AtlarusArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSpawnParticles;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nonnull;

public class AtlarusArmorEffect extends BaseMetallurgyEffect {

    private static final EventHandler<LivingEvent.LivingUpdateEvent> WIND_FALL_DAMAGE = new EventHandler<>(AtlarusArmorEffect::cancelFallDamage, LivingEvent.LivingUpdateEvent.class);

    public AtlarusArmorEffect() {
        super(ModMetals.ATLARUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{WIND_FALL_DAMAGE};
    }

	private static void cancelFallDamage(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		World world = entity.world;

		if (entity.fallDistance >= 4F)
		{
			AxisAlignedBB nearCollitions = entity.getEntityBoundingBox().contract(0, 1.7D, 0).offset(0, -4D, 0);
			if (world.collidesWithAnyBlock(nearCollitions))
			{
				double motionX = 2D - Math.random() * 4D;
				double motionZ = 2D - Math.random() * 4D;

				if (!world.isRemote)
				{
					entity.motionX = motionX;
					entity.motionZ = motionZ;
					entity.velocityChanged = true;

					if (world instanceof WorldServer)
					{
						for (int i = 0; i < 15; i++)
						{
							double particleX = entity.posX + (Math.random() - 0.5D) * (double) entity.width;
							double particleY = entity.posY + Math.random() * (double) entity.height;
							double particleZ = entity.posZ + (Math.random() - 0.5D) * (double) entity.width;

							PacketSpawnParticles packetSpawnParticles = new PacketSpawnParticles(EnumParticleTypes.CLOUD.getParticleID(),
									(float) particleX, (float) particleY, (float) particleZ,
									(float) motionX, -0.25F, (float) motionZ);

							PacketManager.network.sendToAllTracking(packetSpawnParticles,
									new NetworkRegistry.TargetPoint(world.provider.getDimension(), particleX, particleY, particleZ, 0)
							);
						}
					}
				}
				entity.fallDistance = 0F;
			}

		}
	}

}
