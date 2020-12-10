/*==============================================================================
 = Class: EximiteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

import javax.annotation.Nullable;

public class EximiteArmorEffect extends BaseMetallurgyEffect {

	public EximiteArmorEffect()
	{
		super(ModMetals.EXIMITE);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.eximiteArmorEffect && super.isEnabled();
	}

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void livingEvent(LivingEvent event) {
		if (event instanceof LivingSetAttackTargetEvent) {
			if (event.getEntityLiving() instanceof EntityEnderman) {
				EntityLivingBase target = ((EntityEnderman) event.getEntityLiving()).getAttackTarget();
				if (target instanceof EntityPlayer) {
					if (EventUtils.isEntityWearingArmor(target, metal)) {
						((EntityEnderman) event.getEntityLiving()).setAttackTarget(null);
					}
				}
			}
		}
	}
}
