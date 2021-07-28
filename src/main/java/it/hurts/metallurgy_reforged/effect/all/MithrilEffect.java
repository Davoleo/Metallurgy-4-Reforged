/*==============================================================================
 = Class: MithrilEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

public class MithrilEffect extends BaseMetallurgyEffect {

	public MithrilEffect()
	{
		super(ModMetals.MITHRIL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@SubscribeEvent
	public void armorBuffInAnvil(AnvilRepairEvent event)
	{
		if (ItemUtils.isMadeOfMetal(metal, event.getItemInput().getItem(), ItemArmorBase.class, IToolEffect.class))
		{
			int outputEnchCount = event.getItemResult().getEnchantmentTagList().tagCount();
			applyCombatBuffs(event.getItemResult(), outputEnchCount, event.getItemResult().getItem() instanceof ItemArmorBase);
		}
	}

	@SubscribeEvent
	public void equipmentBuff(LivingEquipmentChangeEvent event)
	{
		final ItemStack stack = event.getTo();
		if (ItemUtils.isMadeOfMetal(metal, stack.getItem(), ItemArmorBase.class, IToolEffect.class))
			applyCombatBuffs(stack, stack.getEnchantmentTagList().tagCount(), stack.getItem() instanceof ItemArmorBase);
	}

	private static final UUID PROTECTION_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-1111033DB5CF");
	private final BiFunction<EntityEquipmentSlot, Integer, AttributeModifier> generateProtectionModifier = (slot, level) -> {
		final int originalProtection = metal.getStats().getArmorStats().getDamageReduction()[slot.getIndex()];
		return new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", originalProtection + (level), 0);
	};

	private static final UUID TOUGHNESS_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-1111133DB5CF");
	private final IntFunction<AttributeModifier> generateToughnessModifier = level -> {
		float armorToughness = metal.getStats().getArmorStats().getToughness();
		return new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", armorToughness + (level * 0.5F), 0);
	};

	private static final UUID ATTACK_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-1111233DB5CF");
	private final IntFunction<AttributeModifier> generateAttackModifier = level -> {
		final float attack = metal.getStats().getToolStats().getDamage() + 3F + level;
		return new AttributeModifier(ATTACK_UUID, "MITHRIL_Attack_Buff", attack, 0);
	};
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3"),
			"MITHRIL_Attack_Speed_Restore", -2.4000000953674316D, 0);

	private void applyCombatBuffs(ItemStack stackRef, int enchantments, boolean armor)
	{
		NBTTagCompound stackData = stackRef.getTagCompound();
		if (stackData == null)
			stackData = new NBTTagCompound();

		int delta = enchantments - stackData.getInteger("arcane_boost");
		if (delta != 0)
		{
			final int oldBoostLevel = stackData.getInteger("arcane_boost");
			int boostLevel = oldBoostLevel;
			boostLevel += delta;
			stackData.setInteger("arcane_boost", MathHelper.clamp(boostLevel, 0, 5));

			//Check if the tool already has Attribute modifiers (should always have, but just in case if they don't we create a new empty TagList)
			if (!stackData.hasKey("AttributeModifiers", 9))
				stackData.setTag("AttributeModifiers", new NBTTagList());

			//Get indexes of existing attribute modifiers in NBT
			int protectionIndex = -1;
			int toughnessIndex = -1;
			int attackIndex = -1;
			int attackSpeedIndex = -1;
			NBTTagList serializedModifiers = stackData.getTagList("AttributeModifiers", 10);
			for (int i = 0; i < serializedModifiers.tagCount(); i++)
			{
				AttributeModifier modifier = SharedMonsterAttributes.readAttributeModifierFromNBT(serializedModifiers.getCompoundTagAt(i));
				if (modifier.getID().equals(PROTECTION_UUID))
					protectionIndex = i;
				else if (modifier.getID().equals(TOUGHNESS_UUID))
					toughnessIndex = i;
				else if (modifier.getID().equals(ATTACK_UUID))
					attackIndex = i;
				else if (modifier.getID().equals(ATTACK_SPEED_MODIFIER.getID()))
					attackSpeedIndex = i;
			}

			EntityEquipmentSlot slot = EntityLiving.getSlotForItemStack(stackRef);
			if (armor)
			{
				NBTTagCompound protMod = SharedMonsterAttributes.writeAttributeModifierToNBT(generateProtectionModifier.apply(slot, boostLevel));
				protMod.setString("AttributeName", SharedMonsterAttributes.ARMOR.getName());
				protMod.setString("Slot", slot.getName());
				if (protectionIndex == -1)
					serializedModifiers.appendTag(protMod);
				else
					serializedModifiers.set(protectionIndex, protMod);

				NBTTagCompound toughMod = SharedMonsterAttributes.writeAttributeModifierToNBT(generateToughnessModifier.apply(boostLevel));
				toughMod.setString("AttributeName", SharedMonsterAttributes.ARMOR_TOUGHNESS.getName());
				toughMod.setString("Slot", slot.getName());
				if (toughnessIndex == -1)
					serializedModifiers.appendTag(toughMod);
				else
					serializedModifiers.set(toughnessIndex, toughMod);
			}
			else
			{
				NBTTagCompound attackMod = SharedMonsterAttributes.writeAttributeModifierToNBT(generateAttackModifier.apply(boostLevel));
				attackMod.setString("AttributeName", SharedMonsterAttributes.ATTACK_DAMAGE.getName());
				attackMod.setString("Slot", slot.getName());
				if (attackIndex == -1)
					serializedModifiers.appendTag(attackMod);
				else
					serializedModifiers.set(attackIndex, attackMod);

				NBTTagCompound attackSpeedMod = SharedMonsterAttributes.writeAttributeModifierToNBT(ATTACK_SPEED_MODIFIER);
				attackSpeedMod.setString("AttributeName", SharedMonsterAttributes.ATTACK_SPEED.getName());
				attackSpeedMod.setString("Slot", slot.getName());
				if (attackSpeedIndex == -1)
					serializedModifiers.appendTag(attackSpeedMod);
				else
					serializedModifiers.set(attackSpeedIndex, attackSpeedMod);
			}
		}
	}

	@SubscribeEvent
	public void applyEfficiencyBuff(PlayerEvent.BreakSpeed event)
	{
		// TODO: 28/07/2021 Feedback
		ItemStack tool = event.getEntityPlayer().getHeldItemMainhand();
		if (ItemUtils.isMadeOfMetal(metal, tool.getItem(), IToolEffect.class))
		{
			NBTTagCompound toolData = tool.getTagCompound();
			if (toolData == null)
				return;

			int buffLevel = toolData.getInteger("arcane_boost");
			float newSpeed = event.getOriginalSpeed() + (buffLevel * 1.5F);
			//System.out.println(newSpeed);
			if (buffLevel > 0)
				event.setNewSpeed(newSpeed);
		}

	}

}
