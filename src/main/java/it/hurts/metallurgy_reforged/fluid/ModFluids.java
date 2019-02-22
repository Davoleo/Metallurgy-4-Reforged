package it.hurts.metallurgy_reforged.fluid;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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

	public static final List<FluidMolten> fluidList = new ArrayList<>();
	public static final List<FluidMolten> fluidToRegitry = new ArrayList<>();

//	TODO Sistemare il colore, Rimuovere l'effetto di fuoco dal player, aumentare la lentezza del player.
	public static final FluidMolten TAR = new FluidMolten("molten_tar", 0xFF100500, 1000, true);
	public static  final FluidMolten THERMITE = new FluidMolten("molten_thermite", 0xFFC44205, 3200, true);
	


	public static void registerFluids(){
		for(FluidMolten fluid : fluidToRegitry) {
			FluidRegistry.registerFluid(fluid);
			fluid.initFluidBlock();
			FluidRegistry.addBucketForFluid(fluid);
		}

		for (FluidMolten fluid : fluidList)
		{
			FluidRegistry.addBucketForFluid(fluid);
		}
	}

//	Fluidblocks Section
//	Il FluidBlock deve essere inizializzato all'interno di 'registerFluidBlocks' e definito fuori i metodi

	public static void registerBlocks(IForgeRegistry<Block> registry){
		for(FluidMolten fluid : fluidToRegitry) {
			registry.register(fluid.getFluidBlock());
		}
	}

//  Item Inventario
	public static void registerItem(IForgeRegistry<Item> registry){
		for(FluidMolten fluid : fluidToRegitry) {
			registry.register(fluid.getFluidBlock().createItemBlock());
		}
	}

//  Modello
	public static void registerModels(){
		for(FluidMolten fluid : fluidToRegitry) {
			fluid.getFluidBlock().registerItemModel(Item.getItemFromBlock(fluid.getFluidBlock()));
		}
	}
}
