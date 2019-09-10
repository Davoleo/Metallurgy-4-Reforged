/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PunchEffectStorage
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.capabilities.punch;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;


/**
 * This class is responsible for saving and reading punch effect data from or to server
 */
public class PunchEffectStorage implements IStorage<IPunchEffect> {

	@Override
	public NBTBase writeNBT(Capability<IPunchEffect> capability, IPunchEffect instance, EnumFacing side)
	{

		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("ticks", instance.getHitTicks());
		tag.setBoolean("hasAiDisabled", instance.isAIDisabled());
		tag.setInteger("knockbackTicks", instance.getKnockbackTicks());

		return tag;
	}

	@Override
	public void readNBT(Capability<IPunchEffect> capability, IPunchEffect instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setHitTicks(tag.getInteger("ticks"));
		instance.setNoAI(tag.getBoolean("hasAiDisabled"));
		instance.setKnockbackTicks(tag.getInteger("knockbackTicks"));
	}

}
