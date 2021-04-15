/*==============================================================================
 = Class: AdamantineArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;

import javax.annotation.Nullable;
import java.util.Random;

public class AdamantineArmorEffect extends BaseMetallurgyEffect {

	public AdamantineArmorEffect()
	{
		super(ModMetals.ADAMANTINE);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.adamantineArmorEffect && super.isEnabled();
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
	public void onPlayerTick(EntityPlayer player)
	{
		if (!player.world.isRemote && EventUtils.isEntityWearingArmor(player, metal))
		{
			FoodStats foodStat = player.getFoodStats();
			int amount = 2;
			//quantity experience to remove
			float removeTot = (float) amount / (float) player.xpBarCap();
			//check if the player needs food, if he has enough experience and if the tick is a multiple of 20 (which means that the effect will be applied every second)
			if (player instanceof EntityPlayerMP && player.canEat(false) && (player.experience >= removeTot || player.experienceLevel > 0) && player.ticksExisted % 20 == 0)
			{
				EntityPlayerMP mp = (EntityPlayerMP) player;
				Random rand = new Random();
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
				//play generic eat sound
				mp.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, mp.posX, mp.posY + mp.getEyeHeight(), mp.posZ, 0.3F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F));

			}
		}
	}

}
