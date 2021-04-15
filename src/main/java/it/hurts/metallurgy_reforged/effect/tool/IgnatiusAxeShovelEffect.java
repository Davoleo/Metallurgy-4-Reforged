/*==============================================================================
 = Class: IgnatiusAxeShovelEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;

import javax.annotation.Nullable;

public abstract class IgnatiusAxeShovelEffect extends BaseMetallurgyEffect {

	public IgnatiusAxeShovelEffect()
	{
		super(ModMetals.IGNATIUS);
	}

	@Override
	public boolean isEnabled()
	{
		if (!super.isEnabled())
			return false;

		return getToolClass() == EnumTools.AXE ? ToolEffectsConfig.ignatiusAxeEffect : ToolEffectsConfig.ignatiusShovelEffect;
	}

	@Override
	public boolean isToolEffect()
	{
		return true;
	}

	@Nullable
	@Override
	public abstract EnumTools getToolClass();

	@Override
	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{
		Item tool = event.getHarvester().getHeldItemMainhand().getItem();

		if (tool.equals(metal.getTool(EnumTools.AXE)) || tool.equals(metal.getTool(EnumTools.SHOVEL)))
		{
			dropSmeltedItems(event);
		}
	}

	private void dropSmeltedItems(BlockEvent.HarvestDropsEvent event)
	{
		for (ItemStack drop : event.getDrops())
		{
			ItemStack smeltedItem = FurnaceRecipes.instance().getSmeltingResult(drop);
			// TODO: 15/01/2021 Investigate (for some reason smeltedItem is doubled)
			smeltedItem.setCount(drop.getCount());

			if (!smeltedItem.isEmpty())
			{
				switch (event.getFortuneLevel())
				{
					//50% chance
					case 0:
						if (Utils.random.nextBoolean())
						{
							event.getDrops().clear();
							event.getDrops().add(smeltedItem);
						}
						break;

					//60% chance
					case 1:
						if (Utils.random.nextInt(3) > 0)
						{
							event.getDrops().clear();
							event.getDrops().add(smeltedItem);
						}
						break;

					//90% chance
					case 2:
						if (Utils.random.nextInt(10) > 0)
						{
							event.getDrops().clear();
							event.getDrops().add(smeltedItem);
						}
						break;

					//100% chance
					case 3:
						event.getDrops().clear();
						event.getDrops().add(smeltedItem);
						break;

					default:
						break;
				}
			}
		}
	}

}
