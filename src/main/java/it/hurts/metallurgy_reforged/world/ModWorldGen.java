/*==============================================================================
 = Class: ModWorldGen
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world;

import com.google.common.collect.ImmutableSet;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.world.spawn.*;
import net.minecraft.block.Block;
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

import static it.hurts.metallurgy_reforged.config.WorldGenerationConfig.*;

public class ModWorldGen implements IWorldGenerator {

	private static final Block DEFAULT_WORLD_BLOCK = Blocks.STONE;
	public static final Block DEFAULT_END_BLOCK = Blocks.END_STONE;

	public static final int COMMON = rarity.commonRarity;
	private static final int UNCOMMON = rarity.uncommonRarity;
	private static final int RARE = rarity.rareRarity;
	private static final int VERY_RARE = rarity.veryRareRarity;
	private static final int ULTRA_VERY_RARE = rarity.ultraRareRarity;

	private static final String RETROGEN_NAME = "MetallurgyOreGeneration";
	public static final ModWorldGen INSTANCE = new ModWorldGen();


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		generateWorld(random, chunkX, chunkZ, world, true);
	}

	void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
	{
		if (!newGen && !retrogen)
			return;
		if (world.provider.isSurfaceWorld())
			generateOverworld(random, chunkX, chunkZ, world);
		else if (world.provider.isNether())
			generateNether(random, chunkX, chunkZ, world);
		else
			generateEnd(random, chunkX, chunkZ, world);
		if (!newGen)
		{
			world.getChunk(chunkX, chunkZ).markDirty();
		}
	}

	//Overworld
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world)
	{
		generateOre(ModMetals.COPPER, world, random, chunkX, chunkZ,
				copper.veinSize, COMMON,
				copper.minY, copper.maxY,
				DEFAULT_WORLD_BLOCK, copper.biomes
		);
		generateOre(ModMetals.DEEP_IRON, world, random, chunkX, chunkZ,
				deepIron.veinSize, COMMON,
				deepIron.minY, deepIron.maxY,
				DEFAULT_WORLD_BLOCK, deepIron.biomes
		);
		generateOre(ModMetals.TIN, world, random, chunkX, chunkZ,
				tin.veinSize, COMMON,
				tin.minY, tin.maxY,
				DEFAULT_WORLD_BLOCK, tin.biomes
		);
		generateOre(ModMetals.PROMETHEUM, world, random, chunkX, chunkZ,
				prometheum.veinSize, COMMON,
				prometheum.minY, prometheum.maxY,
				DEFAULT_WORLD_BLOCK, prometheum.biomes
		);
		generateOre(ModMetals.MANGANESE, world, random, chunkX, chunkZ,
				manganese.veinSize, COMMON,
				manganese.minY, manganese.maxY,
				DEFAULT_WORLD_BLOCK, manganese.biomes
		);
		generateOre(ModMetals.OURECLASE, world, random, chunkX, chunkZ,
				oureclase.veinSize, UNCOMMON,
				oureclase.minY, oureclase.maxY,
				DEFAULT_WORLD_BLOCK, oureclase.biomes
		);
		generateOre(ModMetals.INFUSCOLIUM, world, random, chunkX, chunkZ,
				infuscolium.veinSize, UNCOMMON,
				infuscolium.minY, infuscolium.maxY,
				DEFAULT_WORLD_BLOCK, infuscolium.biomes
		);
		generateOre(ModMetals.ADAMANTINE, world, random, chunkX, chunkZ,
				adamantine.veinSize, VERY_RARE,
				adamantine.minY, adamantine.maxY,
				DEFAULT_WORLD_BLOCK, adamantine.biomes
		);
		//noinspection ConstantConditions (RUBRACIUM.getOre() can't be null in any case)
		generateOre(ModMetals.RUBRACIUM, world, random, chunkX, chunkZ,
				rubracium.veinSize, UNCOMMON,
				rubracium.minY, rubracium.maxY,
				new VeinNextToBlockSpawn(DEFAULT_WORLD_BLOCK, rubracium.biomes, ImmutableSet.of(Blocks.LAVA, ModMetals.RUBRACIUM.getOre()))
		);
		generateOre(ModMetals.ATLARUS, world, random, chunkX, chunkZ,
				atlarus.veinSize, VERY_RARE,
				atlarus.minY, atlarus.maxY,
				DEFAULT_WORLD_BLOCK, atlarus.biomes
		);
		generateOre(ModMetals.CARMOT, world, random, chunkX, chunkZ,
				carmot.veinSize, RARE,
				carmot.minY, carmot.maxY,
				DEFAULT_WORLD_BLOCK, carmot.biomes
		);
		generateOre(ModMetals.MITHRIL, world, random, chunkX, chunkZ,
				mithril.veinSize, ULTRA_VERY_RARE,
				mithril.minY, mithril.maxY,
				DEFAULT_WORLD_BLOCK, mithril.biomes
		);
		generateOre(ModMetals.ZINC, world, random, chunkX, chunkZ,
				zinc.veinSize, COMMON,
				zinc.minY, zinc.maxY,
				DEFAULT_WORLD_BLOCK, zinc.biomes
		);
		generateOre(ModMetals.ORICHALCUM, world, random, chunkX, chunkZ,
				orichalcum.veinSize, RARE,
				orichalcum.minY, orichalcum.maxY,
				DEFAULT_WORLD_BLOCK, orichalcum.biomes
		);
		generateOre(ModMetals.ASTRAL_SILVER, world, random, chunkX, chunkZ,
				astralSilver.veinSize, UNCOMMON,
				astralSilver.minY, astralSilver.maxY,
				new HighChanceYLevelSpawn(DEFAULT_WORLD_BLOCK, astralSilver.biomes, 80, true, 0.5F)
		);
		generateOre(ModMetals.SILVER, world, random, chunkX, chunkZ,
				silver.veinSize, UNCOMMON,
				silver.minY, silver.maxY,
				DEFAULT_WORLD_BLOCK, silver.biomes
		);
		generateOre(ModMetals.PLATINUM, world, random, chunkX, chunkZ,
				platinum.veinSize, VERY_RARE,
				platinum.minY, platinum.maxY,
				new HighChanceTemperatureSpawn(DEFAULT_WORLD_BLOCK, platinum.biomes, Biome.TempCategory.WARM, UNCOMMON)
		);
		generateOre(ModMetals.OSMIUM, world, random, chunkX, chunkZ,
				osmium.veinSize, UNCOMMON,
				osmium.minY, osmium.maxY,
				DEFAULT_WORLD_BLOCK, osmium.biomes
		);
		generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkZ,
				sulfur.veinSize, UNCOMMON,
				sulfur.minY, sulfur.maxY,
				DEFAULT_WORLD_BLOCK, sulfur.biomes
		);
		generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkZ,
				phosphorite.veinSize, UNCOMMON,
				phosphorite.minY, phosphorite.maxY,
				DEFAULT_WORLD_BLOCK, phosphorite.biomes
		);
		generateOre(ModBlocks.orePotash, world, random, chunkX, chunkZ,
				potash.veinSize, COMMON,
				potash.minY, potash.maxY,
				DEFAULT_WORLD_BLOCK, potash.biomes
		);
	}

	//Nether
	private void generateNether(Random random, int chunkX, int chunkZ, World world)
	{
		generateOre(ModMetals.IGNATIUS, world, random, chunkX, chunkZ,
				ignatius.veinSize, COMMON,
				ignatius.minY, ignatius.maxY,
				new NetherOreSpawn(ignatius.biomes)
		);
		generateOre(ModMetals.SHADOW_IRON, world, random, chunkX, chunkZ,
				shadowIron.veinSize, COMMON,
				shadowIron.minY, shadowIron.maxY,
				new NetherOreSpawn(shadowIron.biomes)
		);
		generateOre(ModMetals.LEMURITE, world, random, chunkX, chunkZ,
				lemurite.veinSize, COMMON,
				lemurite.minY, lemurite.maxY,
				new NetherOreSpawn(lemurite.biomes)
		);
		generateOre(ModMetals.LUTETIUM, world, random, chunkX, chunkZ,
				lutetium.veinSize, VERY_RARE,
				lutetium.minY, lutetium.maxY,
				new NetherOreSpawn(lutetium.biomes)
		);
		generateOre(ModMetals.MIDASIUM, world, random, chunkX, chunkZ,
				midasium.veinSize, UNCOMMON,
				midasium.minY, midasium.maxY,
				new NetherOreSpawn(midasium.biomes)
		);
		generateOre(ModMetals.VYROXERES, world, random, chunkX, chunkZ,
				vyroxeres.veinSize, UNCOMMON,
				vyroxeres.minY, vyroxeres.maxY,
				new NetherOreSpawn(vyroxeres.biomes)
		);
		generateOre(ModMetals.CERUCLASE, world, random, chunkX, chunkZ,
				cerucalse.veinSize, VERY_RARE,
				cerucalse.minY, cerucalse.maxY,
				new NetherOreSpawn(cerucalse.biomes)
		);
		generateOre(ModMetals.ALDUORITE, world, random, chunkX, chunkZ,
				alduorite.veinSize, UNCOMMON,
				alduorite.minY, alduorite.maxY,
				new NetherOreSpawn(alduorite.biomes)
		);
		generateOre(ModMetals.KALENDRITE, world, random, chunkX, chunkZ,
				kalendrite.veinSize, RARE,
				kalendrite.minY, kalendrite.maxY,
				new NetherOreSpawn(kalendrite.biomes)
		);
		generateOre(ModMetals.VULCANITE, world, random, chunkX, chunkZ,
				vulcanite.veinSize, UNCOMMON,
				vulcanite.minY, vulcanite.maxY,
				new NetherOreSpawn(vulcanite.biomes)
		);
		generateOre(ModMetals.SANGUINITE, world, random, chunkX, chunkZ,
				sanguinite.veinSize, VERY_RARE,
				sanguinite.minY, sanguinite.maxY,
				new NearFortressSpawn()
		);
	}

	private void generateEnd(Random random, int chunkX, int chunkZ, World world)
	{
		generateOre(ModMetals.EXIMITE, world, random, chunkX, chunkZ,
				eximite.veinSize, COMMON,
				eximite.minY, eximite.maxY,
				new SmallIslandsSpawn(eximite.biomes)
		);
		generateOre(ModMetals.MEUTOITE, world, random, chunkX, chunkZ,
				meutoite.veinSize, RARE,
				meutoite.minY, meutoite.maxY,
				DEFAULT_END_BLOCK, meutoite.biomes
		);
	}

	private void generateOre(Metal metal, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, String[] biomesResource)
	{
		this.generateOre(metal, world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, new BaseOreSpawn(blockToReplace, biomesResource));
	}

	private void generateOre(Metal metal, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, IOreSpawn oreSpawn)
	{
		if (metal == null)
			return;

		this.generateOre(metal.getOre(), world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, oreSpawn);
	}

	private void generateOre(BlockOre block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace, String[] biomesResource)
	{
		generateOre(block, world, random, chunkX, chunkZ, veinSize, rarity, minY, maxY, new BaseOreSpawn(blockToReplace, biomesResource));
	}

	private void generateOre(BlockOre block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, IOreSpawn oreSpawn)
	{
		final int heightLimit = world.provider.getActualHeight();

		if (minY < 0 || maxY > heightLimit)
		{
			Metallurgy.logger.error("Illegal Height Arguments on Ore Generator for ore: " + block.getRegistryName().getPath() + " | minY: " + minY + " | maxY: " + maxY);
			return;
		}

		if (veinSize > 0)
		{
			WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), Math.max(veinSize, 3), oreSpawn.getBlockPredicate());
			int heightdiff = maxY - minY + 1;
			int x, y, z;

			int oreSpawnRarity = oreSpawn.getRarity(world.getChunk(chunkX, chunkZ), rarity);

			for (int i = 0; i < oreSpawnRarity; i++)
			{
				x = chunkX * 16 + random.nextInt(16);
				//if min and max Y are not inverted generate ore between the limits
				if (heightdiff >= 0)
					y = minY + random.nextInt(heightdiff);
				else
				{
					//otherwise generate it outside the limits
					if (random.nextBoolean())
						y = heightLimit - random.nextInt(heightLimit - minY);
					else
						y = 1 + random.nextInt(maxY);
				}
				z = chunkZ * 16 + random.nextInt(16);

				BlockPos pos = new BlockPos(x, y, z);

				//Metallurgy.logger.info("Trying to Generate: " + block.getRegistryName().toString());
				if (oreSpawn.canOreSpawn(world.getChunk(chunkX, chunkZ), pos, world.getBlockState(pos), random))
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
				if (verbose_retrogen)
				{
					Metallurgy.logger.log(Level.INFO, "Queuing retrogen for chunk: " + coordinates.toString() + ".");
				}
				regen = true;
			}
		}
		else
			regen = retrogen;

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
