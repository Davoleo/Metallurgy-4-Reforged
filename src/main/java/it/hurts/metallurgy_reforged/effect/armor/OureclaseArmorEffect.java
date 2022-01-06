/*==============================================================================
 = Class: OureclaseArmorEffect
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
import it.hurts.metallurgy_reforged.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

public class OureclaseArmorEffect extends BaseMetallurgyEffect {

	//3 Blocks radius square BB
	private AxisAlignedBB validEntitiesBox = new AxisAlignedBB(-3, -3, -3, 3, 3, 3);

	public OureclaseArmorEffect()
	{
		super(ModMetals.OURECLASE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void damageAndKnockBack(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase wearer = event.getEntityLiving();
		int armorCount = getLevel(wearer);

		if (armorCount == 0)
			return;

		//Every Half a second if the wearer is sprinting
		if (wearer.ticksExisted % 10 == 0 && wearer.isSprinting())
		{
			//Check if there are any entities except the wearer in the area
			List<Entity> entitiesNearby = wearer.world.getEntitiesWithinAABBExcludingEntity(wearer, validEntitiesBox.offset(wearer.getPosition()));

			entitiesNearby.forEach(entity -> {
				if (entity instanceof EntityLivingBase)
				{
					//0..6 damage amount
					float damage = (armorCount * 2) - 2;
					//Damage entity of 1 heart
					entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, damage);
					//Points to entity (normalized)
					Vec3d knockbackDirection = wearer.getPositionVector().subtract(entity.getPositionVector()).normalize();
					((EntityLivingBase) entity).knockBack(wearer, 0.4F + (0.4F / 3F * (armorCount - 1)), knockbackDirection.x, knockbackDirection.z);
				}
			});

			if (!entitiesNearby.isEmpty())
				wearer.world.playSound(null, wearer.getPosition(), ModSounds.CRUSHER_IMPACT, SoundCategory.PLAYERS, 0.4F, 1);
		}
	}

}
