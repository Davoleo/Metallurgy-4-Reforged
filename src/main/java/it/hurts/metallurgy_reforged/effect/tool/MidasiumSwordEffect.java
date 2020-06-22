/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MidasiumSwordEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MidasiumSwordEffect extends BaseMetallurgyEffect {

	public MidasiumSwordEffect()
	{
		super(ModMetals.MIDASIUM);
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public boolean isToolEffect()
	{
		return true;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return EnumTools.SWORD;
	}

	@Override
	public void onEntityKillDrop(List<EntityItem> drops, EntityPlayer killer)
	{
		if (killer.getHeldItemMainhand().getItem() == ModMetals.MIDASIUM.getTool(EnumTools.SWORD))
		{
			List<EntityItem> dupeDrops = new ArrayList<>();

			if (!killer.isCreative())
			{
				if ((int) (Math.random() * 100) <= 50)
				{
					for (EntityItem item : drops)
					{
						EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
						dupeDrops.add(clone);
					}
					drops.addAll(dupeDrops);
				}
			}
			else
			{
				for (EntityItem item : drops)
				{
					EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
					dupeDrops.add(clone);
				}
				drops.addAll(dupeDrops);
			}
		}
	}

}
