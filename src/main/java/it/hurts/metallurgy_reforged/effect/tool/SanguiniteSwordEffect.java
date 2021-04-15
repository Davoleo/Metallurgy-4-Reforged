/*==============================================================================
 = Class: SanguiniteSwordEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import scala.util.Random;

import javax.annotation.Nullable;

public class SanguiniteSwordEffect extends BaseMetallurgyEffect {

	public SanguiniteSwordEffect()
	{
		super(ModMetals.SANGUINITE);
	}

	@Override
	public boolean isEnabled()
	{
		return super.isEnabled();
	}

	@Override
	public boolean isToolEffect()
	{
		return true;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return EnumTools.SWORD;
	}

	@Override
	public void livingEvent(LivingEvent livingEvent)
	{
		if (livingEvent instanceof LivingHurtEvent)
		{

			LivingHurtEvent event = ((LivingHurtEvent) livingEvent);

			//the entity that damaged the event entity
			Entity source = event.getSource().getImmediateSource();
			if (source instanceof EntityPlayer)
			{
				//the player that damaged the event entity
				EntityPlayer player = (EntityPlayer) source;

				if (player.getHeldItemMainhand().getItem() == metal.getTool(EnumTools.SWORD))
				{
					{
						//check if the player is missing hearts.
						if (player.getHealth() < player.getMaxHealth())
						{

							int luck_level = Math.round(player.getLuck());
							//percentage to get healed based on the luck of the player (example: luck 0 = 15%,luck 1 = 20%...)
							int percentage = 15 + (luck_level * 5);
							if (new Random().nextInt(100) < percentage)
							{
								//the heal Amount ,that is the 10% of the damage
								float healAmount = event.getAmount() * 0.15F;
								if (player.getHealth() + healAmount >= player.getMaxHealth())
									healAmount = 0;
								//set the player health
								player.setHealth(player.getHealth() + healAmount);
							}
						}
					}
				}
			}
		}
	}

}
