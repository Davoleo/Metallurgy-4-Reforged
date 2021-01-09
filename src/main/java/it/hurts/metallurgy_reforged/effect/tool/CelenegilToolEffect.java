/*==============================================================================
 = Class: CelenegilToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataProvider;
import it.hurts.metallurgy_reforged.capabilities.effect.ExtraFilledDataBundle;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.capabilities.effect.ProgressiveDataBundle;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.effect.IProgressiveEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class CelenegilToolEffect extends BaseMetallurgyEffect implements IProgressiveEffect {

    public CelenegilToolEffect()
    {
        super(ModMetals.CELENEGIL);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent event)
    {

        EntityPlayer player = event.getPlayer();
        PlayerEffectData data = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null);
        NBTTagCompound compound = data.celenegilToolBundle.getExtra();
        int brokenBlocks = compound.getInteger("broken_blocks");

        //If escalation is complete
        if (brokenBlocks >= 5)
        {
            System.out.println("Full power: " + brokenBlocks);
            System.out.println("Prev: " + compound.getInteger("prev_broken_blocks"));
            //Cancel Item Damage
            ItemStack tool = player.getHeldItemMainhand();
            tool.setItemDamage(tool.getItemDamage() - 1);
            //Reset the effect as active
            compound.setInteger("prev_broken_blocks", brokenBlocks);
        }
        else
        {
            //Increase broken blocks number if escalation is not complete yet
            System.out.println("Increasing...");
            compound.setInteger("prev_broken_blocks", brokenBlocks);
            compound.setInteger("broken_blocks", brokenBlocks + 1);
            data.celenegilToolBundle.setExtra(compound);
            // TODO: 09/01/2021 Play a sound
            if (event.getWorld().isRemote)
            {
                float pitch = (brokenBlocks / 5F) + 0.5F;
                event.getWorld().playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_PLING, SoundCategory.PLAYERS, 1, pitch, false);
            }
        }
    }

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed event)
    {
        EntityPlayer player = event.getEntityPlayer();
        NBTTagCompound data = player.getCapability(EffectDataProvider.PLAYER_EFFECT_DATA_CAPABILITY, null).celenegilToolBundle.getExtra();

        //Double the mining speed if escalation is complete
        if (data.getInteger("broken_blocks") >= 5)
            event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
    }

    @Override
    public void onStep(World world, EntityPlayer player, int maxSteps, int step, ProgressiveDataBundle bundle)
    {
        NBTTagCompound data = ((ExtraFilledDataBundle<NBTTagCompound>) bundle).getExtra();
        int brokenBlocks = data.getInteger("broken_blocks");
        int prevBrokenBlocks = data.getInteger("prev_broken_blocks");

        //on step 1 if the player in on max level, we set the prev variable to -1 so that the effect is flagged as inactive and will be
        //reset on step 3 if the variable is not set to 5 again
        if (step == 1 && prevBrokenBlocks == brokenBlocks)
        {
            System.out.println("inactive");
            data.setInteger("prev_broken_blocks", -1);
        }

        //if prevBrokenBlocks is still -1 after one second it means the effect has to be reset
        if (step == 3 && prevBrokenBlocks == -1)
        {
            System.out.println("Reset");
            data.setInteger("prev_broken_blocks", 0);
            data.setInteger("broken_blocks", 0);
            if (world.isRemote)
                world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.PLAYERS, 0.75F, 0.75F, false);
        }
    }
}
