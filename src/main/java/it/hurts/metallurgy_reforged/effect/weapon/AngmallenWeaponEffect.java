/*==============================================================================
 = Class: AngmallenWeaponEffect
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class AngmallenWeaponEffect extends BaseMetallurgyEffect {

	public AngmallenWeaponEffect()
	{
		super(ModMetals.ANGMALLEN);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}


	@SubscribeEvent
	public void buffDamage(LivingHurtEvent event)
	{

		if (event.getSource().getTrueSource() instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) event.getSource().getTrueSource();
			if (!canBeApplied(entity))
				return;


			Spliterator<ItemStack> spliterator = event.getEntityLiving().getArmorInventoryList().spliterator();
			boolean hasArmor = StreamSupport.stream(spliterator, false).anyMatch(stack -> stack.getItem() instanceof ItemArmor);

			//Increase damage by 75% if the enemy has a piece of armor
			if (hasArmor)
			{
				event.setAmount(event.getAmount() * 1.75F);
				Utils.repeat(10, () -> spawnParticle(event.getEntityLiving(), 1.5f, true, 9));
			}
		}
	}

}
