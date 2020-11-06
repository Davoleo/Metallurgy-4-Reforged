package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

public class ItemVulcaniteBuckler extends ItemBuckler {

    public ItemVulcaniteBuckler() {
        super("vulcanite_buckler", 300, 30);
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 40;
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public void onDamageBlocked(EntityLivingBase entity, DamageSource damageSource) {
        if (damageSource.getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase target = ((EntityLivingBase) damageSource.getTrueSource());
            target.world.createExplosion(entity, target.posX, target.posY + target.height / 3, target.posZ, 2F, false);
        }
    }
}
