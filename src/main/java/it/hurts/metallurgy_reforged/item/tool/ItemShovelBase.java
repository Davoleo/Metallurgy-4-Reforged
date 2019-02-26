/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemShovelBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.item.tool;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemShovelBase extends ItemSpade {

    private String name;
    private String tooltip;
    private Enchantment enchantment;
	private int enchantmentLevel;

    public ItemShovelBase(ToolMaterial material, String name)
    {
        this(material, name, null, -1);
    }
    
    public ItemShovelBase(ToolMaterial material, String name, Enchantment enchantment, int enchantmentLevel){
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
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
            ItemStack enchantedShowel = new ItemStack(this);
            if(enchantment != null) {
                enchantedShowel.addEnchantment(enchantment, enchantmentLevel);
            }
            items.add(enchantedShowel);
        }
	}

    public ItemShovelBase setTooltip(String tooltip)
    {
        this.tooltip = tooltip;
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(this.tooltip != null && ModTools.isShovelEffectActive(this))
            tooltip.add(this.tooltip);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":tool/" + name, "inventory"));
    }

}
