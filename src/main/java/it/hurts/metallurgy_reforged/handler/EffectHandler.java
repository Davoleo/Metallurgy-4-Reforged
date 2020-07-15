/*
 * -------------------------------------------------------------------------------------------------------
 * Class: EffectHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.handler;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EffectHandler
{

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onPlayerAttack(player, event.getTarget());
        }
    }

    @SubscribeEvent
    public static void onEntityUseItem(LivingEntityUseItemEvent.Start event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onEntityUseItem(event);
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event)
    {
        Entity attacker = event.getSource().getImmediateSource();
        if(attacker instanceof EntityPlayer)
        {
            EntityPlayer killer = (EntityPlayer) attacker;
            for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
            {
                effect.onPlayerKill(killer, event.getEntityLiving());
            }
        }
    }

    @SubscribeEvent
    public static void onEntityKillDrop(LivingDropsEvent event)
    {
        DamageSource source = event.getSource();
        Entity entity = source.getTrueSource();

        //Makes sure the killer is a player and the killed entity is not a player
        if(entity instanceof EntityPlayer && !(event.getEntity() instanceof EntityPlayer))
        {
            EntityPlayer killer = (EntityPlayer) entity;
            for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
            {
                effect.onEntityKillDrop(event.getDrops(), killer);
            }
        }
    }


    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onEntityHurt(event);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START)
        {
            EntityPlayer player = event.player;

            for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
            {
                effect.onPlayerTick(player);
            }

        }
    }

    @SubscribeEvent
    public static void clipPlayer(LivingEvent.LivingUpdateEvent event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if(player.isSneaking() && !player.world.getCollisionBoxes(player, player.getEntityBoundingBox().grow(0.1D, 0, 0.1D)).isEmpty() && EventUtils.isPlayerWearingArmor(player, ModMetals.ETHERIUM))
            {
                player.noClip = true;
                player.motionY = 0D;
            }
        }
    }

    private static final ModelCubeEtherium etheriumSkybox = new ModelCubeEtherium();
    private static final ResourceLocation etheriumSkyboxTexture = new ResourceLocation(Metallurgy.MODID, "textures/effects/etherium_box.png");

    private static int alphaBox = 0;


    //TODO better wall rendering, currently it isn't perfect :/
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void renderEtheriumBox(RenderWorldLastEvent event)
    {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(player == null)
            return;


        if(isHeadInsideBlock(player) && EventUtils.isPlayerWearingArmor(player, ModMetals.ETHERIUM))
        {
            alphaBox = 30;
        }
        else if(alphaBox > 0)
        {
            alphaBox--;
        }

        if(alphaBox > 0)
        {

            float colorShift = (float) (Math.sin(Math.toRadians(GadgetsHandler.ticks + Minecraft.getMinecraft().getRenderPartialTicks()) * 6D) + 1D) * 0.5F;
            float alpha = alphaBox / 30F;
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().getTextureManager().bindTexture(etheriumSkyboxTexture);
            GlStateManager.translate(0F, -15F, 0F);
            GlStateManager.enableBlend();
            float red = (143F + 54F * colorShift) / 255F;
            float green = (14F + 9F * colorShift) / 255F;
            float blue = (48F + 28F * colorShift) / 255F;
            System.out.println(colorShift);
            GlStateManager.color(red,green,blue, alpha);
            etheriumSkybox.render();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }

    }

    private static boolean isHeadInsideBlock(EntityPlayer player)
    {

        for (int i = 0; i < 8; ++i)
        {
            double d0 = player.posX + (double) (((float) ((i) % 2) - 0.5F) * player.width * 0.8F);
            double d1 = player.posY + (double) (((float) ((i >> 1) % 2) - 0.5F) * 0.1F);
            double d2 = player.posZ + (double) (((float) ((i >> 2) % 2) - 0.5F) * player.width * 0.8F);
            BlockPos blockpos = new BlockPos(d0, d1 + (double) player.getEyeHeight(), d2);
            IBlockState iblockstate = player.world.getBlockState(blockpos);

            if(iblockstate.getMaterial().isOpaque())
            {
                return true;
            }
        }
        return false;
    }



    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onEntityRender(RenderLivingEvent.Pre<EntityLivingBase> event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onEntitiesRender(event.getEntity(), event.getRenderer(), event.getPartialRenderTick(), event.getX(), event.getY(), event.getZ());
        }
    }

    @SubscribeEvent
    public static void onEntityEnteringChunk(EntityEvent.EnteringChunk event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onEntityEnteringChunk(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerAttacked(LivingAttackEvent event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onPlayerAttacked(event);
        }
    }

    @SubscribeEvent
    public static void onPlayerFalling(LivingFallEvent event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onPlayerFalling(event);
        }
    }

    @SubscribeEvent
    public static void onPlayerKnockBack(LivingKnockBackEvent event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.onPlayerKnockback(event);
        }
    }

    @SubscribeEvent
    public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            if(event.getHarvester() != null)
            {
                effect.onBlockHarvested(event);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        for (BaseMetallurgyEffect effect : MetallurgyEffects.effects)
        {
            effect.playerBreakSpeed(event);
        }
    }


}
