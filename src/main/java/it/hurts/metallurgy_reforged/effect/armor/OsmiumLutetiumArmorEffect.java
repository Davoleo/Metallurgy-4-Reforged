/*
 * -------------------------------------------------------------------------------------------------------
 * Class: OsmiumLutetiumArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

import javax.annotation.Nullable;

public class OsmiumLutetiumArmorEffect extends BaseMetallurgyEffect {

	public OsmiumLutetiumArmorEffect(Metal metal)
	{
		super(metal);
	}

	@Override
	public boolean isEnabled()
	{
		if (!super.isEnabled())
			return false;

		if (metal == ModMetals.OSMIUM)
			return ArmorEffectsConfig.osmiumArmorEffect;
		else
			return ArmorEffectsConfig.lutetiumArmorEffect;
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
	public void livingEvent(LivingEvent livingEvent)
	{
		if (livingEvent instanceof LivingKnockBackEvent)
		{

			LivingKnockBackEvent event = ((LivingKnockBackEvent) livingEvent);

			if (event.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();

				int osmiumMultiplier = EventUtils.getArmorPiecesCount(player, ModMetals.OSMIUM.getArmorSet());
				int lutetiumMultiplier = EventUtils.getArmorPiecesCount(player, ModMetals.LUTETIUM.getArmorSet());

				float multiplier;

				multiplier = (float) (((4 - osmiumMultiplier) * 0.17) + ((4 - lutetiumMultiplier) * 0.138));
				event.setStrength(event.getOriginalStrength() * multiplier);
			}
		}
	}

}
