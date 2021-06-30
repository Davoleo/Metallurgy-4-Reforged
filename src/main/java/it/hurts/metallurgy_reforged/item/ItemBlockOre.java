/*==============================================================================
 = Class: ItemBlockOre
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.material.MetalStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemBlockOre extends ItemBlock implements IMetalItem {

    public ItemBlockOre(BlockOre block)
    {
        super(block);
    }

    @Nullable
    @Override
    public MetalStats getMetalStats()
    {
        return ((BlockOre) block).getMetalStats();
    }

    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return isLocked(stack);
    }

    public static boolean isLocked(ItemStack oreStack)
    {
        return oreStack.getTagCompound() != null && oreStack.getTagCompound().getBoolean("locked");
    }

    public static void setLocked(ItemStack oreStack, boolean locked)
    {
        NBTTagCompound oreData = oreStack.getTagCompound();

        if (oreData == null)
            oreData = new NBTTagCompound();

        if (oreData.getBoolean("locked") != locked)
            oreData.setBoolean("locked", locked);

        oreStack.setTagCompound(oreData);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack oreStack = player.getHeldItem(hand);
        if (hasEffect(oreStack))
        {
            return EnumActionResult.FAIL;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
