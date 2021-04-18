/*==============================================================================
 = Class: ElectrumToolEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class ElectrumToolEffect extends BaseMetallurgyEffect {

    public ElectrumToolEffect()
    {
        super(ModMetals.ELECTRUM);
        IItemPropertyGetter condition =
                (stack, worldIn, entityIn) -> stack.getTagCompound() != null && stack.getTagCompound().getBoolean("voltage_control") ? 1F : 0F;
        setupModelOverrides(condition);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory()
    {
        return EnumEffectCategory.TOOL;
    }

    /**
     * Handle State change whenever the tool is right clicked (and save it to NBT)
     * As well as increasing the harvest level of the item
     */
    @Override
    public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        ItemStack toolStack = playerIn.getHeldItem(handIn);
        NBTTagCompound toolData = toolStack.getTagCompound();

        if (toolData == null)
            toolData = new NBTTagCompound();

        boolean newState = !toolData.getBoolean("voltage_control");
        toolData.setBoolean("voltage_control", newState);

        //Pretty Hacky Check ahead
        String toolName = toolStack.getItem().getRegistryName().getPath();
        EnumTools[] toolClasses = getCategory().getTools();

        //Reverse loop over tool classes (pickaxe should be before axe [otherwise pickaxes are treated like axes])
        for (int i = toolClasses.length - 1; i >= 0; i--)
        {
            if (toolName.contains(toolClasses[i].getName()))
            {
                //switch between increased / regular harvest level
                toolData.setInteger("harvest_boost", newState ? 1 : 0);
            }
        }

        if (worldIn.isRemote)
            worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, newState ? 1 : 0.5F, false);

        toolStack.setTagCompound(toolData);
        super.rightClickHandler(worldIn, playerIn, handIn);
    }

    /**
     * Increases durability usage when the effect is active
     *
     * @param event When the block is about to be broken
     */
    @SubscribeEvent
    public void increaseHarvestLevel(BlockEvent.BreakEvent event)
    {
        if (!canBeApplied(event.getPlayer()))
            return;

        //Get the stack inside of the main hand of the player (stack used to harvest the block)
        ItemStack toolStack = event.getPlayer().getHeldItemMainhand();

        if (toolStack.getTagCompound() != null && toolStack.getTagCompound().getBoolean("voltage_control"))
        {
            //Increase Item damage of 9 units (the remaining 1 durability should be removed by the game)
            toolStack.setItemDamage(toolStack.getItemDamage() + 9);
        }
    }

    @SubscribeEvent
    public void increaseBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (!canBeApplied(event.getEntityLiving()))
            return;

        //Get the stack inside of the main hand of the player (stack used to harvest the block)
        ItemStack toolStack = event.getEntityLiving().getHeldItemMainhand();

        //Increase tool efficiency if voltage control is active
        if (toolStack.getTagCompound() != null && toolStack.getTagCompound().getBoolean("voltage_control"))
        {
            event.setNewSpeed(event.getOriginalSpeed() * 3);
        }
    }
}
