/*==============================================================================
 = Class: KalendriteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class KalendriteArmorEffect extends BaseMetallurgyEffect {

	public KalendriteArmorEffect()
	{
		super(ModMetals.KALENDRITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void registerDamageTimestamp(LivingDamageEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if (!canBeApplied(entity))
			return;


		ItemStack armorStack = getArmorRepr(entity);
		NBTTagCompound armorData = armorStack.getTagCompound();

		if (armorData != null && entity.isPotionActive(MobEffects.REGENERATION))
			entity.removePotionEffect(MobEffects.REGENERATION);

		if (armorData == null)
			armorData = new NBTTagCompound();

		armorData.setLong("damage_timestamp", entity.world.getTotalWorldTime());
		armorStack.setTagCompound(armorData);
	}

	@SubscribeEvent
	public void regenHealth(LivingEvent.LivingUpdateEvent event)
	{
		final EntityLivingBase entity = event.getEntityLiving();
		int level = (int) (getLevel(entity) * 4);
		if (level == 0 || entity.ticksExisted % 4 != 0)
			return;

		NBTTagCompound armorData = getArmorRepr(entity).getTagCompound();

		if (armorData != null && armorData.hasKey("damage_timestamp"))
		{
			long delta = entity.world.getTotalWorldTime() - armorData.getLong("damage_timestamp");
			//2^(5 - 1..4) * 20 = 16..2 seconds * 20 = amount in ticks
			int delay = (int) (Math.pow(2, 5 - level) * 20);
			if (delta >= delay)
			{
				//noinspection ConstantConditions
				if (!entity.isPotionActive(MobEffects.REGENERATION) || entity.getActivePotionEffect(MobEffects.REGENERATION).getDuration() <= 200)
					entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 410, level - 1));
			}
		}
	}

}
