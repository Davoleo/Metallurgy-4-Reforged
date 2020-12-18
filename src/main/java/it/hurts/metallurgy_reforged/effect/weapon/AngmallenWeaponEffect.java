/*==============================================================================
 = Class: AngmallenWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.LivingEventHandler;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class AngmallenWeaponEffect extends BaseMetallurgyEffect {

    private final LivingEventHandler<LivingHurtEvent> BUFF_DAMAGE = new LivingEventHandler<>(this::buffDamage, LivingHurtEvent.class);

    public AngmallenWeaponEffect() {
        super(ModMetals.ANGMALLEN);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.WEAPON;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[]{BUFF_DAMAGE};
    }

    @Override
    public EntityLivingBase getEquipUserFromEvent(Event event) {
        if (event instanceof LivingHurtEvent) {
            LivingHurtEvent hurtEvent = (LivingHurtEvent) event;
            Entity attacker = hurtEvent.getSource().getImmediateSource();
            if (attacker instanceof EntityLivingBase) {

                return (EntityLivingBase) attacker;
            }
        }
        return super.getEquipUserFromEvent(event);
    }

    private void buffDamage(LivingHurtEvent event) {

        Spliterator<ItemStack> spliterator = event.getEntityLiving().getArmorInventoryList().spliterator();
        boolean hasArmor = StreamSupport.stream(spliterator, false).anyMatch(stack -> stack.getItem() instanceof ItemArmor);

        //Increase damage by 75% if the enemy has a piece of armor
        if (hasArmor)
            event.setAmount(event.getAmount() * 1.75F);
    }
}
