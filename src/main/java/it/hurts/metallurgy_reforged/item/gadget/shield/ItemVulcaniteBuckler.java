/*==============================================================================
 = Class: ItemVulcaniteBuckler
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

public class ItemVulcaniteBuckler extends ItemBuckler {

    public ItemVulcaniteBuckler() {
        super("vulcanite_buckler", 300, 30);
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 40;
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public void onDamageBlocked(EntityLivingBase entity, DamageSource damageSource) {

        super.onDamageBlocked(entity,damageSource);

        if (damageSource.getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase target = ((EntityLivingBase) damageSource.getTrueSource());
            target.world.createExplosion(entity, target.posX, target.posY + target.height / 3, target.posZ, 2F, false);
        }
    }
}
