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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemArmorBase extends ArmorItem implements IHasModel {

	private EnumArmorEffects effect;
	private Enchantment enchantment;
	private int enchantmentLevel;


	public ItemArmorBase(IArmorMaterial material, EquipmentSlotType slot, String name)
	{
		this(material, slot, name, null, 0);
	}

	public ItemArmorBase(IArmorMaterial material, EquipmentSlotType slot, String name, Enchantment enchantment, int enchantmentLevel)
	{
		super(material, slot, new Item.Properties().group(MetallurgyTabs.tabArmor));
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

	/*
	@OnlyIn(Dist.CLIENT)
	public void getSubItems(@Nonnull ItemGroup tab, @Nonnull NonNullList<ItemStack> items)
	{
		if (this.isInGroup(tab)) {
			ItemStack enchantedArmor = new ItemStack(this);
			if(enchantment != null) {
				enchantedArmor.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedArmor);
		}
	}
	*/

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
		return (GeneralConfig.enableAnvilArmorRepair && getRepairStack().isItemEqual(repair)) || super.getIsRepairable(toRepair, repair);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		if(this.effect != null && effect.isActive())
			tooltip.add(new StringTextComponent(this.effect.getLocalized()));
	}
}