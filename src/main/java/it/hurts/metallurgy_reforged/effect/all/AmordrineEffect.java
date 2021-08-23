/*==============================================================================
 = Class: AmordrineEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;

public class AmordrineEffect extends BaseMetallurgyEffect {


	public AmordrineEffect()
	{
		super(ModMetals.AMORDRINE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.ALL;
	}


	@SubscribeEvent
	public void bindEquipmentToCorpse(PlayerDropsEvent event)
	{

		if (event.getEntityPlayer().getEntityWorld().getGameRules().getBoolean("keepInventory"))
			return;

		Iterator<EntityItem> dropIterator = event.getDrops().iterator();
		removeItemsFromDrops(metal, event.getEntityPlayer(), dropIterator);
	}

	/**
	 * Removes armor and tools made of a specific metal from a player's drop list
	 *
	 * @param metal    The metal the items should be made of
	 * @param player   null if the items should not be added back to a player's inventory
	 * @param dropIter the iterator of entity drops
	 */
	protected static void removeItemsFromDrops(Metal metal, @Nullable EntityPlayer player, Iterator<EntityItem> dropIter)
	{
		while (dropIter.hasNext())
		{
			EntityItem dropEntity = dropIter.next();
			ItemStack item = dropEntity.getItem();
			if (ItemUtils.isMadeOfMetal(metal, item.getItem()) || TartariteEffect.getParagonMetal(item) == metal)
			{
				if (item.getItem() instanceof ItemArmorBase || item.getItem() instanceof IToolEffect)
				{
					if (player != null)
						player.addItemStackToInventory(item);
					dropIter.remove();
				}
			}
		}
	}

	@SubscribeEvent
	public void copyEquipmentToNewEntity(PlayerEvent.Clone event)
	{
		if (event.isCanceled())
			return;

		if (event.getEntityPlayer().getEntityWorld().getGameRules().getBoolean("keepInventory"))
			return;

		if (event.isWasDeath())
		{
			event.getOriginal().inventory.mainInventory.forEach(stack -> {
				if (ItemUtils.isMadeOfMetal(metal, stack.getItem()) || TartariteEffect.getParagonMetal(stack) == metal)
				{
					event.getEntityPlayer().addItemStackToInventory(stack);
				}
			});
		}
	}

}
