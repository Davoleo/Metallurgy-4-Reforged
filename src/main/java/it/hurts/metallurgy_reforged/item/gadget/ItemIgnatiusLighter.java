/*==============================================================================
 = Class: ItemIgnatiusLighter
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemIgnatiusLighter extends ItemExtra {

	public ItemIgnatiusLighter(String name)
	{
		super(name, MetallurgyTabs.tabSpecial, "gadget");
		setMaxStackSize(1);
		setMaxDamage(150);
	}

	protected boolean testAndIgniteTNT(World worldIn, BlockPos pos, EntityPlayer igniter)
	{

		IBlockState state = worldIn.getBlockState(pos);

		if (state.getBlock() instanceof BlockTNT)
		{
			((BlockTNT) state.getBlock()).explode(worldIn, pos, state.withProperty(BlockTNT.EXPLODE, true), igniter);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			return true;
		}

		return false;
	}

	@ParametersAreNonnullByDefault
	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack lighter = player.getHeldItem(hand);

		//Check if TNT is present in the clicked blockPos and ignite it [in case the test passes we don't need to do anything else]
		if (testAndIgniteTNT(worldIn, pos, player))
		{
			lighter.damageItem(1, player);
			return EnumActionResult.SUCCESS;
		}

		if (!player.isSneaking())
			return Items.FLINT_AND_STEEL.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);

		BlockPos blockPos = pos.offset(facing);

		if (!player.canPlayerEdit(blockPos, facing, lighter))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			worldIn.playSound(player, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

			if (itemRand.nextBoolean())
			{
				createFire(worldIn, blockPos, player);

				if (!player.isCreative())
					lighter.damageItem(5, player);
			}

			return EnumActionResult.SUCCESS;
		}
	}

	void createFire(World worldIn, BlockPos initPos, EntityPlayer player)
	{
		final BlockPos START_POS = initPos.offset(player.getHorizontalFacing());
		BlockPos pos;

		for (int x = -1; x <= 1; x++)
			for (int z = -1; z <= 1; z++)
			{
				pos = START_POS;
				pos = pos.add(x, 0, z);

				if (worldIn.isAirBlock(pos) && Blocks.FIRE.canPlaceBlockAt(worldIn, pos) && !worldIn.isRemote)
					worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
			}
	}

}
