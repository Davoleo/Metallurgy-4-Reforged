/*==============================================================================
 = Class: AmordrineEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.handler.LivingEventHandler;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ListIterator;

public class AmordrineEffect extends BaseMetallurgyEffect {

    private final LivingEventHandler<PlayerDropsEvent> BIND_ITEMS_TO_CORPSE = new LivingEventHandler<>(this::bindEquipmentToCorpse, PlayerDropsEvent.class);
    private final LivingEventHandler<PlayerEvent.Clone> COPY_TO_NEW_ENTITY = new LivingEventHandler<>(this::copyEquipmentToNewEntity, PlayerEvent.Clone.class);

    public AmordrineEffect() {
        super(ModMetals.AMORDRINE);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ALL;
    }

    @Override
    public EntityLivingBase getEquipUserFromEvent(Event event) {
        if (event instanceof PlayerDropsEvent) {
            return ((PlayerDropsEvent) event).getEntityPlayer();
        }

        return super.getEquipUserFromEvent(event);
    }

    @Override
    public boolean canBeApplied(EntityLivingBase entity) {
        return true;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[] {BIND_ITEMS_TO_CORPSE, COPY_TO_NEW_ENTITY};
    }

    private void bindEquipmentToCorpse(PlayerDropsEvent event) {

        if(event.getEntityPlayer().getEntityWorld().getGameRules().getBoolean("keepInventory"))
            return;

        EntityPlayer player = (EntityPlayer) getEquipUserFromEvent(event);

        ListIterator<EntityItem> dropIterator = event.getDrops().listIterator();

        while (dropIterator.hasNext()) {
            EntityItem dropEntity = dropIterator.next();
            ItemStack item = dropEntity.getItem();
            if (ItemUtils.isMadeOfMetal(metal, item.getItem())) {
                if (item.getItem() instanceof ItemArmorBase || item.getItem() instanceof IToolEffect) {
                    player.addItemStackToInventory(item);
                    dropIterator.remove();
                }
            }
        }
    }

    private void copyEquipmentToNewEntity(PlayerEvent.Clone event) {
        if (event.isCanceled())
            return;

        if(event.getEntityPlayer().getEntityWorld().getGameRules().getBoolean("keepInventory"))
            return;

        if (event.isWasDeath()) {
            event.getOriginal().inventory.mainInventory.forEach(stack -> {
                if (ItemUtils.isMadeOfMetal(metal, stack.getItem())) {
                    event.getEntityPlayer().addItemStackToInventory(stack);
                }
            });
        }
    }
}
