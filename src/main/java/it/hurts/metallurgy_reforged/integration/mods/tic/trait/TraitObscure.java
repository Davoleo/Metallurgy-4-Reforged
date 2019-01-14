package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 14/01/2019 / 22:50
 * Class: TraitObscure
 * Project: Metallurgy 4 Reforged
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class TraitObscure extends AbstractTrait implements ITrait{

    public TraitObscure()
    {
        super("obscure_trait", TextFormatting.BLACK);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit)
    {
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30, 2));
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective)
    {
        for (int i=0; i<100; ++i)
            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble(), 0, 0.05, 0);
    }

    @Override
    public void register(String name, @Nullable String tooltip)
    {
        Utils.localize(String.format(LOC_Name, name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, tooltip));
    }
}
