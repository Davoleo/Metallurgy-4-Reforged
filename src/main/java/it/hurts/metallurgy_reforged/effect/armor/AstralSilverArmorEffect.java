/*==============================================================================
 = Class: AstralSilverArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.EventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nonnull;

public class AstralSilverArmorEffect extends BaseMetallurgyEffect {

    private final EventHandler<LivingEvent.LivingUpdateEvent> HANDLE_STARLIGHT = new EventHandler<>(this::handleStarlight, LivingEvent.LivingUpdateEvent.class);

    public AstralSilverArmorEffect() {
        super(ModMetals.ANGMALLEN);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public EventHandler<? extends LivingEvent>[] getLivingEvents() {
        return new EventHandler[]{HANDLE_STARLIGHT};
    }

    private void handleStarlight(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        boolean meetsConditions = entity.world.canSeeSky(new BlockPos(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ))
                && (entity.dimension == 1 || !entity.world.isDaytime());

        if (entity.ticksExisted % 80 == 0 && meetsConditions) {
            entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, false, false));

            int level = (int) (getLevel(entity) * 4F);
            if (level > 1) {
                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, level - 2, false, false));
            }
        }
    }
}
