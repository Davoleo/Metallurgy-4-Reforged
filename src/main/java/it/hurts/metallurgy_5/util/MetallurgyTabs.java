package it.hurts.metallurgy_5.util;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.fluid.ModFluids;
import it.hurts.metallurgy_5.item.ModItems;
import it.hurts.metallurgy_5.item.armor.ModArmors;
import it.hurts.metallurgy_5.item.tool.ModTools;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Leonardo
 * Date / Hour: 14/11/2018 / 21.11
 * Class: MetallurgyTabs
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class MetallurgyTabs extends CreativeTabs {

    //TODO : Fix Creative tab icon ticking

    public static final CreativeTabs tabArmor, tabBlock, tabDust, tabFluid, tabIngot, tabMachine, tabOre, tabTool;

    static
    {
        tabArmor = new MetallurgyTabs(0, "armors");
        tabBlock = new MetallurgyTabs(1, "blocks");
        tabDust = new MetallurgyTabs(2, "dusts");
        tabFluid = new MetallurgyTabs(3, "fluids");
        tabIngot = new MetallurgyTabs(4, "ingots");
        tabMachine = new MetallurgyTabs(5, "machines");
        tabOre = new MetallurgyTabs(6, "ores");
        tabTool = new MetallurgyTabs(7, "tools");
    }

    private final int type;
    private int tick;

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
        return Metallurgy_5.MODID + "." + name;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    public ItemStack getTabIconItem()
    {
        tick = Metallurgy_5.ticker/20;

        switch (type)
        {
            case 0: return new ItemStack(ModArmors.armorList.get(tick % ModArmors.armorList.size()));
            case 1: return new ItemStack(ModBlocks.blockAdamantine);
            case 2: return new ItemStack(ModItems.dustAdamantine);
            case 3: return new ItemStack(ModFluids.fluidList.get(tick % ModFluids.fluidList.size()).getBlock());
            case 4: return new ItemStack(ModItems.ingotAdamantine);
            case 5: return new ItemStack(tick % 2 == 0 ? ModBlocks.alloyer : ModBlocks.crusher);
            case 6: return new ItemStack(ModBlocks.oreAdamantine);
            case 7: return new ItemStack(ModTools.toolList.get(tick % ModTools.toolList.size()));
        }
        return ItemStack.EMPTY;
    }
}
