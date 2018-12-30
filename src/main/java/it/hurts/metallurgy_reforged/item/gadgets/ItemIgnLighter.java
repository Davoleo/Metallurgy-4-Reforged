package it.hurts.metallurgy_reforged.item.gadgets;

import it.hurts.metallurgy_reforged.item.ItemBase;
import it.hurts.metallurgy_reforged.util.Utils;
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

/*************************************************
 * Author: Davoleo
 * Date / Hour: 28/12/2018 / 20:11
 * Class: ItemIgnLighter
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemIgnLighter extends ItemBase {

    public ItemIgnLighter(String name)
    {
        super(name);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(facing);
        ItemStack lighter = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos, facing, lighter) || Utils.isFakePlayer(player))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if(worldIn.isAirBlock(pos))
            {

                worldIn.playSound(player, pos, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.north(), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.south(), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.west(), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.east(), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(-1, 0, -1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(-1, 0, 1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(1, 0, -1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(1, 0, 1), Blocks.FIRE.getDefaultState(), 11);

                //TODO : maybe automatize fire creation using a for loop
                /*for(int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);

                        System.out.println("Z: " + pos.getZ());
                    }

                    System.out.println("X: " + pos.getX());
                }*/

            }

            lighter.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public void registerItemModel()
    {
        super.registerItemModel(this, 0, "gadget");
    }
}
