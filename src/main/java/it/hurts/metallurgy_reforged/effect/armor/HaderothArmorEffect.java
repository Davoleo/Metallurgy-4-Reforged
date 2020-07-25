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
import it.hurts.metallurgy_reforged.util.EventUtils;
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
	public void onPlayerTick(EntityPlayer player)
	{
		World world = player.world;

		if (!world.isRemote && EventUtils.isPlayerWearingArmor(player, metal))
		{

			BlockPos lavaPos = new BlockPos(player.posX, player.posY, player.posZ);

			if (world.getBlockState(lavaPos).getBlock() == Blocks.LAVA)
			{
				player.motionY = 0;
				player.velocityChanged = true;
			}
		}
	}

	@Override
	public void onPlayerCollision(GetCollisionBoxesEvent event)
	{
		World world = event.getWorld();
		EntityPlayer player = ((EntityPlayer) event.getEntity());

		if (EventUtils.isPlayerWearingArmor(player, metal))
		{

			BlockPos lavaPos = new BlockPos(player.posX, player.posY, player.posZ);

			if (world.getBlockState(lavaPos).getBlock() == Blocks.LAVA)
			{
				event.getCollisionBoxesList().add(Block.FULL_BLOCK_AABB.offset(lavaPos));

				if (world.isRemote)
					world.spawnParticle(EnumParticleTypes.FLAME, player.posX + Math.random() - 0.5, player.posY, player.posZ + Math.random() - 0.5, 0.3 * (Math.random() - 0.5) * 2, 0.01, 0.3 * (Math.random() - 0.5) * 2);
			}

		}
	}

}
