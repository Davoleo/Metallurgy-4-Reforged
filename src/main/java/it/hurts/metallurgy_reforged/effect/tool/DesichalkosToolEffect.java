/*==============================================================================
 = Class: DesichalkosToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DesichalkosToolEffect extends BaseMetallurgyEffect {

	public DesichalkosToolEffect()
	{
		super(ModMetals.DESICHALKOS);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onBlockDrop(BlockEvent.HarvestDropsEvent event)
	{
		if (!canBeApplied(event.getHarvester()))
			return;

		// TODO: 12/02/2021 Maybe implement Reach distance upgrade here

		//Add drops to the inventory and remove them from the list if they cannot be added
		event.getDrops().removeIf(stack -> event.getHarvester().addItemStackToInventory(stack));
	}

}
