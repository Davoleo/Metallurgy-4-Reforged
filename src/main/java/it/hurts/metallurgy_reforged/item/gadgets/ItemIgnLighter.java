package it.hurts.metallurgy_reforged.item.gadgets;

import it.hurts.metallurgy_reforged.item.ItemBase;
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
        setMaxDamage(150);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(facing);
        ItemStack lighter = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos, facing, lighter))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

            if (itemRand.nextBoolean())
                createFire(worldIn, pos, player);

            if (!player.isCreative())
                lighter.damageItem(1, player);

            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public void registerItemModel()
    {
        super.registerItemModel("gadget");
    }

    protected void createFire(World worldIn, BlockPos pos, EntityPlayer player)
    {
        final BlockPos START_POS = pos.offset(player.getHorizontalFacing());

        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++) {
                pos = START_POS;
                pos = pos.add(x, 0, z);

                if (worldIn.isAirBlock(pos) && !worldIn.isAirBlock(pos.down()) && !worldIn.isRemote)
                    worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
            }
    }
}
