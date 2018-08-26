package it.hurts.metallurgy_5.world;

import it.hurts.metallurgy_5.block.ModBlocks;
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

public class ModWorldGen implements IWorldGenerator {

  @Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
 	switch (world.provider.getDimension())
	{
		case -1:
			generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			break;

		case 0:
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			break;

		default:
			break;

	}
	}

	//Overworld
//	X : (Max 16), Z : (Max 16), MinY (Min 0), MaxY (Max 265), GrandezzaVena (Min 1), VenePerChunk (Min 1) 
	private void generateOverworld(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider1) {
//		(ModBlocks.getDefaultState(), world, random, chunkX * 16, chunkY * 16, , , , );
		generateOre(ModBlocks.oreCopper, world, random, chunkX, chunkY, 4, 7, 8, 15, 60, Blocks.STONE);
		generateOre(ModBlocks.oreDeepIron, world, random, chunkX, chunkY, 4, 6, 5, 10, 30, Blocks.STONE);
		generateOre(ModBlocks.oreTin, world, random, chunkX, chunkY, 6, 8, 10, 20, 48, Blocks.STONE);
//		generateOre(ModBlocks.orePrometheum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 25, BlockMatcher.forBlock(Blocks.STONE), 10, 6);
//		generateOre(ModBlocks.oreManganese.getDefaultState(),world, random, chunkX * 16, chunkY * 16, 25, 72, BlockMatcher.forBlock(Blocks.STONE), 4, 5);
//		generateOre(ModBlocks.oreOureclase.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 47, BlockMatcher.forBlock(Blocks.STONE), 3, 4 );
//		generateOre(ModBlocks.oreInfuscolium.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 65, BlockMatcher.forBlock(Blocks.STONE), 4, 5);
//	    generateOre(ModBlocks.oreAdamantine.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 12, BlockMatcher.forBlock(Blocks.STONE), 4, 1);
//		generateOre(ModBlocks.oreRubracium.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 5, 30, BlockMatcher.forBlock(Blocks.STONE), 3, 2);
//		generateOre(ModBlocks.oreAtlarus.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 100, BlockMatcher.forBlock(Blocks.STONE), 3, 1);
//		generateOre(ModBlocks.oreCarmot.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 100, BlockMatcher.forBlock(Blocks.STONE),  2, 3);
//		generateOre(ModBlocks.oreMithril.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 100, BlockMatcher.forBlock(Blocks.STONE), 3, 3);
//		generateOre(ModBlocks.oreZinc.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 16, 80, BlockMatcher.forBlock(Blocks.STONE), 4, 5);
//		generateOre(ModBlocks.oreOrichalcum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 25, 60, BlockMatcher.forBlock(Blocks.STONE), 4, 2);
//		generateOre(ModBlocks.oreAstralSilver.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 10, 15, BlockMatcher.forBlock(Blocks.STONE), 3, 3);
//		generateOre(ModBlocks.oreSilver.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 60, BlockMatcher.forBlock(Blocks.STONE), 2, 3);
//		generateOre(ModBlocks.orePlatinum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 30, BlockMatcher.forBlock(Blocks.STONE), 3 + random.nextInt(3), 3);
//		generateOre(ModBlocks.oreSulfur.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 15, BlockMatcher.forBlock(Blocks.STONE), 6, 4);
//		generateOre(ModBlocks.orePhosphorite.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 80, BlockMatcher.forBlock(Blocks.STONE), 4, 4);
//		generateOre(ModBlocks.oreBitumen.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 10, BlockMatcher.forBlock(Blocks.STONE) ,2 + random.nextInt(3), 4);
//		generateOre(ModBlocks.orePotash.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 10, 65, BlockMatcher.forBlock(Blocks.STONE), 2 + random.nextInt(3), 4);
	}

	//Nether
	private void generateNether(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		generateOre(ModBlocks.oreIgnatius, world, random, chunkX, chunkY, 3, 8, 4, 0, 128, Blocks.NETHERRACK); //backup chances: 4
	}
	
	private void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVeinSize, int maxVeinSize, int chance, int minY, int maxY, Block generateIn) {
		int veinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
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
