package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 08/12/2018 / 20:55
 * Class: ConfigFeaturePanel
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

@Config.LangKey("config.metallurgy.category.features")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/features", category = "features")
public class ConfigFeaturePanel {

    @Config.Comment({"This section is going to be a control panel for the mod features and items"})
    @Config.Name("TEST")
    public static boolean test = false;


    public static ArmorConfigCategory armors = new ArmorConfigCategory();
    private static class ArmorConfigCategory
    {
        @Config.RequiresMcRestart
        public boolean exampleArmor;
    }

    public static EffectConfigCategory effects = new EffectConfigCategory();
    private static class EffectConfigCategory
    {
        @SuppressWarnings("unused")
		public boolean exampleEffect;
    }

    public static ToolConfigCategory tools = new ToolConfigCategory();
    private static class ToolConfigCategory
    {
        @Config.RequiresMcRestart
        public boolean exampleTool;
    }

}
