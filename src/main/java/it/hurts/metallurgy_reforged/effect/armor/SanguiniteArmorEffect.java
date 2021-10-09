/*==============================================================================
 = Class: SanguiniteArmorEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSanguiniteEntityState;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SanguiniteArmorEffect extends BaseMetallurgyEffect {

    private final Method getExperiencePoints;
    private final Method dropLoot;

    public SanguiniteArmorEffect()
    {
        super(ModMetals.SANGUINITE);

        getExperiencePoints = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_70693_a", int.class, EntityPlayer.class);
        dropLoot = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_184610_a", void.class, boolean.class, int.class, DamageSource.class);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    /**
     * Drops entity items and xp
     *
     * @param killedEntity the entity that is dropping
     * @param killer       the entity that caused the drop
     * @param source       the source through which the {@code killer} caused the drop
     * @throws InvocationTargetException if the methods to drop cannot be invoked
     * @throws IllegalAccessException    if the methods to drop cannot be accessed
     */
    private void dropXPAndItems(EntityLivingBase killedEntity, EntityLivingBase killer, DamageSource source) throws InvocationTargetException, IllegalAccessException
    {
        //Get XP to Drop
        int xp = (int) getExperiencePoints.invoke(killedEntity, killer);

        //Drop XP
        while (xp > 0)
        {
            int xpSlice = EntityXPOrb.getXPSplit(xp);
            xp -= xpSlice;
            killer.world.spawnEntity(new EntityXPOrb(killer.world, killedEntity.posX, killedEntity.posY, killedEntity.posZ, xpSlice));
        }
    }

    @SubscribeEvent
    public void dropCorpseLoot(LivingDeathEvent event)
    {

        if (event.getEntityLiving().getEntityData().getInteger("corpse_state") == 2)
        {
            if (event.getEntityLiving().world.getGameRules().getBoolean("doMobLoot"))
            {
                int looting = ForgeHooks.getLootingLevel(event.getEntityLiving(), event.getSource().getTrueSource(), event.getSource());
                try
                {
                    dropLoot.invoke(event.getEntityLiving(), true, looting, event.getSource());
                }
                catch (IllegalAccessException | InvocationTargetException e)
                {
                    Metallurgy.logger.error("Error while dropping corpse Drops (Necromastery Effect Error)");
                    e.printStackTrace();
                }
            }
        }
    }

    @SubscribeEvent
    public void updateCorpseStates(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();

        int corpseState = entity.getEntityData().getInteger("corpse_state");

        if (corpseState == 2)
            return;


        if (corpseState == 0)
        {
            if (entity.deathTime == 16)
            {

                if (entity.getAttackingEntity() instanceof EntityPlayer)
                {

                    int level = getLevel(entity.getAttackingEntity());
                    if (level == 0)
                        return;

                    entity.getEntityData().setInteger("corpse_state", 1);
                    PacketSanguiniteEntityState packet = new PacketSanguiniteEntityState(entity.getEntityId(), 1);
                    PacketManager.network.sendToAllTracking(packet, entity);
                    entity.ticksExisted = 0;


                    try
                    {
                        dropXPAndItems(entity, entity.getAttackingEntity(), entity.getLastDamageSource());
                    }
                    catch (InvocationTargetException | IllegalAccessException e)
                    {
                        Metallurgy.logger.error("Error while dropping pre-corpse Experience (Necromastery Effect Error)");
                        e.printStackTrace();
                    }
                }
            }
        }
        else if (entity.ticksExisted < 20)
        {
            entity.deathTime = 18;
        }
        else if (entity.deathTime >= 0)
        {
            entity.deathTime -= 2;
        }
        else
        {
            entity.setHealth(entity.getMaxHealth() * 0.4F);
            entity.getEntityData().setInteger("corpse_state", 2);
            entity.deathTime = 0;
        }
    }

    private static final float[] OVERLAY = new float[]{ 100 / 255F, 25 / 255F, 25 / 255F };

    @SideOnly(Side.CLIENT)
    public static void renderLivingCorpse(EntityLivingBase entity, double x, double y, double z)
    {
        int corpseState = entity.getEntityData().getInteger("corpse_state");

        if (corpseState < 1)
            return;

        GlStateManager.pushMatrix();
        //COLORED CORPSE
        GlStateManager.color(OVERLAY[0], OVERLAY[1], OVERLAY[2], 1F);

        //SHAKING CORPSE
        double deltaX = Utils.random.nextGaussian() * 0.01D;
        double deltaZ = Utils.random.nextGaussian() * 0.01D;
        GlStateManager.translate(x + deltaX, y, z + deltaZ);
        GlStateManager.popMatrix();
    }

}
