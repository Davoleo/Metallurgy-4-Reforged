/*==============================================================================
 = Class: MidasiumHoeEffect
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
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class MidasiumHoeEffect extends BaseMetallurgyEffect {

	public MidasiumHoeEffect()
	{
		super(ModMetals.MIDASIUM);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.HOE;
	}

	/**
	 * Handles crop harvesting via right click
	 *
	 * @param event when a player right clicks a block with midasium hoe
	 */
	@SubscribeEvent
	public void hoeInteractBreakCrop(PlayerInteractEvent.RightClickBlock event)
	{
		//Local variables
		World world = event.getWorld();
		EntityPlayer player = event.getEntityPlayer();
		IBlockState state = event.getWorld().getBlockState(event.getPos());
		Block crop = state.getBlock();

		//Check if the effect can be applied and the player is not null and that the block is a crop
		if (canBeApplied(player) && crop instanceof BlockCrops)
		{
			//Called to remove the block and to decide whether to trigger the harvest method
			crop.removedByPlayer(state, world, event.getPos(), player, true);

			//harvest the block drops and play a block breaking sound
			TileEntity tileEntity = world.getTileEntity(event.getPos());
			world.playSound(null, event.getPos(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1F, 1F);
			crop.harvestBlock(world, player, event.getPos(), state, tileEntity, event.getItemStack());
		}
	}

	/**
	 * Handles drop transformation and replacement with gold
	 *
	 * @param event when a block is harvested
	 */
	@SubscribeEvent
	public void hoeHarvest(BlockEvent.HarvestDropsEvent event)
	{
		IBlockState state = event.getState();
		EntityPlayer player = event.getHarvester();

		if (canBeApplied(player) && state.getBlock() instanceof BlockCrops)
		{

			//50% chance
			if (Utils.random.nextBoolean())
			{
				BlockCrops crops = (BlockCrops) state.getBlock();
				List<ItemStack> drops = event.getDrops();
				ItemStack seeds = crops.getItem(player.world, event.getPos(), state);

				//Damage the hoe that was used to transmute the drops
				ItemStack hoe = player.getHeldItemMainhand();
				hoe.setItemDamage(hoe.getItemDamage() + 1);

				//Replace all seeds with gold nuggets and everything else with gold ingots
				List<ItemStack> goldDrops = drops.stream()
						.map(stack -> new ItemStack(ItemStack.areItemsEqual(stack, seeds) ? Items.GOLD_NUGGET : Items.GOLD_INGOT, stack.getCount()))
						.collect(Collectors.toList());

				//clear old drops and add new drop list
				drops.clear();
				drops.addAll(goldDrops);

				for (int i = 0; i < 10; i++)
				{
					double d1 = (Utils.random.nextDouble() - 0.5) * 0.15;
					double d2 = (Utils.random.nextDouble() - 0.5) * 0.15;
					double d3 = (Utils.random.nextDouble() - 0.5) * 0.15;
					spawnParticle(event.getWorld(), event.getPos(), 2, true, 3, d1, d2, d3);
				}
				event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_NOTE_CHIME, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
	}

}
