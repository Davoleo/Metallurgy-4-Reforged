/*==============================================================================
 = Class: InolashiteWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class InolashiteWeaponEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public InolashiteWeaponEffect()
    {
        super(ModMetals.INOLASHITE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void doubleHit(LivingDamageEvent event)
    {
        Entity entity = event.getSource().getImmediateSource();

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer attacker = ((EntityPlayer) entity);
            if (!canBeApplied(attacker))
                return;

            ProgressiveDataBundle bundle = getEffectCapability(attacker).inolashiteWeaponBundle;

            if (!bundle.isEffectInProgress())
            {
                bundle.incrementStep(attacker);
            }
        }
    }

    @Override
    public void onStep(World world, EntityPlayer entity, int maxSteps, int step)
    {
        IAttributeInstance attackSpeed = entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        double originalBaseSpeed = attackSpeed.getBaseValue();

        //Instantly recover from weapon cooldown
        attackSpeed.setBaseValue(1000);
        EntityLivingBase lastAttackedEntity = entity.getLastAttackedEntity();

        if (lastAttackedEntity != null)
        {
            lastAttackedEntity.hurtResistantTime = 0;
            //Attack the last attacked entity with the current item
            entity.attackTargetEntityWithCurrentItem(lastAttackedEntity);
        }

        //Can't put this inside of the if check because apparently lastAttackedEntity's always false on the client
        entity.swingArm(EnumHand.MAIN_HAND);

        //Reset the original base attack speed
        attackSpeed.setBaseValue(originalBaseSpeed);
    }
}
