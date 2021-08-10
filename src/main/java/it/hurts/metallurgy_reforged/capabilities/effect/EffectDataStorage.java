/*==============================================================================
 = Class: EffectDataStorage
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.effect;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EffectDataStorage implements Capability.IStorage<PlayerEffectData> {

	@Nullable
	@Override
	public NBTBase writeNBT(Capability<PlayerEffectData> capability, PlayerEffectData instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();

		instance.effectBundles.values().forEach(bundle -> bundle.toNBT(tag));
		tag.setInteger("amordrine_jumps", instance.getAmordrineJumps());

		tag.setInteger("desichalkos_armor_time_without_damage", instance.desichalkosTimeWithoutTakingDamage);
		tag.setInteger("desichalkos_armor_absorb_level", instance.desichalkosAbsorbLevel);

		tag.setInteger("krik_height", instance.krikHeight);

		tag.setInteger("quicksilver_armor_step", instance.quicksilverArmorStep);

		return tag;
	}

	@Override
	public void readNBT(Capability<PlayerEffectData> capability, PlayerEffectData instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound tag = ((NBTTagCompound) nbt);

		instance.effectBundles.values().forEach(bundle -> bundle.fromNBT(tag));
		instance.setAmordrineJumps(tag.getInteger("amordrine_jumps"));

		instance.desichalkosTimeWithoutTakingDamage = tag.getInteger("desichalkos_armor_time_without_damage");
		instance.desichalkosAbsorbLevel = tag.getInteger("desichalkos_armor_absorb_level");

		instance.krikHeight = tag.getInteger("krik_height");

		instance.quicksilverArmorStep = tag.getInteger("quicksilver_armor_step");
	}

}
