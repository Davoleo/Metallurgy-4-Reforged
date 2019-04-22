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

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHoeBase extends ItemHoe {

    private String name;
    private String tooltip;

    public ItemHoeBase(ToolMaterial material, String name)
    {
        super(material);
        setTranslationKey(Metallurgy.MODID + "." + name);
        setRegistryName(Metallurgy.MODID, name);
        this.name = name;
        setCreativeTab(MetallurgyTabs.tabTool);
        ModTools.toolList.add(this);
    }

    private ItemStack getRepairStack()
    {
        String material = this.getMaterialName().toLowerCase();
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
        if(this.tooltip != null)
            tooltip.add(this.tooltip);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":tool/" + name, "inventory"));
    }

}
