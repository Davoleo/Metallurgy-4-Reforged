/*==============================================================================
 = Class: ItemArmorBase
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.armor;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.proxy.ClientProxy;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemArmorBase extends ItemArmor {

    private Set<BaseMetallurgyEffect> effects = new HashSet<>();
    private Enchantment enchantment;
    private int enchantmentLevel;

    private MetalStats metalStats;

    public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, MetalStats metalStats)
    {
        super(material, 0, slot);
        ItemUtils.initItem(this, metalStats.getName() + '_' + getSlotArmorSuffix(slot), MetallurgyTabs.tabArmor);
        this.metalStats = metalStats;
    }

    public void setEffect(BaseMetallurgyEffect effect)
    {
        this.effects.add(effect);
    }

    public void setEnchanted(Enchantment enchantment, int enchantmentLevel)
    {
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
    }

    public MetalStats getMetalStats()
    {
        return metalStats;
    }

    private ItemStack getRepairStack()
    {
        String material = this.metalStats.getName().toLowerCase();
        Metal metal = ModMetals.metalMap.get(material);
        if (metal != null)
            return new ItemStack(metal.getIngot());
        else
            return ItemStack.EMPTY;
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public FontRenderer getFontRenderer(@Nonnull ItemStack stack)
    {
        return ClientProxy.fontRenderer;
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair)
    {
        return (GeneralConfig.enableAnvilArmorRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        ItemUtils.buildTooltip(tooltip, effects);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            ItemStack enchantedArmor = new ItemStack(this);
            if (enchantment != null)
            {
                enchantedArmor.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedArmor);
        }
    }

    @Nonnull
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == this.armorType)
        {
            multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(
                    Constants.ModAttributes.ARMOR_MAX_HEALTH.get(equipmentSlot),
                    "Metallurgy Armor Max Health",
                    metalStats.getArmorStats().getMaxHealth() / 4D,
                    0)
            );

            multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(
                    Constants.ModAttributes.ARMOR_KNOCKBACK_RESISTANCE.get(equipmentSlot),
                    "Metallurgy Armor Knockback Resistance",
                    metalStats.getArmorStats().getKnockbackResistance() / 4D,
                    0)
            );

            multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(
                    Constants.ModAttributes.ARMOR_MOVEMENT_SPEED.get(equipmentSlot),
                    "Metallurgy Armor Movement Speed",
                    metalStats.getArmorStats().getMovementSpeed() / 4D,
                    0)
            );
        }

        return multimap;
    }

    private String getSlotArmorSuffix(EntityEquipmentSlot slot)
    {
        switch (slot)
        {
            case HEAD:
                return "helmet";
            case CHEST:
                return "chestplate";
            case LEGS:
                return "leggings";
            case FEET:
                return "boots";
            default:
                return "THIS_CAN'T_POSSIBLY_HAPPEN,_FUCK!";
        }
    }

}