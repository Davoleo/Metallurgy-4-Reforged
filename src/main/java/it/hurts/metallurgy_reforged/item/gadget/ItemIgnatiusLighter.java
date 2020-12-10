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

public class ItemIgnatiusLighter extends ItemExtra {

	public ItemIgnatiusLighter(String name)
	{
		super(name, MetallurgyTabs.tabSpecial, "gadget");
		setMaxStackSize(1);
		setMaxDamage(150);
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		BlockPos blockPos = pos.offset(facing);
		ItemStack lighter = player.getHeldItem(hand);

		if (!player.canPlayerEdit(blockPos, facing, lighter))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			worldIn.playSound(player, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

			if (itemRand.nextBoolean())
				createFire(worldIn, blockPos, player);

			if (!player.isCreative())
				lighter.damageItem(1, player);

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
