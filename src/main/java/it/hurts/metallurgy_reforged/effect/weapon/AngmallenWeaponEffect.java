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
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class AngmallenWeaponEffect extends BaseMetallurgyEffect {

    public AngmallenWeaponEffect() {
        super(ModMetals.ANGMALLEN);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.WEAPON;
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

    @SubscribeEvent
    public void buffDamage(LivingHurtEvent event) {

        if (!canBeApplied(getEquipUserFromEvent(event)))
            return;

        Spliterator<ItemStack> spliterator = event.getEntityLiving().getArmorInventoryList().spliterator();
        boolean hasArmor = StreamSupport.stream(spliterator, false).anyMatch(stack -> stack.getItem() instanceof ItemArmor);

        //Increase damage by 75% if the enemy has a piece of armor
        if (hasArmor) {
            event.setAmount(event.getAmount() * 1.75F);
            if (!event.getEntity().world.isRemote)
                for (int i = 0; i < 10; i++)
                    spawnParticle(event.getEntity(), 1.5f, 10);
        }
    }
}
