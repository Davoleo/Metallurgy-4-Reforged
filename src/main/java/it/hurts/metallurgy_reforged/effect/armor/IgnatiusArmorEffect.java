/*==============================================================================
 = Class: IgnatiusArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class IgnatiusArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	private static final DamageSource WATER_DAMAGE = new DamageSource("waterDamage") {
		@Nonnull
		@Override
		public ITextComponent getDeathMessage(@Nonnull EntityLivingBase entityLivingBaseIn)
		{
			return new TextComponentTranslation("message.metallurgy.water_damage_death", entityLivingBaseIn.getDisplayName());
		}
	}.setDamageBypassesArmor();

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
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		ProgressiveDataBundle bundle = getBundle(entity, metal, getCategory());

		if (!entity.isInLava())
			bundle.resetProgress(entity);

		//currentStep >= (0.25 | 0.5 | 0.75 | 1) * 40
		int lavaImmunityTimespan = (getLevel(entity) * (maxSteps / 10));
		if (step >= lavaImmunityTimespan)
		{
			bundle.resetProgress(entity);
			assert metal.getArmorSet() != null;
			for (ItemArmorBase armorItem : metal.getArmorSet())
				entity.getCooldownTracker().setCooldown(armorItem, 200);
		}

		//System.out.println("Current Step: " + step);
	}

	@SubscribeEvent
	public void lavaBath(LivingAttackEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		int level = getLevel(entity);
		if (level == 0)
			return;

		DamageSource source = event.getSource();
		//If the entity is not a player bundle becomes null
		ProgressiveDataBundle bundle = null;
		if (entity instanceof EntityPlayer)
			bundle = getBundle(((EntityPlayer) entity), metal, getCategory());

		if (bundle == null || (!bundle.isEffectInProgress() && source == DamageSource.LAVA))
		{
			if (entity instanceof EntityPlayer)
			{
				if (((EntityPlayer) entity).getCooldownTracker().getCooldown(getArmorRepr(entity).getItem(), 0) > 0)
					return;

				//Kickstart the timer
				//The bundle SHOULD NEVER be null if we're here
				assert bundle != null;
				bundle.incrementStep(((EntityPlayer) entity));
			}

			event.setCanceled(true);
		}

		if (source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE || (source == DamageSource.LAVA && (bundle == null || bundle.isEffectInProgress())))
		{
			if (source == DamageSource.LAVA && entity.ticksExisted % 25 - (level * 5) == 0)
				event.getEntityLiving().heal(event.getAmount() * (level / 6F));
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
				entity.attackEntityFrom(WATER_DAMAGE, 1F);
				entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1F, false);
			}

			spawnParticle(entity, 5F, true, 5);
		}
	}

	@SubscribeEvent
	public void drinkDamage(LivingEntityUseItemEvent.Finish event)
	{
		EnumAction action = event.getItem().getItem().getItemUseAction(event.getItem());
		if (action == EnumAction.DRINK)
		{
			EntityLivingBase entity = event.getEntityLiving();
			entity.attackEntityFrom(WATER_DAMAGE, 2F);
			entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1F, false);
		}
	}

}
