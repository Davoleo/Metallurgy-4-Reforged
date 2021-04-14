/*==============================================================================
 = Class: GeneralConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config.metallurgy.category.general")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/general")
public class GeneralConfig {

    @Config.Name("Enable On World Join Message")
    @Config.Comment("When set to true it shows the warning message when joining the world")
    @Config.RequiresMcRestart
    public static boolean warning = true;

    @Config.Name("Road Speed Multiplier")
    @Config.Comment("Set the road speed multiplier")
    @Config.RangeDouble(min = 1)
    public static double roadSpeed = 1.50D;

    @Config.Name("Disable tinker integration")
    @Config.Comment("Set to true to disable Tinkers' Construct Integration")
    public static boolean tinkerIntegration = false;

    @Config.Name("Tinker Materials Blacklist")
    @Config.Comment("Add a material in this array to blacklist it during game boot - material name format is \"snake_case\" (e.g. 'osmium' or 'damascus_steel')")
    @Config.RequiresMcRestart
    public static String[] tinkerMaterialsBlacklist = new String[]{};

    @Config.Name("Disable ConArm Integration")
    @Config.Comment("Set to true to disable Construct's Armory Integration")
    public static boolean armoryIntegration = false;

    @Config.Name("Disable IF Integration")
    @Config.Comment("Set to true to disable Industrial Foregoing Integration")
    public static boolean inForegoingIntegration = false;

    @Config.Name("Disable Automatic EMC values")
    @Config.Comment("Set to true to disable default EMC values")
    public static boolean projectEIntegration = false;

    @Config.Name("Enable Duplication Trait")
    @Config.Comment("When set to true the duplication trait from tinker integration is enabled")
    public static boolean enableDuplicationTrait = true;

    @Config.Name("Should Tool be repairable in Anvil")
    @Config.Comment("Set this to true to be able to repair tools in Vanilla Anvil")
    public static boolean enableAnvilToolRepair = true;

    @Config.Name("Should Armor be repairable in Anvil")
    @Config.Comment("Set this to true to enable the possibility to repair armors in Vanilla Anvil")
    public static boolean enableAnvilArmorRepair = true;

    @Config.Name("Enable Ore Particles")
    @Config.Comment("Set this to true to enable ore particles (Particles that are bigger depending on the harvest tier of the ore) [default: false]")
    public static boolean enableOreParticles = true;

    @Config.Name("Should High Tier Ore emit light")
    @Config.Comment("Set this to true to make ore blocks that have a harvest level higher than 5 to emit light [default: false]")
    @Config.RequiresWorldRestart
    public static boolean enableOreLight = true;

    @Config.Name("Thermite Fuel Value")
    @Config.Comment("How many items should 1 thermite item cook in a vanilla furnace (this value is automatically doubled when thermite is used in metallurgy machiens) [default: 8]")
    @Config.RangeInt(min = 1, max = 64)
    @Config.SlidingOption
    @Config.RequiresMcRestart
    public static int thermiteFuelValue = 8;

    @Config.Name("OreDict Crusher Recipes")
    @Config.Comment("When set to true the mod will load new recipes from the OreDictionary (this could cause some incompatibility issues when removing recipes with CraftTweaker disable this if you see that some recipes are not being removed)")
    @Config.RequiresMcRestart
    public static boolean enableOreDictCrusherRecipes = true;

    @Config.Name("Enable Custom Materials Stats Configuration file")
    @Config.Comment("Loads the `material.json` file in the config folder that allows players to edit any material stat in the mod and to disable specific metals")
    @Config.RequiresMcRestart
    public static boolean enableCustomMaterialStatsConfig = false;

    @Config.Name("Enable EnderIO alloying recipes xml file")
    @Config.Comment("Loads the `metallurgy_enderio_alloys.xml` file in the config folder that allows players to edit alloy recipes that you can make in the Alloy Smelter from EnderIO")
    @Config.RequiresMcRestart
    public static boolean enableEnderIOAlloyConfig = false;

    @Config.Name("Mob that can spawn equipped")
    @Config.Comment("A list of mob ids that can spawn with metallurgy armor and/or tools")
    public static String[] mobsThatCanHaveEquipment = {
            "minecraft:zombie",
            "minecraft:skeleton",
            "minecraft:wither_skeleton",
            "minecraft:stray",
            "minecraft:husk"
    };

    //Handles Config Synchronization
    public static class ChangeListener {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
        {
            if (eventArgs.getModID().equals(Metallurgy.MODID))
                ConfigManager.sync(Metallurgy.MODID, Config.Type.INSTANCE);
        }

    }

}
