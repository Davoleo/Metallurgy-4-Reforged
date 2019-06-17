/*
 * -------------------------------------------------------------------------------------------------------
 * Class: BlockPhosphorusLamp
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block;

import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockPhosphorusLamp extends BlockOrientable {

    public BlockPhosphorusLamp()
    {
        super(Material.IRON, "phosphorus_lamp", MetallurgyTabs.tabSpecial);
        setSoundType(SoundType.METAL);
        setHardness(4);
        setHarvestLevel("pickaxe", 1);
    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Nonnull
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(Constants.PHOSPHORUS_LAMP);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing facing = state.getValue(FACING);
        switch (facing)
        {
            case NORTH:
                return new AxisAlignedBB(0.3, 0.09, 0.85, 0.7, 0.6, 0.45);
            case SOUTH:
                return new AxisAlignedBB(0.3, 0.09, 0.15, 0.7, 0.6, 0.55);
            case WEST:
                return new AxisAlignedBB(0.85, 0.09, 0.3, 0.45, 0.6, 0.7);
            case EAST:
                return new AxisAlignedBB(0.15, 0.09, 0.3, 0.55, 0.6, 0.7);
            case UP:
                return new AxisAlignedBB(0.30, 0.0, 0.30, 0.69, 0.5, 0.69);
            case DOWN:
                return new AxisAlignedBB(0.30, 0.13, 0.30, 0.69, 0.63, 0.69);
            default:
                return super.getBoundingBox(state, source, pos);
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess blockAccess, BlockPos pos)
    {
        if (checkLightPollution(pos))
            return 8;
        else
            return 0;
    }

    private boolean checkLightPollution(BlockPos pos)
    {
        World world = Minecraft.getMinecraft().world;
        boolean isDay = (world.getWorldTime() % 23300) <= 12800;
        EnumFacing freeFace = BlockUtils.getFreeFacing(world, pos);
        int skyLightPollution;
        int blockLightPollution;
        if (freeFace != null) {
            skyLightPollution = world.getLightFor(EnumSkyBlock.SKY, pos.offset(freeFace));
            blockLightPollution = world.getLightFor(EnumSkyBlock.BLOCK, pos.offset(freeFace));
        }
        else {
            skyLightPollution = 0;
            blockLightPollution = 0;
        }
        return  !isDay || skyLightPollution < 6 || blockLightPollution == 0;

    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        switch (state.getValue(FACING))
        {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case DOWN:
                i = i | 5;
                break;
            case UP:
                i = i | 6;
                break;
        }
        return i;
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        switch (meta)
        {
            case 1:
                state = state.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                state = state.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                state = state.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                state = state.withProperty(FACING, EnumFacing.NORTH);
                break;
            case 5:
                state = state.withProperty(FACING, EnumFacing.DOWN);
                break;
            case 6:
                state = state.withProperty(FACING, EnumFacing.UP);
                break;
        }

        return state;
    }
}
