/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemArmorBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.armor;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemArmorBase extends ItemArmor implements IHasModel {

	private EnumArmorEffects effect;
	private Enchantment enchantment;
	private int enchantmentLevel;


	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name)
	{
		this(material, slot, name, null, 0);
	}

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, Enchantment enchantment, int enchantmentLevel)
	{
		super(material, 0, slot);
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
		ItemUtils.initItem(this, name, MetallurgyTabs.tabArmor, ModArmors.armorList);
	}

	@Nonnull
	@Override
	public String getCategory()
	{
		return "armor";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
	{
		if(this.isInCreativeTab(tab)) {
			ItemStack enchantedArmor = new ItemStack(this);
			if(enchantment != null) {
				enchantedArmor.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedArmor);
		}
	}

	public void setEffect(EnumArmorEffects effect)
	{
		this.effect = effect;
	}

	private ItemStack getRepairStack()
	{
		String material = this.getArmorMaterial().getName().toLowerCase();
		Metal metal = Utils.getMetalFromString(material);
		if (metal != null)
			return new ItemStack(metal.getIngot());
		else return ItemStack.EMPTY;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair)
	{
		return (GeneralConfig.enableAnvilArmorRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(this.effect != null && effect.isActive())
			tooltip.add(this.effect.getLocalized());
	}
}