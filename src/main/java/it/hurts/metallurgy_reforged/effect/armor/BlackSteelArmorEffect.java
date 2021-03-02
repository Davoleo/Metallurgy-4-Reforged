/*==============================================================================
 = Class: BlackSteelArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class BlackSteelArmorEffect extends BaseMetallurgyEffect {

    public BlackSteelArmorEffect()
    {
        super(ModMetals.BLACK_STEEL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void onEntityHurtEvent(LivingHurtEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        float level = getLevel(entity);
        if (level == 0)
            return;

        int slownessAmp = getPotionAmplifier(entity, MobEffects.SLOWNESS);
        int resistanceAmp = getPotionAmplifier(entity, MobEffects.RESISTANCE);

        if (entity.getRNG().nextBoolean())
        {
            //amp can be -1 to 2
            //you get a new effect amplifier for each armor piece
            //For example if you wear two pieces: level = 2 & effect Amps can increase up to 1
            if (resistanceAmp == slownessAmp && level * 4 > slownessAmp && slownessAmp <= 2)
            {
                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, slownessAmp + 1));
                entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 200, slownessAmp + 1));

                if (!entity.world.isRemote)
                {
                    entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.7F, 0.7F);
                    for (int i = 0; i < 10; i++)
                        spawnParticle(entity, 3F, true, 9);
                }
            }
        }

    }

    /**
     * @param entity The entity this potion effect is applied to
     * @param potion The potion you need to compute the amplifier of
     * @return The amplifier level of the applied potion effect (-1 if the effect is not applied)
     */
    private int getPotionAmplifier(EntityLivingBase entity, Potion potion)
    {
        for (PotionEffect activePotionEffect : entity.getActivePotionEffects())
        {
            if (activePotionEffect.getPotion().equals(potion))
                return activePotionEffect.getAmplifier();
        }
        return -1;
    }
}
