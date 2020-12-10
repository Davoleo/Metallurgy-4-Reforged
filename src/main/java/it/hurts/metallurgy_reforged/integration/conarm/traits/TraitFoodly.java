/*==============================================================================
 = Class: TraitFoodly
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyConArmorStats;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class TraitFoodly extends AbstractArmorTrait implements IConarmMetallurgyTrait {

	public TraitFoodly()
	{
		super("foodly", TextFormatting.DARK_RED);
	}

	@SubscribeEvent
	public void onArmorTick(PlayerTickEvent event)
	{
		if (MetallurgyConArmorStats.hasValidArmorTrait(event.player, "foodly"))
		{
			FoodStats foodStat = event.player.getFoodStats();
			int amount = 4;
			//quantity experience to remove
			float removeTot = (float) amount / (float) event.player.xpBarCap();
			//check if the player needs food ,if he has enough experience and if the tick is a multiple of 20 (which means that the effect will be applied every second)
			if (event.player instanceof EntityPlayerMP && event.player.canEat(false) &&
					(event.player.experience >= removeTot || event.player.experienceLevel > 0) &&
					event.player.ticksExisted % 20 == 0)
			{
				EntityPlayerMP mp = (EntityPlayerMP) event.player;
				mp.experience -= removeTot;

				if (mp.experienceTotal - amount >= 0)
					mp.experienceTotal -= amount;

				if (mp.experience < 0.0F)
				{
					mp.experience = 1F - mp.experience;
					mp.addExperienceLevel(-1);
				}

				//add Food Level
				foodStat.addStats(1, 0.5F);
				//update experience count on the client side
				mp.connection.sendPacket(new SPacketSetExperience(mp.experience, mp.experienceTotal, mp.experienceLevel));
				//play generic eat sound (pitched by Davoleo :DDD)
				mp.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, mp.posX, mp.posY + mp.getEyeHeight(), mp.posZ, 0.3F, 1.5F));

			}
		}
	}


}
