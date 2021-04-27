/*==============================================================================
 = Class: BlockInfoDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import jline.internal.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class BlockInfoDataBundle extends ProgressiveDataBundle {

    private BlockPos pos;
    private IBlockState state;

    public BlockInfoDataBundle(String key, BlockPos pos, IBlockState state, int currentStep, int maxSteps, int stepDelay)
    {
        super(key, currentStep, maxSteps, stepDelay);
        this.pos = pos;
        this.state = state;
    }

    @Override
    public boolean isEffectInProgress()
    {
        return super.isEffectInProgress() && pos != null;
    }

    @Override
    public void resetProgress(EntityPlayer player)
    {
        super.resetProgress(player);
        pos = null;
    }

    @Nullable
    public IBlockState getState()
    {
        return state;
    }

    public void setState(IBlockState state)
    {
        this.state = state;
    }

    @Nullable
    public BlockPos getPos()
    {
        return pos;
    }

    public void setPos(BlockPos pos)
    {
        this.pos = pos;
    }

    @Override
    public byte getType()
    {
        return 1;
    }

    @Override
    public void toNBT(NBTTagCompound compound)
    {
        super.toNBT(compound);
        if (pos != null)
            compound.setLong(prefixKey + "_pos", pos.toLong());
    }

    @Override
    public void fromNBT(NBTTagCompound compound)
    {
        super.fromNBT(compound);

        if (compound.hasKey(prefixKey + "_pos"))
            pos = BlockPos.fromLong(compound.getLong(prefixKey + "_pos"));
        else
            pos = null;
    }
}
