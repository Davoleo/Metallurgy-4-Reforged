package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.armor.ModArmors;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Leonardo
 * Date / Hour: 14/11/2018 / 21.11
 * Class: MetallurgyTabs
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class MetallurgyTabs extends CreativeTabs {

    //  TO.DO : Fix Creative tab icon ticking

    public static final CreativeTabs tabArmor, tabBlock, tabDust, tabFluid, tabIngot, tabSpecial, tabOre, tabTool;
    
    static
    {
        tabArmor = new MetallurgyTabs(0, "armors");
        tabBlock = new MetallurgyTabs(1, "blocks");
        tabDust = new MetallurgyTabs(2, "dusts");
        tabFluid = new MetallurgyTabs(3, "fluids");
        tabIngot = new MetallurgyTabs(4, "ingots");
        tabSpecial = new MetallurgyTabs(5, "special");
        tabOre = new MetallurgyTabs(6, "ores");
        tabTool = new MetallurgyTabs(7, "tools");
    }

    private final int type;

    public MetallurgyTabs(int type, String name)
    {
        super(getUName(name));
        this.type = type;
    }

    /*public MetallurgyTabs(int type, int index, String name)
    {
        super(index, getUName(name));
        this.type = type;
    }*/

    private static String getUName(String name)
    {
        return Metallurgy.MODID + "." + name;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    public ItemStack createIcon()
    {
        switch (type)
        {
            case 0: return new ItemStack(ModArmors.adamantine_chest);
            case 1: return new ItemStack(ModMetals.ADAMANTINE.getBlock());
            case 2: return new ItemStack(ModMetals.ADAMANTINE.getDust());
            case 3: return new ItemStack(ModMetals.ADAMANTINE.getFluidBlock());
            case 4: return new ItemStack(ModMetals.ADAMANTINE.getIngot());
            case 5: return new ItemStack(ModBlocks.crusher);
            case 6: return new ItemStack(ModMetals.ADAMANTINE.getOre());
            case 7: return new ItemStack(ModTools.adamantine_pickaxe);
        }
        return ItemStack.EMPTY;
    }
}
