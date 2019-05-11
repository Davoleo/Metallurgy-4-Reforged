/*
 * -------------------------------------------------------------------------------------------------------
 * Class: IntegrationProjectE
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods;

import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;


public class IntegrationProjectE{
    public static void init() {
        for (Metal m : ModMetals.metalList) {
            if(m.getToolMaterial() != null){

                int harvestLevel = m.getToolMaterial().getHarvestLevel();
                if (harvestLevel == 0)
                    harvestLevel = 1;
                long emc = harvestLevel * harvestLevel * 200;


                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getBlock()), emc * 9);

//                if (!m.isAlloy())
                    ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), emc);

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getDust()), emc);

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getNugget()), emc / 9);

//Special Alloy EMC Calculations
//                if (m.isAlloy())
//                {
//                    Table<ItemStack, ItemStack, ItemStack> recipes = BlockAlloyerRecipes.getInstance().getRecipeTable();
//
//                    for (Table.Cell<ItemStack, ItemStack, ItemStack> entry : recipes.cellSet())
//                    {
//                        if (entry.getValue().getItem().equals(m.getIngot()))
//                        {
//                            ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), ProjectEAPI.getEMCProxy().getValue(entry.getRowKey()) + ProjectEAPI.getEMCProxy().getValue(entry.getColumnKey()));
//                        }
//                    }
//                }
            }
        }
    }
}
