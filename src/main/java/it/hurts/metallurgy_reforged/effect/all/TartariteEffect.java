/*==============================================================================
 = Class: TartariteEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TartariteEffect extends BaseMetallurgyEffect {

	public TartariteEffect()
	{
		super(ModMetals.TARTARITE);
		setupModelOverrides((stack, worldIn, entityIn) -> {
			if (stack.getTagCompound() == null)
				return 0F;

			Metal paragonMetal = getParagonMetal(stack);
			if (paragonMetal == null)
				return 0F;

			switch (paragonMetal.toString())
			{
				case "celenegil":
					return stack.getTagCompound().getBoolean("glory_seeker") ? 1F : 0F;
				case "electrum":
					return stack.getTagCompound().getBoolean("voltage_control") ? 1F : 0F;
			}

			return 0F;
		});
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
	{
		Metal metal = getParagonMetal(stack);
		if (metal == null)
			return;
		Map<EnumEffectCategory, BaseMetallurgyEffect> metalEffects = MetallurgyEffects.effects.row(metal);

		EnumTools toolType = EnumTools.byInstance(stack.getItem());
		if (toolType != null)
		{
			metalEffects.forEach((category, effect) -> {
				if (ArrayUtils.contains(category.getTools(), toolType))
					effect.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
			});
		}
		else if (metalEffects.get(EnumEffectCategory.ARMOR) != null)
			MetallurgyEffects.effects.get(metal, EnumEffectCategory.ARMOR)
					.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);

		BaseMetallurgyEffect itemEffect = metalEffects.get(EnumEffectCategory.ALL);
		if (itemEffect != null)
			itemEffect.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
	{
		ItemStack stack = playerIn.getHeldItem(handIn);

		Metal metal = getParagonMetal(stack);
		if (metal == null)
			return;

		EnumTools toolType = EnumTools.byInstance(stack.getItem());
		if (toolType == null)
			return;

		MetallurgyEffects.effects.row(metal).forEach((category, effect) -> {
			if (ArrayUtils.contains(category.getTools(), toolType))
				effect.rightClickHandler(worldIn, playerIn, handIn);
		});
	}

	public static void setParagon(ItemStack tartarStack, @Nonnull Metal metal)
	{
		//Get or Initialize NBTTag
		NBTTagCompound compound = tartarStack.getTagCompound();
		if (compound == null)
			compound = new NBTTagCompound();

		MetalStats metalStats = metal.getStats();

		compound.setString("paragon", metalStats.getName());
		compound.setBoolean("paragon_absorbed", true);

		int infusedDurability;
		if (metalStats.getToolStats() != null)
			infusedDurability = metalStats.getToolStats().getMaxUses();
		else
			infusedDurability = metalStats.getArmorStats().getDurability() * 16; // *16 -> Chestplate value
		//(infusedDurability >= 1000 ? 3000 : 750)

		float durabilityRatio = (
				((float) tartarStack.getMaxDamage() + infusedDurability)
						/ (tartarStack.getMaxDamage() - infusedDurability));
		compound.setFloat("durability_boost", durabilityRatio);

		tartarStack.setTagCompound(compound);

		EntityEquipmentSlot slot = EntityLiving.getSlotForItemStack(tartarStack);

		Multimap<String, AttributeModifier> itemMods = tartarStack.getItem().getAttributeModifiers(slot, tartarStack);

		if (slot != EntityEquipmentSlot.MAINHAND && metal.hasArmorSet())
		{
			if (metalStats.getArmorStats().getMaxHealth() != 0)
				ItemUtils.editOrAddModifier(itemMods, SharedMonsterAttributes.MAX_HEALTH, Constants.ModAttributes.ARMOR_MAX_HEALTH.get(slot), metalStats.getArmorStats().getMaxHealth() / 4D);
			if (metalStats.getArmorStats().getKnockbackResistance() != 0)
				ItemUtils.editOrAddModifier(itemMods, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, Constants.ModAttributes.ARMOR_KNOCKBACK_RESISTANCE.get(slot), metalStats.getArmorStats().getKnockbackResistance() / 4D);
			if (metalStats.getArmorStats().getMovementSpeed() != 0)
				ItemUtils.editOrAddModifier(itemMods, SharedMonsterAttributes.MOVEMENT_SPEED, Constants.ModAttributes.ARMOR_MOVEMENT_SPEED.get(slot), metalStats.getArmorStats().getMovementSpeed() / 4D);
		}

		if (slot == EntityEquipmentSlot.MAINHAND && metal.hasToolSet())
		{

			if (metalStats.getToolStats().getMaxHealth() != 0)
				ItemUtils.editOrAddModifier(itemMods, SharedMonsterAttributes.MAX_HEALTH, Constants.ModAttributes.MAX_HEALTH, metalStats.getToolStats().getMaxHealth());
			if (metalStats.getToolStats().getMovementSpeed() != 0)
				ItemUtils.editOrAddModifier(itemMods, SharedMonsterAttributes.MOVEMENT_SPEED, Constants.ModAttributes.MOVEMENT_SPEED, metalStats.getToolStats().getMovementSpeed());
			if (metalStats.getToolStats().getReachDistance() != 0)
				ItemUtils.editOrAddModifier(itemMods, EntityPlayer.REACH_DISTANCE, Constants.ModAttributes.REACH_DISTANCE, metalStats.getToolStats().getReachDistance());
		}

		itemMods.forEach((attributeName, modifier) -> tartarStack.addAttributeModifier(attributeName, modifier, slot));
	}

	@Nullable
	public static Metal getParagonMetal(ItemStack tartarStack)
	{
		NBTTagCompound compound = tartarStack.getTagCompound();
		if (compound != null)
			return ModMetals.metalMap.get(compound.getString("paragon"));
		else
			return null;
	}

}
