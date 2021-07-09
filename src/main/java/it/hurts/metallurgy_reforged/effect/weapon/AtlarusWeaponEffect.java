/*==============================================================================
 = Class: AtlarusWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class AtlarusWeaponEffect extends BaseMetallurgyEffect {

	private static final int RANGE = 5;

	public AtlarusWeaponEffect()
	{
		super(ModMetals.ATLARUS);
	}

	@Nonnull
	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.RightClickItem event)
	{
		World world = event.getWorld();
		EntityPlayer player = event.getEntityPlayer();

		if (!canBeApplied(player))
			return;

		AxisAlignedBB box = new AxisAlignedBB(player.posX, player.posY, player.posZ, player.posX, player.posY, player.posZ).grow(RANGE);


		player.swingArm(event.getHand());

		//enemies to affect
		for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, box))
		{
			Vec3d motionVector = new Vec3d(entity.posX - player.posX, 0.6D, entity.posZ - player.posZ).normalize();
			if (!world.isRemote)
			{
				entity.motionX += motionVector.x * 1.5D;
				entity.motionY += motionVector.y;
				entity.motionZ += motionVector.z * 1.5D;
				entity.velocityChanged = true;
			}
			else
			{
				for (int i = 0; i < 4; i++)
				{
					double particleX = entity.posX + (Math.random() - 0.5D) * entity.width;
					double particleY = entity.posY + Math.random() * entity.height;
					double particleZ = entity.posZ + (Math.random() - 0.5D) * entity.width;
					world.spawnParticle(EnumParticleTypes.CLOUD, particleX, particleY, particleZ, motionVector.x, 0, motionVector.z);
				}
			}
		}

		BlockPos playerPos = player.getPosition();
		BlockPos.getAllInBox(playerPos.add(-RANGE, -RANGE, -RANGE), playerPos.add(RANGE, RANGE, RANGE)).forEach(pos -> {
			IBlockState state = world.getBlockState(pos);
			if ((state.getBlock() instanceof BlockLeaves || state.getBlock() instanceof BlockVine) && !world.isRemote)
				world.destroyBlock(pos, true);
		});

		if (!world.isRemote)
			world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.AMBIENT, 1F, 0.75F);

		for (int i = 0; i < 60; i++)
			world.spawnParticle(EnumParticleTypes.CLOUD, player.posX, player.posY + 0.4D, player.posZ, 0.7D - Math.random() * 1.4D, 0.1D, 0.7D - Math.random() * 1.4D);

		player.getCooldownTracker().setCooldown(event.getItemStack().getItem(), 20 * 2);
	}

}
