/*==============================================================================
 = Class: NearFortressSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import it.hurts.metallurgy_reforged.util.ModChecker;
import it.hurts.metallurgy_reforged.world.ModWorldGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NearFortressSpawn extends NetherOreSpawn {

    public NearFortressSpawn()
    {
        super(null);
    }

    @Override
    public boolean canOreSpawn(World world, BlockPos pos, IBlockState state, Random random)
    {
        return true;
    }

    @Override
    public int getRarity(World world, int chunkX, int chunkZ, int originalRarity)
    {
        if (ModChecker.isNetherExLoaded)
            return originalRarity;

        BlockPos pos = new BlockPos(chunkX * 16, world.getSeaLevel(), chunkZ * 16);

        BlockPos blockpos = world.findNearestStructure("Fortress", pos, false);

        if (blockpos != null && blockpos.getDistance(pos.getX(), pos.getY(), pos.getZ()) < 90)
        {
            return ModWorldGen.COMMON;
        }

        return originalRarity;


    }
}
