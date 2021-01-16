/*==============================================================================
 = Class: ExtraFilledDataBundle
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

// TODO: 16/01/2021 I don't like this at all, I'll have to think of a better way of implementing this soon
public abstract class ExtraFilledDataBundle<E extends NBTBase> extends ProgressiveDataBundle {

    private E extraVariable;

    public ExtraFilledDataBundle(String key, int currentStep, int maxSteps, @Nonnull E extraVariable)
    {
        super(key, currentStep, maxSteps);
        this.extraVariable = extraVariable;
    }

    @Override
    public void toNBT(NBTTagCompound compound)
    {
        super.toNBT(compound);
        if (extraVariable != null)
            compound.setTag(key + "_extra", extraVariable);
    }

    @Override
    public void fromNBT(NBTTagCompound compound)
    {
        super.fromNBT(compound);
        extraVariable = (E) compound.getTag(key + "_extra");
    }

    @Override
    public abstract boolean isEffectInProgress();

    @Nonnull
    public E getExtra()
    {
        if (extraVariable == null)
            return (E) new NBTTagCompound();
        return extraVariable;
    }

    public void setExtra(@Nonnull E extraVariable)
    {
        this.extraVariable = extraVariable;
    }
}
