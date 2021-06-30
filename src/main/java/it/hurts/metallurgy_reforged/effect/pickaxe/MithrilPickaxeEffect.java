/*==============================================================================
 = Class: MithrilPickaxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.pickaxe;

import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.ItemBlockOre;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class MithrilPickaxeEffect extends BaseMetallurgyEffect {

    public MithrilPickaxeEffect()
    {
        super(ModMetals.MITHRIL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.PICKAXE;
    }

    @SubscribeEvent
    public void multiplyOres(BlockEvent.HarvestDropsEvent event)
    {
        if (!canBeApplied(event.getHarvester()))
            return;

        if (event.getState().getBlock() instanceof BlockOre)
        {
            //Get the one and only dropStack
            ItemStack oreDrop = event.getDrops().get(0);

            //Locks the itemBlock in any case
            if (oreDrop.getItem() instanceof ItemBlockOre)
                ItemBlockOre.setLocked(oreDrop, true);

            //1..4 <-> 25%..100%
            float chance = (event.getFortuneLevel() + 1) / 4F;
            if (Math.random() <= chance)
            {
                //3 damage points
                event.getHarvester().getHeldItemMainhand().damageItem(2, event.getHarvester());
                //Increase stack count by 1
                oreDrop.setCount(oreDrop.getCount() + 1);
                event.getWorld().playSound(null, event.getPos(), SoundEvents.BLOCK_NOTE_HARP, SoundCategory.BLOCKS, 1F, 1F);
            }
        }
    }

}
