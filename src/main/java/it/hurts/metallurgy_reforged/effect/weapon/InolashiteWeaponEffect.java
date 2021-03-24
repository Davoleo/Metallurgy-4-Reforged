/*==============================================================================
 = Class: InolashiteWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

public class InolashiteWeaponEffect extends BaseMetallurgyEffect {

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
    public void doubleHit(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer attacker = event.player;
        if (!canBeApplied(attacker) || event.phase != TickEvent.Phase.START)
            return;

        if (attacker.getCooledAttackStrength(0) > 0.6)
        {
            EntityLivingBase lastAttackedEntity = attacker.getLastAttackedEntity();
            if (lastAttackedEntity != null)
            {

                IAttributeInstance attackSpeed = attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
                double originalBaseSpeed = attackSpeed.getBaseValue();

                //Instantly recover from weapon cooldown
                attackSpeed.setBaseValue(1000);

                lastAttackedEntity.hurtResistantTime = 0;
                //Attack the last attacked entity with the current item
                attacker.attackTargetEntityWithCurrentItem(lastAttackedEntity);
                attacker.setLastAttackedEntity(null);

                //Reset the original base attack speed
                attackSpeed.setBaseValue(originalBaseSpeed);

                if (!attacker.world.isRemote)
                {
                    //Reset and restart swing animation
                    attacker.isSwingInProgress = false;
                    attacker.swingProgress = 0;
                    attacker.swingProgressInt = 0;
                    attacker.swingArm(EnumHand.MAIN_HAND);
                }
            }
        }
    }
}
