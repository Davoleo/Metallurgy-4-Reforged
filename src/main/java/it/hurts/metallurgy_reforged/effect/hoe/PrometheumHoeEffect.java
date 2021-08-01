/*==============================================================================
 = Class: PrometheumHoeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.hoe;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class PrometheumHoeEffect extends BaseMetallurgyEffect {

	public PrometheumHoeEffect()
	{
		super(ModMetals.PROMETHEUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.HOE;
	}

	@SubscribeEvent
	public void harvest(BlockEvent.HarvestDropsEvent event)
	{
		EntityPlayer harvester = event.getHarvester();
		if (!canBeApplied(harvester) || harvester.world.isRemote)
			return;

		Block block = event.getState().getBlock();

		if (block instanceof BlockCrops || block instanceof BlockMelon)
		{
			for (ItemStack drop : event.getDrops())
			{
				if (Math.random() < 0.5F)
				{
					for (int i = 0; i < 20; i++)
						spawnParticle(harvester.world, event.getPos(), 1F, true, 6, 0, 0, 0);

					drop.setCount(drop.getCount() + Math.random() < 0.5F ? 1 : 2);
					event.setDropChance(1F);
				}
			}

		}
		else if (block instanceof BlockTallGrass || block instanceof BlockLeaves)
		{
			for (int i = 0; i < 4; i++)
			{
				int fortune = event.getFortuneLevel();

				//Get drops and avoids triggering the event again
				@SuppressWarnings("deprecation")
				List<ItemStack> drrrrops = block.getDrops(event.getWorld(), event.getPos(), event.getState(), fortune);

				for (ItemStack item : drrrrops)
				{
					if (event.getWorld().rand.nextFloat() <= event.getDropChance())
						Block.spawnAsEntity(event.getWorld(), event.getPos(), item);
				}
			}
		}
	}

}
