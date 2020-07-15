/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitDuplication
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitDuplication extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitDuplication()
	{
		super("duplication_trait", TextFormatting.YELLOW);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		if ((!(target instanceof EntityPlayer)) && (target.getHealth() <= 0.0F && (int) (Math.random() * 100) < 50))
			for (EntityItem item : target.capturedDrops)
			{
				EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
				item.world.spawnEntity(clone);
			}
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}

}
