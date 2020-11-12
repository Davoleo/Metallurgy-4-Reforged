/*==============================================================================
 = Class: ItemCeruclaseShield
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCeruclaseShield extends ItemShieldBase {

    public ItemCeruclaseShield()
    {
        super("ceruclase_shield", 500);
    }

    @Override
    public int getItemEnchantability()
    {
        return 18;
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 200;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {

        ItemStack stack = playerIn.getHeldItem(handIn);
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null)
            tag = new NBTTagCompound();

        BlockPos playerPos = playerIn.getPosition();

        if (!tag.hasKey("playerX")) {
            tag.setInteger("playerX", playerPos.getX());
            tag.setInteger("playerY", playerPos.getY());
            tag.setInteger("playerZ", playerPos.getZ());
            stack.setTagCompound(tag);

            playerIn.fallDistance /= 2F;
            manageShield(worldIn, new BlockPos.MutableBlockPos(playerPos), false);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(@Nonnull ItemStack oldStack, @Nonnull ItemStack newStack, boolean slotChanged)
    {
        return false;
    }

    //---------------------
    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        ((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(this, 60);
        removeTagAndShield(worldIn, stack);
    }

    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!isSelected)
            removeTagAndShield(worldIn, stack);


        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean onDroppedByPlayer(@Nonnull ItemStack item, @Nonnull EntityPlayer player)
    {
        removeTagAndShield(player.world, item);
        return super.onDroppedByPlayer(item, player);
    }

    private void removeTagAndShield(World world, ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag.hasKey("playerX"))
        {
            BlockPos playerPos = new BlockPos(
                    tag.getInteger("playerX"),
                    tag.getInteger("playerY"),
                    tag.getInteger("playerZ")
            );

            tag.removeTag("playerX");
            tag.removeTag("playerY");
            tag.removeTag("playerZ");

            manageShield(world, new BlockPos.MutableBlockPos(playerPos), true);
        }
    }
    //---------------------

    private static final int RANGE = 5;

    private void manageShield(World world, BlockPos.MutableBlockPos playerPos, boolean destroy)
    {
        if (world.isRemote)
            return;

        for (int x = -RANGE -1; x < RANGE + 1; x++)
        {
            for (int y = -RANGE - 1; y < RANGE + 1; y++)
            {
                for (int z = -RANGE - 1; z < RANGE + 1; z++)
                {
                    BlockPos blockPos = playerPos.add(x,y,z);
                    IBlockState blockState = world.getBlockState(blockPos);
                    if(Math.ceil(blockPos.getDistance(playerPos.getX(), playerPos.getY(), playerPos.getZ())) == RANGE)
                    {
                        boolean canPlaceIce = world.mayPlace(Blocks.DIRT, blockPos, true, EnumFacing.UP, null) || blockState.getBlock() instanceof BlockBush;

                        if(!destroy) {
                            if (canPlaceIce)
                                world.setBlockState(blockPos, Blocks.ICE.getDefaultState());
                        }
                        else if(blockState.getBlock() == Blocks.ICE)
                        {
                            world.setBlockToAir(blockPos);
                        }
                    }
                }
            }
        }

        if (!destroy)
            world.playSound(null, playerPos, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.BLOCKS, 1F, 2F);
        else
            world.playSound(null, playerPos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 0.75F, 1F);
    }
}
