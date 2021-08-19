/*==============================================================================
 = Class: ShadowIronArmorEffect
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
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.stream.StreamSupport;

public class ShadowIronArmorEffect extends BaseMetallurgyEffect {

	public ShadowIronArmorEffect()
	{
		super(ModMetals.SHADOW_IRON);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void evadeDamage(LivingHurtEvent event)
	{
		final EntityLivingBase entity = event.getEntityLiving();
		int level = getLevel(entity);
		if (level == 0)
			return;

		if (Math.random() < level / 10F)
		{
			//noinspection OptionalGetWithoutIsPresent
			ItemStack pieceToDamage = StreamSupport.stream(entity.getArmorInventoryList().spliterator(), true)
					.filter(stack -> ItemUtils.isMadeOfMetal(metal, stack.getItem()))
					.findAny().get();

			pieceToDamage.damageItem(Math.round(event.getAmount()), entity);
			event.setCanceled(true);

			Vec3d halvedLookVec = entity.getLookVec().scale(0.5);
			entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1F, 1F);
			for (int i = 0; i < 10; i++)
				spawnParticle(entity.world,
						entity.posX + halvedLookVec.x, entity.posY + 1.1F, entity.posZ + halvedLookVec.z,
						1.25F, true, 2);
		}
	}

}
