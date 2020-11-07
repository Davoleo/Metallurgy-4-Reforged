/*==============================================================================
 = Class: PhosphorusLampSavedData
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.block.gadget;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;
import java.util.List;

public class PhosphorusLampSavedData extends WorldSavedData {

	private static final String DATA_NAME = Metallurgy.MODID + "_PhosphorusLampRange";

	private List<BlockPos> lamps = Lists.newArrayList();

	public PhosphorusLampSavedData()
	{
		super(DATA_NAME);
	}

	//Used by Forge in MapStorage#getOrLoadData
	@SuppressWarnings("unused")
	public PhosphorusLampSavedData(String dataId)
	{
		super(dataId);
	}

	@Override
	public void readFromNBT(@Nonnull NBTTagCompound nbt)
	{
		int i = 0;
		while (nbt.hasKey("lamp_" + i))
		{
			lamps.add(BlockPos.fromLong(nbt.getLong("lamp_" + i)));
			i++;
		}

		markDirty();
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
	{
		for (int i = 0; i < lamps.size(); i++)
			compound.setLong("lamp_" + i, lamps.get(i).toLong());
		return compound;
	}

	public void addLanternToList(BlockPos lantern)
	{
		//System.out.println("Added:\t" + lantern);
		lamps.add(lantern);
		markDirty();
	}

	public void removeLanternFromList(BlockPos lantern)
	{
		//System.out.println("Removed:\t" + lantern);
		lamps.remove(lantern);
		markDirty();
	}

	public boolean isEntityInRange(BlockPos entityPos, World world)
	{
		boolean found = false;
		int i = 0;

		while (!found && i < lamps.size())
		{
			BlockPos block = lamps.get(i);
			if (world.isBlockLoaded(block) && block.getDistance(entityPos.getX(), entityPos.getY(), entityPos.getZ()) <= 8)
				found = true;

			i++;
		}
		return found;
	}

	public static PhosphorusLampSavedData getInstance(World world)
	{
		MapStorage storage = world.getPerWorldStorage();
		PhosphorusLampSavedData instance = ((PhosphorusLampSavedData) storage.getOrLoadData(PhosphorusLampSavedData.class, DATA_NAME));

		if (instance == null)
		{
			instance = new PhosphorusLampSavedData();
			storage.setData(DATA_NAME, instance);
		}

		return instance;
	}

}
