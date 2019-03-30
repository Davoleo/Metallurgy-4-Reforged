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

import java.util.ArrayDeque;
import java.util.Random;

import org.apache.logging.log4j.Level;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.OreGenerationConfig;
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

public class ModWorldGen implements IWorldGenerator {
	
	private final Block DEFAULT_WORLD = Blocks.STONE;
	private final Block DEFAULT_NETHER = Blocks.NETHERRACK;
	private final Block DEFAULT_END = Blocks.END_STONE;
	
	private final int COMMON = OreGenerationConfig.rarity.commonRarity;
	private final int UNCOMMON = OreGenerationConfig.rarity.uncommonRarity;
	private final int RARE = OreGenerationConfig.rarity.rareRarity;
	private final int ULTRA_RARE = OreGenerationConfig.rarity.ultraRareRarity;

	public static final String RETROGEN_NAME = "MetallurgyOreGeneration";
	public static ModWorldGen instance = new ModWorldGen();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
      generateWorld(random, chunkX, chunkZ, world, true);
	}

	void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {
        if (!newGen && !OreGenerationConfig.retrogen)
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
		generateOre(ModMetals.COPPER.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.copper.veinSize, COMMON, OreGenerationConfig.copper.minY, OreGenerationConfig.copper.maxY, DEFAULT_WORLD, (Biome) null);
		//Generated in aquatic biomes only
		generateOre(ModMetals.DEEP_IRON.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.deepIron.veinSize, COMMON, OreGenerationConfig.deepIron.minY, OreGenerationConfig.deepIron.maxY, DEFAULT_WORLD, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.BEACH, Biomes.COLD_BEACH, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER, Biomes.RIVER);
		generateOre(ModMetals.TIN.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.tin.veinSize, COMMON, OreGenerationConfig.tin.minY,OreGenerationConfig.tin.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.PROMETHEUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.prometheum.veinSize, COMMON, OreGenerationConfig.prometheum.minY,OreGenerationConfig.prometheum.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.MANGANESE.getOre(),world, random, chunkX, chunkZ, OreGenerationConfig.manganese.veinSize, COMMON, OreGenerationConfig.manganese.minY, OreGenerationConfig.manganese.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.OURECLASE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.oureclase.veinSize, UNCOMMON, OreGenerationConfig.oureclase.minY, OreGenerationConfig.oureclase.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.INFUSCOLIUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.infuscolium.veinSize, UNCOMMON, OreGenerationConfig.infuscolium.minY, OreGenerationConfig.infuscolium.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ADAMANTINE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.adamantine.veinSize, RARE, OreGenerationConfig.adamantine.minY, OreGenerationConfig.adamantine.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.RUBRACIUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.rubracium.veinSize, UNCOMMON,OreGenerationConfig.rubracium.minY, OreGenerationConfig.rubracium.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ATLARUS.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.atlarus.veinSize, RARE, OreGenerationConfig.atlarus.minY, OreGenerationConfig.atlarus.maxY,  DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.CARMOT.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.carmot.veinSize, UNCOMMON, OreGenerationConfig.carmot.minY, OreGenerationConfig.carmot.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.MITHRIL.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.mithril.veinSize, ULTRA_RARE, OreGenerationConfig.mithril.minY, OreGenerationConfig.mithril.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ZINC.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.zinc.veinSize, COMMON, OreGenerationConfig.zinc.minY, OreGenerationConfig.zinc.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ORICHALCUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.orichalcum.veinSize, UNCOMMON,OreGenerationConfig.orichalcum.minY, OreGenerationConfig.orichalcum.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.ASTRAL_SILVER.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.astralSilver.veinSize, RARE, OreGenerationConfig.astralSilver.minY, OreGenerationConfig.astralSilver.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.SILVER.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.silver.veinSize, UNCOMMON, OreGenerationConfig.silver.minY, OreGenerationConfig.silver.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModMetals.PLATINUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.platinum.veinSize, RARE, OreGenerationConfig.platinum.minY, OreGenerationConfig.platinum.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkZ,  OreGenerationConfig.sulfur.veinSize, UNCOMMON, OreGenerationConfig.sulfur.minY, OreGenerationConfig.sulfur.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkZ, OreGenerationConfig.phosphorite.veinSize, UNCOMMON, OreGenerationConfig.phosphorite.minY, OreGenerationConfig.phosphorite.maxY, DEFAULT_WORLD, (Biome) null);
		generateOre(ModBlocks.orePotash, world, random, chunkX, chunkZ, OreGenerationConfig.potash.veinSize, COMMON, OreGenerationConfig.potash.minY, OreGenerationConfig.potash.maxY, DEFAULT_WORLD, (Biome) null);
  }

	//Nether
	private void generateNether(Random random, int chunkX, int chunkZ, World world){
		generateOre(ModMetals.IGNATIUS.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.ignatius.veinSize, COMMON,  OreGenerationConfig.ignatius.minY,  OreGenerationConfig.ignatius.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.SHADOW_IRON.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.shadowIron.veinSize, COMMON, OreGenerationConfig.shadowIron.minY, OreGenerationConfig.shadowIron.minY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.LEMURITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.lemurite.veinSize, COMMON, OreGenerationConfig.lemurite.minY, OreGenerationConfig.lemurite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.MIDASIUM.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.midasium.veinSize, UNCOMMON, OreGenerationConfig.midasium.minY, OreGenerationConfig.midasium.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.VYROXERES.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.vyroxeres.veinSize, UNCOMMON, OreGenerationConfig.vyroxeres.minY, OreGenerationConfig.vyroxeres.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.CERUCLASE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.cerucalse.veinSize, RARE, OreGenerationConfig.cerucalse.minY, OreGenerationConfig.cerucalse.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.ALDUORITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.alduorite.veinSize, UNCOMMON, OreGenerationConfig.alduorite.minY, OreGenerationConfig.alduorite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.KALENDRITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.kalendrite.veinSize, UNCOMMON, OreGenerationConfig.kalendrite.minY, OreGenerationConfig.kalendrite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.VULCANITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.vulcanite.veinSize, UNCOMMON, OreGenerationConfig.vulcanite.minY, OreGenerationConfig.vulcanite.maxY, DEFAULT_NETHER, (Biome) null);
		generateOre(ModMetals.SANGUINITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.sanguinite.veinSize, RARE, OreGenerationConfig.sanguinite.minY, OreGenerationConfig.sanguinite.maxY, DEFAULT_NETHER, (Biome) null);
	}

	private void generateEnd(Random random, int chunkX, int chunkZ, World world) {
		generateOre(ModMetals.EXIMITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.eximite.veinSize, COMMON, OreGenerationConfig.eximite.minY, OreGenerationConfig.eximite.maxY, DEFAULT_END, (Biome) null);
		generateOre(ModMetals.MEUTOITE.getOre(), world, random, chunkX, chunkZ, OreGenerationConfig.meutoite.veinSize, COMMON, OreGenerationConfig.meutoite.minY, OreGenerationConfig.meutoite.maxY, DEFAULT_END, (Biome) null);
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
                if (OreGenerationConfig.verbose_retrogen)
                {
                    Metallurgy.logger.log(Level.DEBUG, "Queuing retrogen for chunk: " + coordinates.toString() + ".");
                }
                regen = true;
            }
        }
        else
            regen = OreGenerationConfig.retrogen;

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
