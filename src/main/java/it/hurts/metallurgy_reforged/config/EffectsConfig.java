package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 08/12/2018 / 20:55
 * Class: EffectsConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Config.LangKey("config.metallurgy.category.effects_control_panel")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "ON/OFF")
public class EffectsConfig {

    //Armors

    public static boolean adamantineArmorEffect = true;
    @Config.Name("Amordrine Armor Effect")
    @Config.Comment("Full Armor gives Strength II")
    public static boolean amordrineArmorEffect = true;
    @Config.Name("Angmallen Armor Effect")
    @Config.Comment("Full Armor gives Luck")
    public static boolean angmallenArmorEffect = true;
    @Config.Name("Astral Silver Armor Effect")
    @Config.Comment("Full Armor gives Jump Boost")
    public static boolean astralSilverArmorEffect = true;
    @Config.Name("Carmot Armor Effect")
    @Config.Comment("Full Armor gives Haste")
    public static boolean carmotArmorEffect = true;
    @Config.Name("Celenegil Armor Effect")
    @Config.Comment("Full Armor gives Resistance II")
    public static boolean celenegilArmorEffect = true;
    @Config.Name("Deep Iron Armor Effect")
    @Config.Comment("You move faster underwater")
    public static boolean deepIronArmorEffect = true;
    @Config.Name("Eximite Helmet Effect")
    @Config.Comment("Endermen won't notice you when watch them")
    public static boolean eximiteArmorEffect = true;
    @Config.Name("Kalendrite Armor Effect")
    @Config.Comment("Full Armor gives Strength I")
    public static boolean kaledriteArmorEffect = true;
    @Config.Name("Mithril Armor Effect")
    @Config.Comment("Makes Entities around you glow")
    public static boolean mithrilArmorEffect = true;
    @Config.Name("Platinum Armor Effect")
    @Config.Comment("The helmet gives you night vision")
    public static boolean platinumArmorEffect = true;
    @Config.Name("Prometheum Armor Effect")
    @Config.Comment("Removes poison effect whenever you get it")
    public static boolean prometheumArmorEffect = true;
    @Config.Name("Quicksilver")
    @Config.Comment("Movements speed up")
    public static boolean quicksilverArmorEffect = true;
    @Config.Name("Shadow Steel Armor Effect")
    @Config.Comment("Absorbed damage is proportional to the darkness")
    public static boolean shadowSteelArmorEffect = true;
    @Config.Name("Vulcanite Armor Effect")
    @Config.Comment("You can't take damage from fire")
    public static boolean vulcaniteArmorEffect = true;

    //Swords

    //Pickaxes
    @Config.Name("Deep Iron Pickaxe Effect")
    @Config.Comment("Underwater mining is not slowed down")
    public static boolean deepIronPickaxeEffect = true;

    //Tools
    @Config.Name("Shadow Steel Tools Effect")
    @Config.Comment("The tool speed is proportional to the darkness")
    public static boolean shadowSteelToolSpeedEffect = true;

    //Swords
    @Config.Name("Desichalkos Sword Effect")
    @Config.Comment("Gives some random effect to the target")
    public static boolean desichalkosSwordEffect = true;
    @Config.Name("Ignatius Sword Effect")
    @Config.Comment("Fire aspect")
    public static boolean ignatiusSwordEffect = true;
    @Config.Name("Kalendrite Sword Effect")
    @Config.Comment("Chance to regenerate your life on hit")
    public static boolean kalendriteSwordEffect = true;
    @Config.Name("Shadow Iron Sword Effect")
    @Config.Comment("Chance to blind the target")
    public static boolean shadowIronSwordEffect = true;
    @Config.Name("Shadow Steel Sword Effect")
    @Config.Comment("Speed and Damage is proportional to the darkness")
    public static boolean shadowSteelSwordEffect = true;
    @Config.Name("Tartarite Sword Effect")
    @Config.Comment("Withers the target")
    public static boolean tartariteSwordEffect = true;
    @Config.Name("Vulcanite Sword Effect")
    @Config.Comment("Fire Aspect")
    public static boolean vulcaniteSwordEffect = true;
    @Config.Name("Vyroxeres Sword Effect")
    @Config.Comment("Poisons the target")
    public static boolean vyroxeresSwordEffect = true;

    private EffectsConfig(){}

}
