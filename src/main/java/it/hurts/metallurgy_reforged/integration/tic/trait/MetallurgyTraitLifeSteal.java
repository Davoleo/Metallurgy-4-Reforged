/*==============================================================================
 = Class: MetallurgyTraitLifeSteal
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitLifeSteal extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitLifeSteal()
	{
		super("life_steal_trait", TextFormatting.LIGHT_PURPLE);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		if (player instanceof EntityPlayer)
		{
			EntityPlayer pl = (EntityPlayer) player;
			if (pl.getHealth() < pl.getMaxHealth())
			{

				int luck_level = Math.round(pl.getLuck());
				//percentage to get healed based on the luck of the player (example: luck 0 = 20%,luck 1 = 40%...)
				float chance = 0.20F + (luck_level * 0.20F);
				if (Math.random() < chance)
				{
					//the heal Amount ,that is the 40% of the damage
					float healAmount = damageDealt * 0.40F;
					if (pl.getHealth() < pl.getMaxHealth())
						pl.heal(healAmount);
				}
			}
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
