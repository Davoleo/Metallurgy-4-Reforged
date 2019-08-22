/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemUtils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ModelLoader;

import java.util.List;

public class ItemUtils {

    public static void initItem(Item item, String name, ItemGroup tab, List list)
    {
        //item.setTranslationKey(Metallurgy.MODID + "." + name);
        item.setRegistryName(Metallurgy.MODID, name);
        //item.setCreativeTab(tab);
        if (list != null)
        	list.add(item);
    }

    //method to check if stack is a specific tool Material
    public static boolean isItemStackASpecificToolMaterial(Metal metal, ItemStack toolStack, String... except)
    {

    	Item item = toolStack.getItem();
    	if(!toolStack.isEmpty() && item instanceof ToolItem)
    	{
    		ToolItem tool = (ToolItem) toolStack.getItem();
   		boolean valid = tool.getTier() == metal.getToolMaterial();
    	for(String type : except)
    	{
    		String toolName = metal.getStats().getName() + "_" + type;
    		if(tool.getTranslationKey().equalsIgnoreCase(toolName))
    		 valid = false;
    	}
    	  return valid;
    	}
    	return false;
    }

//	public static boolean equalsWildcard(ItemStack wild, ItemStack check)
//	{
//		if (wild.isEmpty() || check.isEmpty())
//		{
//			return check.equals(wild);
//		}
//
//		return wild.getItem() == check.getItem()
//				&& (wild.getItemDamage() == OreDictionary.WILDCARD_VALUE
//				|| check.getItemDamage() == OreDictionary.WILDCARD_VALUE
//				|| wild.getItemDamage() == check
//				.getItemDamage());
//	}

	public static ItemStack getToolRepairStack(ToolItem tool)
	{
		String material = tool.getTier().toString().toLowerCase();
		Metal metal = Utils.getMetalFromString(material);
		if (metal != null)
			return new ItemStack(metal.getIngot());
		else return ItemStack.EMPTY;
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerCustomItemModel(Item item, int meta)
	{
		//ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void editInventoryStackSize(NonNullList<ItemStack> inventory, int slot, int amount)
	{
		if (slot >= 0 && slot < inventory.size() && !inventory.get(slot).isEmpty()) {
			inventory.get(slot).grow(amount);
			if (inventory.get(slot).getCount() <= 0)
				inventory.set(slot, ItemStack.EMPTY);
		}
	}

	//check if itemstack is a specific armor material
	 public static boolean isItemStackSpecificArmorMaterial(Metal metal,ItemStack armor)
	 {
		 return !armor.isEmpty() && armor.getItem() instanceof ItemArmorBase && ((ItemArmorBase)armor.getItem()).getArmorMaterial().getName().equalsIgnoreCase(metal.getArmorMaterial().getName());
	 }

	 @OnlyIn(Dist.CLIENT)
	public static void registerCustomItemModel(Item item, int meta, String subdir)
	{
		//ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + subdir + (!subdir.equals("") ? "/" : "") + item.getRegistryName().getPath(), "inventory"));
	}

	/**
	 * @param ingot the ingot you want the metal of
	 * @return The metal the parameter ingot is made of (null if metal doesn't exist)
	 */
	public static Metal getMetalFromIngot(ItemStack ingot)
		{
			for (Metal metal : ModMetals.metalList)
			{
				if (metal.getIngot() == ingot.getItem())
					return metal;
			}

			return null;
		}

	}
