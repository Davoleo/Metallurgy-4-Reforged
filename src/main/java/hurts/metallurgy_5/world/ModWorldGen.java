package hurts.metallurgy_5.world;

import java.util.Random;

import hurts.metallurgy_5.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator {


	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
	
		if (world.provider.getDimension() == 0) { // the overworld
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}	
	}
	
//	X : (Max 16), Z : (Max 16), MinY (Min 0), MaxY (Max 265), GrandezzaVena (Min 1), VenePerChunk (Min 1) 
	private void generateOverworld(Random random, int chunkX, int chunkY, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider1) {
//		(ModBlocks.getDefaultState(), world, random, chunkX * 16, chunkY * 16, , , , );
		generateOre(ModBlocks.oreCopper.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 16, 80, 4 + random.nextInt(4), 8);
		generateOre(ModBlocks.oreDeepIron.getDefaultState(), world, random, chunkX *16, chunkY * 16, 1, 15, 4, 5);
		generateOre(ModBlocks.oreTin.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 16, 70, 6, 10);
		generateOre(ModBlocks.orePrometheum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 25, 10, 6);
		generateOre(ModBlocks.oreManganese.getDefaultState(),world, random, chunkX * 16, chunkY * 16, 25, 72, 4, 5);
		generateOre(ModBlocks.oreOureclase.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 47, 3, 4 );
		generateOre(ModBlocks.oreInfuscolium.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 0, 65, 4, 5);
	    generateOre(ModBlocks.oreAdamantine.getDefaultState(), world, random, chunkX * 16, chunkY * 16,0 ,12 ,4 ,1);
		generateOre(ModBlocks.oreRubracium.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 5, 30, 3, 2);
		generateOre(ModBlocks.oreAtlarus.getDefaultState(), world, random, chunkX * 16, chunkY * 16,1 ,100 ,3 ,1 );
		generateOre(ModBlocks.oreCarmot.getDefaultState(), world, random, chunkX * 16, chunkY * 16,0 ,100 ,2 ,3);
		generateOre(ModBlocks.oreMithril.getDefaultState(), world, random, chunkX * 16, chunkY * 16,0 ,100 ,3 ,3);
		generateOre(ModBlocks.oreZinc.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 16, 80, 4, 5);
		generateOre(ModBlocks.oreOrichalcum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 25, 60, 4, 2);
		generateOre(ModBlocks.oreAstralSilver.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 10, 15, 3, 3);
		generateOre(ModBlocks.oreSilver.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 60,2 , 3);
		generateOre(ModBlocks.orePlatinum.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 30, 3 + random.nextInt(3), 3);
		generateOre(ModBlocks.oreSulfur.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 15, 6, 4);
		generateOre(ModBlocks.orePhosphorite.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 80, 4, 4);
		generateOre(ModBlocks.oreBitumen.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 1, 10, 2 + random.nextInt(3), 4);
		generateOre(ModBlocks.orePotash.getDefaultState(), world, random, chunkX * 16, chunkY * 16, 10, 65,2 + random.nextInt(3), 4);
		
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;
	
		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
	
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
}
