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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemUtils {

    public static void initItem(Item item, String name, CreativeTabs tab, List list)
    {
        item.setTranslationKey(Metallurgy.MODID + "." + name);
        item.setRegistryName(Metallurgy.MODID, name);
        item.setCreativeTab(tab);
        list.add(item);
    }

    //method to check if stack is a specific tool Material
    public static boolean isItemStackASpecificToolMaterial(Metal metal, ItemStack toolStack, String... except)
    {

    	Item item = toolStack.getItem();
    	if(!toolStack.isEmpty() && item instanceof ItemTool)
    	{
   		ItemTool tool = (ItemTool) toolStack.getItem();
   		boolean valid = tool.getToolMaterialName().equalsIgnoreCase(metal.getToolMaterial().name());
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

	public static boolean equalsWildcard(ItemStack wild, ItemStack check)
	{
		if (wild.isEmpty() || check.isEmpty())
		{
			return check == wild;
		}

		return wild.getItem() == check.getItem()
				&& (wild.getItemDamage() == OreDictionary.WILDCARD_VALUE
				|| check.getItemDamage() == OreDictionary.WILDCARD_VALUE
				|| wild.getItemDamage() == check
				.getItemDamage());
	}

	public static ItemStack getToolRepairStack(ItemTool tool)
	{
		String material = tool.getToolMaterialName().toLowerCase();
		Metal metal = Utils.getMetalFromString(material);
		if (metal != null)
			return new ItemStack(metal.getIngot());
		else return ItemStack.EMPTY;
	}

	@SideOnly(Side.CLIENT)
	public static void registerCustomItemModel(Item item, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
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

		@SideOnly(Side.CLIENT)
		public static void registerCustomItemModel(Item item, int meta, String subdir)
		{
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":" + subdir + "/" + item.getRegistryName().getPath(), "inventory"));
		}
	}
