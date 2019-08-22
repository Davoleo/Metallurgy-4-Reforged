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

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Mana provider
 *
 * This class is responsible for providing a capability.
 */
public class PunchEffectProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(IPunchEffect.class)
    public static final Capability<IPunchEffect> PUNCH_EFFECT_CAP = null;

    private final IPunchEffect instance;

    public PunchEffectProvider()
    {
        this.instance =  PUNCH_EFFECT_CAP != null ? PUNCH_EFFECT_CAP.getDefaultInstance() : new PunchEffect();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return PUNCH_EFFECT_CAP.orEmpty(capability, LazyOptional.of(() -> this.instance));
    }

    @Override
    public INBT serializeNBT()
    {
        return PUNCH_EFFECT_CAP != null ? PUNCH_EFFECT_CAP.getStorage().writeNBT(PUNCH_EFFECT_CAP, this.instance, null) : null;
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        if(PUNCH_EFFECT_CAP != null)
            PUNCH_EFFECT_CAP.getStorage().readNBT(PUNCH_EFFECT_CAP, this.instance, null, nbt);
    }
}
