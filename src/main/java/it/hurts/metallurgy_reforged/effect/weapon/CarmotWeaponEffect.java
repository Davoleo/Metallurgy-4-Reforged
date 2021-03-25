/*==============================================================================
 = Class: CarmotWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CarmotWeaponEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public CarmotWeaponEffect()
    {
        super(ModMetals.CARMOT);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void chainAttack(LivingHurtEvent event)
    {
        Entity tSource = event.getSource().getTrueSource();
        if (tSource instanceof EntityPlayer)
        {
            EntityPlayer attacker = ((EntityPlayer) tSource);
            if (!canBeApplied(attacker))
                return;

            EntityLivingBase attacked = event.getEntityLiving();

            ExtraFilledDataBundle data = attacker.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).carmotWeaponBundle;
            int id = attacked.getEntityId();
            data.setExtra("id", id);
            data.setExtra("amount", event.getAmount());
            data.incrementStep(attacker);
        }
    }

    @Override
    public void onStep(World world, EntityPlayer attacker, int maxSteps, int step)
    {
        ExtraFilledDataBundle data = attacker.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).carmotWeaponBundle;
        int attackedId = data.getExtraInt("id");
        //Should only be an EntityLivingBase anyways
        EntityLivingBase attacked = (EntityLivingBase) world.getEntityByID(attackedId);

        //The entity was already removed from the world for some reason
        if (attacked == null)
            return;

        attacker.world.getEntitiesWithinAABB(attacked.getClass(), new AxisAlignedBB(
                attacked.posX - 2, attacked.posY - 2, attacked.posZ - 2,
                attacked.posX + 2, attacked.posY + 2, attacked.posZ + 2
        )).forEach(entity -> {
            if (entity != attacked)
            {
                entity.attackEntityFrom(DamageSource.GENERIC, data.getExtraInt("amount"));
                for (int i = 0; i < 5; i++)
                    spawnParticle(entity, 1.5F, true, 3);
            }
        });
    }
}
