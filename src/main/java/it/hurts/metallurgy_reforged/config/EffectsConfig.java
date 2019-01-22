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
    public static boolean adamantineArmorEffect = true;
    public static boolean amordrineArmorEffect = true;
    public static boolean angmallenArmorEffect = true;
    public static boolean astralSilverArmorEffect = true;
    public static boolean carmotArmorEffect = true;
    public static boolean celenegilArmorEffect = true;
    @Config.Ignore //FIXME Not Linked
    public static boolean ceruclaseArmorEffect = true;
    public static boolean deepIronArmorEffect = true;
    @Config.Ignore //FIXME Not Linked
    public static boolean eximiteArmorEffect = true;
    public static boolean kaledriteArmorEffect = true;
    public static boolean mithrilArmorEffect = true;
    public static boolean platinumArmorEffect = true;
    public static boolean prometheumArmorEffect = true;
    public static boolean quicksilverArmorEffect = true;
    public static boolean vulcaniteArmorEffect = true;
    @Config.Ignore //FIXME Not Linked
    public static boolean inolashiteArmorEffect = true;
    public static boolean shadowSteelArmorEffect = true;

    //Pickaxes
    public static boolean deepIronPickaxeEffect = true;

    
    //Tools 
    public static boolean shadowSteelToolSpeedEffect = true;

    //Swords
    public static boolean ignatiusSwordEffect = true;
    public static boolean kalendriteSwordEffect = true;
    public static boolean shadowIronSwordEffect = true;
    public static boolean tartariteSwordEffect = true;
    public static boolean vulcaniteSwordEffect = true;
    public static boolean vyroxeresSwordEffect = true;
    public static boolean shadowSteelSwordEffect = true;
    public static boolean desichalkosSwordEffect = true;

    private EffectsConfig(){}

    @SubscribeEvent
    static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent e)
    {
        if(Metallurgy.MODID.equals(e.getModID()))
            ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
    }

}
