/*==============================================================================
 = Class: OrichalcumEffect
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
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class OrichalcumEffect extends BaseMetallurgyEffect {

    public OrichalcumEffect()
    {
        super(ModMetals.ORICHALCUM);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ALL;
    }

    @SubscribeEvent
    public void killDurabilityRegen(LivingDeathEvent event)
    {
        Entity sourceEnt = event.getSource().getImmediateSource();
        if (sourceEnt instanceof EntityLivingBase)
        {
            if (!canBeApplied(((EntityLivingBase) sourceEnt)))
                return;

            sourceEnt.getEquipmentAndArmor().forEach(equip -> {
                if (ItemUtils.isMadeOfMetal(metal, equip.getItem()))
                {
                    int newDurability = equip.getItemDamage() - (int) event.getEntityLiving().getMaxHealth();
                    equip.setItemDamage(newDurability);

                    Vec3d halvedLookVec = sourceEnt.getLookVec().scale(0.5);
                    //Maybe a 2 cycles for?
                    Utils.repeat(2, () -> spawnParticle(sourceEnt.world,
                            sourceEnt.posX + halvedLookVec.x, sourceEnt.posY + 1.1F, sourceEnt.posZ + halvedLookVec.z, 0.4F, true, 5));
                }
            });
        }
    }

}
