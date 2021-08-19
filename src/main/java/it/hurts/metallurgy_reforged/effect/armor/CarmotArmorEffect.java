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
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;

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


	@SubscribeEvent
	public void livingEvent(LivingEntityUseItemEvent livingEvent)
	{
		float level = getLevel(livingEvent.getEntityLiving());

		if (level == 0)
			return;

		apply(livingEvent, level);
	}

	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (getLevel(event.getEntityLiving()) == 4)
		{
			if (event.getEntityLiving().ticksExisted % 40 == 0)
			{
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.HASTE, 100, 2));
			}
		}
	}

	private static boolean isItemBlacklisted(Item item)
	{
		String[] blacklist = new String[]{"bountifulbaubles:magicmirror",
		                                  "aoa3:apoco_shower", "aoa3:atomizer", "aoa3:beamer", "aoa3:blast_chiller",
		                                  "aoa3:blood_drainer", "aoa3:bone_blaster", "aoa3:bubble_horn",
		                                  "aoa3:confetti_cannon",
		                                  "aoa3:confetti_cluster", "aoa3:dark_destroyer", "aoa3:darkly_guster",
		                                  "aoa3:death_ray", "aoa3:doom_bringer",
		                                  "aoa3:eradicator", "aoa3:flowercorne", "aoa3:fragment", "aoa3:froster",
		                                  "aoa3:gas_blaster",
		                                  "aoa3:ghoul_gasser", "aoa3:gold_bringer", "aoa3:gravity_blaster",
		                                  "aoa3:hell_horn", "aoa3:illusion_revolver",
		                                  "aoa3:illusion_smg", "aoa3:ion_blaster", "aoa3:iro_miner",
		                                  "aoa3:laser_blaster", "aoa3:light_blaster",
		                                  "aoa3:light_spark", "aoa3:luna_blaster", "aoa3:mecha_blaster",
		                                  "aoa3:mind_blaster", "aoa3:moon_destroyer",
		                                  "aoa3:moon_shiner", "aoa3:odious", "aoa3:orbocron", "aoa3:paralyzer",
		                                  "aoa3:party_popper",
		                                  "aoa3:poison_plunger", "aoa3:power_ray", "aoa3:proton", "aoa3:reefer",
		                                  "aoa3:revolution",
		                                  "aoa3:seaocron", "aoa3:skullo_blaster", "aoa3:soul_drainer",
		                                  "aoa3:soul_spark", "aoa3:soul_storm",
		                                  "aoa3:spirit_shower", "aoa3:swarmotron", "aoa3:toxic_terrorizer",
		                                  "aoa3:vortex_blaster", "aoa3:whimsy_winder",
		                                  "aoa3:withers_wrath"
		};

		ResourceLocation regName = item.getRegistryName();

		if (regName != null)
			return ArrayUtils.contains(blacklist, regName);

		return true;
	}

	public void apply(LivingEntityUseItemEvent event, float level)
	{
		ItemStack stack = event.getItem();
		Item item = stack.getItem();

		if (isItemBlacklisted(item))
			return;

		int duration = event.getDuration();

		if (event instanceof LivingEntityUseItemEvent.Tick && Math.random() < level / 4F)
		{
			event.setDuration(duration - 1);

			EntityLivingBase entity = event.getEntityLiving();
			Vec3d halvedLookVec = entity.getLookVec().scale(0.5);

			if (entity.world.isRemote)
			{
				//Maybe a 2 cycles for?
				for (int i = 0; i < 2; i++)
					spawnParticle(entity.world,
							entity.posX + halvedLookVec.x, entity.posY + 1.1F, entity.posZ + halvedLookVec.z,
							0.4F, true, 5);
			}
		}
	}

}
