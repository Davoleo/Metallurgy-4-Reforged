/*
 * -------------------------------------------------------------------------------------------------------
 * Class: GuiHandler
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int CRUSHER = 0;
    public static final int ALLOYER = 1;

    @Override
    public Container getServerGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case CRUSHER:
                //return new ContainerCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x,y,z)));
            case ALLOYER:
                //return new ContainerAlloyer(player.inventory, (TileEntityAlloyer)world.getTileEntity(new BlockPos(x,y,z)));
            default:
                return null;
        }
    }


    @Override
    public Object getClientGuiElement(int ID, PlayerEntity player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case CRUSHER:
                //return new GuiCrusher(player.inventory, (TileEntityCrusher)world.getTileEntity(new BlockPos(x,y,z)));
            case ALLOYER:
                //return new GuiAlloyer(player.inventory, (TileEntityAlloyer)world.getTileEntity(new BlockPos(x,y,z)));
            default:
                return null;
        }
    }
}
