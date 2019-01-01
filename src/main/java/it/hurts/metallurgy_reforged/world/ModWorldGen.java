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
//	X : (Max 16), Z : (Max 16), MinY (Min 0), MaxY (Max 265), GrandezzaVena (Min 2), VenePerChunk (Min 3)
	private void generateOverworld(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider1) {
//		(ModBlocks.getDefaultState(), world, random, chunkX * 16, chunkY * 16, , , , );
		generateOre(ModMetals.COPPER.getOre(), world, random, chunkX, chunkY, 4, 7, 8, 35, 120, Blocks.STONE);
		generateOre(ModMetals.DEEP_IRON.getOre(), world, random, chunkX, chunkY, 3, 5, 7, 10, 30, Blocks.STONE);
		generateOre(ModMetals.TIN.getOre(), world, random, chunkX, chunkY, 6, 8, 10, 25, 48, Blocks.STONE);
		generateOre(ModMetals.PROMETHEUM.getOre(), world, random, chunkX, chunkY, 6, 10, 10, 0, 64, Blocks.STONE);
		generateOre(ModMetals.MANGANESE.getOre(),world, random, chunkX, chunkY, 4, 5, 11, 0, 128, Blocks.STONE);
		generateOre(ModMetals.OURECLASE.getOre(), world, random, chunkX, chunkY, 3, 4, 10, 0, 128, Blocks.STONE);
		generateOre(ModMetals.INFUSCOLIUM.getOre(), world, random, chunkX, chunkY, 4, 5, 8, 10, 72, Blocks.STONE);
		generateOre(ModMetals.ADAMANTINE.getOre(), world, random, chunkX, chunkY, 2, 6, 4, 0, 64, Blocks.STONE);
		generateOre(ModMetals.RUBRACIUM.getOre(), world, random, chunkX, chunkY, 2, 4, 7, 10, 40, Blocks.STONE);
		generateOre(ModMetals.ATLARUS.getOre(), world, random, chunkX, chunkY, 2, 6, 4, 0, 64,  Blocks.STONE);
		generateOre(ModMetals.CARMOT.getOre(), world, random, chunkX, chunkY, 2, 6, 20, 0, 64, Blocks.STONE);
		generateOre(ModMetals.MITHRIL.getOre(), world, random, chunkX, chunkY, 2, 5, 3, 32, 128, Blocks.STONE);
		generateOre(ModMetals.ZINC.getOre(), world, random, chunkX, chunkY, 4, 5, 4, 0, 128, Blocks.STONE);
		generateOre(ModMetals.ORICHALCUM.getOre(), world, random, chunkX, chunkY, 2, 4, 10, 0, 128, Blocks.STONE);
		generateOre(ModMetals.ASTRAL_SILVER.getOre(), world, random, chunkX, chunkY, 3, 3, 8, 0, 64, Blocks.STONE);
		generateOre(ModMetals.SILVER.getOre(), world, random, chunkX, chunkY, 3, 5 + random.nextInt(3), 17, 0, 128, Blocks.STONE);
		generateOre(ModMetals.PLATINUM.getOre(), world, random, chunkX, chunkY, 2, 3 +random.nextInt(3), 2, 0, 80, Blocks.STONE);
		generateOre(ModBlocks.oreSulfur, world, random, chunkX, chunkY, 4, 6, 18, 0, 15, Blocks.STONE);
		generateOre(ModBlocks.orePhosphorite, world, random, chunkX, chunkY, 4, 5, 17, 0, 100, Blocks.STONE);
		generateOre(ModBlocks.oreBitumen, world, random, chunkX, chunkY, 2, 4 + random.nextInt(3), 10, 64, 128, Blocks.STONE);
		generateOre(ModBlocks.orePotash, world, random, chunkX, chunkY,  2, 4 + random.nextInt(3), 11, 32, 72, Blocks.STONE);
  }

	//Nether
	private void generateNether(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
		generateOre(ModMetals.IGNATIUS.getOre(), world, random, chunkX, chunkY, 3, 8, 7, 0, 255, Blocks.NETHERRACK); //backup chances: 4
		generateOre(ModMetals.SHADOW_IRON.getOre(), world, random, chunkX, chunkY, 2, 6, 7, 0, 124, Blocks.NETHERRACK);
		generateOre(ModMetals.LEMURITE.getOre(), world, random, chunkX, chunkY, 3, 6, 6, 0, 100, Blocks.NETHERRACK);
		generateOre(ModMetals.MIDASIUM.getOre(), world, random, chunkX, chunkY, 3, 6, 7, 0, 256, Blocks.NETHERRACK);
		generateOre(ModMetals.VYROXERES.getOre(), world, random, chunkX, chunkY, 2, 7, 4, 0, 120, Blocks.NETHERRACK);
		generateOre(ModMetals.CERUCLASE.getOre(), world, random, chunkX, chunkY, 3, 5, 16, 0, 128, Blocks.NETHERRACK);
		generateOre(ModMetals.ALDUORITE.getOre(), world, random, chunkX, chunkY, 2, 6, 15, 0, 128, Blocks.NETHERRACK);
		generateOre(ModMetals.KALENDRITE.getOre(), world, random, chunkX, chunkY, 2, 6, 4, 27, 120, Blocks.NETHERRACK);
		generateOre(ModMetals.VULCANITE.getOre(), world, random, chunkX, chunkY, 2, 9, 5, 0, 128, Blocks.NETHERRACK);
		generateOre(ModMetals.SANGUINITE.getOre(), world, random, chunkX, chunkY, 2, 4, 8, 0, 128, Blocks.NETHERRACK);
	}

	private void generateEnd(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateOre(ModMetals.EXIMITE.getOre(), world, random, chunkX, chunkY, 2, 7, 8, 0, 128, Blocks.END_STONE);
		generateOre(ModMetals.MEUTOITE.getOre(), world, random, chunkX, chunkY, 2, 7, 8, 0, 128, Blocks.END_STONE);
	}

	private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVeinSize, int maxVeinSize, int chance, int minY, int maxY, Block generateIn) {
		int veinSize;
		if(maxVeinSize != minVeinSize)
			veinSize = minVeinSize + random.nextInt(Math.abs(maxVeinSize - minVeinSize));
		else
			veinSize = minVeinSize + random.nextInt(2);
  		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), veinSize, BlockMatcher.forBlock(generateIn));
		for (int i = 0; i < chance; i++){
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
		}
	}

}
