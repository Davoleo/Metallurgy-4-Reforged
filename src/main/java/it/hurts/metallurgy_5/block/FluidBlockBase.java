package it.hurts.metallurgy_5.block;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/*************************************************
 * Author: Davoleo
 * Date: 30/08/2018
 * Hour: 10.12
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class FluidBlockBase extends BlockFluidClassic {

    private String name;

    public FluidBlockBase(Fluid fluid, Material material, String name)
    {
        super(fluid, material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Metallurgy_5.tabFluid);
    }

    public void registerItemModel(Item itemBlock)
    {
        Metallurgy_5.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock()
    {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

}
