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

import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.recipe.BlockAlloyerRecipes;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class IntegrationProjectE{

    public static void init() {

        for (Metal m : ModMetals.metalList)
        {
            if(m.hasToolSet())
            {
                long emc = getEMCbyHarvestLevel(m.getToolMaterial().getHarvestLevel());


                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getBlock()), emc * 9);

                if (!m.isAlloy())
                    ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), emc);

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getDust()), emc);

                ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getNugget()), emc / 9);


//              Special Alloy EMC Calculations
                if (m.isAlloy())
                {
                    Table<ItemStack, ItemStack, ItemStack> recipes = BlockAlloyerRecipes.getInstance().getRecipeTable();

                    for (Table.Cell<ItemStack, ItemStack, ItemStack> entry : recipes.cellSet())
                    {
                        if (entry.getValue().getItem().equals(m.getIngot()))
                            ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(m.getIngot()), getAlloyIngredientsEMC(entry.getRowKey()) + getAlloyIngredientsEMC(entry.getColumnKey()));
                    }
                }
            }
        }
    }

    private static long getAlloyIngredientsEMC(ItemStack ingot)
    {
        if (ingot.getItem().equals(Items.IRON_INGOT))
            return 256;
        else if (ingot.getItem().equals(Items.GOLD_INGOT))
            return 2048;

        Metal metal = ItemUtils.getMetalFromIngot(ingot);

        if (metal.hasToolSet())
            return getEMCbyHarvestLevel(metal.getToolMaterial().getHarvestLevel());
        return 0;
    }

    private static long getEMCbyHarvestLevel(int harvestLevel)
    {
        if (harvestLevel == 0)
            harvestLevel = 1;
        return harvestLevel * harvestLevel * 150;
    }
}
