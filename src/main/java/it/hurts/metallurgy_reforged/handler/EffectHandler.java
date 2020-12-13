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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EffectHandler {


	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public static void livingEvent(LivingEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
			for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
				for (BaseMetallurgyEffect.EventInstance eventInstance : effect.getEvents())
					if (eventInstance.canApply(event) && effect.canBeApplied((EntityPlayer) event.getEntityLiving()))
						eventInstance.getConsumer().accept(event);

	}


}
