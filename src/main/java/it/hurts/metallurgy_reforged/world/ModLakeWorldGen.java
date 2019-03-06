/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModLakeWorldGen
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.world;

import java.util.Random;

import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModLakeWorldGen {
	
	@SubscribeEvent
	public static void populatesEvent(PopulateChunkEvent.Pre event) {
		World world = event.getWorld();
		Random rand = new Random();
		
		if((rand.nextInt(100) + 1) < GeneralConfig.tarLakePercentage)
			if (world.provider.getDimension() == 0) {
				int x = event.getChunkX() * 16;
				int z = event.getChunkZ() * 16;

				BlockPos blockpos = new BlockPos(x, 0, z);
				IChunkGenerator cgen = world.provider.createChunkGenerator();
				if(cgen instanceof ChunkGeneratorOverworld) {
					if (net.minecraftforge.event.terraingen.TerrainGen.populate(cgen, world, rand, event.getChunkX(), event.getChunkZ(), false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)){
						int i1 = rand.nextInt(16) + 8;
						int j1 = rand.nextInt(256);
						int k1 = rand.nextInt(16) + 8;
						(new WorldGenLakes(ModFluids.TAR.getFluidBlock())).generate(world, rand, blockpos.add(i1, j1, k1));
					}
				}
			}
	}

}
