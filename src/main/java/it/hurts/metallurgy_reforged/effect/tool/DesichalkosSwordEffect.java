/*==============================================================================
 = Class: DesichalkosSwordEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.Random;

public class DesichalkosSwordEffect extends BaseMetallurgyEffect {

	public DesichalkosSwordEffect()
	{
		super(ModMetals.DESICHALKOS);
	}

	@Override
	public boolean isEnabled()
	{
		return ToolEffectsConfig.desichalkosSwordEffect && super.isEnabled();
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
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if (event instanceof PlayerInteractEvent.RightClickItem)
		{
			EntityPlayer player = event.getEntityPlayer();
			World world = event.getWorld();
			Item sword = player.getHeldItem(event.getHand()).getItem();
			int radius = ToolEffectsConfig.desichalkosSwordBlinkRadius;

			if (sword == metal.getTool(EnumTools.SWORD))
			{
				Vec3d look = player.getLookVec();
				Vec3d scaledLookVec = look.scale(radius);

				Vec3d eyePosition = player.getPositionEyes(1.0F);
				Vec3d targetPos = new Vec3d(eyePosition.x + scaledLookVec.x,
						eyePosition.y + scaledLookVec.y,
						eyePosition.z + scaledLookVec.z);
				RayTraceResult result = world.rayTraceBlocks(eyePosition, targetPos,
						false, true, true);

				if (result != null && result.typeOfHit != RayTraceResult.Type.MISS)
				{
					BlockPos pos = result.getBlockPos();

					switch (result.sideHit)
					{
						case WEST:
							this.teleport(player, pos.getX() - 1, pos.getY(), pos.getZ());
							break;
						case EAST:
							this.teleport(player, pos.getX() + 1, pos.getY(), pos.getZ());
							break;
						case NORTH:
							this.teleport(player, pos.getX(), pos.getY(), pos.getZ() - 1);
							break;
						case SOUTH:
							this.teleport(player, pos.getX(), pos.getY(), pos.getZ() + 1);
							break;
						case UP:
							this.teleport(player, pos.getX(), pos.getY() + 1, pos.getZ());
							break;
						case DOWN:
							this.teleport(player, pos.getX(), pos.getY() - player.height, pos.getZ());
							break;
						default:
							break;
					}
				}
				else
				{
					this.teleport(player, targetPos.x, player.getPosition().getY(), targetPos.z);
				}

				player.swingArm(event.getHand());

				world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1, 1);
				player.getCooldownTracker().setCooldown(sword, 20 * ToolEffectsConfig.desichalkosSwordEffectCooldown);
			}
		}
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
