package it.hurts.metallurgy_reforged.integration.mods;

import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import it.hurts.metallurgy_reforged.Metallurgy;

import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.util.ResourceLocation;

public class IntegrationIndustrialF {

    public static void preInit(){
        LaserDrillEntry.addOreFile(new ResourceLocation(Metallurgy.MODID, "ore.json"));
        Metallurgy.logger.info("Metallurgy ore are added to Laser Drill recipes");
    }

}
