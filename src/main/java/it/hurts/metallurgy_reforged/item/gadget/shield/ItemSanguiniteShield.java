/*==============================================================================
 = Class: ItemSanguiniteShield
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class ItemSanguiniteShield extends ItemShieldBase {

	public ItemSanguiniteShield()
	{
		super("sanguinite_shield", 1000);
	}

	@Override
	public int getItemEnchantability()
	{
		return 25;
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 600;
	}

	@Override
    public void onDamageBlocked(EntityLivingBase player, DamageSource damageSource, float amount) {
        if (damageSource.getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase target = ((EntityLivingBase) damageSource.getTrueSource());
            target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 1));
        }
    }

}
