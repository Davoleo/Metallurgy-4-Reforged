package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class ItemTartariteShield extends ItemShieldBase {

    public ItemTartariteShield()
    {
        super("tartarite_shield", 1000);
    }

    @Override
    public int getItemEnchantability()
    {
        return 25;
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack)
    {
        return 600;
    }

    @Override
    public void onDamageBlocked(EntityLivingBase entity, DamageSource damageSource)
    {
        if (damageSource.getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase target = ((EntityLivingBase) damageSource.getTrueSource());
            target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 10, 1));
        }
    }
}
