/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTabs
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.ArmorConfig;
import it.hurts.metallurgy_reforged.config.ToolConfig;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MetallurgyTabs extends CreativeTabs {

    //  TO.DO : Fix Creative tab icon ticking

    public static final CreativeTabs tabArmor, tabBlock, tabDust, tabFluid, tabIngot, tabNugget, tabSpecial, tabOre, tabTool;
    
    static
    {
        tabArmor = new MetallurgyTabs(0, "armors");
        tabBlock = new MetallurgyTabs(1, "blocks");
        tabDust = new MetallurgyTabs(2, "dusts");
        tabFluid = new MetallurgyTabs(3, "fluids");
        tabIngot = new MetallurgyTabs(4, "ingots");
        tabNugget = new MetallurgyTabs(5, "nuggets");
        tabSpecial = new MetallurgyTabs(6, "special");
        tabOre = new MetallurgyTabs(7, "ores");
        tabTool = new MetallurgyTabs(8, "tools");
    }

    private final int type;

    public MetallurgyTabs(int type, String name)
    {
        super(getUName(name));
        this.type = type;
    }

    private static String getUName(String name)
    {
        return Metallurgy.MODID + "." + name;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    public ItemStack createIcon()
    {
        switch (type){
            case 0: return ArmorConfig.allArmor[0] ? new ItemStack(ModArmors.adamantine_chest) : ItemStack.EMPTY;
            case 1: return new ItemStack(ModMetals.ADAMANTINE.getBlock());
            case 2: return new ItemStack(ModMetals.ADAMANTINE.getDust());
            case 3: return new ItemStack(ModMetals.ADAMANTINE.getFluidBlock());
            case 4: return new ItemStack(ModMetals.ADAMANTINE.getIngot());
            case 5: return new ItemStack(ModMetals.ADAMANTINE.getNugget());
            case 6: return new ItemStack(ModBlocks.crusher);
            case 7: return new ItemStack(Objects.requireNonNull(ModMetals.ADAMANTINE.getOre()));
            case 8: return ToolConfig.allTools[0][2] ? new ItemStack(ModTools.adamantine_pickaxe) : ItemStack.EMPTY;
            default: return ItemStack.EMPTY;
        }
    }
}
