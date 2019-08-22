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

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.WorldGenerationConfig;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorldGen {
	
	private static final int COMMON = WorldGenerationConfig.rarity.commonRarity;
	private static final int UNCOMMON = WorldGenerationConfig.rarity.uncommonRarity;
	private static final int RARE = WorldGenerationConfig.rarity.rareRarity;
	private static final int ULTRA_RARE = WorldGenerationConfig.rarity.ultraRareRarity;

	public static final String RETROGEN_NAME = "MetallurgyOreGeneration";
	//public static ModWorldGen instance = new ModWorldGen();

	public static void addGenFeatures() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			if (biome.getCategory() == Biome.Category.NETHER) {
				generateNether(biome);
                
            } else if (biome.getCategory() == Biome.Category.THEEND) {
            	generateEnd(biome);
                
            } else {
            	generateOverworld(biome);
            }
		}
	}
	
//	@Override
//	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
//      generateWorld(random, chunkX, chunkZ, world, true);
//	}
//
//	void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
//    {
//        if (!newGen && !WorldGenerationConfig.retrogen)
//            return;
//        if(world.provider.isSurfaceWorld())
//        	 generateOverworld(random, chunkX, chunkZ, world);
//        else
//        	if(world.provider.isNether())
//        		generateNether(random, chunkX, chunkZ, world);
//        	else
//        		generateEnd(random, chunkX, chunkZ, world);
//        if (!newGen) {
//            world.getChunk(chunkX, chunkZ).markDirty();
//        }
//    }

	//Overworld
	private static void generateOverworld(Biome biome) {
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.COPPER.getOre(), WorldGenerationConfig.copper.veinSize, COMMON, WorldGenerationConfig.copper.minY, WorldGenerationConfig.copper.maxY);
		//Generated in aquatic biomes only
		if (biome.getCategory() == Biome.Category.OCEAN || biome.getCategory() == Biome.Category.RIVER || biome.getCategory() == Biome.Category.BEACH)
			generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.DEEP_IRON.getOre(), WorldGenerationConfig.deepIron.veinSize, COMMON, WorldGenerationConfig.deepIron.minY, WorldGenerationConfig.deepIron.maxY);
		
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.TIN.getOre(), WorldGenerationConfig.tin.veinSize, COMMON, WorldGenerationConfig.tin.minY, WorldGenerationConfig.tin.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.PROMETHEUM.getOre(), WorldGenerationConfig.prometheum.veinSize, COMMON, WorldGenerationConfig.prometheum.minY, WorldGenerationConfig.prometheum.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.MANGANESE.getOre(), WorldGenerationConfig.manganese.veinSize, COMMON, WorldGenerationConfig.manganese.minY, WorldGenerationConfig.manganese.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.OURECLASE.getOre(), WorldGenerationConfig.oureclase.veinSize, UNCOMMON, WorldGenerationConfig.oureclase.minY, WorldGenerationConfig.oureclase.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.INFUSCOLIUM.getOre(), WorldGenerationConfig.infuscolium.veinSize, UNCOMMON, WorldGenerationConfig.infuscolium.minY, WorldGenerationConfig.infuscolium.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.ADAMANTINE.getOre(), WorldGenerationConfig.adamantine.veinSize, RARE, WorldGenerationConfig.adamantine.minY, WorldGenerationConfig.adamantine.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.RUBRACIUM.getOre(), WorldGenerationConfig.rubracium.veinSize, UNCOMMON, WorldGenerationConfig.rubracium.minY, WorldGenerationConfig.rubracium.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.ATLARUS.getOre(), WorldGenerationConfig.atlarus.veinSize, RARE, WorldGenerationConfig.atlarus.minY, WorldGenerationConfig.atlarus.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.CARMOT.getOre(), WorldGenerationConfig.carmot.veinSize, UNCOMMON, WorldGenerationConfig.carmot.minY, WorldGenerationConfig.carmot.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.MITHRIL.getOre(), WorldGenerationConfig.mithril.veinSize, ULTRA_RARE, WorldGenerationConfig.mithril.minY, WorldGenerationConfig.mithril.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.ZINC.getOre(), WorldGenerationConfig.zinc.veinSize, COMMON, WorldGenerationConfig.zinc.minY, WorldGenerationConfig.zinc.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.ORICHALCUM.getOre(), WorldGenerationConfig.orichalcum.veinSize, UNCOMMON, WorldGenerationConfig.orichalcum.minY, WorldGenerationConfig.orichalcum.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.ASTRAL_SILVER.getOre(), WorldGenerationConfig.astralSilver.veinSize, RARE, WorldGenerationConfig.astralSilver.minY, WorldGenerationConfig.astralSilver.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.SILVER.getOre(), WorldGenerationConfig.silver.veinSize, UNCOMMON, WorldGenerationConfig.silver.minY, WorldGenerationConfig.silver.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.PLATINUM.getOre(), WorldGenerationConfig.platinum.veinSize, RARE, WorldGenerationConfig.platinum.minY, WorldGenerationConfig.platinum.maxY);
        generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.LUTETIUM.getOre(), WorldGenerationConfig.lutetium.veinSize, RARE, WorldGenerationConfig.lutetium.minY, WorldGenerationConfig.lutetium.maxY);
        generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.OSMIUM.getOre(), WorldGenerationConfig.osmium.veinSize, UNCOMMON, WorldGenerationConfig.osmium.minY, WorldGenerationConfig.osmium.maxY);
		//generateOre(biome, FillerBlockType.NATURAL_STONE, ModBlocks.oreSulfur,  WorldGenerationConfig.sulfur.veinSize, UNCOMMON, WorldGenerationConfig.sulfur.minY, WorldGenerationConfig.sulfur.maxY);
		//generateOre(biome, FillerBlockType.NATURAL_STONE, ModBlocks.orePhosphorite, WorldGenerationConfig.phosphorite.veinSize, UNCOMMON, WorldGenerationConfig.phosphorite.minY, WorldGenerationConfig.phosphorite.maxY);
		//generateOre(biome, FillerBlockType.NATURAL_STONE, ModBlocks.orePotash, WorldGenerationConfig.potash.veinSize, COMMON, WorldGenerationConfig.potash.minY, WorldGenerationConfig.potash.maxY);
  }

	//Nether
	private static void generateNether(Biome biome) {
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.IGNATIUS.getOre(), WorldGenerationConfig.ignatius.veinSize, COMMON, WorldGenerationConfig.ignatius.minY, WorldGenerationConfig.ignatius.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.SHADOW_IRON.getOre(), WorldGenerationConfig.shadowIron.veinSize, COMMON, WorldGenerationConfig.shadowIron.minY, WorldGenerationConfig.shadowIron.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.LEMURITE.getOre(), WorldGenerationConfig.lemurite.veinSize, COMMON, WorldGenerationConfig.lemurite.minY, WorldGenerationConfig.lemurite.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.MIDASIUM.getOre(), WorldGenerationConfig.midasium.veinSize, UNCOMMON, WorldGenerationConfig.midasium.minY, WorldGenerationConfig.midasium.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.VYROXERES.getOre(), WorldGenerationConfig.vyroxeres.veinSize, UNCOMMON, WorldGenerationConfig.vyroxeres.minY, WorldGenerationConfig.vyroxeres.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.CERUCLASE.getOre(), WorldGenerationConfig.cerucalse.veinSize, RARE, WorldGenerationConfig.cerucalse.minY, WorldGenerationConfig.cerucalse.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.ALDUORITE.getOre(), WorldGenerationConfig.alduorite.veinSize, UNCOMMON, WorldGenerationConfig.alduorite.minY, WorldGenerationConfig.alduorite.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.KALENDRITE.getOre(), WorldGenerationConfig.kalendrite.veinSize, UNCOMMON, WorldGenerationConfig.kalendrite.minY, WorldGenerationConfig.kalendrite.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.VULCANITE.getOre(), WorldGenerationConfig.vulcanite.veinSize, UNCOMMON, WorldGenerationConfig.vulcanite.minY, WorldGenerationConfig.vulcanite.maxY);
		generateOre(biome, FillerBlockType.NETHERRACK, ModMetals.SANGUINITE.getOre(), WorldGenerationConfig.sanguinite.veinSize, RARE, WorldGenerationConfig.sanguinite.minY, WorldGenerationConfig.sanguinite.maxY);
	}
	
	private static void generateEnd(Biome biome) {
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.EXIMITE.getOre(), WorldGenerationConfig.eximite.veinSize, COMMON, WorldGenerationConfig.eximite.minY, WorldGenerationConfig.eximite.maxY);
		generateOre(biome, FillerBlockType.NATURAL_STONE, ModMetals.MEUTOITE.getOre(), WorldGenerationConfig.meutoite.veinSize, COMMON, WorldGenerationConfig.meutoite.minY, WorldGenerationConfig.meutoite.maxY);
	}
	
	private static void generateOre(Biome biome, FillerBlockType fillerBlock, Block ore, int size, int rarity, int minY, int maxY) {
		biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, 
				new OreFeatureConfig(fillerBlock, ore.getDefaultState(), size),
				Placement.COUNT_RANGE,
				new CountRangeConfig(rarity, minY, 0, maxY))); // Count, MinHeight, MaxHeightBase, MaxHeight
	}

