/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EventUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EventUtils {
	
//	method to check if player wears the complete ArmorEffectHandler.
	public static boolean isPlayerWearingArmor(PlayerEntity pl,Item[] armor)
	{
			
		boolean flag = true;
			
		  List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());
		  for(int i = 0; i < list.size();i++) {
		  if(!list.get(i).getItem().equals(armor[3 - i]))
           flag = false;
		   }
		 return flag;
		}
		
//  get Specific ArmorEffectHandler Equip [3 = helmet,2 = chest,1 = legs, boots = 0]
	public static boolean isPlayerWearingSpecificArmorPiece(PlayerEntity pl,int index,Item armorEquip)
	{			
		List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());	      
	    return list.get(index).getItem().equals(armorEquip);
	}

	/**
	 * @param pl EntityPlayer
	 * @param armor An array with all armor pieces
	 * @return 	The number of pieces of armor worn by the player
	 */
	public static int getArmorPiecesCount(PlayerEntity pl, Item[] armor){
		List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());

		int counter = 0;

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getItem().equals(armor[3 - i])){
				counter++;
			}
		}

		return counter;
	}

}
