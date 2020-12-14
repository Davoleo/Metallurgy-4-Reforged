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
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketRenderDeathProtection;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
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

    public void onEntityDeath(LivingDeathEvent event) {
        EntityLivingBase entity = event.getEntityLiving();

        if (!entity.world.isRemote && entity instanceof EntityPlayerMP) {
            EntityEquipmentSlot randomArmorSlot = EntityEquipmentSlot.values()[2 + Utils.random.nextInt(4)];

            ItemStack armorPiece = entity.getItemStackFromSlot(randomArmorSlot);
            if (ItemUtils.isMadeOfMetal(metal, armorPiece.getItem())) {
                entity.setItemStackToSlot(randomArmorSlot, ItemStack.EMPTY);
                entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1, 1);
                entity.setHealth(1F);
                event.setCanceled(true);

                PacketRenderDeathProtection packet = new PacketRenderDeathProtection(entity, armorPiece);
                CriteriaTriggers.USED_TOTEM.trigger(((EntityPlayerMP) entity), armorPiece);
                PacketManager.network.sendTo(packet, ((EntityPlayerMP) entity));
                PacketManager.network.sendToAllTracking(packet, entity);
            }
        }

        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 25, 2));
        entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 60, 1));
    }
}