//    private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, Biome... biomes) {
//
//        if (minY < 0 || maxY > 256 || minY > maxY)
//            throw new IllegalArgumentException("Illegal Height Arguments for Biome Specific WorldGenerator");
//
//        if(veinSize > 0) {
//        	 WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), veinSize < 3 && veinSize != 0 ? 3:veinSize, BlockMatcher.forBlock(blockToReplace));
//             int heightdiff = maxY - minY +1;
//             int x, y, z;
//
//             for (int i = 0; i < rarity; i++){
//                 x = chunkX * 16 +random.nextInt(16);
//                 y = minY + random.nextInt(heightdiff);
//                 z = chunkZ * 16 + random.nextInt(16);
//
//                 BlockPos pos = new BlockPos(x, y, z);
//                 
//                 if (biomes[0] != null)
//                 {
//     				for (Biome biome : biomes)
//     					if (world.getBiome(pos).equals(biome))
//     						generator.generate(world, random, pos);
//     			}
//                 else
//                 	generator.generate(world, random, pos);
//             }
//        }		
//    }

    //Retrogen data save & load
//    @SubscribeEvent
//    public void onChunkSave(ChunkDataEvent.Save event)
//    {
//        NBTTagCompound genTag = event.getData().getCompoundTag(RETROGEN_NAME);
//        if (!genTag.hasKey("generated"))
//            genTag.setBoolean("generated", true);
//        event.getData().setTag(RETROGEN_NAME, genTag);
//    }
//
//    @SubscribeEvent
//    public void onChunkLoad(ChunkDataEvent.Load event)
//    {
//        int dimension = event.getWorld().provider.getDimension();
//
//        boolean regen = false;
//        NBTTagCompound tag = (NBTTagCompound) event.getData().getTag(RETROGEN_NAME);
//        ChunkPos coordinates = event.getChunk().getPos();
//
//        if (tag != null)
//        {
//            boolean generated = false;
//            if (generated)
//            {
//                if (WorldGenerationConfig.verbose_retrogen)
//                {
//                    Metallurgy.logger.log(Level.INFO, "Queuing retrogen for chunk: " + coordinates.toString() + ".");
//                }
//                regen = true;
//            }
//        }
//        else
//            regen = WorldGenerationConfig.retrogen;
//
//        if (regen)
//        {
//            ArrayDeque<ChunkPos> chunks = WorldTickHandler.chunksToGenerate.get(dimension);
//
//            if (chunks == null)
//            {
//                WorldTickHandler.chunksToGenerate.put(dimension, new ArrayDeque<>(128));
//                chunks = WorldTickHandler.chunksToGenerate.get(dimension);
//            }
//            if (chunks != null)
//            {
//                chunks.addLast(coordinates);
//                WorldTickHandler.chunksToGenerate.put(dimension, chunks);
//            }
//        }
//    }
//
//	@Override
//	public void generate(Random random, int chunkX, int chunkZ, World world, ChunkGenerator chunkGenerator,
//			AbstractChunkProvider chunkProvider) {
//		// TODO Auto-generated method stub
//		
//	}
}
