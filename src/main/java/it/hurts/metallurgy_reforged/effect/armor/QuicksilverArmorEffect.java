/*==============================================================================
 = Class: QuicksilverArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

public class QuicksilverArmorEffect extends BaseMetallurgyEffect {

	public QuicksilverArmorEffect()
	{
		super(ModMetals.QUICKSILVER);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ARMOR;
	}

	@SubscribeEvent
	public void sprintThrottling(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.END || !canBeApplied(event.player))
			return;

		if (event.player.ticksExisted % 10 != 0)
			return;

		PlayerEffectData capa = event.player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);
		if (capa == null)
			return;

		//Max Seconds for the level
		int maxStep = (int) (Math.max(8 * (1 - getLevel(event.player)), 0.5F));

		if (capa.quicksilverArmorStep < maxStep && event.player.isSprinting())
		{
			//From 1 to 4
			float level = getLevel(event.player) * 4;
			//5/2 * n^2 + 5/2 * n
			final float speedMultiplier = 1 + (2.5F * level * level + 2.5F * level) * 0.01F;

			//Lerp between 1 and speedMultiplier depending on the step
			double stepMultiplier = MathHelper.clampedLerp(1, speedMultiplier, capa.quicksilverArmorStep / (float) maxStep);
			Metallurgy.logger.info("total:" + speedMultiplier + "Lerped: " + stepMultiplier);

			event.player.motionX *= stepMultiplier;
			event.player.motionZ *= stepMultiplier;
			//Metallurgy.logger.info("motion_x: " + entity.motionX + "| motion_z: " + entity.motionZ);
			capa.quicksilverArmorStep++;
		}
		else if (!event.player.isSprinting() && capa.quicksilverArmorStep > 0)
		{
			capa.quicksilverArmorStep = 0;
			for (ItemArmorBase armor : metal.getArmorSet())
				event.player.getCooldownTracker().setCooldown(armor, 30);
		}
		else
		{
			System.out.println("Top Speed! nothing to do?");
		}
	}

}
