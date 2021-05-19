/*==============================================================================
 = Class: ItemHoeBase
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.tool;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.proxy.ClientProxy;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemHoeBase extends ItemHoe implements IToolEffect {

    private Set<BaseMetallurgyEffect> effects = new HashSet<>();
    private final MetalStats metalStats;

    public ItemHoeBase(ToolMaterial material, MetalStats metalStats)
    {
        super(material);
        this.metalStats = metalStats;
        ItemUtils.initItem(this, metalStats.getName() + "_hoe", MetallurgyTabs.tabTool);
    }

    private ItemStack getRepairStack()
    {
        Metal metal = ModMetals.metalMap.get(metalStats.getName());

        if (metal != null)
            return new ItemStack(metal.getIngot());
        else
            return ItemStack.EMPTY;
    }

    @Override
    public int getMaxDamage(@Nonnull ItemStack stack)
    {
        NBTTagCompound compound = stack.getTagCompound();

        if (compound != null && compound.hasKey("durability_boost"))
        {
            float dBoost = compound.getFloat("durability_boost");
            return (int) (dBoost * super.getMaxDamage(stack));
        }

        return super.getMaxDamage(stack);
    }

    @Override
    public boolean getIsRepairable(@Nonnull ItemStack toRepair, @Nonnull ItemStack repair)
    {
        return (GeneralConfig.enableAnvilToolRepair && ItemUtils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        ItemUtils.buildStatsTooltip(tooltip, EnumTools.HOE, this.metalStats.getToolStats(), stack);
        ItemUtils.buildEffectTooltip(tooltip, effects);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public FontRenderer getFontRenderer(@Nonnull ItemStack stack)
    {
        return ClientProxy.fontRenderer;
    }

    @Nonnull
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(@Nonnull EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        ItemUtils.setToolAttributes(equipmentSlot, multimap, metalStats);
        return multimap;
    }

    @Override
    public MetalStats getMetalStats()
    {
        return this.metalStats;
    }

    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.HOE;
    }

    @Override
    public void addEffect(BaseMetallurgyEffect effect)
    {
        effects.add(effect);
    }

}
