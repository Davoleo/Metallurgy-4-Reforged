package it.hurts.metallurgy_reforged.world.spawn;

import it.hurts.metallurgy_reforged.world.ModWorldGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class SmallIslandsSpawn extends BaseOreSpawn
{
    public SmallIslandsSpawn(Biome[] biomes)
    {
        super(ModWorldGen.DEFAULT_END,biomes);
    }

    @Override
    public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
    {
        if(pos.getX() > 5000 || pos.getZ() > 5000)
            return super.canOreSpawn(world, pos, state, random);
        return false;
    }
}
