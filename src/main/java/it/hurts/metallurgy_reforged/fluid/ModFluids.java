package it.hurts.metallurgy_reforged.fluid;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;


/*************************************************
 * Author: Davoleo
 * Reworked by ItHurtsLikeHell
 * Date: 28/08/2018
 * Hour: 17.24
 * Project: Metallurgy
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ModFluids {

	public static final List<Fluid> fluidList = new ArrayList<>();
	public static final List<FluidMolten> fluidToRegitry = new ArrayList<>();

//	TODO Sistemare il colore
	public static final FluidMolten THERMITE = new FluidMolten("thermite", 0xFFC44205, 3200, true);


	public static void registerFluids(){
		for(Fluid fluid : fluidToRegitry) {
			FluidRegistry.registerFluid(fluid);
			FluidMolten.initFluidBlock(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}

		for (Fluid fluid : fluidList)
		{
			FluidRegistry.addBucketForFluid(fluid);
		}
	}

//	Fluidblocks Section
//	Il FluidBlock deve essere inizializzato all'interno di 'registerFluidBlocks' e definito fuori i metodi

	public static void registerFluidBlocks(IForgeRegistry<Block> registry){
		for(FluidMolten fluid : fluidToRegitry) {
			registry.register(fluid.getFluidBlock());
		}
	}

//  Item Inventario
	public static void registerFluidItemBlocks(IForgeRegistry<Item> registry){
		for(FluidMolten fluid : fluidToRegitry) {
			registry.register(fluid.getFluidBlock().createItemBlock());
		}
	}

	//    Modello
	public static void registerFluidItemModels(){
		for(FluidMolten fluid : fluidToRegitry) {
			fluid.getFluidBlock().registerItemModel(Item.getItemFromBlock(fluid.getFluidBlock()));
		}
	}
}
