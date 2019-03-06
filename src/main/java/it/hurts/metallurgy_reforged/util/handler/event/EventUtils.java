package it.hurts.metallurgy_reforged.util.handler.event;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EventUtils {
	
//	method to check if player wears the complete Armor.
	public static boolean isPlayerWearingArmor(EntityPlayer pl,Item[] armor)
	{
			
		boolean flag = true;
			
		  List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());
		  for(int i = 0; i < list.size();i++) {
		  if(!list.get(i).getItem().equals(armor[3 - i]))
           flag = false;
		   }
		 return flag;
		}
		
//  get Specific Armor Equip [3 = helmet,2 = chest,1 = legs, boots = 0]
	public static boolean isPlayerWearingSpecificArmorPiece(EntityPlayer pl,int index,Item armorEquip)
	{			
		List<ItemStack> list = Lists.newArrayList(pl.getArmorInventoryList().iterator());	      
	    return list.get(index).getItem().equals(armorEquip);
	}

}
