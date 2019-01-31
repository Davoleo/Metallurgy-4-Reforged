package it.hurts.metallurgy_reforged.item.gadgets.gauntlet.util;

import it.hurts.metallurgy_reforged.util.capabilities.punch.IPunchEffect;
import it.hurts.metallurgy_reforged.util.capabilities.punch.PunchEffectProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 05 gen 2019
 * Time   : 13:56:46
 *
 ***************************/
public class GauntletEffect {
	
	@SubscribeEvent
	public void addPunchEffect(AttackEntityEvent event)
	{
		int hungerValue = 1;
		EntityPlayer pl = event.getEntityPlayer();
		Entity entity = event.getTarget();
		if(entity instanceof EntityLivingBase)
		{
			EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			if(GauntletOperation.isWearingGauntlet(pl) && entityLivingBase.deathTime <= 0)
			{

				if(pl.getFoodStats().getFoodLevel() >= hungerValue || pl.isCreative()){

					IPunchEffect effect = entityLivingBase.getCapability( PunchEffectProvider.PUNCH_EFFECT_CAP, null);
					if(effect != null && (effect.getHitTicks() <= 0 || effect.getDelayHit() > 0)) {
						effect.setDelayHit(5);
						effect.setPunchingPlayer(pl);
						effect.setRotPitchPlayer(pl.rotationPitch);
						effect.setRotYawPlayer(pl.getRotationYawHead());


						if(entityLivingBase instanceof EntityLiving) {
							EntityLiving livingEntity = (EntityLiving) entityLivingBase;
							if(!livingEntity.isAIDisabled())
							{
								effect.setNoAI(livingEntity.isAIDisabled());
								livingEntity.setNoAI(true);
							}
						}
					}
					if(!pl.isCreative())
					{
						pl.getFoodStats().addStats(-1, -0.5F);

					}

					Random rand = new Random();

					for (int i = 0; i < 20; ++i)
						entity.world.spawnParticle(EnumParticleTypes.CRIT, entity.posX + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), entity.posY + rand.nextDouble() * ((double)entity.height * 1.5D), entity.posZ + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), 0.0D, 0.0D, 0.0D);
				}
				else
					pl.sendStatusMessage(new TextComponentTranslation("effect.metallurgy.punch_effect_tired"),true);

			}

		}
	}
		
		
//	Event tick entity
	@SubscribeEvent
	public void applyPunchThrowEffects(LivingUpdateEvent event)
	{
		
		EntityLivingBase entity = event.getEntityLiving();
		IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
				
		if (effect != null)
		{
			if(effect.getDelayHit() > 0) {
				effect.addHitTicks();
				effect.setDelayHit(effect.getDelayHit() - 1);
				entity.motionX = 0D;
				entity.motionY = 0D;
				entity.motionZ = 0D;
				entity.hurtResistantTime = 10;

			}
			else if(effect.getHitTicks() > 0 && entity.deathTime <= 0)
			{
				EntityPlayer pl = effect.getPunchingPlayer(entity.world);
				 if(entity instanceof EntityLiving)
					  ((EntityLiving)entity).setNoAI(effect.isAIDisabled());

				if(pl != null)
				throwEntity(pl, entity);
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
					entity.world.spawnParticle(EnumParticleTypes.CLOUD, entity.posX + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), entity.posY + rand.nextDouble() * ((double)entity.height * 1.5D), entity.posZ + (rand.nextDouble() - 0.5D) * ((double)entity.width * 1.5D), 0.0D, 0.0D, 0.0D);

				AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.4D, 0.4D,0.4D);
				if(!entity.isDead) {
					//				destroy blocks and damage the punched entity (the damage is based from block's hardness
					for (double i = axisalignedbb.minX; i < axisalignedbb.maxX; i += 0.1D) {
						for (double j = axisalignedbb.minY; j < axisalignedbb.maxY; j += 0.1D) {
							for (double k = axisalignedbb.minZ; k < axisalignedbb.maxZ; k += 0.1D) {

								BlockPos pos = new BlockPos(i, j, k);
								if (!entity.world.isAirBlock(pos)) {
									IBlockState state = entity.world.getBlockState(pos);
									float hardness = state.getBlockHardness(entity.world, pos);
									if (hardness >= 0) {
										if (!state.getMaterial().isLiquid()) {
											if (!entity.world.isRemote)
												entity.world.destroyBlock(pos, true);
											entity.hurtResistantTime = 0;
											entity.attackEntityFrom(DamageSource.causeMobDamage(entity.getLastAttackedEntity()), 0.5F);
										}
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
		private static void throwEntity(EntityPlayer pl, EntityLivingBase entity){
					IPunchEffect effect = entity.getCapability(PunchEffectProvider.PUNCH_EFFECT_CAP, null);
					effect.setKnockbackTicks(1);
		}

}
