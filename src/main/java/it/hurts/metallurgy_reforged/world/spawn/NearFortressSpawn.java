package it.hurts.metallurgy_reforged.world.spawn;

import com.google.common.base.Predicate;
import it.hurts.metallurgy_reforged.world.ModWorldGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NearFortressSpawn implements IOreSpawn
{
    @Override
    public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
    {
        return true;
    }

    @Override
    public Predicate<IBlockState> getBlockPredicate()
    {
        return BlockMatcher.forBlock(Blocks.NETHERRACK);
    }

    @Override
    public int getRarity(World world, int chunkX, int chunkZ, int originalRarity)
    {
        BlockPos pos = new BlockPos(chunkX * 16, world.getSeaLevel(), chunkZ * 16);

        BlockPos blockpos = world.findNearestStructure("Fortress", pos, false);

        if(blockpos != null && blockpos.getDistance(pos.getX(), pos.getY(), pos.getZ()) < 90)
        {
            return ModWorldGen.COMMON;
        }

        return originalRarity;


    }
}
