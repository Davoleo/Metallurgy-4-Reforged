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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

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
    public void onStep(World world, EntityLivingBase entity, int maxSteps, int step)
    {
        System.out.println(step);

        //Timer Expires
        if (step == maxSteps)
        {
            entity.noClip = false;
            entity.getArmorInventoryList().forEach(stack -> ((EntityPlayer) entity).getCooldownTracker().setCooldown(stack.getItem(), 400));
        }
    }

    @SubscribeEvent
    public void livingEvent(LivingEvent.LivingUpdateEvent event)
    {

        if (event.getEntityLiving() instanceof EntityPlayer && !event.getEntityLiving().world.isRemote)
        {
            EntityLivingBase entity = event.getEntityLiving();

            if (!canBeApplied(entity))
                return;

            for (ItemStack piece : entity.getArmorInventoryList())
            {
                if (!piece.isEmpty() && ((EntityPlayer) entity).getCooldownTracker().getCooldown(piece.getItem(), 0) != 0)
                    return;
            }

            ProgressiveDataBundle bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).etheriumArmorBundle;

            // TODO: 20/02/2021 Particles on the wall that you're going through?
            if (entity.isSneaking() && !entity.world.getCollisionBoxes(entity, entity.getEntityBoundingBox().grow(0.1D, 0, 0.1D)).isEmpty())
            {
                bundle.setPaused(false);

                //start the timer
                if (!bundle.isEffectInProgress())
                {
                    bundle.incrementStep();
                }
                entity.noClip = true;
                entity.motionY = 0D;
            }
            else
            {
                bundle.setPaused(true);
            }
        }
    }
}
