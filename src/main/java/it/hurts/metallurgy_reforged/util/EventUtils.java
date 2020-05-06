/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EventUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class EventUtils {

	/**
	 * @param player The player who may be wearing the armor
	 * @param metal  The metal the armor is made of
	 *
	 * @return whether a player is wearing the complete armor set
	 */
	public static boolean isPlayerWearingArmor(EntityPlayer player, Metal metal)
	{
		boolean fullArmored = true;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
			{
				if (player.getItemStackFromSlot(slot).getItem() != metal.getArmorPiece(slot))
				{
					fullArmored = false;
				}
			}
		}

		return fullArmored;
	}

	/**
	 * @param player     The player who may be wearing the armor piece
	 * @param slot       The slot in which the player may be wearing a specific armor piece
	 * @param armorEquip The armor item the player may be wearing in the specified slot
	 *
	 * @return whether the player is wearing a specific armor item in a specific Equipment Slot
	 */
	public static boolean isPlayerWearingSpecificArmorPiece(EntityPlayer player, EntityEquipmentSlot slot, Item armorEquip)
	{
		return player.inventory.armorInventory.get(slot.getIndex()).getItem() == armorEquip;
	}

	/**
	 * @param pl    EntityPlayer
	 * @param armor An array with all armor pieces
	 *
	 * @return The number of pieces of armor worn by the player
	 */
	public static int getArmorPiecesCount(EntityPlayer pl, Item[] armor)
	{
		List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());

		int counter = 0;

		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getItem().equals(armor[3 - i]))
			{
				counter++;
			}
		}

		return counter;
	}

}
