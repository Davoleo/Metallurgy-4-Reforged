/*==============================================================================
 = Class: MidasiumToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.weapon.MidasiumWeaponEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MidasiumToolEffect extends BaseMetallurgyEffect {

    public MidasiumToolEffect()
    {
        super(ModMetals.MIDASIUM);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent
    public void fortuneAndGreed(BlockEvent.HarvestDropsEvent event)
    {
        if (!canBeApplied(event.getHarvester()))
            return;

        System.out.println("CALLED");

        List<ItemStack> drops = event.getDrops();
        Block theBlock = event.getState().getBlock();
        if (drops.contains(new ItemStack(theBlock)))
            return;

        //Looting level + 1 * 30 is the % at which the effect should take effect (obviously clamped at 100%)
        int chanceModifier = event.getFortuneLevel() + 1;
        float chance = Math.min(chanceModifier * 0.3F, 1);

        if (Math.random() <= chance)
        {
            ItemUtils.compactStackList(drops);
            List<ItemStack> newDrops = new ArrayList<>();

            for (ItemStack drop : drops)
            {
                MidasiumWeaponEffect.StackWrapperImpl stackWrapper = new MidasiumWeaponEffect.StackWrapperImpl(drop);
                MidasiumWeaponEffect.applyGreedEffect(stackWrapper, event.getFortuneLevel());
                newDrops.add(stackWrapper.getItem());
            }

            drops.clear();
            drops.addAll(newDrops);
            Utils.repeat(drops.size(), () ->
                    spawnParticle(event.getWorld(), event.getPos(), 1F, true, 5,
                            Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05));
        }
    }
}
