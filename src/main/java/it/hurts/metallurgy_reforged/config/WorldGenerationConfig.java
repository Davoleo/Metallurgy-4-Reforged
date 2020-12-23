/*==============================================================================
 = Class: WorldGenerationConfig
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config.metallurgy.category.worldgen")
@Config(modid = Metallurgy.MODID, name = "metallurgy_reforged/worldgen", category = "ore_generation")
public class WorldGenerationConfig {

    //	Subclass Category
    public static SubCategoryRarity rarity = new SubCategoryRarity();

    //OverWorld ores
    public static CategoryAdamantine adamantine = new CategoryAdamantine();
    public static CategoryAstralSilver astralSilver = new CategoryAstralSilver();
    public static CategoryAtlarus atlarus = new CategoryAtlarus();
    public static CategoryCarmot carmot = new CategoryCarmot();
    public static CategoryCopper copper = new CategoryCopper();
    public static CategoryDeepIron deepIron = new CategoryDeepIron();
    public static CategoryInfuscolium infuscolium = new CategoryInfuscolium();
    public static CategoryLutetium lutetium = new CategoryLutetium();
    public static CategoryManganese manganese = new CategoryManganese();
    public static CategoryMithril mithril = new CategoryMithril();
    public static CategoryOureclase oureclase = new CategoryOureclase();
    public static CategoryOrichalcum orichalcum = new CategoryOrichalcum();
    public static CategoryOsmium osmium = new CategoryOsmium();
    public static CategoryPhosphorite phosphorite = new CategoryPhosphorite();
    public static CategoryPlatinum platinum = new CategoryPlatinum();
    public static CategoryPotash potash = new CategoryPotash();
    public static CategoryPrometheum prometheum = new CategoryPrometheum();
    public static CategoryRubracium rubracium = new CategoryRubracium();
    public static CategorySilver silver = new CategorySilver();
    public static CategorySulfur sulfur = new CategorySulfur();
    public static CategoryTin tin = new CategoryTin();
    public static CategoryZinc zinc = new CategoryZinc();

    //Nether ores
    public static CategoryAlduorite alduorite = new CategoryAlduorite();
    public static CategoryCeruclase cerucalse = new CategoryCeruclase();
    public static CategoryIgnatius ignatius = new CategoryIgnatius();
    public static CategoryKalendrite kalendrite = new CategoryKalendrite();
    public static CategoryLemurite lemurite = new CategoryLemurite();
    public static CategoryMidasium midasium = new CategoryMidasium();
    public static CategorySanguinite sanguinite = new CategorySanguinite();
    public static CategoryShadowIron shadowIron = new CategoryShadowIron();
    public static CategoryVulcanite vulcanite = new CategoryVulcanite();
    public static CategoryVyroxeres vyroxeres = new CategoryVyroxeres();

    //End ores
    public static CategoryEximite eximite = new CategoryEximite();
    public static CategoryMeutoite meutoite = new CategoryMeutoite();

    @Config.Name("The spawnrate of tar lakes in the overworld")
    @Config.Comment("Sets to 0 to disable tar lakes world generation")
    @Config.RangeInt(min = 0, max = 500)
    public static int tarLakePercentage = 20;

    @Config.Name("Tar Lakes Worldgen Dimension Whitelist")
    @Config.Comment("Add the ID of the dimensions you want tar lakes to spawn in")
    public static Integer[] tarLakeDimensionWhiteList = { 0 };

    @Config.Name("Retrogen")
    @Config.Comment(value = "Enable/Disable Retrogen")
    public static boolean retrogen = true;

    @Config.Name("Verbose Retrogen")
    @Config.Comment(value = "Enable/Disable verbose logging for retrogen")
    public static boolean verbose_retrogen = false;

    public static class CategoryMeutoite {

        @Config.Comment("Meutoite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Meutoite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Meutoite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 7;

        @Config.Comment("Meutoite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryEximite {

        @Config.Comment("Eximite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Eximite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Eximite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 7;

        @Config.Comment("Eximite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryVyroxeres {

        @Config.Comment("Vyroxeres minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Vyroxeres maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 120;

        @Config.Comment("Vyroxeres indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Vyroxeres biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryVulcanite {

        @Config.Comment("Vulcanite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 27;

        @Config.Comment("Vulcanite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 39;

        @Config.Comment("Vulcanite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Vulcanite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategorySanguinite {

        @Config.Comment("Sanguinite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Sanguinite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Sanguinite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 4;

        @Config.Comment("Sanguinite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryShadowIron {

        @Config.Comment("Shadow Iron minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 113;

        @Config.Comment("Shadow Iron maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 123;

        @Config.Comment("Shadow Iron indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Shadow Iron biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryMidasium {

        @Config.Comment("Midasium minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 32;

        @Config.Comment("Midasium maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 255;

        @Config.Comment("Midasium indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Midasium biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryLemurite {

        @Config.Comment("Lemurite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Lemurite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 100;

        @Config.Comment("Lemurite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 7;

        @Config.Comment("Lemurite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryKalendrite {

        @Config.Comment("Kalendrite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 27;

        @Config.Comment("Kalendrite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 120;

        @Config.Comment("Kalendrite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Kalendrite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryIgnatius {

        @Config.Comment("Ignatius minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Ignatius maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 255;

        @Config.Comment("Ignatius indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 10;

        @Config.Comment("Ignatius biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryCeruclase {

        @Config.Comment("Ceruclase minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Ceruclase maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Ceruclase indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Ceruclase biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryAlduorite {

        @Config.Comment("Alduorite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Alduorite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Alduorite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 4;

        @Config.Comment("Alduorite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryPotash {

        @Config.Comment("Potash minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 32;

        @Config.Comment("Potash maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 72;

        @Config.Comment("Potash indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 7;

        @Config.Comment("Potash biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryPhosphorite {

        @Config.Comment("Phosphorite minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Phosphorite maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 100;

        @Config.Comment("Phosphorite indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Phosphorite biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategorySulfur {

        @Config.Comment("Sulfur minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Sulfur maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 15;

        @Config.Comment("Sulfur indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Sulfur biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryOsmium {

        @Config.Comment("Osmium minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 3;

        @Config.Comment("Osmium maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 10;

        @Config.Comment("Osmium indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Osmium biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryLutetium {

        @Config.Comment("Lutetium minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 20;

        @Config.Comment("Lutetium maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 50;

        @Config.Comment("Lutetium indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 4;

        @Config.Comment("Lutetium biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryPlatinum {

        @Config.Comment("Platinum minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Platinum maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 80;

        @Config.Comment("Platinum indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 4;

        @Config.Comment("Platinum biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategorySilver {

        @Config.Comment("Silver minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Silver maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Silver indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 8;

        @Config.Comment("Silver biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryAstralSilver {

        @Config.Comment("Astral Silver minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 24;

        @Config.Comment("Astral Silver maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 64;

        @Config.Comment("Astral Silver indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Astral Silver biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryOrichalcum {

        @Config.Comment("Orichalcum minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Orichalcum maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Orichalcum indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Orichalcum biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryZinc {

        @Config.Comment("Zinc minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Zinc maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Zinc indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 8;

        @Config.Comment("Zinc biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryMithril {

        @Config.Comment("Mithril minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 32;

        @Config.Comment("Mithril maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Mithril indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Mithril biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryCarmot {

        @Config.Comment("Carmot minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 32;

        @Config.Comment("Carmot maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 64;

        @Config.Comment("Carmot indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Carmot biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryAtlarus {

        @Config.Comment("Atlarus minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 12;

        @Config.Comment("Atlarus maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 48;

        @Config.Comment("Atlarus indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Atlarus biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryAdamantine {

        @Config.Comment("Adamantine minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Adamantine maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 30;

        @Config.Comment("Adamantine indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 3;

        @Config.Comment("Adamantine biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryRubracium {

        @Config.Comment("Rubracium minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 10;

        @Config.Comment("Rubracium maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 40;

        @Config.Comment("Rubracium indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Rubracium biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryInfuscolium {

        @Config.Comment("Infuscolium minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 10;

        @Config.Comment("Infuscolium maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 72;

        @Config.Comment("Infuscolium indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 7;

        @Config.Comment("Infuscolium biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryOureclase {

        @Config.Comment("Ourseclase minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Ourseclase maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Ourseclase indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Ourseclase biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryManganese {

        @Config.Comment("Manganese minimum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int minY = 0;

        @Config.Comment("Manganese maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public int maxY = 128;

        @Config.Comment("Manganese indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 9;

        @Config.Comment("Manganese biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryPrometheum {

        @Config.Comment("Prometheum minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 0;

        @Config.Comment("Prometheum maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 64;

        @Config.Comment("Prometheum indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 6;

        @Config.Comment("Prometheum biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{ "minecraft:jungle", "minecraft:jungle_hills", "minecraft:jungle_edge" };

    }

    public static class CategoryTin {

        @Config.Comment("Tin minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 25;

        @Config.Comment("Tin maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 48;

        @Config.Comment("Tin indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 10;

        @Config.Comment("Tin biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }

    public static class CategoryDeepIron {

        @Config.Comment("Deep Iron minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 10;

        @Config.Comment("Deep Iron maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 30;

        @Config.Comment("Deep Iron indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 5;

        @Config.Comment("Deep Iron biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{
                "minecraft:ocean",
                "minecraft:beaches",
                "minecraft:river",
                "minecraft:frozen_river",
                "minecraft:frozen_ocean",
                "minecraft:stone_beach",
                "minecraft:cold_beach" };

    }

    public static class CategoryCopper {

        @Config.Comment("Copper minimum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int minY = 35;

        @Config.Comment("Copper maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public int maxY = 120;

        @Config.Comment("Copper indicative vein size (Set to 0 to disable this ore)")
        @Config.RangeInt(min = 0)
        public int veinSize = 8;

        @Config.Comment("Copper biome spawn")
        @Config.RequiresWorldRestart
        public String[] biomes = new String[]{};

    }


    public static class SubCategoryRarity {

        @Config.Name("Common Ore Rarity")
        @Config.Comment("Set the value of common Rarity [ The smaller the value, the rarer it is ] (Don't change if you aren't a dev)")
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1)
        public int commonRarity = 10;

        @Config.Name("Uncommon Ore Rarity")
        @Config.Comment("Set the value of uncommon Rarity [ The smaller the value, the rarer it is ] (Don't change if you aren't a dev)")
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1)
        public int uncommonRarity = 7;

        @Config.Name("Rare Ore Rarity")
        @Config.Comment("Set the value of rare Rarity [ The smaller the value, the rarer it is ] (Don't change if you aren't a dev)")
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1)
        public int rareRarity = 3;

        @Config.Name("Ultra Rare Ore Rarity")
        @Config.Comment("Set the value of ultra rare Rarity [ The smaller the value, the rarer it is ] (Don't change if you aren't a dev)")
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1)
        public int ultraRareRarity = 1;

    }

}
