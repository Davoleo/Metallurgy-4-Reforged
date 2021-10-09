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
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSpawnVanillaParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class AtlarusArmorEffect extends BaseMetallurgyEffect {

	public AtlarusArmorEffect()
	{
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

		AxisAlignedBB effectRange = entity.getEntityBoundingBox().grow(3D);

		if (!world.isRemote)
		{
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, effectRange);

			for (Entity e : list)
			{
				double velocity = e.getPositionVector().distanceTo(new Vec3d(e.prevPosX, e.prevPosY, e.prevPosZ));

				if (!e.getEntityBoundingBox().intersects(entity.getEntityBoundingBox().grow(1D)) && (e instanceof IProjectile || velocity >= 0.6))
				{


					List<Entity> entitiesInPath = world.getEntitiesWithinAABBExcludingEntity(e, e.getEntityBoundingBox().expand(e.motionX, e.motionY, e.motionZ).grow(1.0D));
					if (entitiesInPath.stream().anyMatch(entity1 -> entity1.getUniqueID().equals(entity.getUniqueID())))
					{
						shoot(e, -e.motionX, -e.motionY, -e.motionZ, 0.5F, 36F);
						e.velocityChanged = true;

						double particleX = e.posX + (Math.random() - 0.5D) * (double) e.width;
						double particleY = e.posY + Math.random() * (double) e.height;
						double particleZ = e.posZ + (Math.random() - 0.5D) * (double) e.width;

						PacketSpawnVanillaParticles packetSpawnParticles = new PacketSpawnVanillaParticles(EnumParticleTypes.CLOUD.getParticleID(),
								(float) particleX, (float) particleY, (float) particleZ,
								(float) e.motionX, (float) e.motionY, (float) e.motionZ);

						for (int i = 0; i < 5; i++)
							PacketManager.network.sendToAllTracking(packetSpawnParticles, e);
					}
				}
			}

		}

	}

	private void shoot(Entity entity, double x, double y, double z, float velocity, float inaccuracy)
	{
		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x = x / (double) f;
		y = y / (double) f;
		z = z / (double) f;
		x = x + (1D - entity.world.rand.nextGaussian() * 2D) * 0.252F;
		y = y + (1D - entity.world.rand.nextGaussian() * 2D) * 0.252F;
		z = z + (1D - entity.world.rand.nextGaussian() * 2D) * 0.252F;
		x = x * (double) velocity;
		y = y * (double) velocity;
		z = z * (double) velocity;
		entity.motionX = x;
		entity.motionY = y;
		entity.motionZ = z;
		float f1 = MathHelper.sqrt(x * x + z * z);
		entity.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
		entity.rotationPitch = (float) (MathHelper.atan2(y, f1) * (180D / Math.PI));
		entity.prevRotationYaw = entity.rotationYaw;
		entity.prevRotationPitch = entity.rotationPitch;
	}


}
