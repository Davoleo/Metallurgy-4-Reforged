/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyConArmorStats
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.conarm;

import c4.conarm.lib.materials.ArmorMaterials;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import c4.conarm.lib.tinkering.TinkersArmor;
import c4.conarm.lib.traits.IArmorTrait;
import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;

public class MetallurgyConArmorStats extends ArmorMaterials{
	
	public static TrimMaterialStats getTrimStats(Metal metal) {
		float durability = metal.getStats().getArmorStats().getDurability();
		
		return new TrimMaterialStats(durability / 12);
	}
	
	public static CoreMaterialStats getCoreStats(Metal metal) {
		float durability = metal.getStats().getArmorStats().getDurability() / 6;
		float defensePoint = getDefensePoint(metal.getStats().getArmorStats().getDamageReduction());
		
		return new CoreMaterialStats(durability, defensePoint);
	}
	
//	Increment the multiplier to gain a good durability
	public static PlatesMaterialStats getPlatesStats(Metal metal) {
		float durability = metal.getStats().getArmorStats().getDurability() / 3;
		float multiplier = 0.094F;
		float modifier = (float) (Math.sqrt(durability) * multiplier);
		float toughness = metal.getStats().getArmorStats().getToughness();
		
		return new PlatesMaterialStats(modifier > 2 ? modifier * 0.5F : modifier, durability, toughness);
	}
	
//	TODO Modify the maxDefensePoint?
	private static int getDefensePoint(int[] defensePoint) {
		int maxDefensePoint = 0;
		
		for(int i = 0; i < defensePoint.length; i++) {
			maxDefensePoint += defensePoint[i];
		}
		return maxDefensePoint;
	}
	
	public static boolean isThatArmorTrait(EntityPlayer player, String traitToCheck) {
		boolean flag = false;
		
		for(ItemStack stack : player.inventory.armorInventory){
			
			Item item = stack.getItem();
			if(item instanceof TinkersArmor){
				
				NBTTagList list = TagUtil.getTraitsTagList(stack);
				
				for (int i = 0; i < list.tagCount(); i++) {
					ITrait trait = TinkerRegistry.getTrait(list.getStringTagAt(i));
					
					if (trait != null && trait instanceof IArmorTrait) {
						IArmorTrait armorTrait = (IArmorTrait) trait;
						
						if(armorTrait.getIdentifier().equals(traitToCheck))
							flag = true;
					}
				}
			}
		}
		
		return flag;
	}
	
}
