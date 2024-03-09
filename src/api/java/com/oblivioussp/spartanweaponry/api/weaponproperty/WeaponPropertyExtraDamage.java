package com.oblivioussp.spartanweaponry.api.weaponproperty;

import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class WeaponPropertyExtraDamage extends WeaponPropertyWithCallback {
    public WeaponPropertyExtraDamage(String propType, String propModId, float propMagnitude) {
        super(propType, propModId, propMagnitude);
    }

    @Override
    public PropertyQuality getQuality() {
        return type == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_THROWN ? PropertyQuality.NEUTRAL : PropertyQuality.POSITIVE;
    }

    @Override
    protected void addTooltipDescription(ItemStack stack, List<String> tooltip) {
        //! REMOVED config check because internal calls
        if (false && (type == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_CHEST ||
                type == WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED))
            tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc.armor_points", "tooltip", modId, MathHelper.floor((magnitude - 1.0f) * 100.0f), 0F));
        else
            tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "  " + SpartanWeaponryAPI.internalHandler.translateFormattedString(type + ".desc", "tooltip", modId, MathHelper.floor((magnitude - 1.0f) * 100.0f)));
    }

    @Override
    public float modifyDamageDealt(ToolMaterialEx material, float baseDamage, float initialDamage, DamageSource source, EntityLivingBase attacker, EntityLivingBase victim) {
        if (attacker == null || victim == null)
            return baseDamage;
        float bonusDamage = (this.getMagnitude() - 1.0f) * Math.min(baseDamage, initialDamage);

        switch (type) {
            case (WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_CHEST):
                if (victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
                    //! Removed Config check and max value because internal calls
                    return !false || victim.getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue() <= 0F
                            ? baseDamage + bonusDamage : baseDamage;
            case (WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNARMOURED): {
                if (victim.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() && victim.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() &&
                        victim.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() && victim.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty())
                    //! Removed Config check and max value because internal calls
                    return !false || victim.getEntityAttribute(SharedMonsterAttributes.ARMOR).getBaseValue() <= 0F
                            ? baseDamage + bonusDamage : baseDamage;
                return baseDamage;
            }
            case (WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_RIDING): {
                return attacker.isRiding() ? baseDamage + bonusDamage : baseDamage;
            }
            case (WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_UNDEAD):
                return victim.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? baseDamage + bonusDamage : baseDamage;
            case (WeaponProperties.PROPERTY_TYPE_EXTRA_DAMAGE_BACKSTAB): {
                Entity immediateEntity = source.getImmediateSource();
                float yaw = source.isProjectile() ? -immediateEntity.rotationYaw : immediateEntity.rotationYaw;
                float victimYaw = victim.rotationYaw;
                float difference = victimYaw - yaw;
                difference = posMod(difference + 180.0f, 360.0f) - 180.0f;
                boolean doBonusDamage = MathHelper.abs(difference) <= 30.0f;
                return doBonusDamage ? baseDamage + bonusDamage : baseDamage;
            }
            default:
                return baseDamage;
        }
    }

    /**
     * Positive modulo for angle calculations
     *
     * @param num
     * @param den
     * @return
     */
    private float posMod(float num, float den) {
        return (num % den + den) % den;
    }
}
