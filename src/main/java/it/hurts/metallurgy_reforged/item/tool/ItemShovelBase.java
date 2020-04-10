/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemShovelBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

//TODO: if instanceof ItemAxeBase -> modelSubDir = tool/shovel
public class ItemShovelBase extends ItemSpade {

	private EnumToolEffects effect;
	private Enchantment enchantment = null;
	private int enchantmentLevel = -1;

	public ItemShovelBase(ToolMaterial material, String name)
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

	@Override
	public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair)
	{
		return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(ItemUtils.getToolRepairStack(this), repair)) || super.getIsRepairable(toRepair, repair);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (this.effect != null && effect.isActive())
			tooltip.add(effect.getLocalized());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			ItemStack enchantedShowel = new ItemStack(this);
			if (enchantment != null)
			{
				enchantedShowel.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedShowel);
		}
	}
}
