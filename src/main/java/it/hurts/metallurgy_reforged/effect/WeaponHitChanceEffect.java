/*==============================================================================
 = Class: WeaponHitChanceEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import it.hurts.metallurgy_reforged.material.Metal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;
@Deprecated
public class WeaponHitChanceEffect extends BaseMetallurgyEffect {

	protected Random random = new Random();
	protected int chance;
	protected Supplier<PotionEffect> effect;

	public WeaponHitChanceEffect(Metal metal, int chance, Supplier<PotionEffect> effect) {
		super(metal);
        this.chance = chance;
        this.effect = effect;
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return EnumEffectCategory.WEAPON;
    }


    public void onPlayerAttack(EntityPlayer attacker, Entity target) {
        if (!attacker.world.isRemote && target instanceof EntityLivingBase) {
            if (random.nextInt(100) < chance) {
                EntityLivingBase livingTarget = ((EntityLivingBase) target);
                livingTarget.addPotionEffect(effect.get());
            }
        }
    }

}
