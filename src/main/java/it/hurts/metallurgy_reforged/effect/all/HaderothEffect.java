/*==============================================================================
 = Class: HaderothEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.all;

import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.NBTUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class HaderothEffect extends BaseMetallurgyEffect {

    private final Supplier<AttributeModifier> ARMOR_MODIFIER_SUPPLIER = () -> new AttributeModifier("Haderoth Armor Buff", 2.5F, 0);

    private final NBTTagCompound rebornCompound = new NBTTagCompound();

    public HaderothEffect()
    {
        super(ModMetals.HADEROTH);
        rebornCompound.setBoolean("reborn", true);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ALL;
    }

    @Override
    public float getLevel(EntityLivingBase entity)
    {
        int armor = EventUtils.getArmorPiecesCount(entity, metal);
        int hand = ItemUtils.isMadeOfMetal(metal, entity.getHeldItemMainhand().getItem()) ? 5 : -1;
        return Math.max(armor / 4F, hand);
    }

    @SubscribeEvent
    public void applyToolMetamorphosis(BlockEvent.BreakEvent event)
    {
        //if (getLevel(event.getPlayer()) < 5)
        //    return;

        //TODO Implement
    }

    @SubscribeEvent
    public void applyArmorMetamorphosis(LivingHurtEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        float level = getLevel(entity);
        if (level == 0)
            return;

        //The damage applied is computed by dividing the pure damage amount by 4
        float damage = event.getAmount() / 4.0F;
        if (damage < 1.0F)
            damage = 1F;

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values())
        {

            if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
            {
                ItemStack stack = entity.getItemStackFromSlot(slot);

                //If the item was already reborn
                if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("reborn"))
                    continue;

                //Check if the item would break | This doesn't take Unbreakable into account
                if (stack.getItem() instanceof ItemArmor && stack.getItemDamage() + damage >= stack.getMaxDamage())
                {
                    //Copy the old itemstack
                    ItemStack newPiece = stack.copy();

                    //Increase the maxdamage by 200
                    newPiece.getItem().setMaxDamage(stack.getMaxDamage() + 200);

                    //Apply armor buff I guess
                    Multimap<String, AttributeModifier> modifiers = newPiece.getItem().getAttributeModifiers(slot, newPiece);
                    modifiers.put(SharedMonsterAttributes.ARMOR.getName(), ARMOR_MODIFIER_SUPPLIER.get());
                    modifiers.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), ARMOR_MODIFIER_SUPPLIER.get());

                    //and set the current stack damage to 0
                    newPiece.setItemDamage(0);

                    if (newPiece.getTagCompound() == null)
                        newPiece.setTagCompound(rebornCompound);
                    else
                        NBTUtils.injectCompound("", newPiece.getTagCompound(), rebornCompound);

                    //Set the new stack to the slot of the entity
                    entity.setItemStackToSlot(slot, stack);

                    //Play item resurrection sounds
                    entity.world.playSound(null, entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 0.75F, 1);
                    entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 0.5F, 1.25F);
                }
            }
        }
    }
}
