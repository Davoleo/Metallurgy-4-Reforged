/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PunchEffectProvider
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.capabilities.punch;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;

/**
 * Mana provider
 *
 * This class is responsible for providing a capability.
 */
public class PunchEffectProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IPunchEffect.class)
    public static final Capability<IPunchEffect> PUNCH_EFFECT_CAP = null;

    private IPunchEffect instance = PUNCH_EFFECT_CAP != null ? PUNCH_EFFECT_CAP.getDefaultInstance() : new PunchEffect(); //Oh well, it does work �\_(?)_/�

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing)
    {
        return capability.equals(PUNCH_EFFECT_CAP);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing)
    {
        return capability.equals(PUNCH_EFFECT_CAP) ? PUNCH_EFFECT_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return PUNCH_EFFECT_CAP != null ? PUNCH_EFFECT_CAP.getStorage().writeNBT(PUNCH_EFFECT_CAP, this.instance, null) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        if(PUNCH_EFFECT_CAP != null)
            PUNCH_EFFECT_CAP.getStorage().readNBT(PUNCH_EFFECT_CAP, this.instance, null, nbt);
    }
}
