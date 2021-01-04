/*==============================================================================
 = Class: BrassToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class BrassToolEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public BrassToolEffect()
    {
        super(ModMetals.BRASS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent
    public void harvestBlocks(BlockEvent.BreakEvent event)
    {
        if (!canBeApplied(event.getPlayer()))
            return;

        if (!event.getWorld().isRemote)
        {
            EntityPlayer player = event.getPlayer();
            Vec3d look = player.getLookVec();

            ProgressiveDataBundle effectBundle = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).brassToolBundle;
            //Initializes the progressive effect
            effectBundle.setPos(event.getPos());
            effectBundle.setState(event.getState());
            effectBundle.incrementStep();
        }
    }

    @Override
    public void onStep(World world, BlockPos pos, IBlockState state, int maxSteps, int step)
    {
        if (!world.isRemote)
        {
            float pitch = ((8 - step) / 8F);
            world.playSound(null, pos, SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.BLOCKS, 1, pitch);

            //for (int x = -step - 1; x < step + 1; x++)
            //{
            //    for (int y = -step - 1; y < step + 1; y++)
            //    {
            //        for (int z = -step - 1; z < step + 1; z++)
            //        {
            //            BlockPos blockPos = pos.add(x, y, z);
            //            IBlockState blockState = world.getBlockState(blockPos);
            //
            //            if (state == null || pos == null)
            //                return;
            //
            //            if (Math.ceil(blockPos.getDistance(pos.getX(), pos.getY(), pos.getZ())) == step)
            //            {
            //                if (Block.isEqualTo(blockState.getBlock(), state.getBlock()))
            //                    world.destroyBlock(pos, false);
            //            }
            //        }
            //    }
            //}
        }
    }

    @Deprecated
    private Vec3i roundVector(Vec3d vector)
    {

        double absX = Math.abs(vector.x);
        double absY = Math.abs(vector.y);
        double absZ = Math.abs(vector.z);

        if (absX > 0.75)
            return new Vec3i(Math.signum(vector.x), 0, 0);
        else if (absY > 0.75)
            return new Vec3i(0, Math.signum(vector.y), 0);
        else if (absZ > 0.75)
            return new Vec3i(0, 0, Math.signum(vector.z));
        else
        {
            if (absX + absY > 0.75)
                return new Vec3i(Math.signum(vector.x), Math.signum(vector.y), 0);
            else if (absX + absZ > 0.75)
                return new Vec3i(Math.signum(vector.x), 0, Math.signum(vector.z));
            else if (absY + absZ > 0.75)
                return new Vec3i(0, Math.signum(vector.y), Math.signum(vector.z));

            else
            {
                return new Vec3i(Math.signum(vector.x), Math.signum(vector.y), Math.signum(vector.z));
            }
        }
    }
}
