package it.hurts.metallurgy_reforged.integration.mods;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;


public class IntegrationProjectE{
    public static void init() {
        for (Metal m : ModMetals.metalList) {
            if(m.getToolMaterial() != null){
                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getBlock()), m.getToolMaterial().getHarvestLevel() > 0 ?
                        (long) (((m.getToolMaterial().getHarvestLevel() * 250) / 4.5) * 9) : (long) ((250 / 4.5) * 9));

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), m.getToolMaterial().getHarvestLevel() > 0 ?
                        (long) ((m.getToolMaterial().getHarvestLevel() * 250) / 4.5) : (long) (250 / 4.5));

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getDust()), m.getToolMaterial().getHarvestLevel() > 0 ?
                        (long) ((m.getToolMaterial().getHarvestLevel() * 250) / 4.5) : (long) (250 / 4.5));

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getBlock()), m.getToolMaterial().getHarvestLevel() > 0 ?
                        (long) (((m.getToolMaterial().getHarvestLevel() * 250) / 4.5) / 9) : (long) ((250 / 4.5) / 9));
            }
        }
    }
}
