/*==============================================================================
 = Class: DamascusSteelArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.entity.EntityPierKnight;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class DamascusSteelArmorEffect extends BaseMetallurgyEffect {

    public DamascusSteelArmorEffect()
    {
        super(ModMetals.DAMASCUS_STEEL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void spawnPier(LivingDamageEvent event)
    {
        float level = getLevel(event.getEntityLiving());

        if (level == 0)
            return;

        //Get a random slot from the Armor slots (the "2 +" skips main hand and off hand slots)
        EntityEquipmentSlot randomArmorSlot = EntityEquipmentSlot.values()[2 + Utils.random.nextInt(4)];
        EntityLivingBase entity = event.getEntityLiving();

        ItemStack armorPiece = entity.getItemStackFromSlot(randomArmorSlot);

        if (entity instanceof EntityPlayerMP)
        {
            // if any of the piece is on cooldown the effect is cancelled
            if (((EntityPlayerMP) entity).getCooldownTracker().getCooldown(armorPiece.getItem(), 0) != 0)
                return;
        }

        if (!entity.world.isRemote && event.getSource().getTrueSource() instanceof EntityLivingBase && !entity.getEntityData().getBoolean("has_pier"))
        {

            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
            EntityPierKnight pierknight = new EntityPierKnight(entity.world, entity, attacker, (byte) (4 * level));
            pierknight.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
            entity.world.spawnEntity(pierknight);
            entity.getEntityData().setBoolean("has_pier", true);
        }
    }
}
