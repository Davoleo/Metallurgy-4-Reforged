package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 10/02/2019 / 16:48
 * Class: GauntletConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2019
 **************************************************/

@Config.LangKey("config.metallurgy.category.rubracium_gauntlet")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/gadgets", category = "gauntlet")
public class GauntletConfig {

    @Config.Name("Gauntlet Max Damage")
    @Config.Comment("Sets the Maximum Damage value for the Rubracium Gauntlet")
    @Config.RequiresMcRestart
    public static int gauntletMaxDamage = 750;

    @Config.Name("Attack Modifier")
    @Config.Comment("Sets the gauntlet attack damage")
    public static double gauntletAttackDamage = 3D;

    @Config.Name("Attack Speed Modifier")
    @Config.Comment("We suggest to set the value to 16 or else the process bar will be rendered")
    @Config.RangeDouble(min = -3D, max = 16D)
    public static double gauntletAttackSpeed = 16D;

    @Config.Name("Gauntlet Hunger Modifier")
    @Config.Comment("Sets the gauntlet hunger modifier [ 1 = 0.5 ]")
    @Config.RangeInt(min = 0)
    public static int gauntletHungerModifier = 1;

    @Config.Name("Gauntlet Block Damage")
    @Config.Comment(value = "Sets the damage dealt to the target by being hit on the block")
    @Config.RangeDouble(min = 0)
    public static double gauntletBlockDamageModifier = 0.5;
}
