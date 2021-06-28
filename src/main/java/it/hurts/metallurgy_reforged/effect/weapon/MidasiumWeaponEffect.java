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
        // TODO: 28/06/2021 Maybe find a way to generalize this and use the same code used in MidasiumToolEffect
        Entity sourceEnt = event.getSource().getImmediateSource();
        if (sourceEnt instanceof EntityLivingBase && canBeApplied((EntityLivingBase) sourceEnt))
        {
            //Looting level + 1 * 30 is the % at which the effect should take effect (obviously clamped at 100%)
            int chanceModifier = event.getLootingLevel() + 1;
            float chance = Math.min(chanceModifier * 0.3F, 1);

            if (Math.random() <= chance)
            {
                event.getDrops().forEach(drop -> {
                    //5% chance of turning all the drops into gold
                    if (Math.random() <= 0.05)
                    {
                        drop.setItem(new ItemStack(Items.GOLD_INGOT, drop.getItem().getCount()));
                    }
                    else
                    {
                        //looting / looting + 1 chance of increasing drop count by 2 instead of 1 (based on Vanilla Looting)
                        float doubleIncreaseChance = event.getLootingLevel() / ((float) event.getLootingLevel() + 1);
                        ItemStack dropStack = drop.getItem();
                        int increaseAmount = Math.random() <= doubleIncreaseChance ? 1 : 2;
                        dropStack.setCount(dropStack.getCount() + increaseAmount);
                    }

                    Utils.repeat(3, () -> spawnParticle(drop.world, drop.posX, drop.posY, drop.posZ,
                            drop.motionX * 0.2, drop.motionY * 0.2, drop.motionZ * 0.2, 1, true, 5));
                });
            }
        }
    }
}
