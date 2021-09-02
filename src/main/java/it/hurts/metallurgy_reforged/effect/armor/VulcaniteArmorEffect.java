/*==============================================================================
 = Class: VulcaniteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class VulcaniteArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

	private static final int RADIUS = 5;

	public VulcaniteArmorEffect()
	{
		super(ModMetals.VULCANITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		int level = getLevel(event.getEntityLiving());
		if (level == 0)
			return;

		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer entity = ((EntityPlayer) event.getEntityLiving());
			ExtraFilledDataBundle data = getEffectCapability(entity).vulcaniteArmorBundle;

			int hits = data.getExtraInt("hits");

			if (hits > 4)
			{
				entity.world.getEntitiesWithinAABBExcludingEntity(entity,
						new AxisAlignedBB(entity.posX - RADIUS, entity.posY - 1, entity.posZ - RADIUS,
								entity.posX + RADIUS, entity.posY + 1, entity.posZ + RADIUS)
				).forEach(enemy -> {
					entity.world.newExplosion(entity, enemy.posX, enemy.posY + 1.1, enemy.posZ, 0.7F + (level * 0.1F), level > 2, false);
					if (level > 2)
						entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 200));
					for (int i = 0; i < 10; i++)
						spawnParticle(enemy, 2.5F, true, 3);
				});

				data.setExtra("hits", 0);
			}
			else
			{
				data.setExtra("hits", hits + 1);
				data.setExtra("inactive", false);
			}
		}
	}


	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		ExtraFilledDataBundle bundle = getEffectCapability(entity).vulcaniteArmorBundle;

		if (step == 1 && !bundle.getExtraBool("inactive"))
		{
			bundle.setExtra("inactive", true);
		}

		if (step == 3 && bundle.getExtraBool("inactive"))
		{
			bundle.setExtra("hits", 0);
		}
	}

}
