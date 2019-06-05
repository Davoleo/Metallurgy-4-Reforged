/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemPotashFertilizer
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadgets;

import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPotashFertilizer extends Item implements IHasModel {

    public ItemPotashFertilizer()
    {
        ItemUtils.initItem(this, "potash_fertilizer", MetallurgyTabs.tabSpecial, ModItems.itemList);
    }

    @Nonnull
    @Override
    public String getCategory()
    {
        return "";
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(Constants.POTASH_FERTILIZER);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
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
