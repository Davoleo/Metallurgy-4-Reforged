/*==============================================================================
 = Class: HaderothArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;

@Deprecated
public class HaderothArmorEffect extends BaseMetallurgyEffect {

	public HaderothArmorEffect() {
        super(ModMetals.HADEROTH);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[0];
    }

    public void onPlayerCollision(GetCollisionBoxesEvent event) {
        World world = event.getWorld();
        EntityPlayer player = ((EntityPlayer) event.getEntity());
        AxisAlignedBB playerBB = event.getAabb();
        if (EventUtils.isEntityWearingArmor(player, metal)) {
            if (world.isMaterialInBB(playerBB.grow(0.1D), Material.LAVA)) {
				BlockPos.PooledMutableBlockPos minPos = BlockPos.PooledMutableBlockPos.retain(playerBB.minX + 0.001D, playerBB.minY + 0.001D, playerBB.minZ + 0.001D);
				BlockPos.PooledMutableBlockPos maxPos = BlockPos.PooledMutableBlockPos.retain(playerBB.maxX - 0.001D, playerBB.maxY - 0.001D, playerBB.maxZ - 0.001D);
				BlockPos.PooledMutableBlockPos pos = BlockPos.PooledMutableBlockPos.retain();

				if (world.isAreaLoaded(minPos, maxPos))
				{
					for (int i = minPos.getX(); i <= maxPos.getX(); ++i)
					{
						for (int j = minPos.getY(); j <= maxPos.getY(); ++j)
						{
							for (int k = minPos.getZ(); k <= maxPos.getZ(); ++k)
							{
								pos.setPos(i, j, k);
								IBlockState state = world.getBlockState(pos);

								if (state.getMaterial() == Material.LAVA)
								{
									AxisAlignedBB lavaBox = Block.FULL_BLOCK_AABB.offset(pos);
									event.getCollisionBoxesList().add(lavaBox);
								}

							}
						}
					}
				}
				if (world.isRemote)
				{
					for (int i = 0; i < 10; i++)
					{
						double particleX = player.posX + Math.random() - 0.5D;
						double particleZ = player.posZ + Math.random() - 0.5D;

						AxisAlignedBB checkBox = new AxisAlignedBB(particleX - 0.05D, player.posY - 0.05D, particleZ - 0.05D, particleX + 0.05D, player.posY + 0.05D, particleZ + 0.05D);
						if (world.isMaterialInBB(checkBox, Material.LAVA))
							world.spawnAlwaysVisibleParticle(EnumParticleTypes.FLAME.getParticleID(), particleX, player.posY, particleZ, 0D, -Math.random() * 0.3D, 0D);
					}
				}
			}
		}
	}

}
