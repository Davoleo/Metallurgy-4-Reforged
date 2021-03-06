/*==============================================================================
 = Class: HaderothWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static it.hurts.metallurgy_reforged.Metallurgy.logger;

public class HaderothWeaponEffect extends BaseMetallurgyEffect {

    public HaderothWeaponEffect()
    {
        super(ModMetals.HADEROTH);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void handleKillStreak(LivingDeathEvent event)
    {
        Entity source = event.getSource().getImmediateSource();
        if (source instanceof EntityLivingBase && canBeApplied((EntityLivingBase) source))
        {
            ItemStack toolStack = ((EntityLivingBase) source).getHeldItemMainhand();
            NBTTagCompound compound = toolStack.getTagCompound();
            if (compound == null)
                compound = new NBTTagCompound();

            ResourceLocation targetRegName = getMobType(event.getEntity());
            if (targetRegName != null)
            {
                String killedType = compound.getString("killed_type");
                if (killedType.equals(targetRegName.toString()))
                {
                    compound.setInteger("kill_count", compound.getInteger("kill_count") + 1);
                }
                else
                {
                    compound.setInteger("kill_count", 1);
                    compound.setString("killed_type", targetRegName.toString());
                }
            }

            logger.info("Entity Type: {} | Kill Count: {}", compound.getString("killed_type"), compound.getInteger("kill_count"));
            toolStack.setTagCompound(compound);
        }
    }

    @SubscribeEvent
    public void applyEffectModifier(LivingHurtEvent event)
    {
        Entity source = event.getSource().getImmediateSource();

        if (source instanceof EntityLivingBase && canBeApplied((EntityLivingBase) source))
        {
            NBTTagCompound toolData = ((EntityLivingBase) source).getHeldItemMainhand().getTagCompound();

            if (toolData != null)
            {
                ResourceLocation targetType = getMobType(event.getEntity());
                int killCount = toolData.getInteger("kill_count");
                if (killCount > 0 && targetType != null && toolData.getString("killed_type").equals(targetType.toString()))
                {
                    // TODO: 06/03/2021 Balance: Might be a bit to strong
                    event.setAmount(event.getAmount() * (killCount * 1.25F));
                    Utils.repeat(15, () -> spawnParticle(event.getEntity(), 2F, true, 5));
                }
            }
        }
    }

    /**
     * @return The Registry name of the entity (null if invalid or some other edge case)
     */
    @Nullable
    private ResourceLocation getMobType(Entity entity)
    {
        ResourceLocation targetRegName = null;
        for (EntityEntry entry : ForgeRegistries.ENTITIES)
        {
            if (entry.getEntityClass() == entity.getClass())
                targetRegName = ForgeRegistries.ENTITIES.getKey(entry);
        }

        return targetRegName;
    }
}
