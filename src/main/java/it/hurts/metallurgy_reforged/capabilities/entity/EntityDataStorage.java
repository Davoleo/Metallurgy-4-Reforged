/*==============================================================================
 = Class: EntityDataStorage
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.capabilities.entity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EntityDataStorage implements Capability.IStorage<EntityData> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<EntityData> capability, EntityData instance, EnumFacing side)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("hasTraded", instance.wasSnatched);
        if (instance.snatchableBlock != null)
            tag.setInteger("tradeBlockID", Block.getStateId(instance.snatchableBlock));
        tag.setBoolean("initialized", instance.initialized);

        return tag;
    }

    @Override
    public void readNBT(Capability<EntityData> capability, EntityData instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.wasSnatched = tag.getBoolean("hasTraded");
        if (tag.hasKey("tradeBlockID"))
            instance.snatchableBlock = Block.getStateById(tag.getInteger("tradeBlockID"));
        instance.initialized = tag.getBoolean("initialized");
    }

}
