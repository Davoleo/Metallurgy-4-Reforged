/*==============================================================================
 = Class: EffectHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EffectHandler {

	// TODO: 13/12/2020 Find a better way to do this with generics
	@SubscribeEvent
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void livingEvent(LivingEvent event)
	{
		for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
			for (LivingEventHandler livingEventHandler : effect.getEvents())
			{
				if (livingEventHandler.equalsEvent(event) && effect.canBeApplied(effect.getEquipUserFromEvent(event)))
					livingEventHandler.getDelegate().accept(event);
			}
	}

}
