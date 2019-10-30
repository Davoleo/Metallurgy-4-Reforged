/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PickaxeEffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;

public class PickaxeEffectHandler {

	@SubscribeEvent
	public static void breakSpeed(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer pl = event.getEntityPlayer();
		ItemStack mainHandStack = pl.getHeldItemMainhand();

		if (pl.isInWater() && mainHandStack.getItem() == ModMetals.DEEP_IRON.getTool(EnumTools.PICKAXE) && ToolEffectsConfig.deepIronPickaxeEffect)
		{
			event.setNewSpeed(event.getOriginalSpeed() * 3);
		}


		//		set tools break speed based on light except for hoe and sword
		if (ToolEffectsConfig.shadowSteelToolSpeedEffect && mainHandStack.getItem() == ModMetals.SHADOW_STEEL.getTool(EnumTools.PICKAXE))
		{
			float percentage = Utils.getLightArmorPercentage(pl, 100F);
			float speed = event.getNewSpeed() * percentage / 40F;
			event.setNewSpeed(event.getOriginalSpeed() + speed);
		}
	}

	@SubscribeEvent
	public static void onBlockDrop(BlockEvent.HarvestDropsEvent event) {

		Item heldItem = event.getHarvester().getHeldItemMainhand().getItem();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		//IBlockState blockState = world.getBlockState(pos);

		if (heldItem.equals(ModMetals.IGNATIUS.getTool(EnumTools.SHOVEL)))
		{
			List<ItemStack> drops = event.getDrops();
			for (ItemStack drop : drops)
			{
				ItemStack smeltedItem = FurnaceRecipes.instance().getSmeltingResult(drop);

				if (!smeltedItem.isEmpty())
				{
					event.setDropChance(0);
					world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), smeltedItem));
				}
			}
		}

		// TODO: 30/10/2019 ASK PIER
		//ResourceLocation regName = blockState.getBlock().getRegistryName();
		//
		//if (regName != null && regName.getPath().contains("_ore"))
		//{
		//	String nuggetReg = regName.getPath().replace("_ore", "_nugget");
		//	Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(regName.getNamespace(), nuggetReg));
		//	if (nugget != null && Utils.random.nextBoolean())
		//	{
		//		event.getDrops().add(new ItemStack(nugget, Utils.random.nextInt(3)));
		//	}
		//}
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {

		Item heldItem = event.getPlayer().getHeldItemMainhand().getItem();
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState blockState = world.getBlockState(pos);

		if (heldItem.equals(ModMetals.IGNATIUS.getTool(EnumTools.PICKAXE)))
		{
			ResourceLocation regName = blockState.getBlock().getRegistryName();

			if (regName != null && regName.getPath().contains("_ore"))
			{
				String nuggetReg = regName.getPath().replace("_ore", "_nugget");
				Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(regName.getNamespace(), nuggetReg));
				if (nugget != null && Utils.random.nextBoolean())
				{
					world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(nugget, Utils.random.nextInt(3))));
				}
			}
		}
	}
}
