package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 10/02/2019 / 18:30
 * Class: OreGenerationConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2019
 **************************************************/


@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/worldgen", category = "ore_generation")
public class OreGenerationConfig {

    @Config.Name("Retrogen")
    @Config.Comment(value = "Enable/Disable Retrogen")
    public static boolean retrogen = true;

    @Config.Name("Verbose Retrogen")
    @Config.Comment(value = "Enable/Disable verbose logging for retrogen")
    public static boolean verbose_retrogen = false;

    @Config.Comment("Copper minimum Y level")
    public static int copperMinY = 35;

    @Config.Comment("Copper maximum Y level")
    public static int copperMaxY = 120;

    @Config.Comment("Copper indicative vein size")
    public static int copperVeinSize = 8;
}
