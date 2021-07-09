/*==============================================================================
 = Class: MithrilWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class MithrilWeaponEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	public MithrilWeaponEffect()
	{
		super(ModMetals.MITHRIL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void startMagicalDmgDelay(LivingDamageEvent event)
	{
		Entity sourceEnt = event.getSource().getImmediateSource();

		if (sourceEnt instanceof EntityPlayer && canBeApplied(((EntityLivingBase) sourceEnt)))
		{

			EntityPlayer player = ((EntityPlayer) sourceEnt);

			ProgressiveDataBundle effBundle = getBundle(player, metal, getCategory());
			if (effBundle.isEffectInProgress())
				return;

			//Set the effect stack to the correct weapon
			effBundle.setEffectStack(player.getHeldItemMainhand(), null);
			//kickstart the delay
			effBundle.incrementStep(player);
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		if (!effectStack.isEmpty())
		{
			if (effectStack.getItem() instanceof IToolEffect)
			{
				//If the effect item is correct
				IToolEffect tool = ((IToolEffect) effectStack.getItem());
				//Damage is 30% of the original weapon damage
				float attackDamage = tool.getMetalStats().getToolStats().getDamage() / 0.3F;

				//Get the last attacked entity and damage it if it's not dead or null
				EntityLivingBase target = entity.getLastAttackedEntity();
				if (target != null && !target.isDead)
				{
					//30% of the original weapon damage (underclamped at 1) as MAGIC DAMAGE
					target.attackEntityFrom(DamageSource.MAGIC, Math.max(1, attackDamage));
					//Particles exploding from the torso of the enemy (magical damage repr)
					Utils.repeat(10, () -> {
						double r1 = (Math.random() * 0.15) - 0.075;
						double r2 = (Math.random() * 0.15) - 0.075;
						double r3 = (Math.random() * 0.15) - 0.075;
						spawnParticle(world, target.posX, target.posY + (0.6 * target.height), target.posZ, r1, r2, r3, 2, true, 6);
					});
				}
			}
		}
	}

}
