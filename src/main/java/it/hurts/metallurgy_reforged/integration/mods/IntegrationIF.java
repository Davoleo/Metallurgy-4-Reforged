/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationIF
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.util.ResourceLocation;

public class IntegrationIF {

    public static void preInit(){
        LaserDrillEntry.addOreFile(new ResourceLocation(Metallurgy.MODID, "ore.json"));
        Metallurgy.logger.info("Metallurgy ore are added to Laser Drill recipes");
    }

}
