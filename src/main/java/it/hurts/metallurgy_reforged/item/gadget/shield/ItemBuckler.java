package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class ItemBuckler extends ItemShieldBase {

    protected int maxCooldown;

    public ItemBuckler(String name, int durability, int cooldown) {
        super(name, durability);
        this.maxCooldown = cooldown;
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return maxCooldown;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        setOnCooldown(entityLiving,  maxCooldown - timeLeft);
    }

    @Override
    public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
    {
        if (count <= 1)
            setOnCooldown(player);
    }

    @Override
    public void onDamageBlocked(EntityLivingBase entity, DamageSource damageSource) {
        setOnCooldown(entity);
    }

    /**
     * Makes the shield go on cooldown for a custom amount of time
     * @param cooldown amount of time the shield should be on cooldown for
     */
    public void setOnCooldown(EntityLivingBase player, int cooldown)
    {
        if (player instanceof EntityPlayer)
            ((EntityPlayer) player).getCooldownTracker().setCooldown(this, cooldown);
    }

    /**
     * Makes the shield go on cooldown for the standard amount of time
     */
    public void setOnCooldown(EntityLivingBase player)
    {
        this.setOnCooldown(player, maxCooldown);
    }
}
