/*==============================================================================
 = Class: CarmotWeaponEffect
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
import it.hurts.metallurgy_reforged.render.font.FontColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CarmotWeaponEffect extends BaseMetallurgyEffect {

    // FIXME: 05/01/2021 remove this
    @Deprecated
    private boolean overclocked = false;

    public CarmotWeaponEffect()
    {
        super(ModMetals.CARMOT);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void onItemUse(LivingEntityUseItemEvent.Finish event)
    {
        EntityLivingBase entity = event.getEntityLiving();

        if (!canBeApplied(entity))
            return;

        NBTTagCompound compound = event.getItem().getTagCompound();
        if (compound == null)
            compound = new NBTTagCompound();

        compound.setBoolean("overclocked", !compound.getBoolean("overclocked"));
        overclocked = !overclocked;
        entity.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1F, 0.75F, false);
    }

    @SubscribeEvent
    public void onPlayerAttack(LivingHurtEvent event)
    {
        if (overclocked)
            event.setAmount(event.getAmount() * 1.25F);
    }

    @SubscribeEvent
    public void renderTooltip(RenderTooltipEvent.Pre event)
    {
        event.getLines().add("\nOverclocked: " + FontColor.encodeColor(metal.getStats().getColorHex()) + overclocked);
    }

    @Override
    public EntityLivingBase getEquipUserFromEvent(Event event)
    {
        if (event instanceof LivingEntityUseItemEvent)
            return ((LivingEntityUseItemEvent) event).getEntityLiving();
        else if (event instanceof LivingHurtEvent)
        {
            Entity entity = ((LivingHurtEvent) event).getSource().getTrueSource();
            if (entity instanceof EntityLivingBase)
                return ((EntityLivingBase) entity);
        }

        return null;
    }
}
