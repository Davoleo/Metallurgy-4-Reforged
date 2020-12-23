/*==============================================================================
 = Class: PhosphorusLampConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/gadgets", category = "phosphorus_lamp")
public class PhosphorusLampConfig {

    @Config.Name("Lantern entity collision")
    @Config.Comment("Set this to false to disable entity collisions with lantern")
    public static boolean enableLanternCollision = true;

    @Config.Name("Phosphorus Lantern Light Level")
    @Config.Comment("Sets how bright the lantern should be")
    public static int lanternLightLevel = 8;

}
