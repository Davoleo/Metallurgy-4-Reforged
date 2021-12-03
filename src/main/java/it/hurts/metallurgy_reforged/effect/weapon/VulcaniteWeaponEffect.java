/*==============================================================================
 = Class: VulcaniteWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class VulcaniteWeaponEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	public VulcaniteWeaponEffect()
	{
		super(ModMetals.VULCANITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	/**
	 * Handles Self-explosion after the time is up
	 */
	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		//Explosion on self
		if (step == maxSteps)
		{
			float currentCooldown = entity.getCooldownTracker().getCooldown(entity.getHeldItemMainhand().getItem(), 0);
			float explosionPower = 0.8F;
			//float explosionPower = Math.max(0.25F, 2.75F - currentCooldown / 40);
			world.createExplosion(null, entity.posX, entity.posY + 0.65, entity.posZ, explosionPower, false);

			//Reset Effect
			if (entity.getHeldItemMainhand().getTagCompound() != null)
				entity.getHeldItemMainhand().getTagCompound().setBoolean("primed", false);
		}

	}

	/**
	 * Handles weapon ignition (priming)
	 */
	@Override
	public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack weapon = playerIn.getHeldItemMainhand();
		CooldownTracker cooldownTracker = playerIn.getCooldownTracker();

		//If previous effect hasn't finished yet
		if (cooldownTracker.getCooldown(weapon.getItem(), 0) > 0)
			return;

		playerIn.playSound(SoundEvents.ENTITY_TNT_PRIMED, 0.75F, 1.25F);

		NBTTagCompound data = weapon.getTagCompound();
		if (data == null)
			data = new NBTTagCompound();

		data.setBoolean("primed", true);
		weapon.setTagCompound(data);
		getBundle(playerIn, metal, getCategory()).incrementStep(null);

		//2.5 seconds fuse before explosion
		cooldownTracker.setCooldown(weapon.getItem(), 50);
	}

	/**
	 * Handles Enemy explosion
	 */
	@SubscribeEvent
	public void explodeEnemy(LivingDamageEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();

		if (sourceEnt instanceof EntityLivingBase)
		{
			EntityLivingBase attacker = ((EntityLivingBase) sourceEnt);
			if (!canBeApplied(attacker))
				return;

			NBTTagCompound data = attacker.getHeldItemMainhand().getTagCompound();
			if (data == null)
				return;

			if (data.getBoolean("primed"))
			{
				//Reset Effect
				data.setBoolean("primed", false);
				if (attacker instanceof EntityPlayer)
					getBundle(((EntityPlayer) attacker), metal, getCategory()).resetProgress(((EntityPlayer) attacker));

				EntityLivingBase target = event.getEntityLiving();
				float currentCooldown = attacker instanceof EntityPlayer ?
						((EntityPlayer) attacker).getCooldownTracker().getCooldown(attacker.getHeldItemMainhand().getItem(), 0) :
						100F;

				float explosionPower = 0.32F + (1F - currentCooldown) * 1.1F;
				System.out.println("Cool: " + currentCooldown + " EXP: " + explosionPower);
				attacker.world.createExplosion(attacker, target.posX, target.posY, target.posZ, explosionPower, false);
				//Knock entity back
				target.knockBack(attacker, 2F, attacker.posX - target.posX, attacker.posZ - target.posZ);

				//Knock-back attacker
				//attacker.knockBack(target, 1F, target.posX - attacker.posX, target.posZ - attacker.posZ);
			}
		}
	}

}
