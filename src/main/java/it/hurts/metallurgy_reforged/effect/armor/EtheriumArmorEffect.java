/*==============================================================================
 = Class: EtheriumArmorEffect
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;

public class EtheriumArmorEffect extends BaseMetallurgyEffect {

	public EtheriumArmorEffect()
	{
		super(ModMetals.ETHERIUM);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.etheriumArmorEffect && super.isEnabled();
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
	public void livingEvent(LivingEvent event)
	{
		if (event instanceof LivingEvent.LivingUpdateEvent)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				if (player.isSneaking() && !player.world.getCollisionBoxes(player, player.getEntityBoundingBox().grow(0.1D, 0, 0.1D)).isEmpty() && EventUtils.isEntityWearingArmor(player, metal))
				{
					player.noClip = true;
					player.motionY = 0D;
				}
			}
		}
	}

}
