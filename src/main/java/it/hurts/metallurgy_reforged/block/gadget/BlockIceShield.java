/*==============================================================================
 = Class: BlockIceShield
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block.gadget;

import it.hurts.metallurgy_reforged.util.BlockUtils;
import it.hurts.metallurgy_reforged.util.Constants;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockIceShield extends BlockBreakable {


    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

    public BlockIceShield()
    {
        super(Material.ICE, false);

        BlockUtils.initBlock(this, "ice_shield", null, 0.5F, 2000f, Constants.Tools.PICKAXE, 0);
        this.setDefaultSlipperiness(0.98F);
        this.setLightOpacity(3);
        this.setSoundType(SoundType.GLASS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));

    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, MathHelper.clamp(meta, 0, 3));
    }

    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{ AGE });
    }

    @Override
    public void updateTick(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state, @Nonnull Random rand)
    {
        int age = state.getValue(AGE);
        if (age < 3)
        {
            worldIn.scheduleUpdate(pos, this, 10 + rand.nextInt(11));
            worldIn.setBlockState(pos, state.withProperty(AGE, age + 1), 2);
        }
        else
        {
            worldIn.setBlockToAir(pos);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 0.15F, 1.25F);
        }

    }

    @Override
    public int quantityDropped(@Nonnull Random random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    public EnumPushReaction getPushReaction(@Nonnull IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

}
