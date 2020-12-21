/*==============================================================================
 = Class: AngmallenArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.block.BlockOre;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nonnull;

public class AngmallenArmorEffect extends BaseMetallurgyEffect {

    private final EventHandler<LivingEvent.LivingUpdateEvent> DETECT_NEARBY_ORES = new EventHandler<>(this::detectNearbyOres, LivingEvent.LivingUpdateEvent.class);
    private final int RADIUS = 4;

    public AngmallenArmorEffect() {
        super(ModMetals.ANGMALLEN);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{DETECT_NEARBY_ORES};
    }

    private void detectNearbyOres(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = ((EntityPlayer) event.getEntity());

            if (player.world.isRemote && player.ticksExisted % 20 == 0) {
                if (player.getRNG().nextFloat() < getLevel(player) * 0.4) {
                    BlockPos rareOrePos = getRareOrePos(player.world, player.getPosition());
                    if (rareOrePos != null)
                        player.world.playSound(rareOrePos.getX(), rareOrePos.getY(), rareOrePos.getZ(), SoundEvents.BLOCK_NOTE_BELL, SoundCategory.PLAYERS, 0.75F, 1, true);
                }
            }
        }
    }

    private BlockPos getRareOrePos(World world, BlockPos playerPos) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -RADIUS; x < RADIUS; x++) {
            for (int y = -RADIUS; y < RADIUS; y++) {
                for (int z = -RADIUS; z < RADIUS; z++) {

                    pos.setPos(playerPos.getX() + x, playerPos.getY() + y, playerPos.getZ() + z);
                    IBlockState state = world.getBlockState(pos);
                    if (state.getBlock() instanceof BlockOre && state.getBlock().getHarvestLevel(state) > 3) {
                        return pos;
                    }
                }
            }
        }

        return null;
    }

}
