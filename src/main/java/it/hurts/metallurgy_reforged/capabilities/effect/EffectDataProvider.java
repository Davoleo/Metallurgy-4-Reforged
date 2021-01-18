/*==============================================================================
 = Class: EffectDataProvider
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EffectDataProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(PlayerEffectData.class)
    public static final Capability<PlayerEffectData> PLAYER_EFFECT_DATA_CAPABILITY = null;

    private final PlayerEffectData instance;

    public EffectDataProvider()
    {
        this.instance = PLAYER_EFFECT_DATA_CAPABILITY != null ? PLAYER_EFFECT_DATA_CAPABILITY.getDefaultInstance() : new PlayerEffectData();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability.equals(PLAYER_EFFECT_DATA_CAPABILITY);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability.equals(PLAYER_EFFECT_DATA_CAPABILITY) ? PLAYER_EFFECT_DATA_CAPABILITY.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return PLAYER_EFFECT_DATA_CAPABILITY != null ? PLAYER_EFFECT_DATA_CAPABILITY.getStorage().writeNBT(PLAYER_EFFECT_DATA_CAPABILITY, this.instance, null) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        if (PLAYER_EFFECT_DATA_CAPABILITY != null)
            PLAYER_EFFECT_DATA_CAPABILITY.getStorage().readNBT(PLAYER_EFFECT_DATA_CAPABILITY, this.instance, null, nbt);
    }

}
