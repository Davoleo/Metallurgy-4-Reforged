/*==============================================================================
 = Class: EximiteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class EximiteArmorEffect extends BaseMetallurgyEffect {

	public EximiteArmorEffect()
	{
		super(ModMetals.EXIMITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	// TODO: 28/02/2021 Consider investigating more on potion effect immunity
	@SubscribeEvent
	public void onShulkerAttack(LivingSetAttackTargetEvent event)
	{

		if (getLevel(event.getTarget()) < 1)
			return;

		EntityLivingBase entity = event.getEntityLiving();
		if (entity instanceof EntityShulker)
		{
			EntityShulker shulker = ((EntityShulker) entity);

			EntityPlayer newTarget = shulker.world.getNearestAttackablePlayer(shulker.posX, shulker.posY + shulker.getEyeHeight(), shulker.posZ, 16, 16, e -> 1.0, player -> getLevel(player) < 1);
			shulker.setAttackTarget(newTarget);

			Utils.repeat(20, () -> spawnParticle(shulker, 4F, false, 3));
		}
	}

}
