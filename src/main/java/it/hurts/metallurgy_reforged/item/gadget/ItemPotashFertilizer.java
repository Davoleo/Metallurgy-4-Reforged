/*==============================================================================
 = Class: ItemPotashFertilizer
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemPotashFertilizer extends ItemExtra {

	public ItemPotashFertilizer()
	{
		super("potash_fertilizer", MetallurgyTabs.tabSpecial, "gadget");
		setTooltip(Constants.POTASH_FERTILIZER);
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(@Nonnull EntityPlayer player, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (ItemDye.applyBonemeal(player.getHeldItem(hand), worldIn, pos, player, hand))
		{
			if (worldIn.isRemote)
				for (int i = 0; i < 50; i++)
				{
					worldIn.spawnParticle(EnumParticleTypes.DRIP_LAVA, pos.getX() + itemRand.nextDouble(), pos.getY() + itemRand.nextDouble(),
							pos.getZ() + itemRand.nextDouble(), 0, 0, 0);
				}

			return EnumActionResult.SUCCESS;
		}
		else
			return EnumActionResult.FAIL;
	}

}
