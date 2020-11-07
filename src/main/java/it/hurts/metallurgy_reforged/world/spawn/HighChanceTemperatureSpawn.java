/*==============================================================================
 = Class: HighChanceTemperatureSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.world.spawn;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

/**
 * this class permits to spawn an ore more likely in a specific temperature than another one.
 */
public class HighChanceTemperatureSpawn extends BaseOreSpawn
{

    private final Biome.TempCategory tempCategory;
    private final int newRarity;


    public HighChanceTemperatureSpawn(Block blockToReplace,Biome[] biomes, Biome.TempCategory tempCategory, int newRarity)
    {
        super(blockToReplace,biomes);
        this.tempCategory = tempCategory;
        this.newRarity = newRarity;
    }


    @Override
    public int getRarity(World world, int chunkX, int chunkZ,int originalRarity)
    {

        for (byte b : world.getChunk(chunkX, chunkZ).getBiomeArray())
        {
            Biome biome = Biome.getBiomeForId(b);
            if(biome != null && getTempCategory(biome) == tempCategory)
            {
                return newRarity;
            }
        }

        return originalRarity;
    }
    private Biome.TempCategory getTempCategory(Biome biome)
    {
        return (double)biome.getDefaultTemperature() >= 0.95D ? Biome.TempCategory.WARM : biome.getTempCategory();
    }
}
