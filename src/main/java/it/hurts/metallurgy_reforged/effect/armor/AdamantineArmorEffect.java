/*==============================================================================
 = Class: AdamantineArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class AdamantineArmorEffect extends BaseMetallurgyEffect {

    private final LivingEventHandler<LivingDeathEvent> ENTITY_HURTS = new LivingEventHandler<>(this::onEntityDeath, LivingDeathEvent.class);

    public AdamantineArmorEffect() {
        super(ModMetals.ADAMANTINE);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ARMOR;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[]{ENTITY_HURTS};
    }

    // TODO: 13/12/2020 add particle effect
    public void onEntityDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (getLevel(entity) < 1)
            return;

        event.setCanceled(true);

        if (!entity.world.isRemote) {
            entity.setItemStackToSlot(EntityEquipmentSlot.values()[2 + Utils.random.nextInt(4)], ItemStack.EMPTY);
            entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1, 1);
            entity.setHealth(2F);
        }

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 2));
    }
}
