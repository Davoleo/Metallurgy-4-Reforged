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

	public ItemVulcaniteBuckler()
	{
		super("vulcanite_buckler", 762, 40);
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 40;
	}

	@Override
	public int getItemEnchantability()
	{
		return 20;
	}

	@Override
	public void onDamageBlocked(EntityLivingBase player, DamageSource damageSource, float amount)
	{

		super.onDamageBlocked(player, damageSource, amount);

		if (damageSource.getImmediateSource() instanceof EntityLivingBase)
		{
			EntityLivingBase target = ((EntityLivingBase) damageSource.getImmediateSource());
			target.world.createExplosion(player, target.posX, target.posY, target.posZ, 0.3F, false);
			target.knockBack(target, 2.5F, player.posX - target.posX, player.posZ - target.posZ);
		}

	}

}
