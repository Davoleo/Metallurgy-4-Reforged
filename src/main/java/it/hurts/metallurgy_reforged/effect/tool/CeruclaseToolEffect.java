/*==============================================================================
 = Class: CeruclaseToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.advancement.CommonCriterionInstances;
import it.hurts.metallurgy_reforged.advancement.ModAdvancements;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CeruclaseToolEffect extends BaseMetallurgyEffect {

	public CeruclaseToolEffect()
	{
		super(ModMetals.CERUCLASE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@SubscribeEvent
	public void instaMine(PlayerEvent.BreakSpeed event)
	{
		if (!canBeApplied(event.getEntityPlayer()))
			return;

		boolean canHarvest = event.getState().getBlock().canHarvestBlock(event.getEntityPlayer().world, event.getPos(), event.getEntityPlayer());
		if (!canHarvest)
			return;

		Item tool = event.getEntityPlayer().getHeldItemMainhand().getItem();
		String blockToolClass = event.getState().getBlock().getHarvestTool(event.getState());

		if (blockToolClass != null && tool.getRegistryName().getPath().contains(blockToolClass))
		{
			//check passed because axe is contained in pickaxe
			if (blockToolClass.equals("axe") && tool.getRegistryName().getPath().contains("pickaxe"))
				return;

			event.setNewSpeed(100);
			if (event.getEntityPlayer() instanceof EntityPlayerMP)
			{
				ModAdvancements.Triggers.LOW_TEMPERATURES.trigger(
						(EntityPlayerMP) event.getEntityPlayer(),
						new CommonCriterionInstances.AlwaysTrue(ModAdvancements.Triggers.LOW_TEMPERATURES.getId())
				);
			}
		}
	}

}
