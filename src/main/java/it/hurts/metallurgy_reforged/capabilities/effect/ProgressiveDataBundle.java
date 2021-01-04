/*==============================================================================
 = Class: ProgressiveDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class ProgressiveDataBundle {

    private String key;
    private BlockPos pos;
    private IBlockState state;
    private int currentStep;
    private final int maxSteps;

    public ProgressiveDataBundle(String key, BlockPos pos, IBlockState state, int currentStep, int maxSteps)
    {
        this.key = key;
        this.pos = pos;
        this.state = state;
        this.currentStep = currentStep;
        this.maxSteps = maxSteps;
    }

    public String getKey()
    {
        return key;
    }

    public IBlockState getState()
    {
        return state;
    }

    public void setState(IBlockState state)
    {
        this.state = state;
    }

    public BlockPos getPos()
    {
        return pos;
    }

    public void setPos(BlockPos pos)
    {
        this.pos = pos;
    }

    public int getCurrentStep()
    {
        return currentStep;
    }

    public int getMaxSteps()
    {
        return maxSteps;
    }

    public boolean isEffectInProgress()
    {
        return currentStep > 0 && pos != null;
    }

    public void incrementStep()
    {
        if (currentStep < maxSteps)
            currentStep++;
        else
            resetProgress();
    }

    public void resetProgress()
    {
        currentStep = 0;
        pos = null;
    }

    public void toNBT(NBTTagCompound compound)
    {
        compound.setInteger(key + "_current_step", currentStep);
        if (pos != null)
            compound.setLong(key + "_pos", pos.toLong());
    }

    public void fromNBT(NBTTagCompound compound)
    {
        currentStep = compound.getInteger(key + "_current_step");

        if (compound.hasKey(key + "_pos"))
            pos = BlockPos.fromLong(compound.getLong(key + "_pos"));
        else
            pos = null;
    }
}
