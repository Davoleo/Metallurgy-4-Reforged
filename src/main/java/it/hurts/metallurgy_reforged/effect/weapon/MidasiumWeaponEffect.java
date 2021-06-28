/*==============================================================================
 = Class: MidasiumWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class MidasiumWeaponEffect extends BaseMetallurgyEffect {

    public MidasiumWeaponEffect()
    {
        super(ModMetals.MIDASIUM);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void lootingAndGreed(LivingDropsEvent event)
    {
        Entity sourceEnt = event.getSource().getImmediateSource();
        if (sourceEnt instanceof EntityLivingBase && canBeApplied((EntityLivingBase) sourceEnt))
        {
            if (applyGreedEffect(event.getDrops(), event.getLootingLevel()))
            {
                Utils.repeat(event.getDrops().size(), () -> {
                    double f = Math.random();
                    spawnParticle(event.getEntity().world, event.getEntity().getPosition(), 1F, true, 5,
                            (double) (f * 0.1 - 0.05), (double) (f * 0.1 - 0.05), (double) (f * 0.1 - 0.05));
                });
            }
        }
    }

    private interface StackWrapper {
        ItemStack getItem();

        void setItem(ItemStack stack);
    }

    @SuppressWarnings("EntityConstructor")
    public static class StackWrapperImpl extends EntityItem implements StackWrapper {
        private ItemStack item;

        public StackWrapperImpl(ItemStack stack)
        {
            super(null);
            this.item = stack;
        }

        @Nonnull
        @Override
        public ItemStack getItem()
        {
            return this.item;
        }

        @Override
        public void setItem(@Nonnull ItemStack stack)
        {
            this.item = stack;
        }
    }

    public static boolean applyGreedEffect(List<? extends EntityItem> stacks, int fortuneLootingLevel)
    {
        //Looting level + 1 * 30 is the % at which the effect should take effect (obviously clamped at 100%)
        int chanceModifier = fortuneLootingLevel + 1;
        float chance = Math.min(chanceModifier * 0.3F, 1);

        if (Math.random() <= chance)
        {
            stacks.forEach(drop -> {

                //5% chance of turning all the drops into gold
                if (Math.random() <= 0.05)
                    drop.setItem(new ItemStack(Items.GOLD_INGOT, drop.getItem().getCount()));
                else
                {
                    //looting / looting + 1 chance of increasing drop count by 2 instead of 1 (based on Vanilla Looting)

                    float doubleIncreaseChance = (float) fortuneLootingLevel / fortuneLootingLevel + 1;
                    ItemStack dropStack = drop.getItem();
                    int increaseAmount = Math.random() <= doubleIncreaseChance ? 1 : 2;
                    dropStack.setCount(dropStack.getCount() + increaseAmount);
                }
            });

            return true;
        }

        return false;
    }
}
