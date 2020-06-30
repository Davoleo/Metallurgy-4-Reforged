package it.hurts.metallurgy_reforged.world.spawn;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class BiomeOreSpawn extends BaseOreSpawn
{

    private final Biome[] biomes;

    public BiomeOreSpawn(Block blockToReplace, Biome[] biomes)
    {
        super(blockToReplace);
        this.biomes = biomes;
    }

    @Override
    public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
    {
        return isInBiome(world, pos, this.biomes);
    }


    /**
     * returns if the biome in current position is contained in biomes
     */
    private static boolean isInBiome(World world, BlockPos pos, Biome[] biomes)
    {
        for (Biome biome : biomes)
            if(world.getBiome(pos) == biome)
                return true;
        return false;
    }
}
