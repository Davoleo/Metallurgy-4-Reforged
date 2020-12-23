/*==============================================================================
 = Class: NetherOreSpawn
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

/* -----------------------------------
 * Author: Davoleo
 * Date / Hour: 01/09/2020 / 19:27
 * Class: NetherOreSpawn
 * Project: Metallurgy 4 Reforged
 * ----------------------------------- */

package it.hurts.metallurgy_reforged.world.spawn;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class NetherOreSpawn extends BaseOreSpawn {

    public NetherOreSpawn(Biome[] biomes)
    {
        super(null, biomes);
    }

    @SuppressWarnings("Guava")
    @Override
    public Predicate<IBlockState> getBlockPredicate()
    {
        return (blockState) -> {
            ResourceLocation block = blockState.getBlock().getRegistryName();
            return block.equals(new ResourceLocation("minecraft", "netherrack")) ||
                    block.equals(new ResourceLocation("netherex", "gloomy_netherrack")) ||
                    block.equals(new ResourceLocation("netherex", "lively_netherrack")) ||
                    block.equals(new ResourceLocation("netherex", "fiery_netherrack")) ||
                    block.equals(new ResourceLocation("netherex", "icy_netherrack"));
        };
    }

}
