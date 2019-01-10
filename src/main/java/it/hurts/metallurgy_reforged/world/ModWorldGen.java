package it.hurts.metallurgy_reforged.world;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ModWorldGen implements IWorldGenerator {
	
	private final Block DEFAULT_WORLD = Blocks.STONE;
	private final Block DEFAULT_NETHER = Blocks.NETHERRACK;
	private final Block DEFAULT_END = Blocks.END_STONE;
	
	private final int COMMON = 30;
	private final int UNCOMMON = 20;
	private final int RARE = 10;
	private final int ULTRA_RARE = 5;

  @Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
	  switch (world.provider.getDimension()){
			case -1:
				generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;

			case 0:
				generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;

			case 1:
				generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
				break;
			default:
				break;
	  }
	}

	//Overworld
	private void generateOverworld(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider1) {
		generateOre(ModMetals.COPPER.getOre(), world, random, chunkX, chunkY, 7, COMMON, 35, 120, DEFAULT_WORLD);
		generateOre(ModMetals.DEEP_IRON.getOre(), world, random, chunkX, chunkY, 5, COMMON, 10, 30, DEFAULT_WORLD);
		generateOre(ModMetals.TIN.getOre(), world, random, chunkX, chunkY, 10, COMMON, 25, 48, DEFAULT_WORLD);
		generateOre(ModMetals.PROMETHEUM.getOre(), world, random, chunkX, chunkY, 6, COMMON, 0, 64, DEFAULT_WORLD);
		generateOre(ModMetals.MANGANESE.getOre(),world, random, chunkX, chunkY, 9, COMMON, 0, 128, DEFAULT_WORLD);
		generateOre(ModMetals.OURECLASE.getOre(), world, random, chunkX, chunkY, 5, UNCOMMON, 0, 128, DEFAULT_WORLD);
		generateOre(ModMetals.INFUSCOLIUM.getOre(), world, random, chunkX, chunkY, 7, UNCOMMON, 10, 72, DEFAULT_WORLD);
		generateOre(ModMetals.ADAMANTINE.getOre(), world, random, chunkX, chunkY, 5, RARE, 0, 64, DEFAULT_WORLD);
		generateOre(ModMetals.RUBRACIUM.getOre(), world, random, chunkX, chunkY, 6, UNCOMMON, 10, 40, DEFAULT_WORLD);
		generateOre(ModMetals.ATLARUS.getOre(), world, random, chunkX, chunkY, 7, RARE, 0, 64,  DEFAULT_WORLD);
		generateOre(ModMetals.CARMOT.getOre(), world, random, chunkX, chunkY, 6, UNCOMMON, 0, 64, DEFAULT_WORLD);
		generateOre(ModMetals.MITHRIL.getOre(), world, random, chunkX, chunkY, 5, ULTRA_RARE, 32, 128, DEFAULT_WORLD);
		generateOre(ModMetals.ZINC.getOre(), world, random, chunkX, chunkY, 8, COMMON, 0, 128, DEFAULT_WORLD);
		generateOre(ModMetals.ORICHALCUM.getOre(), world, random, chunkX, chunkY, 4, UNCOMMON, 0, 128, DEFAULT_WORLD);
		generateOre(ModMetals.ASTRAL_SILVER.getOre(), world, random, chunkX, chunkY, 6, RARE, 0, 64, DEFAULT_WORLD);
		generateOre(ModMetals.SILVER.getOre(), world, random, chunkX, chunkY, 8, UNCOMMON, 0, 128, DEFAULT_WORLD);
		generateOre(ModMetals.PLATINUM.getOre(), world, random, chunkX, chunkY, 4, RARE, 0, 80, DEFAULT_WORLD);
		generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkY, 6, UNCOMMON, 0, 15, DEFAULT_WORLD);
		generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkY, 5, UNCOMMON, 0, 100, DEFAULT_WORLD);
		generateOre(ModBlocks.oreBitumen, world, random, chunkX, chunkY, 10, COMMON, 64, 128, DEFAULT_WORLD);
		generateOre(ModBlocks.orePotash, world, random, chunkX, chunkY, 7, COMMON, 32, 72, DEFAULT_WORLD);
  }

	//Nether
	private void generateNether(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
//		I think we need to fix the min and max Y
		generateOre(ModMetals.IGNATIUS.getOre(), world, random, chunkX, chunkY, 10, COMMON, 0, 255, DEFAULT_NETHER);
		generateOre(ModMetals.SHADOW_IRON.getOre(), world, random, chunkX, chunkY, 6, COMMON, 0, 124, DEFAULT_NETHER);
		generateOre(ModMetals.LEMURITE.getOre(), world, random, chunkX, chunkY, 7, COMMON, 0, 100, DEFAULT_NETHER);
		generateOre(ModMetals.MIDASIUM.getOre(), world, random, chunkX, chunkY, 6, UNCOMMON, 0, 256, DEFAULT_NETHER);
		generateOre(ModMetals.VYROXERES.getOre(), world, random, chunkX, chunkY, 5, UNCOMMON, 0, 120, DEFAULT_NETHER);
		generateOre(ModMetals.CERUCLASE.getOre(), world, random, chunkX, chunkY, 5, RARE, 0, 128, DEFAULT_NETHER);
		generateOre(ModMetals.ALDUORITE.getOre(), world, random, chunkX, chunkY, 4, UNCOMMON, 0, 128, DEFAULT_NETHER);
		generateOre(ModMetals.KALENDRITE.getOre(), world, random, chunkX, chunkY, 5, UNCOMMON, 27, 120, DEFAULT_NETHER);
		generateOre(ModMetals.VULCANITE.getOre(), world, random, chunkX, chunkY, 5, UNCOMMON, 0, 128, DEFAULT_NETHER);
		generateOre(ModMetals.SANGUINITE.getOre(), world, random, chunkX, chunkY, 4, RARE, 0, 128, DEFAULT_NETHER);
	}

	private void generateEnd(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateOre(ModMetals.EXIMITE.getOre(), world, random, chunkX, chunkY, 7, UNCOMMON, 0, 128, DEFAULT_END);
		generateOre(ModMetals.MEUTOITE.getOre(), world, random, chunkX, chunkY, 7, UNCOMMON, 0, 128, DEFAULT_END);
	}

	private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int veinSize, int rarity, int minY, int maxY, Block blockToReplace) {
		
		if (minY < 0 || maxY > 256 || minY > maxY)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		
		WorldGenMinable generator = new WorldGenMinable(block.getDefaultState(), veinSize, BlockMatcher.forBlock(blockToReplace));
		int heightdiff = maxY - minY +1;
		int x, y, z;
		
		for (int i = 0; i < rarity; i++){
			x = chunkX * 16 +random.nextInt(16);
			y = minY + random.nextInt(heightdiff);
			z = chunkZ * 16 + random.nextInt(16);

			generator.generate(world, random, new BlockPos(x, y, z));
		}
	}

}
