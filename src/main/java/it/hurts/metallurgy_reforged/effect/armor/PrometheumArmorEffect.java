/*==============================================================================
 = Class: PrometheumArmorEffect
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
import it.hurts.metallurgy_reforged.util.WorldUtils;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Optional;

public class PrometheumArmorEffect extends BaseMetallurgyEffect {

	public PrometheumArmorEffect()
	{
		super(ModMetals.PROMETHEUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void boneMealNearbyFlora(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if (!canBeApplied(entity))
			return;

		if (entity.ticksExisted % 10 == 0 && entity.isSprinting())
		{
			Optional<BlockPos> growablePos = WorldUtils.getRandomBlockWithinRadius(10, entity.getPosition(), 3, 1, 3,
					pos -> entity.world.getBlockState(pos).getBlock() instanceof IGrowable);

			float chance = getLevel(entity) * 0.75F;
			if (growablePos.isPresent() && Math.random() < chance)
			{
				if (ItemDye.applyBonemeal(ItemStack.EMPTY, entity.world, growablePos.get()))
				{
					for (int i = 0; i < 5; i++)
						spawnParticle(entity.world, growablePos.get(), 1, true, 3, 0, 0, 0);

					entity.getArmorInventoryList().forEach(armorStack -> armorStack.setItemDamage(armorStack.getItemDamage() + 2));
				}
			}
		}

	}

}
