package it.hurts.metallurgy_reforged.integration.mods.industrialforegoing;

import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetallurgyDrillEntry {
    private static final Biome[] OVERWORLD = {Biomes.HELL, Biomes.SKY};
    private static final Biome[] HELL = {Biomes.HELL};
    private static final Biome[] SKY = {Biomes.SKY};

    private static final List<Biome> WORLD = new ArrayList<Biome>(Arrays.asList(OVERWORLD));
    private static final List<Biome> NETHER = new ArrayList<Biome>(Arrays.asList(HELL));
    private static final List<Biome> END = new ArrayList<Biome>(Arrays.asList(SKY));

    public static List<LaserDrillEntry> laserEntry = new ArrayList<LaserDrillEntry>();

    public static void createEntry(){
        laserEntry.add(new LaserDrillEntry(0 , new ItemStack(ModMetals.ADAMANTINE.getOre()), 100, null, WORLD));
    }

}
