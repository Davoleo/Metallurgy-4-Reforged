/*==============================================================================
 = Class: WorldTickHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world;

import gnu.trove.map.hash.TIntObjectHashMap;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayDeque;
import java.util.Random;

public class WorldTickHandler {

	public static WorldTickHandler instance = new WorldTickHandler();

	public static TIntObjectHashMap<ArrayDeque<ChunkPos>> chunksToGenerate = new TIntObjectHashMap<>();

	@SubscribeEvent
	public void onTickEnd(TickEvent.WorldTickEvent event)
	{
		if (event.side != Side.SERVER)
			return;

		if (event.phase == TickEvent.Phase.END)
		{
			World world = event.world;
			int dimension = world.provider.getDimension();

			ArrayDeque<ChunkPos> chunks = chunksToGenerate.get(dimension);

			if (chunks != null && !chunks.isEmpty())
			{
				ChunkPos c = chunks.pollFirst();
				long worldSeed = world.getSeed();
				Random random = new Random(worldSeed);
				long xSeed = random.nextLong() >> 2 + 1L;
				long zSeed = random.nextLong() >> 2 + 1L;
				random.setSeed(xSeed * c.x + zSeed * c.z ^ worldSeed);
				ModWorldGen.instance.generateWorld(random, c.x, c.z, world, false);
				chunksToGenerate.put(dimension, chunks);
			}
			else if (chunks != null)
				chunksToGenerate.remove(dimension);
		}
	}

}
