/*==============================================================================
 = Class: HaderothArmorEffect
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class HaderothArmorEffect extends BaseMetallurgyEffect {

    public HaderothArmorEffect()
    {
        super(ModMetals.HADEROTH);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void buffWearer(LivingEvent.LivingUpdateEvent event)
    {

        EntityLivingBase entity = event.getEntityLiving();

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
        {
            if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
            {
                final ItemStack stack = entity.getItemStackFromSlot(slot);
                if (stack.getItem() == metal.getArmorPiece(slot) && stack.getTagCompound() != null && stack.getTagCompound().hasKey("reborn"))
                {
                    switch (slot)
                    {
                        case HEAD:
                            if (entity.isPotionActive(MobEffects.HUNGER))
                                entity.removePotionEffect(MobEffects.HUNGER);
                            break;
                        case CHEST:
                            if (entity.ticksExisted % 40 == 0)
                                entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 60, 0, false, false));
                            break;
                        case LEGS:
                            if (entity.isPotionActive(MobEffects.SLOWNESS))
                                entity.removePotionEffect(MobEffects.SLOWNESS);
                            break;
                        case FEET:
                            if (entity.isPotionActive(MobEffects.LEVITATION))
                                entity.removePotionEffect(MobEffects.LEVITATION);
                            break;
                    }
                }
            }
        }
    }
}
