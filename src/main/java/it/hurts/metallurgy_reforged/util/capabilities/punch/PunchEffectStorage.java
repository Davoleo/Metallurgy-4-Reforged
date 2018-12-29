package it.hurts.metallurgy_reforged.util.capabilities.punch;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;



/**
 * This class is responsible for saving and reading punch effect data from or to server
 */
public class PunchEffectStorage implements IStorage<IPunchEffect>
{

	@Override
	public NBTBase writeNBT(Capability<IPunchEffect> capability, IPunchEffect instance, EnumFacing side) {
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("ticks", instance.getHitTicks());
		tag.setBoolean("hasNoClip", instance.hasNoClip());
		return tag;
	}

	@Override
	public void readNBT(Capability<IPunchEffect> capability, IPunchEffect instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setHitTicks(tag.getInteger("ticks"));
		instance.setNoClip(tag.getBoolean("hasNoClip"));
		
	}

}
