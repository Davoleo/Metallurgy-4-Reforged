/*==============================================================================
 = Class: QuicksilverArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;
import java.util.UUID;

public class QuicksilverArmorEffect extends BaseMetallurgyEffect {

    private static final UUID SPEED_UUID = UUID.fromString("91AEAB56-376B-1298-935B-2F7F68070635");

    public QuicksilverArmorEffect()
    {
        super(ModMetals.QUICKSILVER);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void sprintThrottling(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END || !canBeApplied(event.player))
            return;

        PlayerEffectData capa = event.player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);
        if (capa == null)
            return;

        IAttributeInstance attributeInstance = event.player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED);

        //From 1 to 4
        float level = getLevel(event.player);
        //Max Ticks for the level
        float maxStep = Math.round(Math.max(8 * (1F - level), 0.5F) * 20);

        float armorAmount = level * 4;

        //5/2 * n^2 + 5/2 * n
        float speedMultiplier = (2.5F * armorAmount * armorAmount + 2.5F * armorAmount) * 0.01F;

        double speed = Math.pow(event.player.posX - event.player.prevPosX, 2) + Math.pow(event.player.posZ - event.player.prevPosZ, 2);
        //apa.quicksilverArmorStep max 3 levels
        if (event.player.isSprinting() && speed >= 0.05D)
        {
            if (capa.quicksilverTick <= maxStep)
            {
                capa.quicksilverTick++;

                //this is to make a gradual speed increase while sprinting
                speedMultiplier *= Math.min((float) capa.quicksilverTick / maxStep, 1F);

                AttributeModifier attributemodifier = new AttributeModifier(SPEED_UUID, this.getName(), speedMultiplier, 2);
                attributeInstance.removeModifier(SPEED_UUID);
                attributeInstance.applyModifier(attributemodifier);

            }
            else
                event.player.stepHeight = 1F;
        }
        else if (capa.quicksilverTick > 0)
        {
            capa.quicksilverTick = 0;
            attributeInstance.removeModifier(SPEED_UUID);
            event.player.stepHeight = 0.6F;
        }
    }


    @SubscribeEvent
    public void walkOnLiquid(GetCollisionBoxesEvent event)
    {
        World world = event.getWorld();
        Entity entity = event.getEntity();
        AxisAlignedBB playerBB = event.getAabb();
        if (!(entity instanceof EntityPlayer))
            return;

        float level = getLevel((EntityLivingBase) entity);
        if (level == 0.0F)
            return;

        PlayerEffectData capa = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);
        if (capa == null)
            return;

        //Max Ticks for the level
        float maxStep = Math.round(Math.max(8 * (1F - level), 0.5F) * 20);

        if (capa.quicksilverTick < maxStep)
            return;

        float armorAmount = level * 4;


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

                        boolean canWalkOnLava = state.getMaterial() == Material.LAVA && armorAmount > 2;

                        if (state.getMaterial() == Material.WATER || canWalkOnLava)
                        {
                            AxisAlignedBB fluidBox = Block.FULL_BLOCK_AABB.offset(pos);
                            event.getCollisionBoxesList().add(fluidBox);
                        }

                    }
                }
            }
        }

    }
}
