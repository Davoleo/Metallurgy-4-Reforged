/*==============================================================================
 = Class: ItemBuckler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class ItemBuckler extends ItemShieldBase {

    protected int cooldown;

    public ItemBuckler(String name, int durability, int cooldown)
    {
        super(name, durability);
        this.cooldown = cooldown;
    }

    @Override
    public abstract int getMaxItemUseDuration(@Nonnull ItemStack stack);

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase player, int count)
    {
        if (count <= 1)
            setOnCooldown(player);
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft)
    {
        setOnCooldown(entityLiving);
    }

    /**
     * Makes the shield go on cooldown for a standard amount of time
     */
    public void setOnCooldown(EntityLivingBase player) {
        if (player instanceof EntityPlayer)
            ((EntityPlayer) player).getCooldownTracker().setCooldown(this, cooldown);
    }

}
