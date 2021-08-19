/*==============================================================================
 = Class: SanguiniteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSanguiniteEntityState;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SanguiniteArmorEffect extends BaseMetallurgyEffect {

	private final Method getExperiencePoints;
	private final Method dropLoot;

	public SanguiniteArmorEffect()
	{
		super(ModMetals.SANGUINITE);

		getExperiencePoints = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_70693_a", int.class, EntityPlayer.class);
		dropLoot = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_184610_a", void.class, boolean.class, int.class, DamageSource.class);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	/**
	 * Drops entity items and xp
	 *
	 * @param killedEntity the entity that is dropping
	 * @param killer       the entity that caused the drop
	 * @param source       the source through which the {@code killer} caused the drop
	 *
	 * @throws InvocationTargetException if the methods to drop cannot be invoked
	 * @throws IllegalAccessException    if the methods to drop cannot be accessed
	 */
	private void dropXPAndItems(EntityLivingBase killedEntity, EntityLivingBase killer, DamageSource source) throws InvocationTargetException, IllegalAccessException
	{
		//Get XP to Drop
		int xp = (int) getExperiencePoints.invoke(killedEntity, killer);

		//Drop XP
		while (xp > 0)
		{
			int xpSlice = EntityXPOrb.getXPSplit(xp);
			xp -= xpSlice;
			killer.world.spawnEntity(new EntityXPOrb(killer.world, killedEntity.posX, killedEntity.posY, killedEntity.posZ, xpSlice));
		}

		killedEntity.captureDrops = true;
		killedEntity.capturedDrops.clear();

		if (killer.world.getGameRules().getBoolean("doMobLoot"))
		{
			int looting = ForgeHooks.getLootingLevel(killedEntity, killer, source);
			dropLoot.invoke(killedEntity, true, looting, source);
		}

		killedEntity.captureDrops = false;

		for (EntityItem item : killedEntity.capturedDrops)
		{
			//Creating a new entityItem so it doesn't affect the other drops?
			EntityItem dropEnt = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
			item.world.spawnEntity(dropEnt);
		}
	}

	@SubscribeEvent
	public void reviveEnemyCorpse(LivingDeathEvent event)
	{
		//True source so it works with ranged weapons as well
		// (we're currently working on an armor effect)
		Entity sourceEnt = event.getSource().getTrueSource();

		if (sourceEnt instanceof EntityPlayer)
		{
			EntityPlayer killer = ((EntityPlayer) sourceEnt);

			int level = getLevel(killer);
			if (level == 0)
				return;

			/*event.getEntityLiving().isEntityUndead() &&*/

			final EntityLivingBase killedEntity = event.getEntityLiving();

			//If the killed mob is hostile
			if (killedEntity instanceof IMob && Math.random() <= level / 4F)
			{
				if (killedEntity.getEntityData().getInteger("corpse_state") > 0)
					return;

				//Reset the time existed in the world
				killedEntity.ticksExisted = 0;
				//Sets entity to be rendered shaking and red
				killedEntity.getEntityData().setInteger("corpse_state", 1);
				PacketSanguiniteEntityState packet = new PacketSanguiniteEntityState(killedEntity.getEntityId(), 1);
				PacketManager.network.sendToAllTracking(packet, killedEntity);

				//Cancel death and set health and invulnerability
				event.setCanceled(true);
				killedEntity.setHealth(10F);
				killedEntity.setEntityInvulnerable(true);
				((EntityLiving) killedEntity).setNoAI(true);

				try
				{
					dropXPAndItems(killedEntity, killer, event.getSource());
				}
				catch (InvocationTargetException | IllegalAccessException e)
				{
					Metallurgy.logger.error("Error while dropping pre-corpse Experience & Drops (Necromastery Effect Error)");
					e.printStackTrace();
				}
			}
		}
	}

	@SubscribeEvent
	public void updateCorpseStates(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		int corpseState = entity.getEntityData().getInteger("corpse_state");

		switch (corpseState)
		{
			case 0:
			default:
				return;
			case 1:
				entity.deathTime++;

				if (entity.deathTime == 19)
					entity.getEntityData().setInteger("corpse_state", 2);
				return;
			case 2:
				entity.deathTime--;

				if (entity.deathTime == 0)
				{
					entity.setEntityInvulnerable(false);
					((EntityLiving) entity).setNoAI(false);
					entity.getEntityData().setInteger("corpse_state", 0);
				}
				return;
			case 3:
		}

		//Visual Particle feedback
		//double d2 = this.rand.nextGaussian() * 0.02D;
		//double d0 = this.rand.nextGaussian() * 0.02D;
		//double d1 = this.rand.nextGaussian() * 0.02D;
		//this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
	}

	private static final float[] OVERLAY = new float[]{100 / 255F, 25 / 255F, 25 / 255F};

	@SideOnly(Side.CLIENT)
	public static void renderLivingCorpse(EntityLivingBase entity, double x, double y, double z)
	{
		int corpseState = entity.getEntityData().getInteger("corpse_state");

		if (corpseState < 2)
			return;

		GlStateManager.pushMatrix();
		//COLORED CORPSE
		GlStateManager.color(OVERLAY[0], OVERLAY[1], OVERLAY[2], 1F);

		//SHAKING CORPSE
		double deltaX = Utils.random.nextGaussian() * 0.01D;
		double deltaZ = Utils.random.nextGaussian() * 0.01D;
		GlStateManager.translate(x + deltaX, y, z + deltaZ);
		GlStateManager.popMatrix();
	}

}
