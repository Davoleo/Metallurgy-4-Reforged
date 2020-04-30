/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemSwordBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemSwordBase extends ItemSword {

	private EnumToolEffects effect;
	private Enchantment enchantment = null;
	private int enchantmentLevel = -1;

	public ItemSwordBase(ToolMaterial material, String name)
	{
		super(material);
		ItemUtils.initItem(this, name, MetallurgyTabs.tabTool);
	}

	public void setEffect(EnumToolEffects effect)
	{
		this.effect = effect;
	}

	public void setEnchanted(Enchantment enchantment, int enchantmentLevel)
	{
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
	}

	private ItemStack getRepairStack()
	{
		String material = this.getToolMaterialName().toLowerCase();
		Metal metal = Utils.getMetalFromString(material);
		if (metal != null)
			return new ItemStack(metal.getIngot());
		else
			return ItemStack.EMPTY;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair)
	{
		return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (this.effect != null && effect.isActive())
			tooltip.add(this.effect.getLocalized());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			ItemStack enchantedSword = new ItemStack(this);
			if (enchantment != null)
			{
				enchantedSword.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedSword);
		}
	}

	//todo ADD ATTRIBUTE VARIABLES
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
	{
		return super.getItemAttributeModifiers(equipmentSlot);
	}

}
