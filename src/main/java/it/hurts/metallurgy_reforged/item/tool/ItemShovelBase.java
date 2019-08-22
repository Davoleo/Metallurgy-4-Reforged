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

import javax.annotation.Nonnull;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;

public class ItemShovelBase extends ShovelItem implements IHasModel {

    private EnumToolEffects effect;
    private Enchantment enchantment;
	private int enchantmentLevel;

    public ItemShovelBase(IItemTier material, String name)
    {
        this(material, name, null, -1);
    }
    
    public ItemShovelBase(IItemTier material, String name, Enchantment enchantment, int enchantmentLevel){
        super(material, 1.5F, -3.0F,  new Item.Properties().group(MetallurgyTabs.tabTool));
        ItemUtils.initItem(this, name, MetallurgyTabs.tabTool, ModTools.toolList);
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
    }
    
//    @Override
//    @SideOnly(Side.CLIENT)
//	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
//    {
//        if(this.isInCreativeTab(tab)) {
//            ItemStack enchantedShowel = new ItemStack(this);
//            if(enchantment != null) {
//                enchantedShowel.addEnchantment(enchantment, enchantmentLevel);
//            }
//            items.add(enchantedShowel);
//        }
//	}

    public void setEffect(EnumToolEffects effect)
    {
        this.effect = effect;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, @Nonnull ItemStack repair)
    {
        return (GeneralConfig.enableAnvilToolRepair && ItemUtils.getToolRepairStack(this).isItemEqual(repair)) || super.getIsRepairable(toRepair, repair);
    }

//    @SideOnly(Side.CLIENT)
//    @Override
//    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
//    {
//        if(this.effect != null && effect.isActive())
//        tooltip.add(effect.getLocalized());
//    }

    @Nonnull
    @Override
    public String getCategory()
    {
        return "tool/shovel";
    }

}
