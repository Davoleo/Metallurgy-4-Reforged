/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ItemHoeBase
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
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.IHasModel;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHoeBase extends HoeItem implements IHasModel {

    public ItemHoeBase(IItemTier material, String name)
    {
        super(material, -1.0F, new Item.Properties());
        ItemUtils.initItem(this, name, MetallurgyTabs.tabTool, ModTools.toolList);
    }

    private ItemStack getRepairStack()
    {
        String material = this.getTier().toString().toLowerCase();
        Metal metal = Utils.getMetalFromString(material);
        if (metal != null)
            return new ItemStack(metal.getIngot());
        else return ItemStack.EMPTY;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return (GeneralConfig.enableAnvilToolRepair && getRepairStack().isItemEqual(repair)) || super.getIsRepairable(toRepair, repair);
    }

    @Nonnull
    @Override
    public String getCategory()
    {
        return "tool/hoe";
    }

}
