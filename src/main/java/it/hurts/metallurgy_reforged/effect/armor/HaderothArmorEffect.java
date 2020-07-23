/*
 * -------------------------------------------------------------------------------------------------------
 * Class: HaderothArmorEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.config.ArmorEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;

import javax.annotation.Nullable;

public class HaderothArmorEffect extends BaseMetallurgyEffect {

	public HaderothArmorEffect()
	{
		super(ModMetals.HADEROTH);
	}

	@Override
	public boolean isEnabled()
	{
		return ArmorEffectsConfig.haderothArmorEffect;
	}

	@Override
	public boolean isToolEffect()
	{
		return false;
	}

	@Nullable
	@Override
	public EnumTools getToolClass()
	{
		return null;
	}

	@Override
	public void onPlayerCollision(GetCollisionBoxesEvent event)
	{
		World world = event.getWorld();
		EntityPlayer player = ((EntityPlayer) event.getEntity());
		int radius = 6;

		Iterable<BlockPos> positions = BlockPos.getAllInBox(player.getPosition().getX() - radius, player.getPosition().getY() - 2, player.getPosition().getZ() - radius,
				player.getPosition().getX() + radius, player.getPosition().getY() + 2, player.getPosition().getZ() + radius);

		positions.forEach(blockPos -> {
			Block block = world.getBlockState(blockPos).getBlock();
			if (block == Blocks.LAVA)
			{
				event.getCollisionBoxesList().add(Block.FULL_BLOCK_AABB.offset(blockPos));
			}
		});

		if (world.isRemote)
			world.spawnParticle(EnumParticleTypes.FLAME, player.posX - Math.random(), player.posY, player.posZ + Math.random(), 0, -0.1D, 0);
	}

}
