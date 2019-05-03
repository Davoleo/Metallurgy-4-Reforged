/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModWorldGen
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.world;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.WorldGenerationConfig;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.util.ArrayDeque;
import java.util.Random;

public class ModWorldGen implements IWorldGenerator {
	
	private final Block DEFAULT_WORLD = Blocks.STONE;
	private final Block DEFAULT_NETHER = Blocks.NETHERRACK;
	private final Block DEFAULT_END = Blocks.END_STONE;
	
	private final int COMMON = WorldGenerationConfig.rarity.commonRarity;
	private final int UNCOMMON = WorldGenerationConfig.rarity.uncommonRarity;
	private final int RARE = WorldGenerationConfig.rarity.rareRarity;
	private final int ULTRA_RARE = WorldGenerationConfig.rarity.ultraRareRarity;

	public static final String RETROGEN_NAME = "MetallurgyOreGeneration";
	public static ModWorldGen instance = new ModWorldGen();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
      generateWorld(random, chunkX, chunkZ, world, true);
	}

	void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {
        if (!newGen && !WorldGenerationConfig.retrogen)
            return;
        if(world.provider.isSurfaceWorld())
        	 generateOverworld(random, chunkX, chunkZ, world);
        else
        	if(world.provider.isNether())
        		generateNether(random, chunkX, chunkZ, world);
        	else
        		generateEnd(random, chunkX, chunkZ, world);
        if (!newGen) {
            world.getChunk(chunkX, chunkZ).markDirty();
        }
    }

	//Overworld
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		generateOre(ModMetals.COPPER.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.copper.veinSize, COMMON, WorldGenerationConfig.copper.minY, WorldGenerationConfig.copper.maxY, DEFAULT_WORLD, (Biome) null);
		//Generated in aquatic biomes only
		generateOre(ModMetals.DEEP_IRON.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.deepIron.veinSize, COMMON, WorldGenerationConfig.deepIron.minY, WorldGenerationConfig.deepIron.maxY, DEFAULT_WORLD, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.BEACH, Biomes.COLD_BEACH, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER, Biomes.RIVER);
		generateOre(ModMetals.TIN.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.tin.veinSize, COMMON, WorldGenerationConfig.tin.minY, WorldGenerationConfig.tin.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.PROMETHEUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.prometheum.veinSize, COMMON, WorldGenerationConfig.prometheum.minY, WorldGenerationConfig.prometheum.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.MANGANESE.getOre(),world, random, chunkX, chunkZ, WorldGenerationConfig.manganese.veinSize, COMMON, WorldGenerationConfig.manganese.minY, WorldGenerationConfig.manganese.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.OURECLASE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.oureclase.veinSize, UNCOMMON, WorldGenerationConfig.oureclase.minY, WorldGenerationConfig.oureclase.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.INFUSCOLIUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.infuscolium.veinSize, UNCOMMON, WorldGenerationConfig.infuscolium.minY, WorldGenerationConfig.infuscolium.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ADAMANTINE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.adamantine.veinSize, RARE, WorldGenerationConfig.adamantine.minY, WorldGenerationConfig.adamantine.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.RUBRACIUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.rubracium.veinSize, UNCOMMON, WorldGenerationConfig.rubracium.minY, WorldGenerationConfig.rubracium.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ATLARUS.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.atlarus.veinSize, RARE, WorldGenerationConfig.atlarus.minY, WorldGenerationConfig.atlarus.maxY,  DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.CARMOT.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.carmot.veinSize, UNCOMMON, WorldGenerationConfig.carmot.minY, WorldGenerationConfig.carmot.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.MITHRIL.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.mithril.veinSize, ULTRA_RARE, WorldGenerationConfig.mithril.minY, WorldGenerationConfig.mithril.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ZINC.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.zinc.veinSize, COMMON, WorldGenerationConfig.zinc.minY, WorldGenerationConfig.zinc.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ORICHALCUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.orichalcum.veinSize, UNCOMMON, WorldGenerationConfig.orichalcum.minY, WorldGenerationConfig.orichalcum.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ASTRAL_SILVER.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.astralSilver.veinSize, RARE, WorldGenerationConfig.astralSilver.minY, WorldGenerationConfig.astralSilver.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.SILVER.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.silver.veinSize, UNCOMMON, WorldGenerationConfig.silver.minY, WorldGenerationConfig.silver.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.PLATINUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.platinum.veinSize, RARE, WorldGenerationConfig.platinum.minY, WorldGenerationConfig.platinum.maxY, DEFAULT_WORLD, (Biome) null);
        generateOre(ModMetals.LUTETIUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.lutetium.veinSize, RARE, WorldGenerationConfig.lutetium.minY, WorldGenerationConfig.lutetium.maxY, DEFAULT_WORLD, (Biome) null);
        generateOre(ModMetals.OSMIUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.osmium.veinSize, UNCOMMON, WorldGenerationConfig.osmium.minY, WorldGenerationConfig.osmium.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkZ,  WorldGenerationConfig.sulfur.veinSize, UNCOMMON, WorldGenerationConfig.sulfur.minY, WorldGenerationConfig.sulfur.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkZ, WorldGenerationConfig.phosphorite.veinSize, UNCOMMON, WorldGenerationConfig.phosphorite.minY, WorldGenerationConfig.phosphorite.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.orePotash, world, random, chunkX, chunkZ, WorldGenerationConfig.potash.veinSize, COMMON, WorldGenerationConfig.potash.minY, WorldGenerationConfig.potash.maxY, DEFAULT_WORLD, (Biome) null);
  }

	//Nether
	private void generateNether(Random random, int chunkX, int chunkZ, World world){
		generateOre(ModMetals.IGNATIUS.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.ignatius.veinSize, COMMON,  WorldGenerationConfig.ignatius.minY,  WorldGenerationConfig.ignatius.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.SHADOW_IRON.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.shadowIron.veinSize, COMMON, WorldGenerationConfig.shadowIron.minY, WorldGenerationConfig.shadowIron.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.LEMURITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.lemurite.veinSize, COMMON, WorldGenerationConfig.lemurite.minY, WorldGenerationConfig.lemurite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.MIDASIUM.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.midasium.veinSize, UNCOMMON, WorldGenerationConfig.midasium.minY, WorldGenerationConfig.midasium.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.VYROXERES.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.vyroxeres.veinSize, UNCOMMON, WorldGenerationConfig.vyroxeres.minY, WorldGenerationConfig.vyroxeres.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.CERUCLASE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.cerucalse.veinSize, RARE, WorldGenerationConfig.cerucalse.minY, WorldGenerationConfig.cerucalse.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.ALDUORITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.alduorite.veinSize, UNCOMMON, WorldGenerationConfig.alduorite.minY, WorldGenerationConfig.alduorite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.KALENDRITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.kalendrite.veinSize, UNCOMMON, WorldGenerationConfig.kalendrite.minY, WorldGenerationConfig.kalendrite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.VULCANITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.vulcanite.veinSize, UNCOMMON, WorldGenerationConfig.vulcanite.minY, WorldGenerationConfig.vulcanite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.SANGUINITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.sanguinite.veinSize, RARE, WorldGenerationConfig.sanguinite.minY, WorldGenerationConfig.sanguinite.maxY, DEFAULT_NETHER, (Biome) null);
	}

	private void generateEnd(Random random, int chunkX, int chunkZ, World world) {
		generateOre(ModMetals.EXIMITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.eximite.veinSize, COMMON, WorldGenerationConfig.eximite.minY, WorldGenerationConfig.eximite.maxY, DEFAULT_END, (Biome) null);
		generateOre(ModMetals.MEUTOITE.getOre(), world, random, chunkX, chunkZ, WorldGenerationConfig.meutoite.veinSize, COMMON, WorldGenerationConfig.meutoite.minY, WorldGenerationConfig.meutoite.maxY, DEFAULT_END, (Biome) null);
	}

    private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, Biome... biomes) {

        if (minY < 0 || maxY > 256 || minY > maxY)
            throw new IllegalArgumentException("Illegal Height Arguments for Biome Specific WorldGenerator");

        if(veinSize > 0) {
        	 WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), veinSize < 3 && veinSize != 0 ? 3:veinSize, BlockMatcher.forBlock(blockToReplace));
             int heightdiff = maxY - minY +1;
             int x, y, z;

             for (int i = 0; i < rarity; i++){
                 x = chunkX * 16 +random.nextInt(16);
                 y = minY + random.nextInt(heightdiff);
                 z = chunkZ * 16 + random.nextInt(16);

                 BlockPos pos = new BlockPos(x, y, z);
                 
                 if (biomes[0] != null)
                 {
     				for (Biome biome : biomes)
     					if (world.getBiome(pos).equals(biome))
     						generator.generate(world, random, pos);
     			}
                 else
                 	generator.generate(world, random, pos);
             }
        }		
    }

    //Retrogen data save & load
    @SubscribeEvent
    public void onChunkSave(ChunkDataEvent.Save event)
    {
        NBTTagCompound genTag = event.getData().getCompoundTag(RETROGEN_NAME);
        if (!genTag.hasKey("generated"))
            genTag.setBoolean("generated", true);
        event.getData().setTag(RETROGEN_NAME, genTag);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkDataEvent.Load event)
    {
        int dimension = event.getWorld().provider.getDimension();

        boolean regen = false;
        NBTTagCompound tag = (NBTTagCompound) event.getData().getTag(RETROGEN_NAME);
        ChunkPos coordinates = event.getChunk().getPos();

        if (tag != null)
        {
            boolean generated = false;
            if (generated)
            {
                if (WorldGenerationConfig.verbose_retrogen)
                {
                    Metallurgy.logger.log(Level.INFO, "Queuing retrogen for chunk: " + coordinates.toString() + ".");
                }
                regen = true;
            }
        }
        else
            regen = WorldGenerationConfig.retrogen;

        if (regen)
        {
            ArrayDeque<ChunkPos> chunks = WorldTickHandler.chunksToGenerate.get(dimension);

            if (chunks == null)
            {
                WorldTickHandler.chunksToGenerate.put(dimension, new ArrayDeque<>(128));
                chunks = WorldTickHandler.chunksToGenerate.get(dimension);
            }
            if (chunks != null)
            {
                chunks.addLast(coordinates);
                WorldTickHandler.chunksToGenerate.put(dimension, chunks);
            }
        }
    }
}
