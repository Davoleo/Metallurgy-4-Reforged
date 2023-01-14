/*==============================================================================
 = Class: CarmotArmorEffect
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
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CarmotArmorEffect extends BaseMetallurgyEffect {

	public CarmotArmorEffect()
	{
		super(ModMetals.CARMOT);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}


	@SubscribeEvent()
	public void livingEvent(LivingEntityUseItemEvent livingEvent)
	{
		int level = getLevel(livingEvent.getEntityLiving());

		if (level == 0)
			return;

		apply(livingEvent, level);
	}

	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (getLevel(event.getEntityLiving()) >= 4)
		{
			if (event.getEntityLiving().ticksExisted % 40 == 0)
			{
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.HASTE, 100, 2));
			}
		}
	}

	public void apply(LivingEntityUseItemEvent event, int level) {
		Item item = event.getItem().getItem();

		if (event instanceof LivingEntityUseItemEvent.Tick) {
			EntityLivingBase entity = event.getEntityLiving();

			//System.out.println(event.getDuration() % 4);
			if ((event.getDuration() + 1) % 4 < level) {
				item.onUsingTick(event.getItem(), entity, event.getDuration());
				event.setDuration(event.getDuration() - 1);
			}


			Vec3d halvedLookVec = entity.getLookVec().scale(0.5);

			if (entity.world.isRemote) {
				//Maybe a 2-cycles for?
				for (int i = 0; i < 2; i++)
					spawnParticle(entity.world,
							entity.posX + halvedLookVec.x, entity.posY + 1.1F, entity.posZ + halvedLookVec.z,
							0.4F, true, 5);
			}
		}
	}

}
