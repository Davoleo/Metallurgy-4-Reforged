/*==============================================================================
 = Class: ShadowSteelEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ShadowSteelEffect extends BaseMetallurgyEffect {

	public ShadowSteelEffect()
	{
		super(ModMetals.SHADOW_STEEL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	@Override
	public void inventoryTick(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (entityIn instanceof EntityPlayer)
		{
			int ticksBetween = 50 - Math.round(EventUtils.getDarknessLevel(entityIn, 25));

			if (worldIn.isRemote || entityIn.ticksExisted % ticksBetween != 0)
				return;

			if (stack.getItemDamage() > 0)
				stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}

}
