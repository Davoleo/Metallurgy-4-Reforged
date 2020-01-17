/*
 * -------------------------------------------------------------------------------------------------------
 * Class: KrikEffectProvider
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.capabilities.krik;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class KrikEffectProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IKrikEffect.class)
	public static final Capability<IKrikEffect> KRIK_EFFECT_CAPABILITY = null;

	private final IKrikEffect instance;

	public KrikEffectProvider()
	{
		this.instance = KRIK_EFFECT_CAPABILITY != null ? KRIK_EFFECT_CAPABILITY.getDefaultInstance() : new KrikEffect();
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability.equals(KRIK_EFFECT_CAPABILITY);
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == KRIK_EFFECT_CAPABILITY ? KRIK_EFFECT_CAPABILITY.cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return KRIK_EFFECT_CAPABILITY != null ? KRIK_EFFECT_CAPABILITY.getStorage().writeNBT(KRIK_EFFECT_CAPABILITY, this.instance, null) : null;
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		if (KRIK_EFFECT_CAPABILITY != null)
			KRIK_EFFECT_CAPABILITY.getStorage().readNBT(KRIK_EFFECT_CAPABILITY, this.instance, null, nbt);
	}

}
