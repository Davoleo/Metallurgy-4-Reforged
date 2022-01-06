/*==============================================================================
 = Class: SanguiniteEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class SanguiniteEffect extends BaseMetallurgyEffect {

	public SanguiniteEffect()
	{
		super(ModMetals.SANGUINITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}

	/**
	 * Removes drops from the players Drop list effectively removing drops
	 *
	 * @param event Called when the player drops inventory on death
	 *
	 * @see AmordrineEffect#removeItemsFromDrops(Metal, EntityPlayer, Iterator)
	 */
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void removeItems(PlayerDropsEvent event)
	{
		Iterator<EntityItem> dropIterator = event.getDrops().iterator();
		AmordrineEffect.removeItemsFromDrops(metal, null, dropIterator);
	}

}
