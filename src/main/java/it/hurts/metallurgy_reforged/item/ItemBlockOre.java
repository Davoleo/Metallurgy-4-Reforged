/*==============================================================================
 = Class: ItemBlockOre
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(BlockOre block) {
        super(block);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().getBoolean("transmuted");
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack oreStack = player.getHeldItem(hand);
        if (oreStack.getTagCompound() != null && oreStack.getTagCompound().getBoolean("transmuted")) {
            return EnumActionResult.FAIL;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
