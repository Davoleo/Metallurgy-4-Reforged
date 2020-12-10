/*==============================================================================
 = Class: KrikEffectStorage
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.krik;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class KrikEffectStorage implements Capability.IStorage<IKrikEffect> {

	@Nullable
	@Override
	public NBTBase writeNBT(Capability<IKrikEffect> capability, IKrikEffect instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("height", instance.getHeight());
		return tag;
	}

	@Override
	public void readNBT(Capability<IKrikEffect> capability, IKrikEffect instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound tag = ((NBTTagCompound) nbt);
		instance.setHeight(tag.getInteger("height"));
	}

}
