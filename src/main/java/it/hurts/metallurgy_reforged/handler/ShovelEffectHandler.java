/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ShovelEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ShovelEffectHandler {

	@SubscribeEvent
	public static void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
		if (event.getHarvester() != null)
		{
			Item heldItem = event.getHarvester().getHeldItemMainhand().getItem();
			World world = event.getWorld();
			BlockPos pos = event.getPos();

			if (heldItem.equals(ModMetals.IGNATIUS.getTool(EnumTools.SHOVEL)))
			{
				dropSmeltedItems(event, world, pos);
			}
		}
	}

	protected static void dropSmeltedItems(BlockEvent.HarvestDropsEvent event, World world, BlockPos pos) {
		List<ItemStack> drops = event.getDrops();
		for (ItemStack drop : drops)
		{
			ItemStack smeltedItem = FurnaceRecipes.instance().getSmeltingResult(drop);

			if (!smeltedItem.isEmpty())
			{
				switch (event.getFortuneLevel())
				{
					//25% chance
					case 0:
						if (Utils.random.nextInt(4) == 0)
						{
							event.setDropChance(0);
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
						}
						break;

					//50% chance
					case 1:
						if (Utils.random.nextInt(2) == 0)
						{
							event.setDropChance(0);
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
						}
						break;

					//75% chance
					case 2:
						if (Utils.random.nextInt(4) != 0)
						{
							event.setDropChance(0);
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
						}
						break;

					//100% chance
					case 3:
					default:
						event.setDropChance(0);
						world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
						break;
				}
			}
		}
	}

}
