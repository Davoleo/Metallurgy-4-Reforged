/*==============================================================================
 = Class: IgnatiusPickaxeEffect
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;

public class IgnatiusPickaxeEffect extends BaseMetallurgyEffect {

	public IgnatiusPickaxeEffect()
	{
		super(ModMetals.IGNATIUS);
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.ignatiusPickaxeEffect;
	}

	@Override
	public boolean isToolEffect()
	{
		return super.isEnabled();
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return EnumTools.PICKAXE;
	}

	@Override
	public void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
	{
		Item heldItem = event.getHarvester().getHeldItemMainhand().getItem();

		if (heldItem.equals(metal.getTool(EnumTools.PICKAXE)))
		{
			for (ItemStack drop : event.getDrops())
			{
				ResourceLocation regName = drop.getItem().getRegistryName();
				System.out.println(regName);

				if (regName != null && regName.getPath().contains("_ore"))
				{
					String nuggetReg = regName.getPath().replace("_ore", "_nugget");
					Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(regName.getNamespace(), nuggetReg));
					if (nugget != null && Utils.random.nextBoolean())
					{
						event.getDrops().add(new ItemStack(nugget, Utils.random.nextInt(3)));
					}
				}
			}
		}
	}

}
