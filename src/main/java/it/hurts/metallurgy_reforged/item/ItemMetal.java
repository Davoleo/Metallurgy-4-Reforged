/*==============================================================================
 = Class: ItemMetal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.MetalStats;
import it.hurts.metallurgy_reforged.model.MetalSample;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMetal extends ItemBase implements IMetalItem {

    private final ItemTypes type;
    private final MetalStats metal;

    public ItemMetal(MetalStats metal, ItemTypes type)
    {
        super(metal.getName() + "_" + type.getName(), type.getTab());
        this.type = type;
        this.metal = metal;
    }

    public ItemTypes getType()
    {
        return type;
    }

    public MetalStats getMetalStats()
    {
        return metal;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn)
    {
        if (type == ItemTypes.INGOT)
        {
            final int tier;
            if (metal.getOreHarvest() == -1)
            {
                Pair<MetalSample, MetalSample> ingredients = AlloyerRecipes.getInstance().getIngredients(stack);
                if (ingredients != null && ingredients.getLeft().getMetal() != null && ingredients.getRight().getMetal() != null)
                    tier = Math.max(ingredients.getLeft().getMetal().getStats().getOreHarvest(), ingredients.getRight().getMetal().getStats().getOreHarvest());
                else
                    tier = 1;
            }
            else
                tier = metal.getOreHarvest();


            tooltip.add("Tier: " + ItemUtils.HarvestLevelFormatting.values()[tier].format + tier);
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
