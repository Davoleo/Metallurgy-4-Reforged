/*
 * -------------------------------------------------------------------------------------------------------
 * Class: MetallurgyTraitKingDice
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitKingDice extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitKingDice()
	{
		super("king_dice_trait", TextFormatting.WHITE);
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
	{
		target.addPotionEffect(new PotionEffect(Utils.getRandomEffect(), 80, 0));
	}

	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
	{
		//		TODO Aggiungere la possibilit� di aggiungere al player effetti positivi, o negativi, randomicamente.
		//		Si potrebbe eliminare questo metodo
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}

}
