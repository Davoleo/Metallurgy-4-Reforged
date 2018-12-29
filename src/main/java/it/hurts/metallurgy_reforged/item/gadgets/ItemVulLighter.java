package it.hurts.metallurgy_reforged.item.gadgets;

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

/*************************************************
 * Author: Davoleo
 * Date / Hour: 28/12/2018 / 21:58
 * Class: ItemVulLighter
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemVulLighter extends ItemIgnLighter {

    public ItemVulLighter(String name)
    {
        super(name);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!player.isSneaking())
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        else
        {
            BlockPos targetPos = pos.offset(facing);
            ItemStack item = player.getHeldItem(hand);

            if (player.canPlayerEdit(targetPos, facing, item) && worldIn.mayPlace(Blocks.LAVA, targetPos, false, facing, player))
            {
                worldIn.playSound(player, targetPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1, 1);
                IBlockState state = Blocks.LAVA.getStateForPlacement(worldIn, targetPos, facing, hitX, hitY, hitZ, 0, player, hand);
                worldIn.setBlockState(targetPos, state);
            player.getCooldownTracker().setCooldown(this, 1200);

            return EnumActionResult.SUCCESS;
        }

            return EnumActionResult.FAIL;

        }
    }

    @Override
    public void registerItemModel()
    {
        super.registerItemModel(this, 0, "gadget");
    }
}
