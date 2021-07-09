/*==============================================================================
 = Class: DeepIronArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DeepIronArmorEffect extends BaseMetallurgyEffect {

	public DeepIronArmorEffect()
	{
		super(ModMetals.DEEP_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void underwaterBoost(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		float level = getLevel(entity);

		if (level > 0 && entity.isInWater())
		{
			if (entity.ticksExisted % 30 == 0)
			{
				int amplifier = (int) ((level * 4) - 1);
				entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 230, 0, false, false));

				if (level >= 0.5)
					entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230, amplifier - 2, false, false));
			}

			entity.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(1 + (level * 4));
			if (entity.motionY < 0)
			{
				entity.motionY -= 0.1 * level;
			}
		}
		else
		{
			entity.getEntityAttribute(EntityLivingBase.SWIM_SPEED).setBaseValue(EntityLivingBase.SWIM_SPEED.getDefaultValue());
		}
	}

}
