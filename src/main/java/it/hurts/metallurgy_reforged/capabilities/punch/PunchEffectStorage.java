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

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;



/**
 * This class is responsible for saving and reading punch effect data from or to server
 */
public class PunchEffectStorage implements IStorage<IPunchEffect>
{

	@Override
	public INBT writeNBT(Capability<IPunchEffect> capability, IPunchEffect instance, Direction side) {
		
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("ticks", instance.getHitTicks());
		tag.putBoolean("hasAiDisabled", instance.isAIDisabled());
		tag.putInt("knockbackTicks", instance.getKnockbackTicks());
	    
		return tag;
	}

	@Override
	public void readNBT(Capability<IPunchEffect> capability, IPunchEffect instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		instance.setHitTicks(tag.getInt("ticks"));
		instance.setNoAI(tag.getBoolean("hasAiDisabled"));
		instance.setKnockbackTicks(tag.getInt("knockbackTicks"));
	}

}
