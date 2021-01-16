/*==============================================================================
 = Class: CelenegilArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CelenegilArmorEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    private static final int RADIUS = 5;

    public CelenegilArmorEffect()
    {
        super(ModMetals.CELENEGIL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        ExtraFilledDataBundle<NBTTagByte> bundle = entity.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).celenegilArmorBundle;

        if (canBeApplied(entity))
            return;

        NBTTagCompound data = entity.getEntityData();

        int hits = data.getInteger("celenegil_armor_hits");

        if (hits > 5)
        {
            entity.clearActivePotions();
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));

            BlockPos entityPos = entity.getPosition();
            entity.world.getEntitiesWithinAABBExcludingEntity(entity,
                    new AxisAlignedBB(entityPos.getX() - RADIUS, entityPos.getY() - 1, entityPos.getZ() - RADIUS,
                            entityPos.getX() + RADIUS, entityPos.getY() + 1, entityPos.getZ() + RADIUS)
            ).forEach(enemy -> {
                Vec3d motionVec = new Vec3d(enemy.posX - entity.posX, 0.60, enemy.posZ - entity.posZ).normalize();
                enemy.motionX += motionVec.x * 1.5;
                enemy.motionY += motionVec.y;
                enemy.motionZ += motionVec.z * 1.5;
            });

            data.setInteger("celenegil_armor_hits", 0);

            for (int i = 0; i < 60; i++)
                spawnParticle(entity.world, entityPos, 4F, 6, 0.7D - Math.random() * 1.6D, 0.01D, 0.7D - Math.random() * 1.6D);
        }
        else
        {
            data.setInteger("celenegil_armor_hits", hits + 1);
            bundle.setExtra(new NBTTagByte((byte) 1));
        }
    }

    @Override
    public void onStep(World world, EntityPlayer player, int maxSteps, int step)
    {
        ExtraFilledDataBundle<NBTTagByte> bundle = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).celenegilArmorBundle;

        if (step == 1 && bundle.getExtra().getByte() > 0)
        {
            bundle.setExtra(new NBTTagByte((byte) 0));
        }

        if (step == 3 && bundle.getExtra().getByte() == 0)
        {
            player.getEntityData().setInteger("celenegil_armor_hits", 0);
        }
    }
}
