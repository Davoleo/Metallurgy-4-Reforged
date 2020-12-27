/*==============================================================================
 = Class: RegistrationConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.registry")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/registry")
@Config.RequiresMcRestart
public class RegistrationConfig {

    @Config.Name("Blocks")
    public static CategoryBlocks categoryBlocks = new CategoryBlocks();
    @Config.Name("Items")
    public static CategoryItems categoryItems = new CategoryItems();

    public static class CategoryBlocks {

        @Config.Name("Raw Metal Blocks")
        @Config.Comment("If true raw 3x3 ingot metal blocks will be registered §c(WARNING: these blocks are necessary for Sublimation Chamber recipes to work)")
        public boolean enableRawMetalBlocks = true;

        @Config.Name("Engraved Metal Blocks")
        @Config.Comment("If true engraved metal blocks will be registered")
        public boolean enableEngravedMetalBlocks = true;

        @Config.Name("Large Bricks Metal Blocks")
        @Config.Comment("If true large bricks metal blocks will be registered")
        public boolean enableLargeBricksMetalBlocks = true;

        @Config.Name("Bricks Metal Blocks")
        @Config.Comment("If true bricks metal blocks will be registered")
        public boolean enableBricksMetalBlocks = true;

        @Config.Name("Crystallized Metal Blocks")
        @Config.Comment("If true crystal metal blocks will be registered")
        public boolean enableCrystalMetalBlocks = true;

        @Config.Name("Hazard Metal Blocks")
        @Config.Comment("If true hazard metal blocks will be registered")
        public boolean enableHazardMetalBlocks = true;

        @Config.Name("Reinforced Glass Blocks")
        @Config.Comment("If true reinforced glass blocks will be registered")
        public boolean enableReinforcedGlassBlocks = true;

        @Config.Name("Molten Metal FluidBlocks")
        @Config.Comment("If true FluidBlocks will be registered (When false fluids will still be registered but you won't be able to place them in-world)")
        public boolean enableMetalFluidBlocks = true;

    }

    public static class CategoryItems {

        @Config.Name("Metal Nuggets")
        @Config.Comment("If true metal nuggets will be registered §c(WARNING: disabling this could break some recipes!)")
        public boolean enableMetalNuggets = true;

        @Config.Name("Metal Dusts")
        @Config.Comment("If true metal dusts will be registered §c(WARNING: disabling this will break some recipes!)")
        public boolean enableMetalDusts = true;

        @Config.Name("Metal Axes")
        @Config.Comment("If true axes will be registered")
        public boolean enableMetalAxes = true;

        @Config.Name("Metal Hoes")
        @Config.Comment("If true hoes will be registered")
        public boolean enableMetalHoes = true;

        @Config.Name("Metal Pickaxes")
        @Config.Comment("If true pickaxes will be registered")
        public boolean enableMetalPickaxes = true;

        @Config.Name("Metal Shovels")
        @Config.Comment("If true shovels will be registered")
        public boolean enableMetalShovels = true;

        @Config.Name("Metal Swords")
        @Config.Comment("If true swords will be registered")
        public boolean enableMetalSwords = true;


        @Config.Name("Metal Armor Sets")
        @Config.Comment("If true armor sets will be registered")
        public boolean enableMetalArmorSets = true;

    }

}
