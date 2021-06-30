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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

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
            //Looting level + 1 * 25 is the % at which the effect should take effect
            float chance = (event.getLootingLevel() + 1) * 0.3F;

            if (Math.random() <= chance)
            {
                event.getDrops().forEach(drop -> applyGreedEffect(drop, event.getLootingLevel()));

                spawnParticle(event.getEntity().world, event.getEntity().getPosition(), 1F, true, 5,
                        Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05, Math.random() * 0.1 - 0.05);
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

    public static void applyGreedEffect(EntityItem drop, int fortuneLootingLevel)
    {
        //7% + 1% (per enchantment level) chance of turning all the drops into gold
        if (Math.random() <= 0.07 + (fortuneLootingLevel * 0.01))
            drop.setItem(new ItemStack(Items.GOLD_INGOT, drop.getItem().getCount()));
        else
        {
            //looting / looting + 1 chance of increasing drop count by 2 instead of 1 (based on Vanilla Looting)
            float doubleIncreaseChance = (float) fortuneLootingLevel / fortuneLootingLevel + 1;
            ItemStack dropStack = drop.getItem();
            int increaseAmount = Math.random() <= doubleIncreaseChance ? 1 : 2;
            dropStack.setCount(dropStack.getCount() + increaseAmount);
        }
    }
}
