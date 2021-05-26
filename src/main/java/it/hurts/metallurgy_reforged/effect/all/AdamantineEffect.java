/*==============================================================================
 = Class: AdamantineEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import java.util.List;

public class AdamantineEffect extends BaseMetallurgyEffect {

    public AdamantineEffect() {
        super(ModMetals.ADAMANTINE);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.ALL;
    }

    @SubscribeEvent
    public void onFinishedEating(LivingEntityUseItemEvent.Finish event) {
        if (!canBeApplied(event.getEntityLiving()))
            return;

        Item food = event.getItem().getItem();
        EntityLivingBase entity = event.getEntityLiving();
        if (food instanceof ItemFood) {
            ItemStack[] equipment = EventUtils.getEquipmentList(metal, entity)
                    .stream()
                    .filter(equip -> equip.getItemDamage() > 0)
                    .toArray(ItemStack[]::new);

            ItemStack stack = equipment[Utils.random.nextInt(equipment.length)];

            if (stack.getItemDamage() != 0)
            {
                if (!entity.world.isRemote)
                    stack.setItemDamage(Math.max(stack.getItemDamage() - ((ItemFood) food).getHealAmount(stack) * 2, 0));
                else
                    entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5F, 3F, false);
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(TickEvent.PlayerTickEvent event)
    {
        EntityPlayer player = event.player;
        if (!canBeApplied(player))
            return;

        if (event.side == Side.SERVER)
        {
            float foodLevelPercentage = player.getFoodStats().getFoodLevel() / 20F;

            if (foodLevelPercentage < 0.85)
            {
                int secondsWait = (1 + MathHelper.floor(6 * foodLevelPercentage)) * 20;

                if (player.ticksExisted % secondsWait == 0)
                {
                    List<ItemStack> equipList = EventUtils.getEquipmentList(metal, player);
                    if (equipList.isEmpty())
                        return;

                    ItemStack randomEquip = equipList.get(Utils.random.nextInt(equipList.size()));

                    randomEquip.setItemDamage(randomEquip.getItemDamage() + 2);

                    if (randomEquip.getItemDamage() > randomEquip.getMaxDamage())
                    {
                        player.renderBrokenItemStack(randomEquip);
                        randomEquip.shrink(1);
                    }

                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS, 0.3F, 0.8F);

                    for (int i = 0; i < 6; i++)
                        spawnParticle(player, 1F, true, 1);
                }
            }
        }
    }

}
