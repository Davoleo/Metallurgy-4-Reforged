/*==============================================================================
 = Class: KrikToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class KrikToolEffect extends BaseMetallurgyEffect {

    public KrikToolEffect()
    {
        super(ModMetals.KRIK);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @Override
    public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack tool = playerIn.getHeldItem(handIn);

        //If the tool is on cooldown cancel the effect
        if (playerIn.getCooldownTracker().getCooldown(tool.getItem(), 0) > 0)
            return;

        Vec3d vec = playerIn.getLookVec().scale(2.5);
        playerIn.motionX += vec.x;
        playerIn.motionY += vec.y;
        playerIn.motionZ += vec.z;
        playerIn.velocityChanged = true;

        worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS, 1.25F, 0.65F);
        playerIn.getCooldownTracker().setCooldown(tool.getItem(), 30);
    }
}
