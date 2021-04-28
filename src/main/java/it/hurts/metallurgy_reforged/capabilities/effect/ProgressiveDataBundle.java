/*==============================================================================
 = Class: ProgressiveDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSyncEffectBundle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public class ProgressiveDataBundle {

    public final int STEP_TICK_DELAY;
    protected final int maxSteps;

    protected String prefixKey;
    protected int currentStep = 0;
    protected boolean paused = false;

    protected long timestamp = -1;

    public ProgressiveDataBundle(String prefixKey, int maxSteps, int stepTickDelay)
    {
        this.prefixKey = prefixKey;
        this.maxSteps = maxSteps;
        this.STEP_TICK_DELAY = stepTickDelay;
    }

    private void sync(@Nullable EntityPlayer player)
    {
        if (player != null && !player.world.isRemote && player instanceof EntityPlayerMP)
            PacketManager.network.sendTo(new PacketSyncEffectBundle(prefixKey, this), (EntityPlayerMP) player);
    }

    public String getPrefixKey()
    {
        return prefixKey;
    }

    public int getCurrentStep()
    {
        return currentStep;
    }

    public long getPrevStepTime()
    {
        return timestamp;
    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setCurrentStep(int currentStep, @Nullable EntityPlayerMP player)
    {
        this.currentStep = currentStep;
        sync(player);
    }

    public int getMaxSteps()
    {
        return maxSteps;
    }

    public boolean isEffectInProgress()
    {
        return currentStep > 0 && !paused;
    }

    public void incrementStep(EntityPlayer player)
    {
        if (currentStep == 0)
            timestamp = player.world.getTotalWorldTime();

        if (currentStep < maxSteps)
        {
            currentStep++;
        }
        else
            resetProgress(player);

        //Debug Log
        //LOGGER.info(getPrefixKey() + ": Current Step " + getCurrentStep() + " Paused: " + isPaused());
        sync(player);
    }

    public void setPaused(boolean paused, EntityPlayer player)
    {
        this.paused = paused;
        sync(player);
    }

    public void resetProgress(EntityPlayer player)
    {
        timestamp = -1;
        currentStep = 0;
        sync(player);
    }

    public byte getType()
    {
        return 0;
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
