/*
 * -------------------------------------------------------------------------------------------------------
 * Class: AtlarusHoeEffect
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nullable;

public class AtlarusHoeEffect extends BaseMetallurgyEffect {

    private static final int MAX_RANGE = 5;

    public AtlarusHoeEffect()
    {
        super(ModMetals.ATLARUS);
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public boolean isToolEffect()
    {
        return true;
    }

    @Nullable
    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.HOE;
    }

    @Override
    public void onPlayerTick(EntityPlayer player)
    {

        World world = player.world;
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() == metal.getTool(EnumTools.HOE) && world.getTotalWorldTime() % 10 == 0)
        {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey("range"))
            {
                int range = tag.getInteger("range");

                for (int x = -range; x <= range; x++)
                {
                    for (int y = -range; y < range; y++)
                    {
                        for (int z = -range; z <= range; z++)
                        {
                            if (x == range || z == range)
                            {
                                BlockPos pos = player.getPosition().add(x, y, z);
                                if (world.getBlockState(pos).getBlock() instanceof BlockBush)
                                {
                                    world.destroyBlock(pos, true);
                                }
                            }
                        }
                    }
                }
                if (range < MAX_RANGE)
                    tag.setInteger("range", range + 1);
                else
                    tag.removeTag("range");

            }
        }


    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event)
    {

        if (event instanceof PlayerInteractEvent.RightClickItem)
        {
            ItemStack stack = event.getItemStack();
            if (stack.getItem() == metal.getTool(EnumTools.HOE))
            {
                NBTTagCompound tag = stack.getTagCompound();
                if (tag == null)
                    tag = new NBTTagCompound();

                if (tag.hasKey("range"))
                    tag.setInteger("range", 0);


            }
        }


    }

    @Override
    public void livingEvent(LivingEvent event)
    {

        if (event instanceof LivingEquipmentChangeEvent)
        {
            LivingEquipmentChangeEvent changeEvent = (LivingEquipmentChangeEvent) event;
            ItemStack from = changeEvent.getFrom();
            if (from.getItem() == metal.getTool(EnumTools.HOE))
            {
                NBTTagCompound tag = from.getTagCompound();
                if (tag != null && tag.hasKey("range"))
                    tag.removeTag("range");
                from.setTagCompound(tag);

            }

        }

    }

}
