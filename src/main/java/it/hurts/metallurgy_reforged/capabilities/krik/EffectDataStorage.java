/*==============================================================================
 = Class: EffectDataStorage
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

public class EffectDataStorage implements Capability.IStorage<PlayerEffectData> {

	@Nullable
	@Override
	public NBTBase writeNBT(Capability<PlayerEffectData> capability, PlayerEffectData instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("amordrine_jumps", instance.getAmordrineJumps());
		tag.setInteger("krik_height", instance.getKrikHeight());
		return tag;
	}

	@Override
	public void readNBT(Capability<PlayerEffectData> capability, PlayerEffectData instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = ((NBTTagCompound) nbt);
		instance.setKrikHeight(tag.getInteger("krik_height"));
		instance.setAmordrineJumps(tag.getInteger("amordrine_jumps"));
	}

}
