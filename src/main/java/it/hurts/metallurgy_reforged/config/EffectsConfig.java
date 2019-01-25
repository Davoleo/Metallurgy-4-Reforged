package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
    @Config.Comment("Saturation Effect")
    @Config.Ignore
    public static boolean adamantineArmorEffect = true;
    @Config.Comment("Strength II")
    public static boolean amordrineArmorEffect = true;
    @Config.Comment("Luck")
    public static boolean angmallenArmorEffect = true;
    @Config.Comment("Jump Boost")
    public static boolean astralSilverArmorEffect = true;
    @Config.Comment("Haste")
    public static boolean carmotArmorEffect = true;
    @Config.Comment("Resistance")
    public static boolean celenegilArmorEffect = true;
    @Config.Comment("Fast underwater movements")
    public static boolean deepIronArmorEffect = true;
    @Config.Comment("The helmet makes endermen stay neutral when you watch them")
    public static boolean eximiteArmorEffect = true;
    @Config.Comment("Strength I")
    public static boolean kaledriteArmorEffect = true;
    @Config.Comment("Makes Entities around you glow")
    public static boolean mithrilArmorEffect = true;
    @Config.Comment("The helmet gives you night vision")
    public static boolean platinumArmorEffect = true;
    @Config.Comment("Removes poison effect from yourself")
    public static boolean prometheumArmorEffect = true;
    @Config.Comment("Speeds you up")
    public static boolean quicksilverArmorEffect = true;
    @Config.Comment("Absorbed damage is proportional to the darkness")
    public static boolean shadowSteelArmorEffect = true;
    @Config.Comment("You can't take damage from fire")
    public static boolean vulcaniteArmorEffect = true;

    //Pickaxes
    @Config.Comment("Underwater mining is not slowed down")
    public static boolean deepIronPickaxeEffect = true;

    //Tools
    @Config.Comment("The tool speed is proportional to the darkness")
    public static boolean shadowSteelToolSpeedEffect = true;

    //Swords
    @Config.Comment("Gives some random effect to the target")
    public static boolean desichalkosSwordEffect = true;
    @Config.Comment("Fire aspect")
    public static boolean ignatiusSwordEffect = true;
    @Config.Comment("Chance to regenerate your life on hit")
    public static boolean kalendriteSwordEffect = true;
    @Config.Comment("Chance to blind the target")
    public static boolean shadowIronSwordEffect = true;
    @Config.Comment("Speed and Damage is proportional to the darkness")
    public static boolean shadowSteelSwordEffect = true;
    @Config.Comment("Wither")
    public static boolean tartariteSwordEffect = true;
    @Config.Comment("Fire Aspect")
    public static boolean vulcaniteSwordEffect = true;
    @Config.Comment("Poison")
    public static boolean vyroxeresSwordEffect = true;

    private EffectsConfig(){}

    @SubscribeEvent
    static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent e)
    {
        if(Metallurgy.MODID.equals(e.getModID()))
            ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
    }

}
