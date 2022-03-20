/*==============================================================================
 = Class: InolashiteToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.WorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class InolashiteToolEffect extends BaseMetallurgyEffect {

	public InolashiteToolEffect()
	{
		super(ModMetals.INOLASHITE);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.TOOL;
	}

	@Override
	public void rightClickHandler(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull EnumHand hand)
	{
		if (!canBeApplied(player))
			return;

		int range = 6;

		ItemStack tool = player.getHeldItem(hand);

		Vec3d eyePosition = player.getPositionEyes(1F);

		Vec3d scaledLookVec = player.getLookVec().scale(range);
		Vec3d targetPos = eyePosition.add(scaledLookVec);

		Iterator<BlockPos> posIt = WorldUtils.getAllColliding(eyePosition, targetPos).iterator();
		Iterator<BlockPos> downPosIt = Collections.emptyIterator();

		//double angleBetweenYAndLook = Utils.angle(scaledLookVec, new Vec3d(0, 1, 0));
		//Vertical player head rotation between 30° and 150°
		if (player.rotationPitch > -60 && player.rotationPitch < 60)
		{
			downPosIt = WorldUtils.getAllColliding(eyePosition.add(0, -1, 0), targetPos.add(0, -1, 0)).iterator();
		}

		EnumFacing facePointed = EnumFacing.getFacingFromVector((float) scaledLookVec.x, (float) scaledLookVec.y, (float) scaledLookVec.z).getOpposite();

		BlockPos assertivePos = null;

		while (posIt.hasNext() || downPosIt.hasNext())
		{
			if (posIt.hasNext())
			{
				BlockPos pos = posIt.next();
				if (!world.isAirBlock(pos))
				{
					if (EventUtils.canHarvest(tool, world.getBlockState(pos)))
					{
						world.destroyBlock(pos, true);
						tool.damageItem(1, player);
					}
					else
					{
						assertivePos = pos;
						break;
					}
				}
			}

			if (downPosIt.hasNext())
			{
				BlockPos pos = downPosIt.next();
				if (!world.isAirBlock(pos))
				{
					if (EventUtils.canHarvest(tool, world.getBlockState(pos)))
					{
						world.destroyBlock(pos, true);
						tool.damageItem(1, player);
					}
					else
					{
						assertivePos = pos;
						break;
					}
				}
			}
		}

		// TODO: 21/04/2021 maybe implement attemptTeleport here
		if (assertivePos != null)
			teleport(player, assertivePos.getX() + 0.5 + facePointed.getXOffset(), assertivePos.getY() + facePointed.getYOffset(), assertivePos.getZ() + 0.5 + facePointed.getZOffset());
		else
			this.teleport(player, player.posX + player.getLookVec().x * range, player.posY + player.getLookVec().y * range + 1, player.posZ + player.getLookVec().z * range);

		player.swingArm(hand);

		world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1, 1);
		player.getCooldownTracker().setCooldown(tool.getItem(), 20 * 10);
		player.hurtResistantTime = 20;
	}

	private void teleport(EntityPlayer player, double x, double y, double z)
	{
		Random random = new Random();

		for (int j = 0; j < 128; ++j)
		{
			double d6 = (double) j / 127.0D;
			float f = (random.nextFloat() - 0.5F) * 0.2F;
			float f1 = (random.nextFloat() - 0.5F) * 0.2F;
			float f2 = (random.nextFloat() - 0.5F) * 0.2F;
			double d3 = player.posX + (x - player.posX) * d6 + (random.nextDouble() - 0.5D) * (double) player.width * 2.0D;
			double d4 = player.posY + (y - player.posY) * d6 + random.nextDouble() * (double) player.height;
			double d5 = player.posZ + (z - player.posZ) * d6 + (random.nextDouble() - 0.5D) * (double) player.width * 2.0D;
			player.world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, f, f1, f2);
		}
		player.setPositionAndUpdate(x, y, z);
	}

}
