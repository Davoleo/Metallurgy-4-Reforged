/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemSwordBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemSwordBase extends ItemSword implements IHasModel {

    private String name;
    private String tooltip;
	private Enchantment enchantment;
	private int enchantmentLevel;

    public ItemSwordBase(ToolMaterial material, String name)
    {
        this(material, name, null, -1);
    }
    
    public ItemSwordBase(ToolMaterial material, String name, Enchantment enchantment, int enchantmentLevel)
    {
        super(material);
        setTranslationKey(Metallurgy.MODID + "." + name);
        setRegistryName(Metallurgy.MODID, name);
        this.name = name;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        setCreativeTab(MetallurgyTabs.tabTool);
        ModTools.toolList.add(this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab)) {
            ItemStack enchantedSword = new ItemStack(this);
            if(enchantment != null) {
                enchantedSword.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedSword);
        }
	}

    public ItemSwordBase setTooltip(String tooltip)
    {
        this.tooltip = tooltip;
        return this;
    }

    private ItemStack getRepairStack()
    {
        String material = this.getToolMaterialName().toLowerCase();
        Metal metal = Utils.getMetalFromString(material);
        if (metal != null)
            return new ItemStack(metal.getIngot());
        else return ItemStack.EMPTY;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return (GeneralConfig.enableAnvilToolRepair && Utils.equalsWildcard(getRepairStack(), repair)) || super.getIsRepairable(toRepair, repair);
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.tooltip != null && ModTools.isSwordEffectActive(this))
            tooltip.add(this.tooltip);
    }

    @Nonnull
    @Override
    public String getCategory()
    {
        return "tool/sword";
    }
}
