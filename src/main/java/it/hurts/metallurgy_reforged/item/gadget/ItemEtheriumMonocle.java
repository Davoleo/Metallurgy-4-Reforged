/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemEtheriumMonocle
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.gadget;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.entity.model.MonocleModel;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemEtheriumMonocle extends ItemExtra {

	public ItemEtheriumMonocle()
	{
		super("etherium_monocle", MetallurgyTabs.tabSpecial, "gadget");
	}

	@Nullable
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
	{
		if (armorSlot == EntityEquipmentSlot.HEAD)
			return new MonocleModel();
		else
			return null;
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
	{
		if (slot == EntityEquipmentSlot.HEAD)
			return Metallurgy.MODID + ":textures/models/etherium_monocle.png";
		else
			return null;
	}

	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity)
	{
		if (stack.getItem() == this && entity instanceof EntityPlayer)
		{
			return armorType == EntityEquipmentSlot.HEAD;
		}
		else
			return false;
	}

}
