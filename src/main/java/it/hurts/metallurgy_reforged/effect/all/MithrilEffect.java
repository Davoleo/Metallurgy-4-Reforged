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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.UUID;

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
        if (ArrayUtils.contains(metal.getArmorSet(), event.getItemInput().getItem()))
        {
            int outputEnchCount = event.getItemResult().getEnchantmentTagList().tagCount();
            applyArmorBuff(event.getItemResult(), outputEnchCount);
        }
    }

    public void equipmentBuff(LivingEquipmentChangeEvent event)
    {
        final ItemStack stack = event.getTo();
        if (ItemUtils.isMadeOfMetal(metal, stack.getItem(), ItemArmorBase.class, IToolEffect.class))
        {
            if (stack.getItem() instanceof ItemArmorBase)
                applyArmorBuff(stack, stack.getEnchantmentTagList().tagCount());
            else if (stack.getItem() instanceof IToolEffect)
            {

            }
        }
    }

    private static final UUID PROTECTION_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-1111033DB5CF");
    private final AttributeModifier[] PROTECTION_MODIFIERS = {
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 0F, 0),
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 2F, 0),
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 4F, 0),
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 6F, 0),
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 8F, 0),
            new AttributeModifier(PROTECTION_UUID, "MITHRIL_Armor_Protection_Buff", 10F, 0),
    };
    private static final UUID TOUGHNESS_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-1111133DB5CF");
    private final AttributeModifier[] TOUGHNESS_MODIFIERS = {
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 0F, 0),
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 1F, 0),
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 2F, 0),
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 3F, 0),
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 4F, 0),
            new AttributeModifier(TOUGHNESS_UUID, "MITHRIL_Armor_Toughness_Buff", 5F, 0),
    };

    private void applyArmorBuff(ItemStack stackRef, int newEnchCount)
    {
        NBTTagCompound stackData = stackRef.getTagCompound();
        if (stackData == null)
            stackData = new NBTTagCompound();

        int delta = newEnchCount - stackData.getInteger("arcane_boost");
        if (delta != 0)
        {

            int boostLevel = stackData.getInteger("arcane_boost");
            boostLevel += delta;
            stackData.setFloat("arcane_protection_boost", MathHelper.clamp(boostLevel, 0, 5));

            stackRef.addAttributeModifier(SharedMonsterAttributes.ARMOR.getName(), PROTECTION_MODIFIERS[boostLevel], EntityLiving.getSlotForItemStack(stackRef));
            stackRef.addAttributeModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), TOUGHNESS_MODIFIERS[boostLevel], EntityLiving.getSlotForItemStack(stackRef));
        }
    }

    private void applyToolWeaponBuff(ItemStack stackRef, int enchantments)
    {
        //SharedMonsterAttributes.ATTACK_DAMAGE
        //Hack into efficiency (or just use another event LAME)
    }

}
