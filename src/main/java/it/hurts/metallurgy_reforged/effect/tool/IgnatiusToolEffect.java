/*==============================================================================
 = Class: IgnatiusToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import com.google.common.collect.Sets;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

public class IgnatiusToolEffect extends BaseMetallurgyEffect {

    public IgnatiusToolEffect() {
        super(ModMetals.IGNATIUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent
    public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
    {
        boolean hadEffect = false;

        if (event.getHarvester() != null && canBeApplied(event.getHarvester()))
            hadEffect = dropSmeltedItems(event.getDrops(), event.getFortuneLevel());

        if (hadEffect)
        {
            Utils.repeat(10, () -> {
                float f1 = (Utils.random.nextFloat() / 16F) - 0.03125F;
                float f2 = (Utils.random.nextFloat() / 16F) - 0.03125F;
                spawnParticle(event.getWorld(), event.getPos(), 2F, true, 3, f1, 0.02, f2);
            });
        }
    }

    public boolean dropSmeltedItems(List<ItemStack> drops, int fortune)
    {
        //0.25 | 0.5 | 0.75 | 1 depending on the fortune level
        float fortuneLevelChance = (fortune + 1) / 4F;
        Set<ItemStack> newDrops = Sets.newHashSet();

        //If a random double value from 0 to 1 (exclusive) is lower than the chance value
        if (Math.random() < fortuneLevelChance)
        {
            for (ItemStack drop : drops)
            {
                //Get the smelted result itemstack (EMPTY if the stack can't be smelted)
                ItemStack smeltedItem = FurnaceRecipes.instance().getSmeltingResult(drop);
                //Set the count of the smelted stack to be the same of the old drop
                smeltedItem.setCount(drop.getCount());
                //Add it to the set (can't edit the original List when looping over it)
                newDrops.add(smeltedItem.isEmpty() ? drop : smeltedItem);
            }

            //Remove all the old drops and add the new ones
            drops.clear();
            drops.addAll(newDrops);

            return true;
        }

        return false;
    }

}
