/*==============================================================================
 = Class: PrometheumArmorEffect
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
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

public class PrometheumArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

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
	public void boneMealNearbyFlora(TickEvent.PlayerTickEvent event)
	{
		if (event.phase != TickEvent.Phase.START)
			return;

		if (!canBeApplied(event.player) || getEffectCapability(event.player) == null)
			return;

		ExtraFilledDataBundle bundle = getEffectCapability(event.player).prometheumArmorBundle;

		if (!bundle.isEffectInProgress() && event.player.isSneaking())
		{
			bundle.setExtra("sneaking", event.player.isSneaking());
			bundle.incrementStep(event.player);
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		ExtraFilledDataBundle bundle = getEffectCapability(entity).prometheumArmorBundle;

		System.out.println(step);

		/*
		if (bundle.getExtraBool("sneaking") && !entity.isSneaking() == (step % 2 != 0))
		{
			bundle.resetProgress(entity);
			return;
		}
		else
			System.out.println("In Time!!");


		if (step == maxSteps) {
			List<BlockPos> growables = WorldUtils.getBlocksWithinRadius(entity.getPosition(), 3, 1, 3,
					pos -> entity.world.getBlockState(pos).getBlock() instanceof IGrowable);

			growables.forEach(pos -> {
				if (entity.isSprinting() && entity.world.getBlockState(pos).getBlock() != Blocks.GRASS)
				{
					float chance = getLevel(entity) * 0.75F;
					if (Math.random() < chance)
					{
						if (ItemDye.applyBonemeal(ItemStack.EMPTY, entity.world, pos))
						{
							entity.world.playEvent(2005, pos, 0);
							entity.getArmorInventoryList().forEach(armorStack -> armorStack.setItemDamage(armorStack.getItemDamage() + 2));
						}
					}
				}
			});
		}
		 */
	}

}
