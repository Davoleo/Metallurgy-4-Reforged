/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemVulcaniteLighter
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemVulcaniteLighter extends ItemIgnatiusLighter {

	public ItemVulcaniteLighter(String name)
	{
		super(name);
		setMaxDamage(500);
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		BlockPos blockPos = pos.offset(facing);
		ItemStack lighter = player.getHeldItem(hand);

		if (!player.isSneaking())
			if (!player.canPlayerEdit(blockPos, facing, lighter))
			{
				return EnumActionResult.FAIL;
			}
			else
			{
				worldIn.playSound(player, blockPos, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.BLOCKS, 1.0F, 2F);

				createFire(worldIn, blockPos, player);

				if (!player.isCreative())
					lighter.damageItem(1, player);

				return EnumActionResult.SUCCESS;
			}
		else
		{
			ItemStack item = player.getHeldItem(hand);

			if (player.canPlayerEdit(blockPos, facing, item) && worldIn.mayPlace(Blocks.LAVA, blockPos, false, facing, player))
			{
				worldIn.playSound(player, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
				IBlockState state = Blocks.LAVA.getStateForPlacement(worldIn, blockPos, facing, hitX, hitY, hitZ, 0, player, hand);
				worldIn.setBlockState(blockPos, state);
				state.getBlock().neighborChanged(state, worldIn, blockPos, state.getBlock(), pos);

				if (!player.isCreative())
					player.getCooldownTracker().setCooldown(this, 400);
				lighter.damageItem(25, player);

				return EnumActionResult.SUCCESS;
			}

			return EnumActionResult.FAIL;
		}
	}

}
