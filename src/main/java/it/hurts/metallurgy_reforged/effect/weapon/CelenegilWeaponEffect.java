/*==============================================================================
 = Class: CelenegilWeaponEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CelenegilWeaponEffect extends BaseMetallurgyEffect {

    public CelenegilWeaponEffect()
    {
        super(ModMetals.CELENEGIL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.WEAPON;
    }

    @SubscribeEvent
    public void playerAttack(LivingDamageEvent event)
    {
        if (event.getSource().getTrueSource() instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase) event.getSource().getTrueSource();
            if (!canBeApplied(entity))
                return;

            if (entity instanceof EntityPlayer)
            {
                float cooldown = ((EntityPlayer) entity).getCooldownTracker().getCooldown(entity.getHeldItemMainhand().getItem(), 0);
                if (cooldown > 0)
                {
                    event.setCanceled(true);
                    return;
                }
            }

            if (entity.getHeldItemMainhand().getTagCompound() == null)
                entity.getHeldItemMainhand().setTagCompound(new NBTTagCompound());

            if (entity.getHeldItemMainhand().getTagCompound().getBoolean("glory_seeker"))
            {
                //Slight damage buff (ignores any kind of protection)
                event.setAmount(event.getAmount() * 1.25F);

                if (event.getEntityLiving().getHealth() - event.getAmount() <= 0)
                {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 160, 0));
                    entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 160, 0));
                }
                else
                {
                    if (entity instanceof EntityPlayer)
                        ((EntityPlayer) entity).getCooldownTracker().setCooldown(entity.getHeldItemMainhand().getItem(), 100);
                }
            }
        }
    }

    @Override
    public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack tool = playerIn.getHeldItem(handIn);
        NBTTagCompound toolData = tool.getTagCompound();

        if (toolData == null)
            toolData = new NBTTagCompound();

        boolean newState = !toolData.getBoolean("glory_seeker");
        toolData.setBoolean("glory_seeker", newState);

        if (worldIn.isRemote)
            worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, newState ? 1 : 0.5F, false);

        tool.setTagCompound(toolData);
    }

    public static void setupModelOverrides()
    {
        IItemPropertyGetter condition =
                (stack, worldIn, entityIn) -> stack.getTagCompound() != null && stack.getTagCompound().getBoolean("glory_seeker") ? 1F : 0F;
        ModMetals.CELENEGIL.getTool(EnumTools.AXE).addPropertyOverride(new ResourceLocation("active"), condition);
        ModMetals.CELENEGIL.getTool(EnumTools.SWORD).addPropertyOverride(new ResourceLocation("active"), condition);
    }
}
