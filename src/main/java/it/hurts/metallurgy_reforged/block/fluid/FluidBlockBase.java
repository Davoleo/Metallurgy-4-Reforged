/*
 * -------------------------------------------------------------------------------------------------------
 * Class: FluidBlockBase
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.block.fluid;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import java.util.Objects;

public class FluidBlockBase extends BlockFluidClassic {

    private String name;

    //Creates an instance of a FluidBlock
    public FluidBlockBase(Fluid fluid, Material material, String name)
    {
        super(fluid, material);
        this.name = name;
        setRegistryName(name);
        setTranslationKey(name);
        setCreativeTab(MetallurgyTabs.tabFluid);
    }

    //Registers the model of the FluidBlock
    public void registerItemModel(Item itemBlock)
    {
        Metallurgy.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    //Creates the inventory version of the FluidBlock
    public Item createItemBlock()
    {
        return new ItemBlock(this).setRegistryName(Objects.requireNonNull(getRegistryName()));
    }

}
