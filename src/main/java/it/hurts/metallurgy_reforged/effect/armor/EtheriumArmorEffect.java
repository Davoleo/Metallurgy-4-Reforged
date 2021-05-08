/*==============================================================================
 = Class: EtheriumArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class EtheriumArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public EtheriumArmorEffect()
    {
        super(ModMetals.ETHERIUM);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public void onStep(World world, EntityPlayer entity, ItemStack effectStack, int maxSteps, int step)
    {
        //System.out.println(step);

        //Timer Expires
        if (step == maxSteps)
        {
            entity.noClip = false;
            entity.getArmorInventoryList().forEach(stack -> entity.getCooldownTracker().setCooldown(stack.getItem(), 400));
        }
    }

    @SubscribeEvent
    public void livingEvent(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer entity = (EntityPlayer) event.getEntityLiving();

            if (!canBeApplied(entity))
                return;

            for (ItemStack piece : entity.getArmorInventoryList())
            {
                if (!piece.isEmpty() && entity.getCooldownTracker().getCooldown(piece.getItem(), 0) != 0)
                    return;
            }

            ProgressiveDataBundle bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).etheriumArmorBundle;

            final List<AxisAlignedBB> collisions = entity.world.getCollisionBoxes(entity, entity.getEntityBoundingBox().grow(0.1D, 0, 0.1D));
            if (entity.isSneaking() && !collisions.isEmpty())
            {
                //Resume the timer
                if (!bundle.isEffectInProgress())
                    bundle.setPaused(false, entity);

                //If the timer is still stopped:
                //Kick-start the timer
                if (!bundle.isEffectInProgress())
                    bundle.incrementStep(entity);

                entity.noClip = true;
                entity.motionY = 0D;

                collisions.forEach(aabb -> {
                    BlockPos pos = new BlockPos(aabb.minX, aabb.minY, aabb.minZ);
                    Utils.repeat(5,
                            () -> spawnParticle(entity.world, pos, 0.75F, false, 0, 0, 0, 0)
                    );
                });
            }
            else
            {
                bundle.setPaused(true, entity);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void setupFogWhileInsideBlocks(EntityViewRenderEvent.RenderFogEvent event)
    {
        Entity entity = event.getEntity();

        if (!entity.world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty())
        {
            net.minecraft.client.renderer.GlStateManager.setFog(net.minecraft.client.renderer.GlStateManager.FogMode.EXP2);
            net.minecraft.client.renderer.GlStateManager.setFogDensity(0.025F);
            //This doesn't work kek
            //float[] colorComps = metal.getStats().getColorRGBValues();
            //net.minecraft.client.renderer.GlStateManager.color(colorComps[0], colorComps[1], colorComps[2]);
        }
    }
}
