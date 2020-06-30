package it.hurts.metallurgy_reforged.world.spawn;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.google.common.base.Predicate;

import java.util.Random;

public class BaseOreSpawn implements IOreSpawn
{
    private final Block blockToReplace;

    public BaseOreSpawn(Block blockToReplace) {this.blockToReplace = blockToReplace;}


    @Override
    public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
    {
        return true;
    }

    @Override
    public Predicate<IBlockState> getBlockPredicate()
    {
        return BlockMatcher.forBlock(blockToReplace);
    }
}
