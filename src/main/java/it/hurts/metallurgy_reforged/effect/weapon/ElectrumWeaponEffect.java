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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
        ItemStack tool = playerIn.getHeldItem(handIn);
        NBTTagCompound toolData = tool.getTagCompound();

        if (toolData == null)
            toolData = new NBTTagCompound();

        toolData.setBoolean("magnet", true);

        if (worldIn.isRemote)
            worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SHULKER_OPEN, SoundCategory.PLAYERS, 1, 1, false);

        tool.setTagCompound(toolData);
    }

    @SubscribeEvent
    public void attractEntity(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();

        if (!canBeApplied(entity))
            return;

        final Predicate<Entity> TARGETS = Predicates.and(
                EntitySelectors.NOT_SPECTATING,
                EntitySelectors.IS_ALIVE,
                Entity::canBeCollidedWith,
                (e) -> e.getEntityId() != entity.getEntityId()  //Excludes the raytrace author
        );

        Vec3d longLook = entity.getLookVec().scale(8);
        List<EntityLivingBase> entitiesToTest = entity.world.getEntitiesWithinAABB(EntityLivingBase.class, entity.getEntityBoundingBox().expand(longLook.x, longLook.y, longLook.z), TARGETS);

        Optional<EntityLivingBase> optionalTarget = entitiesToTest.stream().filter(e -> {
            //Grow the bounding box of the entity to test (see EntityArrow::findEntityOnPath)
            AxisAlignedBB aabb = e.getEntityBoundingBox().grow(0.30000001192092896D);
            //Check if the AABB of the entity to test intersects with the longLookVec
            RayTraceResult result = aabb.calculateIntercept(entity.getPositionVector(), entity.getPositionVector().add(longLook));
            //Valid only if the type of hit is an entity
            return result != null && result.typeOfHit == RayTraceResult.Type.ENTITY;
        }).findAny();


        if (optionalTarget.isPresent())
        {
            //The main entity look vector (normalized)
            Vec3d lookVec = entity.getLookVec();
            EntityLivingBase target = optionalTarget.get();

            //Attract the entity to the player
            target.motionX = -lookVec.x * 0.5;
            target.motionY = -lookVec.y * 0.5;
            target.motionZ = -lookVec.z * 0.5;
            target.velocityChanged = true;

            //Will probably need to be subtract reverse
            Vec3d targetToPlayer = target.getPositionVector().subtract(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);

            //If the vector is 8 blocks long there will be 40 particles in total along the vector that links the player and the target
            final int density = (int) (targetToPlayer.length() * 5);
            for (int i = 0; i < density; i++)
            {
                //setup the 3 proportional units for each axis coordinate
                double unitX = targetToPlayer.x / density;
                double unitY = targetToPlayer.y / density;
                double unitZ = targetToPlayer.z / density;
                spawnParticle(entity.world, entity.posX + unitX, entity.posY + unitY, entity.posZ + unitZ, 1.5F, 3);
            }
        }
        else
        {
            ItemStack toolStack = entity.getHeldItemMainhand();

            //If the result is not an entity or is null we reset the effect and set it on cooldown
            NBTTagCompound compound = toolStack.getTagCompound();
            if (compound != null && compound.getBoolean("magnet"))
            {
                compound.setBoolean("magnet", false);

                if (entity instanceof EntityPlayer)
                {
                    ((EntityPlayer) entity).getCooldownTracker().setCooldown(toolStack.getItem(), 120);
                }
            }
        }
    }

}
