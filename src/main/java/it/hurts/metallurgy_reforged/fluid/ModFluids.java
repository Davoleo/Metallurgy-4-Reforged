/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModFluids
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.fluid;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModFluids {

	public static final List<FluidMolten> fluidList = new ArrayList<>();
	public static final List<FluidMolten> extraFluids = new ArrayList<>();

	public static final FluidMolten TAR = new FluidMoltenTar("molten_tar", 0xFF111419, 1000, true);
	public static final FluidMolten THERMITE = new FluidMolten("molten_thermite", 0xFFC44205, 3200, true);

	public static void registerFluids()
	{
		for (FluidMolten fluid : extraFluids)
		{
			FluidRegistry.registerFluid(fluid);
			fluid.initFluidBlock();
			FluidRegistry.addBucketForFluid(fluid);
		}

		for (FluidMolten fluid : fluidList)
		{
			FluidRegistry.addBucketForFluid(fluid);
		}
	}

	//	FluidBlocks Section
	//Registers the Block
	public static void registerBlocks(IForgeRegistry<Block> registry)
	{
		for (FluidMolten fluid : extraFluids)
		{
			registry.register(fluid.getFluidBlock());
		}
	}

	//  Registers the item
	public static void registerItem(IForgeRegistry<Item> registry)
	{
		for (FluidMolten fluid : extraFluids)
		{
			registry.register(fluid.getFluidBlock().createItemBlock());
		}
	}

	//  Registers the Item Model
	public static void registerModels()
	{
		for (FluidMolten fluid : extraFluids)
		{
			fluid.getFluidBlock().registerItemModel(Item.getItemFromBlock(fluid.getFluidBlock()));
		}
	}

}
