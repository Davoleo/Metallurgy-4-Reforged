/*==============================================================================
 = Class: EventUtils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import jline.internal.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

public class EventUtils {

	/**
	 * @param entity The entity who is wearing the armor
	 * @param metal  The metal the armor is made of
	 *
	 * @return whether a player is wearing the complete armor set
	 */
	public static boolean isEntityWearingArmor(EntityLivingBase entity, Metal metal)
	{
		boolean fullArmored = true;

		for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
		{
			if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && entity.getItemStackFromSlot(slot).getItem() != metal.getArmorPiece(slot))
			{
				fullArmored = false;
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
	 * @param player EntityPlayer
	 * @param armor  An array with all armor pieces
	 *
	 * @return The number of pieces of armor worn by the player
	 */
	public static int getArmorPiecesCount(EntityPlayer player, Item[] armor)
	{
		NonNullList<ItemStack> armorList = player.inventory.armorInventory;

		int count = 0;
		//Reverse for loop because armorList contains armor stacks in reverse order (index 0 are boots)
		for (int i = 0; i < armorList.size(); i++)
		{
			if (armorList.get(3 - i).getItem().equals(armor[i]))
				count++;
		}

		return count;
	}

	@Nullable
	public static Metal getRandomMetalBasedOnDifficulty(World world)
	{

		//Some math Reminders
		//(1 * 1 - 0) * 5 = 5;
		//(2 * 2 - 1) * 5 = 15;
		//(3 * 3 - 2) * 5 = 35;
		//(3^0) * 5 = 5 - 5 * difficulty + 1 = 0;
		//(3^1) * 5 = 15 - 5 * difficulty + 1 = 5;
		//(3^2) * 5 = 45 - 5 * difficulty + 1 = 15;
		//(3^3) * 5 = 135 - 5 * difficulty + 1  = 30;
		float chance = 0;
		switch (world.getDifficulty().getId()) {
			case 0:
				chance = 0;
				break;
			case 1:
				chance = 5;
				break;
			case 2:
				chance = 10;
				break;
			case 3:
				chance = 20;
				break;
		}

		Random random = new Random();

		if ((random.nextFloat() * 100) < chance) {
			Metal[] metalllarray = ModMetals.metalMap.values().stream()
					.filter(metal -> metal != null && metal.hasArmorSet())
					.toArray(Metal[]::new);
			int metalIndex = random.nextInt(metalllarray.length);
			return metalllarray[metalIndex];
		}

		return null;
	}

}
