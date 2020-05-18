/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModLakeWorldGen
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.world;

import it.hurts.metallurgy_reforged.config.WorldGenerationConfig;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ModLakeWorldGen {

	public static IChunkGenerator chunkGenerator = null;

	@SubscribeEvent
	public static void populatesEvent(PopulateChunkEvent.Pre event)
	{
		World world = event.getWorld();
		Random rand = new Random();
		List<Integer> dimensionWhitelist = Arrays.asList(WorldGenerationConfig.tarLakeDimensionWhiteList);
		int randNum = rand.nextInt(500) + 1;

		if (randNum <= WorldGenerationConfig.tarLakePercentage)

			if (dimensionWhitelist.contains(world.provider.getDimension()))
			{
				int x = event.getChunkX() * 16;
				int z = event.getChunkZ() * 16;

				BlockPos blockpos = new BlockPos(x, 0, z);

				final IChunkProvider chunkProvider = world.getChunkProvider();
				if (chunkProvider instanceof ChunkProviderServer)
					chunkGenerator = ((ChunkProviderServer) chunkProvider).chunkGenerator;
				if (chunkGenerator == null)
					chunkGenerator = world.provider.createChunkGenerator();

				if (net.minecraftforge.event.terraingen.TerrainGen.populate(chunkGenerator, world, rand, event.getChunkX(), event.getChunkZ(), false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
				{
					int i1 = rand.nextInt(16) + 8;
					int j1 = rand.nextInt(256);
					int k1 = rand.nextInt(16) + 8;
					(new WorldGenLakes(ModFluids.fluidBlockTar)).generate(world, rand, blockpos.add(i1, j1, k1));
				}
			}
	}

}
