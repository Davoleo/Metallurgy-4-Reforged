/*
 * -------------------------------------------------------------------------------------------------------
 * Class: WorldGenConfig
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/worldgen", category = "world_gen")
public class WorldGenConfig {

    @Config.Name("The spawnrate percentage of tar lake in the overworld")
    @Config.Comment("Sets to 0 to disable")
    @Config.RangeInt(min = 0, max = 100)
    public static int tarLakePercentage = 20;
}
