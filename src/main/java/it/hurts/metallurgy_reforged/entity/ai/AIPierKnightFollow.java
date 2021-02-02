/*==============================================================================
 = Class: AIPierKnightFollow
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity.ai;

import it.hurts.metallurgy_reforged.entity.EntityPierKnight;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AIPierKnightFollow extends EntityAIBase {

    private final EntityPierKnight follower;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed = 1.1;
    private final PathNavigate pierPathFinder;
    private int timeToRecalcPath;
    float maxDist = 2;
    float minDist = 10;
    private float oldWaterCost;

    public AIPierKnightFollow(EntityPierKnight fellowPier)
    {
        this.follower = fellowPier;
        this.world = fellowPier.world;
        this.pierPathFinder = fellowPier.getNavigator();
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        Entity entity = this.follower.getOwner();

        if (entity == null)
        {
            return false;
        }
        else if (entity instanceof EntityPlayer && ((EntityPlayer) entity).isSpectator())
        {
            return false;
        }
        else if (this.follower.getDistanceSq(entity) < (double) (this.minDist * this.minDist))
        {
            //the mob is near enough that they're not going to walk
            return false;
        }
        else
        {
            this.owner = (EntityLivingBase) entity;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.pierPathFinder.noPath() && this.follower.getDistanceSq(this.owner) > (double) (this.maxDist * this.maxDist);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.follower.getPathPriority(PathNodeType.WATER);
        this.follower.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.owner = null;
        this.pierPathFinder.clearPath();
        this.follower.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        this.follower.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float) this.follower.getVerticalFaceSpeed());

        if (--this.timeToRecalcPath <= 0)
        {
            this.timeToRecalcPath = 10;

            if (!this.pierPathFinder.tryMoveToEntityLiving(this.owner, this.followSpeed))
            {
                if (this.follower.getDistanceSq(this.owner) >= 144.0D)
                {
                    int i = MathHelper.floor(this.owner.posX) - 2;
                    int j = MathHelper.floor(this.owner.posZ) - 2;
                    int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                    for (int l = 0; l <= 4; ++l)
                    {
                        for (int i1 = 0; i1 <= 4; ++i1)
                        {
                            if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.isTeleportFriendlyBlock(i, j, k, l, i1))
                            {
                                this.follower.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.follower.rotationYaw, this.follower.rotationPitch);
                                this.pierPathFinder.clearPath();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean isTeleportFriendlyBlock(int x, int z, int y, int xOffset, int zOffset)
    {
        BlockPos blockpos = new BlockPos(x + xOffset, y - 1, z + zOffset);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(this.follower) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
    }
}
