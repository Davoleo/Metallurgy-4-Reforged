package it.hurts.metallurgy_reforged.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.FluidBlockBase;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 * Fatto da ItHurtsLikeHell con l'aiuto di dava TODO Aggiungere questa riga
 **************************************************/

public class ModFluids {

    public static final List<Fluid> fluidList = new ArrayList<>();
    
    private final static ResourceLocation default_still = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_still");
    private final static ResourceLocation default_flowing = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_flow");
    
//    private final Metal THERMITE = new MetalStats()
    public final static FluidMolten THERMITE = new FluidMolten("thermite", default_still, default_flowing, 0x00FFFFFF, 3200, 0);
//    public final static FluidStack THERMITE_STACK = THERMITE.getFluidStack();

    public static void registerFluids()
    {
    	FluidRegistry.registerFluid(THERMITE);
        for (Fluid fluid : fluidList)
        {
        	FluidRegistry.addBucketForFluid(fluid);
        }
        FluidRegistry.addBucketForFluid(THERMITE);
    }
    
  //---------------------------------------------------------------------------------------------------------
    //Fluidblocks Section
//    TODO Rifare la classe seguendo queste linee guida;
//    TODO il FluidBlock deve essere inizializzato all'interno di 'registerFluidBlocks' e definito fuori i metodi
    
    public static FluidBlockBase molten_thermite;
//    FluidBlock
    public static void registerFluidBlocks(IForgeRegistry<Block> registry)
    {
    	molten_thermite = new FluidBlockBase(THERMITE, Material.LAVA, "molten_thermite");
        registry.register(molten_thermite);
    }

//    Item Inventario
    public static void registerFluidItemBlocks(IForgeRegistry<Item> registry)
    {
//    	FluidBlockBase molten_thermite = new FluidBlockBase(THERMITE, Material.LAVA, "molten_thermite");
        registry.register(molten_thermite.createItemBlock());
    }

//    Modello
    public static void registerFluidItemModels()
    {
//    	FluidBlockBase molten_thermite = new FluidBlockBase(THERMITE, Material.LAVA, "molten_thermite");
    	molten_thermite.registerItemModel(Item.getItemFromBlock(molten_thermite));
    }
}
