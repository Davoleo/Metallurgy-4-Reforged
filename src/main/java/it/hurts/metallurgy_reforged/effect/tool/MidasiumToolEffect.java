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
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
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

        List<ItemStack> drops = event.getDrops();
        Block theBlock = event.getState().getBlock();


        //Means the block drops itself -> The effect shouldn't take place
        for (ItemStack drop : drops)
        {
            //Can't be bothered to create a RayTraceResult for the getPickBlock method sorry (I'll use the deprecated insensitive getItem version)
            if (drop.getItem().equals(Item.getItemFromBlock(theBlock))
                    || drop.isItemEqual(theBlock.getItem(event.getWorld(), event.getPos(), event.getState()))
                    || drop.getItem() instanceof ItemBlock)
                return;
        }

        //System.out.println("Will Be duped");

        //Looting level + 1 * 30 is the % at which the effect should take effect
        float chance = (event.getFortuneLevel() + 1) * 0.3F;

        if (Math.random() <= chance)
        {
            List<ItemStack> newDrops = ItemUtils.compactStackList(drops);
            drops.clear();
            for (ItemStack drop : newDrops)
            {
                MidasiumWeaponEffect.StackWrapperImpl stackWrapper = new MidasiumWeaponEffect.StackWrapperImpl(drop);
                MidasiumWeaponEffect.applyGreedEffect(stackWrapper, event.getFortuneLevel());
                drops.add(stackWrapper.getItem());
            }

            for (int i = 0; i < drops.size(); i++)
                spawnParticle(event.getWorld(), event.getPos(), 1F, true, 5,
                        Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05);
        }
    }

}
