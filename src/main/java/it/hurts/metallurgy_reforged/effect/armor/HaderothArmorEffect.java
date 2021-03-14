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
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.Pair;

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

    @Override
    public Pair<String, String> getTooltip()
    {
        Pair<String, String> tooltip = super.getTooltip();
        if (!MetallurgyEffects.haderothEffect.isEnabled())
        {
            int firstBreak = tooltip.getRight().indexOf("\n");
            String trimmed = tooltip.getRight().substring(firstBreak + 1);
            tooltip.setValue(trimmed);
        }

        return tooltip;
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

                //If the main effect is enabled and the armor has not been reborn yet -> terminate adaptability effect
                if (MetallurgyEffects.haderothEffect.isEnabled() && (stack.getTagCompound() == null || !stack.getTagCompound().getBoolean("reborn")))
                    return;

                if (stack.getItem() == metal.getArmorPiece(slot))
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
