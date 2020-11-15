/*==============================================================================
 = Class: ItemOrichalcumBuckler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class ItemOrichalcumBuckler extends ItemBuckler {

    public ItemOrichalcumBuckler() {
        super("orichalcum_buckler", 500, 80);
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 30;
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public void onDamageBlocked(EntityLivingBase player, DamageSource damageSource, float amount) {
        if (damageSource.getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase blockedEntity = ((EntityLivingBase) damageSource.getTrueSource());
            blockedEntity.attackEntityFrom(damageSource, amount * 2);
        }
    }
}
