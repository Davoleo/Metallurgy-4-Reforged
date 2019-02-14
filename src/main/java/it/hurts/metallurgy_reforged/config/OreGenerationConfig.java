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
	
//	Category
	public static SubCategoryRarity rarity = new SubCategoryRarity();
	public static CategoryCopper copper = new CategoryCopper();
	public static CategoryDeepIron deepIron = new CategoryDeepIron();
	public static CategoryTin tin = new CategoryTin();
	public static CategoryPrometheum prometheum = new CategoryPrometheum();
	public static CategoryManganese manganese = new CategoryManganese();
	public static CategoryOureclase oureclase = new CategoryOureclase();
	public static CategoryInfuscolium infuscolium = new CategoryInfuscolium();
	public static CategoryAdamantine adamantine = new CategoryAdamantine();
	
    @Config.Name("Retrogen")
    @Config.Comment(value = "Enable/Disable Retrogen")
    public static boolean retrogen = true;

    @Config.Name("Verbose Retrogen")
    @Config.Comment(value = "Enable/Disable verbose logging for retrogen")
    public static boolean verbose_retrogen = false;
    
    public static class CategoryAdamantine{
    	@Config.Comment("Adamantine minimum Y level")
    	@Config.RangeInt(min = 0, max = 255)
        public static int minY = 0;

        @Config.Comment("Adamantine maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public static int maxY = 30;

        @Config.Comment("Adamantine indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 4;
    }
    
    public static class CategoryInfuscolium {
    	@Config.Comment("Infuscolium minimum Y level")
    	@Config.RangeInt(min = 0, max = 255)
        public static int minY = 10;

        @Config.Comment("Infuscolium maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public static int maxY = 72;

        @Config.Comment("Infuscolium indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 7;
    }
    
    public static class CategoryOureclase {
    	@Config.Comment("Ourseclase minimum Y level")
    	@Config.RangeInt(min = 0, max = 255)
        public static int minY = 0;

        @Config.Comment("Ourseclase maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public static int maxY = 128;

        @Config.Comment("Ourseclase indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 5;
    }
    
    public static class CategoryManganese {
    	@Config.Comment("Manganese minimum Y level")
    	@Config.RangeInt(min = 0, max = 255)
        public static int minY = 0;

        @Config.Comment("Manganese maximum Y level")
        @Config.RangeInt(min = 0, max = 255)
        public static int maxY = 128;

        @Config.Comment("Manganese indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 9;
    }
    
    public static class CategoryPrometheum {
    	@Config.Comment("Prometheum minimum Y level")
    	@Config.RangeInt(min = 2, max = 255)
        public static int minY = 0;

        @Config.Comment("Prometheum maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public static int maxY = 64;

        @Config.Comment("Prometheum indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 6;
    }
    
    public static class CategoryTin {
    	@Config.Comment("Tin minimum Y level")
    	@Config.RangeInt(min = 2, max = 255)
        public static int minY = 25;

        @Config.Comment("Tin maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public static int maxY = 48;

        @Config.Comment("Tin indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 10;
    }
    
    public static class CategoryDeepIron{
    	@Config.Comment("Deep Iron minimum Y level")
    	@Config.RangeInt(min = 2, max = 255)
        public static int minY = 10;

        @Config.Comment("Deep Iron maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public static int maxY = 30;

        @Config.Comment("Deep Iron indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 5;
    }
    
    public static class CategoryCopper{
    	@Config.Comment("Copper minimum Y level")
    	@Config.RangeInt(min = 2, max = 255)
        public static int minY = 35;

        @Config.Comment("Copper maximum Y level")
        @Config.RangeInt(min = 2, max = 255)
        public static int maxY = 120;

        @Config.Comment("Copper indicative vein size")
        @Config.RangeInt(min = 1)
        public static int veinSize = 8;
    }
    
    public static class SubCategoryRarity {
//    	Le variabili devono essere non statiche
    	@Config.Name("Common Ore Rarity")
    	@Config.Comment("Set the value of common Rarity (Don't change if you aren't a dev)")
    	@Config.RequiresMcRestart
    	public int commonRarity = 15;
    	
		@Config.Name("Uncommon Ore Rarity")
    	@Config.Comment("Set the value of uncommon Rarity (Don't change if you aren't a dev)")
    	@Config.RequiresMcRestart
    	public int uncommonRarity = 10;
    	
    	@Config.Name("Rare Ore Rarity")
    	@Config.Comment("Set the value of rare Rarity (Don't change if you aren't a dev)")
    	@Config.RequiresMcRestart
    	public int rareRarity = 5;
    	
    	@Config.Name("Ultra Rare Ore Rarity")
    	@Config.Comment("Set the value of ultra rare Rarity (Don't change if you aren't a dev)")
    	@Config.RequiresMcRestart
    	public int ultraRareRarity = 2;
     }
}
