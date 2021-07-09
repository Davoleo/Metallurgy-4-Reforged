package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class OrichalcumArmorEffect extends BaseMetallurgyEffect {

    public OrichalcumArmorEffect()
    {
        super(ModMetals.ORICHALCUM);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.ARMOR;
    }

    @SubscribeEvent
    public void onEntityKillEvent(LivingDeathEvent event)
    {
        Entity entity = event.getSource().getTrueSource();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase attacker = (EntityLivingBase) entity;

            float level = getLevel(attacker);
            if (level == 0)
                return;

            if (event.getEntityLiving() instanceof EntityLiving)
            {
                //strength can be given only if the bearer kills an hostile mob.
                if (((EntityLiving) event.getEntityLiving()).getAttackTarget() == attacker)
                {
                    int armorCount = (int) (level * 4);

                    //min radius 5 max 14, with a 3 increase per amplification level
                    int radius = 2 + armorCount * 3;

                    AxisAlignedBB searchBox = new AxisAlignedBB(attacker.getPosition()).grow(radius);

                    //total mobs in the radius (ignore the attacker and
                    int totalMobs = entity.world.getEntitiesWithinAABB(EntityLiving.class, searchBox, mob -> mob.isEntityAlive() && mob != attacker && mob.getAttackTarget() == attacker).size();

                    if (totalMobs > 0)
                    {
                        //capped at 6( five because amplifiers start at 0)
                        int amplifier = Math.min(totalMobs - 1, 5);
                        //potion time from min 2 to max 8 seconds.
                        int buffTime = armorCount * 2 * 20;
                        attacker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, buffTime, amplifier));

                    }
                }
            }


        }

    }
}
