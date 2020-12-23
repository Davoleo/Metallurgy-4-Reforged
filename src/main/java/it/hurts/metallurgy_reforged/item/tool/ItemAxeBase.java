/*==============================================================================
 = Class: ItemAxeBase
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.tool;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.proxy.ClientProxy;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
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

public class ItemAxeBase extends ItemAxe implements IToolEffect {

    private Set<BaseMetallurgyEffect> effects = new HashSet<>();
    private Enchantment enchantment = null;
    private int enchantmentLevel = -1;

    private final MetalStats metalStats;

    public ItemAxeBase(ToolMaterial material, MetalStats metalStats)
    {
        super(material, material.getAttackDamage() + 4, -2.5F - (material.getAttackDamage() / 10));
        ItemUtils.initItem(this, metalStats.getName() + "_axe", MetallurgyTabs.tabTool);
        this.metalStats = metalStats;
    }

    @Override
    public MetalStats getMetalStats()
    {
        return metalStats;
    }

    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.AXE;
    }

    @Override
    public void addEffect(BaseMetallurgyEffect effect)
    {
        this.effects.add(effect);
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
        Metal metal = ModMetals.metalMap.get(metalStats.getName());
        return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(new ItemStack(metal.getIngot()), repair))
                || super.getIsRepairable(toRepair, repair);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        ItemUtils.buildTooltip(tooltip, effects);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public FontRenderer getFontRenderer(@Nonnull ItemStack stack)
    {
        return ClientProxy.fontRenderer;
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
        ItemUtils.setToolAttributes(equipmentSlot, multimap, metalStats);
        return multimap;
    }

}
