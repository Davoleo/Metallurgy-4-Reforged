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
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.WorldUtils;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
			if (event.player.getCooldownTracker().getCooldown(getArmorRepr(event.player).getItem(), 0) == 0)
			{
				bundle.setExtra("sneaking", event.player.isSneaking());
				bundle.incrementStep(event.player);
			}
		}
	}

	@Override
	public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
	{
		ExtraFilledDataBundle bundle = getEffectCapability(entity).prometheumArmorBundle;
		boolean sneaking = bundle.getExtraBool("sneaking");

		if (step % 2 != 0)
			bundle.setExtra("sneaking", entity.isSneaking());
		else if (entity.isSneaking() != sneaking)
		{
			List<BlockPos> growables = WorldUtils.getBlocksWithinRadius(entity.getPosition(), 3, 1, 3,
					pos -> entity.world.getBlockState(pos).getBlock() instanceof IGrowable);
			Collections.shuffle(growables);

			final AtomicBoolean grassWasBoneMealed = new AtomicBoolean(false);

			growables.forEach(pos -> {
				float chance = getLevel(entity) * 0.25F * 0.75F;
				if (Math.random() < chance)
				{
					if (!grassWasBoneMealed.get() || world.getBlockState(pos).getBlock() != Blocks.GRASS)
					{

						if (ItemDye.applyBonemeal(ItemStack.EMPTY, entity.world, pos))
						{
							entity.world.playEvent(2005, pos, 0);
							entity.getArmorInventoryList().forEach(armorStack -> {
								if (ItemUtils.isMadeOfMetal(metal, armorStack.getItem()))
									armorStack.damageItem(2, entity);
							});
						}

						if (world.getBlockState(pos).getBlock() == Blocks.GRASS)
							grassWasBoneMealed.set(true);
					}
				}
			});
		}

		if (step == maxSteps)
		{
			assert metal.getArmorSet() != null;
			for (ItemArmorBase armorItem : metal.getArmorSet())
				entity.getCooldownTracker().setCooldown(armorItem, 300);
		}
	}

}
