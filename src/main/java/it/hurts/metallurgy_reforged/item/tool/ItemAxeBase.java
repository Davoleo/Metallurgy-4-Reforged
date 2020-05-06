/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemAxeBase
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
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ToolStats;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemAxeBase extends ItemAxe {

	private EnumToolEffects effect;
	private Enchantment enchantment = null;
	private int enchantmentLevel = -1;

	private final MetalStats metalStats;

	public ItemAxeBase(ToolMaterial material, MetalStats metalStats)
	{
		super(material, material.getAttackDamage() + 4, -2.5F - (material.getAttackDamage() / 10));
		ItemUtils.initItem(this, metalStats.getName() + "_axe", MetallurgyTabs.tabTool);
		this.metalStats = metalStats;
	}

	public ItemAxeBase setEffect(EnumToolEffects effect)
	{
		this.effect = effect;
		return this;
	}

	public ItemAxeBase setEnchanted(Enchantment enchantment, int enchantmentLevel)
	{
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
		return this;
	}

	@Override
	public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair)
	{
		return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(ItemUtils.getToolRepairStack(this), repair)) || super.getIsRepairable(toRepair, repair);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
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
			ItemStack enchantedAxe = new ItemStack(this);
			if (enchantment != null)
			{
				enchantedAxe.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedAxe);
		}
	}

	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
		{
			ToolStats toolStats = metalStats.getToolStats();

			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(Constants.ModAttributes.MAX_HEALTH, "Metallurgy Axe Max Health", toolStats.getMaxHealth(), 0));
			// FIXME: 06/05/2020 movement speed and attack damage do not override the default values
			multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(Constants.ModAttributes.MOVEMENT_SPEED, "Metallurgy Axe Movement Speed", toolStats.getMovementSpeed(), 0));
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Metallurgy Axe Attack Damage", toolStats.getAttackDamage(), 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Metallurgy Axe Attack Speed", toolStats.getAttackSpeed(), 0));
			multimap.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(Constants.ModAttributes.REACH_DISTANCE, "Metallurgy Axe Reach Distance", toolStats.getReachDistance(), 0));
		}

		return multimap;

	}

}
