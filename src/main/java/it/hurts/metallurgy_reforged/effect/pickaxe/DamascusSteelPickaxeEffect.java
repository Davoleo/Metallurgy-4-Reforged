/*==============================================================================
 = Class: DamascusSteelPickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.pickaxe;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class DamascusSteelPickaxeEffect extends BaseMetallurgyEffect {

    public DamascusSteelPickaxeEffect()
    {
        super(ModMetals.DAMASCUS_STEEL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.PICKAXE;
    }

    @SubscribeEvent
    public void onOreBroken(BlockEvent.BreakEvent event)
    {
        if (!canBeApplied(event.getPlayer()))
            return;

        if (Utils.random.nextBoolean())
        {
            Block ore = event.getState().getBlock();
            ItemStack oreStack = new ItemStack(ore);

            //Should avoid crash on OreDictionary#getOreIDs(ItemStack) cause apparently a new ItemStack can be empty sometimes
            if (oreStack.isEmpty())
                return;

            boolean isOre = Arrays.stream(OreDictionary.getOreIDs(oreStack))
                    .mapToObj(OreDictionary::getOreName)
                    .anyMatch(oreName -> oreName.startsWith("ore"));

            if (isOre)
                event.setExpToDrop(event.getExpToDrop() + ore.getHarvestLevel(event.getState()) + 1);
            //Drops an amount of experience equals to the harvest level + 1
        }
    }

}
