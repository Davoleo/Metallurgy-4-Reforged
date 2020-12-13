/*==============================================================================
 = Class: AdamantineEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class AdamantineEffect extends BaseMetallurgyEffect {

    public static final String NAME = "Symbiosis I";

    private final LivingEventHandler<LivingEntityUseItemEvent.Finish> REPAIR_EQUIP =
            new LivingEventHandler<>(this::onFinishedEating, LivingEntityUseItemEvent.Finish.class);

    public AdamantineEffect() {
        super(ModMetals.ADAMANTINE);
    }

    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ALL;
    }

    @Override
    public LivingEventHandler<? extends LivingEvent>[] getEvents() {
        return new LivingEventHandler[]{REPAIR_EQUIP};
    }

    private void onFinishedEating(LivingEntityUseItemEvent.Finish event) {

        Item food = event.getItem().getItem();
        EntityLivingBase entity = event.getEntityLiving();
        if (food instanceof ItemFood) {
            ItemStack[] equipment = EventUtils.getEquipmentList(metal, entity)
                    .stream()
                    .filter(equip -> equip.getItemDamage() > 0)
                    .toArray(ItemStack[]::new);

            ItemStack stack = equipment[Utils.random.nextInt(equipment.length)];

            if (stack.getItemDamage() != 0) {

                if (!entity.world.isRemote)
                    stack.setItemDamage(Math.max(stack.getItemDamage() - ((ItemFood) food).getHealAmount(stack), 0));
                else
                    entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5F, 3F, false);
            }
        }
    }

}
