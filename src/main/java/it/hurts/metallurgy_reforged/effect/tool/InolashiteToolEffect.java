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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
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

        int range = 7;

        Vec3d eyePosition = player.getPositionEyes(1F);

        EnumFacing facePointed = null;
        BlockPos lastPos = null;
        ItemStack tool = player.getHeldItem(hand);

        for (int i = 1; i < range; i++)
        {
            Vec3d scaledLookVec = player.getLookVec().scale(i);
            Vec3d targetPos = eyePosition.add(scaledLookVec);

            RayTraceResult result = world.rayTraceBlocks(eyePosition, targetPos, false, true, false);

            if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                if (facePointed == null)
                    facePointed = result.sideHit;

                if (EventUtils.canHarvest(tool.getItem(), world.getBlockState(result.getBlockPos())))
                {
                    world.destroyBlock(result.getBlockPos(), true);
                    tool.damageItem(1, player);
                }
                else
                {
                    lastPos = result.getBlockPos();
                    facePointed = result.sideHit;
                }

                if (result.sideHit.getAxis().isHorizontal())
                {
                    BlockPos lower = result.getBlockPos().down();
                    if (EventUtils.canHarvest(tool.getItem(), world.getBlockState(lower)))
                    {
                        world.destroyBlock(lower, true);
                        tool.damageItem(1, player);
                    }
                    else
                    {
                        lastPos = lower;
                        facePointed = result.sideHit;
                    }
                }

                if (lastPos != null)
                    break;
            }
        }

        if (lastPos != null)
            this.teleport(player, lastPos.offset(facePointed));
        else
        {
            lastPos = new BlockPos(player.posX + player.getLookVec().x * range, player.posY + player.getLookVec().y * range + 1, player.posZ + player.getLookVec().z * range);
            this.teleport(player, lastPos);
        }

        player.swingArm(hand);

        world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1, 1);
        player.getCooldownTracker().setCooldown(tool.getItem(), 20 * 15);
    }

    private void teleport(EntityPlayer player, Vec3i pos)
    {
        Random random = new Random();

        for (int j = 0; j < 128; ++j)
        {
            double d6 = (double) j / 127.0D;
            float f = (random.nextFloat() - 0.5F) * 0.2F;
            float f1 = (random.nextFloat() - 0.5F) * 0.2F;
            float f2 = (random.nextFloat() - 0.5F) * 0.2F;
            double d3 = player.posX + (pos.getX() - player.posX) * d6 + (random.nextDouble() - 0.5D) * (double) player.width * 2.0D;
            double d4 = player.posY + (pos.getY() - player.posY) * d6 + random.nextDouble() * (double) player.height;
            double d5 = player.posZ + (pos.getZ() - player.posZ) * d6 + (random.nextDouble() - 0.5D) * (double) player.width * 2.0D;
            player.world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, f, f1, f2);
        }
        player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
    }

}
