/*
 * -------------------------------------------------------------------------------------------------------
 * Class: DeepIronArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.container.slot.ArmorCustomSlot;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;
import java.util.List;

public class DeepIronArmorEffect extends BaseMetallurgyEffect {

	public DeepIronArmorEffect()
	{
		super(ModMetals.DEEP_IRON);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.deepIronArmorEffect && super.isEnabled();
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
		if (EventUtils.isPlayerWearingArmor(player, metal) && player.isInWater())
		{

			//Slot index of Armor : 5 - 6 - 7 - 8
			for (int i = 5; i < 9; i++)
			{
				if (!(player.inventoryContainer.inventorySlots.get(i) instanceof ArmorCustomSlot) && !player.isCreative())
				{
					//					Inseriamo nello slot dell'inventario in posizione i un custom slot
					player.inventoryContainer.inventorySlots.set(i, new ArmorCustomSlot(player, i - 5, true));
				}

			}
			//Add effect to Player
			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 3, false, false));
			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, 1, false, false));

			player.addTag("deep_iron_effect");

			//Checks if the playerayer is tourching ground
			if (player.onGround)
			{
				player.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(8);
			}
			else
			{
				//Stop player motion
				player.motionX *= 0.5D;
				player.motionZ *= 0.5D;
			}

			//The player can no longer swim upwards
			player.motionY = -0.6D;

			//When the player is in the water he can step one block height like a horse
			if (player.stepHeight != 1.0F)
				player.stepHeight = 1.0F;
			//turns the stepHeight to normal if the playerayer isn't wearing the deep iron armor or if he is not in water
		}
		else
		{
			if (player.getTags().contains("deep_iron_effect"))
			{
				player.removeTag("deep_iron_effect");
				if (player.stepHeight != 0.6F)
					player.stepHeight = 0.6F;

				if (player.inventoryContainer.inventorySlots.get(5) instanceof ArmorCustomSlot)
				{
					//Insert in c the container "vanilla"
					ContainerPlayer c = new ContainerPlayer(player.inventory, !player.world.isRemote, player);
					List<Slot> slots = c.inventorySlots;
					for (int i = 5; i < 9; i++)
					{
						player.inventoryContainer.inventorySlots.set(i, slots.get(i));
					}
				}

				if (player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= (11 * 20))
					player.removePotionEffect(MobEffects.NIGHT_VISION);
				if (player.getActivePotionEffect(MobEffects.WATER_BREATHING).getDuration() <= (11 * 20))
					player.removePotionEffect(MobEffects.WATER_BREATHING);
				player.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(EntityLivingBase.SWIM_SPEED.getDefaultValue());
			}
		}
	}

}
