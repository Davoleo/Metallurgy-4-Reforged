package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 15/12/2018 / 00:22
 * Class: EffectModifiersConfig
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Config.LangKey("config.metallurgy.category.effects_modifiers")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/effects", category = "Buff/Nerf")
public class EffectModifiersConfig {

    @Config.Ignore
    public static int TEST = 100;

}
