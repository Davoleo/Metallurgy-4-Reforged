package it.hurts.metallurgy_reforged.integration.mods;

import com.buuz135.industrial.api.IndustrialForegoingHelper;
import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import it.hurts.metallurgy_reforged.integration.mods.industrialforegoing.MetallurgyDrillEntry;

public class IntegrationIndustrialF {

    public static void preInit(){
        MetallurgyDrillEntry.createEntry();

        for(LaserDrillEntry t : MetallurgyDrillEntry.laserEntry){
            IndustrialForegoingHelper.addLaserDrillEntry(t);
        }
    }

}
