package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
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
        playerIn.setInvisible(true);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        terminateEffect(entityLiving, stack, maxCooldown - timeLeft);
    }

    @Override
    public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
    {
        if (count <= 1)
            terminateEffect(player, stack, maxCooldown);
    }

    private void terminateEffect(EntityLivingBase player, ItemStack stack, int cooldown)
    {
        ((EntityPlayer) player).getCooldownTracker().setCooldown(stack.getItem(), cooldown);
    }
}
