/*==============================================================================
 = Class: DamascusSteelWeaponEffect
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DamascusSteelWeaponEffect extends BaseMetallurgyEffect {

	public DamascusSteelWeaponEffect()
	{
		super(ModMetals.DAMASCUS_STEEL);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	/**
	 * Mobs drop significantly more experience
	 */
	@SubscribeEvent
	public void onKill(LivingExperienceDropEvent event)
	{
		//Won't change xp drop if a player is killed
		if (event.getEntityLiving() instanceof EntityPlayer)
			return;

		//Only if the required weapons are in hand
		if (canBeApplied(event.getAttackingPlayer()))
		{
			//Set the dropped experience to be either (base * 2 + 2) or (base * 3 + 2)
			event.setDroppedExperience(event.getDroppedExperience() * 3 + 2);

			Utils.repeat(6, () -> spawnParticle(event.getEntityLiving(), 2F, true, 9));
		}
	}

}
