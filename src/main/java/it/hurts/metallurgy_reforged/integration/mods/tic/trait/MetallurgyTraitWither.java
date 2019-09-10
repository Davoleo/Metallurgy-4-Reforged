/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitWither
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitWither extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitWither()
	{
		super("wither_trait", TextFormatting.BLACK);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		if ((int) (Math.random() * 100) <= 20)
			target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 30, 1, false, true));
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}

}
