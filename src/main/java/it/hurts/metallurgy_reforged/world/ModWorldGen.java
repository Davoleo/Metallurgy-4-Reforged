/*
 * -------------------------------------------------------------------------------------------------------
 * Class: ModWorldGen
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.world;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.config.WorldGenerationConfig;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.world.spawn.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

import java.util.ArrayDeque;
import java.util.Random;

public class ModWorldGen implements IWorldGenerator {

    private static final Block DEFAULT_WORLD_BLOCK = Blocks.STONE;
    public static final Block DEFAULT_END_BLOCK = Blocks.END_STONE;

    public static final int COMMON = WorldGenerationConfig.rarity.commonRarity;
    public static final int UNCOMMON = WorldGenerationConfig.rarity.uncommonRarity;
    private final int RARE = WorldGenerationConfig.rarity.rareRarity;
    private final int ULTRA_RARE = WorldGenerationConfig.rarity.ultraRareRarity;

    private static final String RETROGEN_NAME = "MetallurgyOreGeneration";
    public static ModWorldGen instance = new ModWorldGen();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        generateWorld(random, chunkX, chunkZ, world, true);
    }

    void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {
        if(!newGen && !WorldGenerationConfig.retrogen)
            return;
        if(world.provider.isSurfaceWorld())
            generateOverworld(random, chunkX, chunkZ, world);
        else if(world.provider.isNether())
            generateNether(random, chunkX, chunkZ, world);
        else
            generateEnd(random, chunkX, chunkZ, world);
        if(!newGen)
        {
            world.getChunkFromChunkCoords(chunkX, chunkZ).markDirty();
        }
    }

    //Overworld
    private void generateOverworld(Random random, int chunkX, int chunkZ, World world)
    {
        generateOre(ModMetals.COPPER, world, random, chunkX, chunkZ, WorldGenerationConfig.copper.veinSize, COMMON, WorldGenerationConfig.copper.minY, WorldGenerationConfig.copper.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.copper.biomes);
        generateOre(ModMetals.DEEP_IRON, world, random, chunkX, chunkZ, WorldGenerationConfig.deepIron.veinSize, COMMON, WorldGenerationConfig.deepIron.minY, WorldGenerationConfig.deepIron.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.deepIron.biomes);
        generateOre(ModMetals.TIN, world, random, chunkX, chunkZ, WorldGenerationConfig.tin.veinSize, COMMON, WorldGenerationConfig.tin.minY, WorldGenerationConfig.tin.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.tin.biomes);
        generateOre(ModMetals.PROMETHEUM, world, random, chunkX, chunkZ, WorldGenerationConfig.prometheum.veinSize, COMMON, WorldGenerationConfig.prometheum.minY, WorldGenerationConfig.prometheum.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.prometheum.biomes);
        generateOre(ModMetals.MANGANESE, world, random, chunkX, chunkZ, WorldGenerationConfig.manganese.veinSize, COMMON, WorldGenerationConfig.manganese.minY, WorldGenerationConfig.manganese.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.manganese.biomes);
        generateOre(ModMetals.OURECLASE, world, random, chunkX, chunkZ, WorldGenerationConfig.oureclase.veinSize, UNCOMMON, WorldGenerationConfig.oureclase.minY, WorldGenerationConfig.oureclase.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.oureclase.biomes);
        generateOre(ModMetals.INFUSCOLIUM, world, random, chunkX, chunkZ, WorldGenerationConfig.infuscolium.veinSize, UNCOMMON, WorldGenerationConfig.infuscolium.minY, WorldGenerationConfig.infuscolium.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.infuscolium.biomes);
        generateOre(ModMetals.ADAMANTINE, world, random, chunkX, chunkZ, WorldGenerationConfig.adamantine.veinSize, RARE, WorldGenerationConfig.adamantine.minY, WorldGenerationConfig.adamantine.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.adamantine.biomes);
        generateOre(ModMetals.RUBRACIUM, world, random, chunkX, chunkZ, WorldGenerationConfig.rubracium.veinSize, UNCOMMON, WorldGenerationConfig.rubracium.minY, WorldGenerationConfig.rubracium.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.rubracium.biomes);
        generateOre(ModMetals.ATLARUS, world, random, chunkX, chunkZ, WorldGenerationConfig.atlarus.veinSize, RARE, WorldGenerationConfig.atlarus.minY, WorldGenerationConfig.atlarus.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.atlarus.biomes);
        generateOre(ModMetals.CARMOT, world, random, chunkX, chunkZ, WorldGenerationConfig.carmot.veinSize, UNCOMMON, WorldGenerationConfig.carmot.minY, WorldGenerationConfig.carmot.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.carmot.biomes);
        generateOre(ModMetals.MITHRIL, world, random, chunkX, chunkZ, WorldGenerationConfig.mithril.veinSize, ULTRA_RARE, WorldGenerationConfig.mithril.minY, WorldGenerationConfig.mithril.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.mithril.biomes);
        generateOre(ModMetals.ZINC, world, random, chunkX, chunkZ, WorldGenerationConfig.zinc.veinSize, COMMON, WorldGenerationConfig.zinc.minY, WorldGenerationConfig.zinc.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.zinc.biomes);
        generateOre(ModMetals.ORICHALCUM, world, random, chunkX, chunkZ, WorldGenerationConfig.orichalcum.veinSize, UNCOMMON, WorldGenerationConfig.orichalcum.minY, WorldGenerationConfig.orichalcum.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.orichalcum.biomes);
        generateOre(ModMetals.ASTRAL_SILVER, world, random, chunkX, chunkZ, WorldGenerationConfig.astralSilver.veinSize, RARE, WorldGenerationConfig.astralSilver.minY, WorldGenerationConfig.astralSilver.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.astralSilver.biomes);
        generateOre(ModMetals.SILVER, world, random, chunkX, chunkZ, WorldGenerationConfig.silver.veinSize, UNCOMMON, WorldGenerationConfig.silver.minY, WorldGenerationConfig.silver.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.silver.biomes);
        generateOre(ModMetals.PLATINUM, world, random, chunkX, chunkZ, WorldGenerationConfig.platinum.veinSize, RARE, WorldGenerationConfig.platinum.minY, WorldGenerationConfig.platinum.maxY, new HighChanceTemperatureSpawn(DEFAULT_WORLD_BLOCK, convertStringToBiome(WorldGenerationConfig.platinum.biomes), Biome.TempCategory.WARM, UNCOMMON));
        generateOre(ModMetals.LUTETIUM, world, random, chunkX, chunkZ, WorldGenerationConfig.lutetium.veinSize, RARE, WorldGenerationConfig.lutetium.minY, WorldGenerationConfig.lutetium.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.lutetium.biomes);
        generateOre(ModMetals.OSMIUM, world, random, chunkX, chunkZ, WorldGenerationConfig.osmium.veinSize, UNCOMMON, WorldGenerationConfig.osmium.minY, WorldGenerationConfig.osmium.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.osmium.biomes);
        generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkZ, WorldGenerationConfig.sulfur.veinSize, UNCOMMON, WorldGenerationConfig.sulfur.minY, WorldGenerationConfig.sulfur.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.sulfur.biomes);
        generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkZ, WorldGenerationConfig.phosphorite.veinSize, UNCOMMON, WorldGenerationConfig.phosphorite.minY, WorldGenerationConfig.phosphorite.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.phosphorite.biomes);
        generateOre(ModBlocks.orePotash, world, random, chunkX, chunkZ, WorldGenerationConfig.potash.veinSize, COMMON, WorldGenerationConfig.potash.minY, WorldGenerationConfig.potash.maxY, DEFAULT_WORLD_BLOCK, WorldGenerationConfig.potash.biomes);
    }

    //Nether
    private void generateNether(Random random, int chunkX, int chunkZ, World world)
    {
        generateOre(ModMetals.IGNATIUS, world, random, chunkX, chunkZ, WorldGenerationConfig.ignatius.veinSize, COMMON, WorldGenerationConfig.ignatius.minY, WorldGenerationConfig.ignatius.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.ignatius.biomes)));
        generateOre(ModMetals.SHADOW_IRON, world, random, chunkX, chunkZ, WorldGenerationConfig.shadowIron.veinSize, COMMON, WorldGenerationConfig.shadowIron.minY, WorldGenerationConfig.shadowIron.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.shadowIron.biomes)));
        generateOre(ModMetals.LEMURITE, world, random, chunkX, chunkZ, WorldGenerationConfig.lemurite.veinSize, COMMON, WorldGenerationConfig.lemurite.minY, WorldGenerationConfig.lemurite.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.lemurite.biomes)));
        generateOre(ModMetals.MIDASIUM, world, random, chunkX, chunkZ, WorldGenerationConfig.midasium.veinSize, UNCOMMON, WorldGenerationConfig.midasium.minY, WorldGenerationConfig.midasium.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.midasium.biomes)));
        generateOre(ModMetals.VYROXERES, world, random, chunkX, chunkZ, WorldGenerationConfig.vyroxeres.veinSize, UNCOMMON, WorldGenerationConfig.vyroxeres.minY, WorldGenerationConfig.vyroxeres.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.vyroxeres.biomes)));
        generateOre(ModMetals.CERUCLASE, world, random, chunkX, chunkZ, WorldGenerationConfig.cerucalse.veinSize, RARE, WorldGenerationConfig.cerucalse.minY, WorldGenerationConfig.cerucalse.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.cerucalse.biomes)));
        generateOre(ModMetals.ALDUORITE, world, random, chunkX, chunkZ, WorldGenerationConfig.alduorite.veinSize, UNCOMMON, WorldGenerationConfig.alduorite.minY, WorldGenerationConfig.alduorite.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.alduorite.biomes)));
        generateOre(ModMetals.KALENDRITE, world, random, chunkX, chunkZ, WorldGenerationConfig.kalendrite.veinSize, UNCOMMON, WorldGenerationConfig.kalendrite.minY, WorldGenerationConfig.kalendrite.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.kalendrite.biomes)));
        generateOre(ModMetals.VULCANITE, world, random, chunkX, chunkZ, WorldGenerationConfig.vulcanite.veinSize, UNCOMMON, WorldGenerationConfig.vulcanite.minY, WorldGenerationConfig.vulcanite.maxY, new NetherOreSpawn(convertStringToBiome(WorldGenerationConfig.vulcanite.biomes)));
        generateOre(ModMetals.SANGUINITE, world, random, chunkX, chunkZ, WorldGenerationConfig.sanguinite.veinSize, RARE, WorldGenerationConfig.sanguinite.minY, WorldGenerationConfig.sanguinite.maxY, new NearFortressSpawn());
    }

    private void generateEnd(Random random, int chunkX, int chunkZ, World world)
    {
        generateOre(ModMetals.EXIMITE, world, random, chunkX, chunkZ, WorldGenerationConfig.eximite.veinSize, COMMON, WorldGenerationConfig.eximite.minY, WorldGenerationConfig.eximite.maxY, new SmallIslandsSpawn(convertStringToBiome(WorldGenerationConfig.eximite.biomes)));
        generateOre(ModMetals.MEUTOITE, world, random, chunkX, chunkZ, WorldGenerationConfig.meutoite.veinSize, COMMON, WorldGenerationConfig.meutoite.minY, WorldGenerationConfig.meutoite.maxY, DEFAULT_END_BLOCK, WorldGenerationConfig.meutoite.biomes);
    }

    private void generateOre(Metal metal, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, String[] biomesResource)
    {
        this.generateOre(metal, world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, new BaseOreSpawn(blockToReplace, convertStringToBiome(biomesResource)));
    }

    private void generateOre(Metal metal, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, IOreSpawn oreSpawn)
    {
        if (metal == null)
            return;

        this.generateOre(metal.getOre(), world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, oreSpawn);
    }

    private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, String[] biomesResource)
    {
        generateOre(block, world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, new BaseOreSpawn(blockToReplace, convertStringToBiome(biomesResource)));
    }

    private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, IOreSpawn oreSpawn)
    {
        if (minY < 0 || maxY > 256 || minY > maxY)
            throw new IllegalArgumentException("Illegal Height Arguments for Biome Specific WorldGenerator");

        if (veinSize > 0)
        {
            WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), Math.max(veinSize, 3), oreSpawn.getBlockPredicate());
            int heightdiff = maxY - minY + 1;
            int x, y, z;

            int oreSpawnRarity = oreSpawn.getRarity(world, chunkX, chunkZ, rarity);

            for (int i = 0; i < oreSpawnRarity; i++)
            {
                x = chunkX * 16 + random.nextInt(16);
                y = minY + random.nextInt(heightdiff);
                z = chunkZ * 16 + random.nextInt(16);

                BlockPos pos = new BlockPos(x, y, z);

                if(oreSpawn.canOreSpawn(world, pos, world.getBlockState(pos), random))
                    generator.generate(world, random, pos);
            }
        }
    }

    public static Biome[] convertStringToBiome(String[] biomesResource)
    {
        Biome[] biomes = new Biome[biomesResource.length];
        for (int i = 0; i < biomesResource.length; i++)
        {
            biomes[i] = ForgeRegistries.BIOMES.getValue(new ResourceLocation(biomesResource[i]));
            if(biomes[i] == null)
                Metallurgy.logger.error(Metallurgy.NAME + " Invalid biome resource location : " + biomesResource[i] + "!");
        }
        return biomes;
    }

    //Retrogen data save & load
    @SubscribeEvent
    public void onChunkSave(ChunkDataEvent.Save event)
    {
        NBTTagCompound genTag = event.getData().getCompoundTag(RETROGEN_NAME);
        if(!genTag.hasKey("generated"))
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

        if(tag != null)
        {
            boolean generated = false;
            if(generated)
            {
                if(WorldGenerationConfig.verbose_retrogen)
                {
                    Metallurgy.logger.log(Level.INFO, "Queuing retrogen for chunk: " + coordinates.toString() + ".");
                }
                regen = true;
            }
        }
        else
            regen = WorldGenerationConfig.retrogen;

        if(regen)
        {
            ArrayDeque<ChunkPos> chunks = WorldTickHandler.chunksToGenerate.get(dimension);

            if(chunks == null)
            {
                WorldTickHandler.chunksToGenerate.put(dimension, new ArrayDeque<>(128));
                chunks = WorldTickHandler.chunksToGenerate.get(dimension);
            }
            if(chunks != null)
            {
                chunks.addLast(coordinates);
                WorldTickHandler.chunksToGenerate.put(dimension, chunks);
            }
        }
    }

}
