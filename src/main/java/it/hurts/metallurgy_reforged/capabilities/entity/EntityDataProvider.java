/*==============================================================================
 = Class: EntityDataProvider
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.entity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDataProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(EntityData.class)
	public static final Capability<EntityData> ENTITY_DATA_CAPABILITY = null;

	private final EntityData instance;

	public EntityDataProvider()
	{
		this.instance = ENTITY_DATA_CAPABILITY != null ? ENTITY_DATA_CAPABILITY.getDefaultInstance() : new EntityData();
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability.equals(ENTITY_DATA_CAPABILITY);
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == ENTITY_DATA_CAPABILITY ? ENTITY_DATA_CAPABILITY.cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return ENTITY_DATA_CAPABILITY != null ? ENTITY_DATA_CAPABILITY.getStorage().writeNBT(ENTITY_DATA_CAPABILITY, this.instance, null) : null;
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		if (ENTITY_DATA_CAPABILITY != null)
			ENTITY_DATA_CAPABILITY.getStorage().readNBT(ENTITY_DATA_CAPABILITY, this.instance, null, nbt);
	}

}
