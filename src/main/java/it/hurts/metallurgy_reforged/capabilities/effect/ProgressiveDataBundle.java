/*==============================================================================
 = Class: ProgressiveDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public class ProgressiveDataBundle {

    protected final int maxSteps;
    protected String prefixKey;
    protected int currentStep;
    protected boolean paused = false;

    public ProgressiveDataBundle(String prefixKey, int currentStep, int maxSteps)
    {
        this.prefixKey = prefixKey;
        this.currentStep = currentStep;
        this.maxSteps = maxSteps;
    }

    public String getPrefixKey()
    {
        return prefixKey;
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
        return currentStep > 0 && !paused;
    }

    public void incrementStep()
    {
        if (currentStep < maxSteps)
            currentStep++;
        else
            resetProgress();
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }

    public void resetProgress()
    {
        currentStep = 0;
    }

    @OverridingMethodsMustInvokeSuper
    public void toNBT(NBTTagCompound compound)
    {
        compound.setInteger(prefixKey + "_current_step", currentStep);
        compound.setBoolean(prefixKey + "_paused", paused);
    }

    @OverridingMethodsMustInvokeSuper
    public void fromNBT(NBTTagCompound compound)
    {
        currentStep = compound.getInteger(prefixKey + "_current_step");
        paused = compound.getBoolean(prefixKey + "_paused");
    }
}
