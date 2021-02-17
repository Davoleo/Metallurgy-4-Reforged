/*==============================================================================
 = Class: ElectrumWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class ElectrumWeaponEffect extends BaseMetallurgyEffect {

    public ElectrumWeaponEffect()
    {
        super(ModMetals.ELECTRUM);
        IItemPropertyGetter condition =
                (stack, worldIn, entityIn) -> stack.getTagCompound() != null && stack.getTagCompound().getBoolean("magnet") ? 1F : 0F;
        setupModelOverrides(condition);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @Override
    public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {

        ItemStack toolStack = playerIn.getHeldItem(handIn);

        if (worldIn.isRemote)
            worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SHULKER_OPEN, SoundCategory.PLAYERS, 1, 1, false);

        if (playerIn.getCooldownTracker().getCooldown(toolStack.getItem(), 1F) != 0)
            return;

        final Predicate<Entity> TARGETS = Predicates.and(
                EntitySelectors.NOT_SPECTATING,
                EntitySelectors.IS_ALIVE,
                Entity::canBeCollidedWith
        );

        Vec3d longLook = playerIn.getLookVec().scale(8);
        List<Entity> entitiesToTest = playerIn.world.getEntitiesInAABBexcluding(playerIn, playerIn.getEntityBoundingBox().expand(longLook.x, longLook.y, longLook.z), TARGETS);

        Optional<Entity> optionalTarget = entitiesToTest.stream().filter(e -> {
            //Grow the bounding box of the playerIn to test (see EntityArrow::findEntityOnPath)
            AxisAlignedBB aabb = e.getEntityBoundingBox().grow(0.30000001192092896D);
            //Check if the AABB of the playerIn to test intersects with the longLookVec
            RayTraceResult result = aabb.calculateIntercept(playerIn.getPositionEyes(1F), playerIn.getPositionEyes(1F).add(longLook));
            //Valid only if the type of hit is an playerIn

            return result != null;
        }).min((e1, e2) -> {
            //Get both distances from the player
            double d1 = playerIn.getPositionVector().distanceTo(e1.getPositionVector());
            double d2 = playerIn.getPositionVector().distanceTo(e2.getPositionVector());
            return Double.compare(d1, d2);
        });

        if (optionalTarget.isPresent())
        {
            //The main playerIn look vector (normalized)
            Vec3d lookVec = playerIn.getLookVec();
            Entity target = optionalTarget.get();

            //Will probably need to be subtract reverse
            Vec3d targetToPlayer = target.getPositionEyes(1F).subtract(playerIn.getPositionEyes(1F));

            //Attract the playerIn to the player
            target.motionX = -lookVec.x * targetToPlayer.length() * 0.25;
            target.motionY = -lookVec.y * targetToPlayer.length() * 0.25;
            target.motionZ = -lookVec.z * targetToPlayer.length() * 0.25;
            target.velocityChanged = true;

            //If the vector is 8 blocks long there will be 40 particles in total along the vector that links the player and the target
            final int density = (int) (targetToPlayer.length() * 5);
            for (int i = 0; i < density; i++)
            {
                //setup the 3 proportional units for each axis coordinate
                double unitX = targetToPlayer.x * i / (double) density;
                double unitY = targetToPlayer.y * i / (double) density;
                double unitZ = targetToPlayer.z * i / (double) density;
                spawnParticle(playerIn.world, playerIn.posX + unitX, playerIn.posY + playerIn.getEyeHeight() + unitY, playerIn.posZ + unitZ, 1.5F, 3);
            }

            playerIn.getCooldownTracker().setCooldown(toolStack.getItem(), 120);
        }
    }
}
