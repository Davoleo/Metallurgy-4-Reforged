/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OffHandCustomSlot
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class OffHandCustomSlot extends Slot {

	public EntityPlayer player;

	public OffHandCustomSlot(EntityPlayer pl)
	{
		super(pl.inventory, 40, 77, 62);
	}

	@Nullable
	@SideOnly(Side.CLIENT)
	public String getSlotTexture()
	{
		return "minecraft:items/empty_armor_slot_shield";
	}

	public boolean canTakeStack(EntityPlayer playerIn)
	{
		return false;
	}

}
